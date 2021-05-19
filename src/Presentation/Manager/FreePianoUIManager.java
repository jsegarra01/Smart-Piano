package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe

import Business.BusinessFacadeImp;
import Business.Entities.RecordingNotes;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.FreePianoUI;
import Presentation.Ui_Views.Tile;
import Business.Entities.MidiHelper;
import Business.Entities.Translator;


import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

import static Presentation.DictionaryPiano.RECORDING_TIMER;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;


/**
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 9 May 2021
 *
 */
public class FreePianoUIManager implements ActionListener, MouseListener {
    public static int SOUND_SYNTHER = 0 ;
    public ArrayList<RecordingNotes> recordingNotes = new ArrayList<>();

    private MidiHelper finalMidiHelper;
    private KeyListener KL;
    private boolean recording = false;
    private float recordingTime = 0;
    BusinessFacadeImp businessFacadeImp = new BusinessFacadeImp();

    MidiHelper midiHelper = null;
    Timer timer  = new Timer(10, this);
    private boolean modifying = false;
    private boolean selected = false;
    private String tileSelected;

    /**
     * Parametrized constructor, initializes the recorder and teh different overwrites for when a key is pressed in the keyboard
     */
    public FreePianoUIManager() {
        timer.setActionCommand(RECORDING_TIMER);
        try {
            midiHelper = new MidiHelper();
        } catch (MidiUnavailableException exception) {
            exception.printStackTrace();
        }
        this.finalMidiHelper = midiHelper;
        this.KL = new KeyListener() {

            /**
             * When a key has been typed by the user
             * @param e Key that has been pressed
             */
            @Override
            public void keyTyped(KeyEvent e) {
                }

            /**
             * When a key has been pressed. It can happen two different things: or the key is being modified, or we need
             * to output music. In addition, in the latter case we also need to check if we are recording it or not
             * @param e Key that has been pressed
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if(modifying){
                    if(selected){
                        int checkKeyExisted =Translator.setNewKey(tileSelected,e.getExtendedKeyCode());
                        if(checkKeyExisted == -1){
                            JOptionPane.showMessageDialog(contenedor,
                                    "This key is already assigned!", "Modify keys error" , JOptionPane.ERROR_MESSAGE);
                        }else{
                            FreePianoUI.modifyKey(Translator.getFromTile(tileSelected), e);
                            Translator.setKeys(checkKeyExisted, e.getExtendedKeyCode());
                            selected = false;
                        }
                    }
                }else{
                    if(Translator.getPressedFromKey(e.getExtendedKeyCode()) !=null){
                        if(!Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).isPressed()){
                            //finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getCodeFromKey(e)), SOUND_SYNTHER);
                            finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getFromKey(e.getExtendedKeyCode())),SOUND_SYNTHER);
                            Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).setPressed(true);
                            //This gets the initial timer and key pressed for the first time it is clicked
                            if (recording) {
                                recordingNotes.add(new RecordingNotes(Translator.getFromKey(e.getKeyCode()),recordingTime));
                            }
                        }
                        setIconKey(Objects.requireNonNull(Translator.getFromKey(e.getExtendedKeyCode())));
                    }
                }
            }

            /**
             * Happens when the key is released. Ends the sound the key was emitting, returns to the initial image and
             * finally stops the recording for that key if it was used before.
             * @param e Key that has been released
             */
            @Override
            public void keyReleased(KeyEvent e) {
                if (Translator.getPressedFromKey(e.getExtendedKeyCode()) != null) {
                    setIconBack(Objects.requireNonNull(Translator.getFromKey(e.getExtendedKeyCode())));
                    Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).setPressed(false);
                    finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(Translator.getFromKey(e.getExtendedKeyCode())),SOUND_SYNTHER);
                    if (recording) {
                        for (int i = 0; recordingNotes.size() != i; i++) {
                            if (recordingNotes.get(i).getKey().equals(Translator.getFromKey(e.getKeyCode())) && recordingNotes.get(i).getDuration() == 0) {
                                recordingNotes.get(i).setDuration(recordingTime - recordingNotes.get(i).getTime());
                            }
                        }
                    }
                }
            }
        };
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.

        switch (e.getActionCommand()) {
            case RECORDING_TIMER:                           //each time 10 ms have happened, recordingTime will increase
                    recordingTime += 0.01;
                break;
            case FreePianoUI.BTN_RECORD:                    //In the case that the Record button is pressed
                if (recording) {                            //If we were recording and we want to stop
                    recording = false;
                    timer.stop();

                    JPanel myPanel = new JPanel();
                    JTextField titleField = new JTextField( 20);
                    myPanel.add(titleField);
                    JCheckBox box = new JCheckBox("is public?");
                    myPanel.add(box);

                    JOptionPane.showMessageDialog(null, myPanel, "Enter a title for the song", JOptionPane.INFORMATION_MESSAGE);

                    businessFacadeImp.recordedNotesSend(recordingNotes, titleField.getText(), box.isSelected(), recordingTime);
                }
                else {                                      //If we want to start recording
                    recordingTime = 0;
                    timer.restart();
                    recording = true;
                }
                break;
            case Dictionary_login.PROFILE_BUTTON:           //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
                break;
            case FreePianoUI.MODIFY:                        //In the case that the Modify button is pressed
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                modifying = abstractButton.getModel().isSelected();
                FreePianoUI.labelAppear(modifying);
                selected = false;
                break;
        }
    }

    /**
     * Obtains the listener for the key event
     * @return KeyListener. returns the listener for the key event
     */
    public KeyListener getKeyListener(){
        return this.KL;
    }


    //TODO I DON'T UNDERSTAND THESE METHODS, ALEX EXPLICA QUE CONY SON :p (LA SEGUENT TMB PORFAPLIS)
    /**
     *
     * @param string
     */
    private void setIconKey(String string){
        int i = 0;
        while(!string.equals(FreePianoUI.getKeyboard().get(i).getName()) &&
                i<FreePianoUI.getKeyboard().size()){
            i++;
        }
        if(i!=FreePianoUI.getKeyboard().size()){
            FreePianoUI.getKeyboard().get(i).setIcon();
        }
    }

    /**
     *
     * @param string
     */
    private void setIconBack(String string){
        int i = 0;
        while(!string.equals(FreePianoUI.getKeyboard().get(i).getName()) && i<FreePianoUI.getKeyboard().size()){
            i++;
        }
        if(i!=FreePianoUI.getKeyboard().size()){
            if(FreePianoUI.getKeyboard().get(i).getColor()== Color.WHITE){
                FreePianoUI.getKeyboard().get(i).backToWhite();
            }else{
                FreePianoUI.getKeyboard().get(i).backToBlack();
            }
        }
    }

    /**
     * Event that happens when the mouse has clicked something
     * @param e Event of the mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Event that happens when the mouse has pressed something. It might modify the key or make it sound depending
     * on which action we are. Moreover, it can also start the recording of the sound in case we are recording.
     * @param e Event of the mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Tile t = null;
        Object obj = e.getSource();
        if (obj instanceof Tile) {
            t = (Tile) obj;
        }
        System.out.println(Translator.getNumberNoteFromName(e.getComponent().getName()));
        if(modifying){
            if(!selected){
                FreePianoUI.setTileColor(t);
                tileSelected = Objects.requireNonNull(t).getName();
                selected = true;
            }

        }else{
            if (recording) {
                recordingNotes.add(new RecordingNotes(e.getComponent().getName(),recordingTime));
            }
            finalMidiHelper.playSomething(Translator.getNumberNoteFromName(e.getComponent().getName()), SOUND_SYNTHER);
        }
    }

    /** Event that happens when the mouse has release something. It stops to make it sound and
     * if it is recording, stops the recording
     * @param e Event of the mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(e.getComponent().getName()),SOUND_SYNTHER);
        if (recording) {
            for (int i = 0; recordingNotes.size() != i; i++) {
                if (recordingNotes.get(i).getKey().equals(e.getComponent().getName())  && recordingNotes.get(i).getDuration() == 0) {
                    recordingNotes.get(i).setDuration(recordingTime - recordingNotes.get(i).getTime());
                    System.out.println(recordingNotes.get(i).getDuration());
                }
            }
        }
    }

    /**
     * Event that happens when the mouse has entered something
     * @param e Event of the mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Event that happens when the mouse has exited something
     * @param e Event of the mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}