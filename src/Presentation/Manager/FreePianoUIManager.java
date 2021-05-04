package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.Entities.RecordingNotes;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.FreePianoUI;
import Presentation.Ui_Views.PianoTilesUISelector;
import Presentation.Ui_Views.Tile;
import Business.Entities.MidiHelper;
import Business.Entities.Translator;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;


/**
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class FreePianoUIManager implements ActionListener {
    public static String SOUND_TYPE = "SYNTH";
    public static int SOUND_SYNTHER = 0 ;
    public ArrayList<RecordingNotes> recordingNotes;
    private MidiHelper finalMidiHelper;
    private KeyListener KL;
    private boolean recording = false;
    private Translator translator = new Translator();
    MidiHelper midiHelper = null;
    Timer timer  = new Timer(1000, this);

    /**
     * Parametrized constructor
     */
    public FreePianoUIManager() {
        timer.start();
        try {
            midiHelper = new MidiHelper();
        } catch (MidiUnavailableException exception) {
            exception.printStackTrace();
        }
        this.finalMidiHelper = midiHelper;
        this.KL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                }
            @Override
            public void keyPressed(KeyEvent e) {
                if(translator.getPressedFromKey(e.getExtendedKeyCode()) !=null){
                    if(!translator.getPressedFromKey(e.getExtendedKeyCode()).isPressed()){
                        //finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getCodeFromKey(e)), SOUND_SYNTHER);
                        finalMidiHelper.playSomething(Translator.getNumberNoteFromName(translator.getFromKey(e.getExtendedKeyCode())),SOUND_SYNTHER);
                        translator.getPressedFromKey(e.getExtendedKeyCode()).setPressed(true);

                        //This gets the initial timer and key pressed for the first time it is clicked
                        if (recording) {
                            recordingNotes.add(new RecordingNotes(e.getKeyChar(),System.nanoTime()));
                        }
                    }
                    setIconKey(Translator.getCodeFromKey(e));
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (translator.getPressedFromKey(e.getExtendedKeyCode()) != null) {
                    setIconBack(Translator.getCodeFromKey(e));
                    translator.getPressedFromKey(e.getExtendedKeyCode()).setPressed(false);
                    finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(translator.getFromKey(e.getExtendedKeyCode())),SOUND_SYNTHER);
                    if (recording) {

                        recordingNotes.add(new RecordingNotes(e.getKeyChar(),System.nanoTime()));
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
            case FreePianoUI.BTN_RETURN:
                System.out.println("Well... we have already NOT implemented this button!");
                break;
            case FreePianoUI.BTN_RECORD:
                if (recording) {
                    recording = false;
                    timer.stop();
                }
                else {
                    timer.restart();
                    recording = true;
                }
                break;
            case FreePianoUI.BTN_TILE:
                Tile t = null;
                Object obj = e.getSource();
                if (obj instanceof Tile) {
                    t = (Tile) obj;
                }
                /*try {
                    t.setIcon();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }*/
                finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Objects.requireNonNull(t).getName()),SOUND_SYNTHER);
                break;
            case Dictionary_login.PROFILE_BUTTON:       //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
                break;
        }
    }
    public KeyListener getKeyListener(){
        return this.KL;
    }


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
}