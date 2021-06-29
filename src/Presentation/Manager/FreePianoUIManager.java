package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe

import Business.BusinessFacadeImp;
import Business.Entities.RecordingNotes;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.FreePianoUI;
import Presentation.Ui_Views.Tile;
import Business.MidiHelper;
import Business.Translator;


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
 * @version 2.0 23 May 2021
 *
 */
public class FreePianoUIManager implements ActionListener, MouseListener {
    private static final int SOUND_SYNTHER = 0 ;

    /*
    Defines the player of the music of the FreePiano
     */
    private final MidiHelper finalMidiHelper;
    private final KeyListener KL;
    private final ArrayList<RecordingNotes> recordingNotes;
    private final Timer timer;

    private boolean recording;
    private boolean modifying;
    private boolean selected;

    private float recordingTime;
    private String tileSelected;

    /**
     * Parametrized constructor, initializes the recorder and the different overwrites for when a key is pressed in the keyboard
     */
    public FreePianoUIManager() {
        
        //Initialitzations of the variables
        recordingNotes = new ArrayList<>();
        recording = false;
        recordingTime = 0;
        modifying = false;
        selected = false;

        timer = new Timer(10, this);
        timer.setActionCommand(RECORDING_TIMER);
        
        MidiHelper midiHelper = null;
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
            public void keyTyped(KeyEvent e) {}

            /**
             * When a key has been pressed. It can happen two different things: or the key is being modified, or we need
             * to output music. In addition, in the latter case we also need to check if we are recording it or not
             * @param e Key that has been pressed
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if(modifying){
                    if(selected){
                        // Will only return true if the key was already assigned to which the user s trying to give,
                        // if the user gives a new one, then it will just swap them and return false.
                        selected = BusinessFacadeImp.getBusinessFacade().modifyKey(Translator.setNewKey(tileSelected,e.getExtendedKeyCode()));
                        if(!selected){
                            FreePianoUI.modifyKey(Translator.getFromTile(tileSelected), e);
                            Translator.setKeys(Translator.setNewKey(tileSelected,e.getExtendedKeyCode()), e.getExtendedKeyCode());
                        }
                    }
                }else{
                    if(Translator.getPressedFromKey(e.getExtendedKeyCode()) !=null){
                        if(!Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).isPressed()){
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
            case RECORDING_TIMER ->                           //each time 10 ms have happened, recordingTime will increase
                    recordingTime += 0.01;
            case FreePianoUI.BTN_RECORD -> {                    //In the case that the Record button is pressed
                if (recording) {//If we were recording and we want to stop
                    timer.stop();
                    noteRecordingUpdate();
                    //BusinessFacadeImp.getBusinessFacade().noteRecordingUpdate(recordingNotes, recordingTime);
                } else {                                      //If we want to start recording
                    recordingTime = 0;
                    timer.restart();
                }
                recording = !recording;
            }
            case Dictionary_login.PROFILE_BUTTON ->           //In the case that the Profile button is pressed
                    card.show(contenedor, PROFILE_UI);
            case FreePianoUI.MODIFY -> {                        //In the case that the Modify button is pressed
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                modifying = abstractButton.getModel().isSelected();
                FreePianoUI.labelAppear(modifying);
                selected = false;
            }
        }
    }

    /**
     * Obtains the listener for the key event
     * @return KeyListener. returns the listener for the key event
     */
    public KeyListener getKeyListener(){
        return this.KL;
    }


    /**
     * Method that sets the key as pressed
     * @param string Defines the string that stores the tile which has been pressed
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
     * Method that sets back the tile to its color
     * @param string Defines the tile to change color
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

    private void noteRecordingUpdate(){
        JPanel myPanel = new JPanel();
        JTextField titleField = new JTextField(20);
        myPanel.add(titleField);
        JCheckBox box = new JCheckBox("is public?");
        myPanel.add(box);

        int option = JOptionPane.showInternalConfirmDialog(null, myPanel, "Enter a title for the song", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION){
            if(titleField.getText().isEmpty()){
                BusinessFacadeImp.getBusinessFacade().setError(18);
                noteRecordingUpdate();
            }else{
                BusinessFacadeImp.getBusinessFacade().recordedNotesSend(recordingNotes, titleField.getText(), box.isSelected(), recordingTime);
            }
        }

    }
}