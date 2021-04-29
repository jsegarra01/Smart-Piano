package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Presentation.Dictionary_login;
import Presentation.Ui_Views.TempFreePianoUI;
import Presentation.Ui_Views.Tile;
import Business.Entities.MidiHelper;
import Business.Entities.Translator;

import javax.sound.midi.MidiUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import static Presentation.Dictionary_login.PROFILE_UI;
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
public class TempFreePianoUIManager implements ActionListener {

    public static String SOUND_TYPE = "SYNTH";
    public static int SOUND_SYNTHER = 0 ;
    private MidiHelper finalMidiHelper;
    MidiHelper midiHelper = null;
    private KeyListener KL;

    /**
     * Parametrized constructor
     */
    public TempFreePianoUIManager() {
        try {
            midiHelper = new MidiHelper();
        } catch (MidiUnavailableException exception) {
            exception.printStackTrace();
        }
        this.finalMidiHelper = midiHelper;
        this.KL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getCodeFromKey(e)), SOUND_SYNTHER);
            }
            @Override
            public void keyPressed(KeyEvent e) {
                finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getCodeFromKey(e)), SOUND_SYNTHER);
            }
            @Override
            public void keyReleased(KeyEvent e) {
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
            case TempFreePianoUI.BTN_RETURN:
                System.out.println("Well... we have already NOT implemented this button!");
                break;
            case TempFreePianoUI.BTN_RECORD:
                System.out.println("Well... we have already NOT implemented this button!");
                break;
            case TempFreePianoUI.BTN_SUSTAIN_SOUND:
                SOUND_TYPE = "PIANO";
                break;
            case TempFreePianoUI.BTN_SYNTH_SOUND:
                SOUND_TYPE = "SYNTH";
                break;
            case TempFreePianoUI.BTN_NEXT_SYNTHER:
                if(SOUND_SYNTHER <= 127){
                    SOUND_SYNTHER++;
                    TempFreePianoUI.setTypeName(finalMidiHelper.getInstrument());
                }else{
                    SOUND_SYNTHER = 0;
                }
                break;
            case TempFreePianoUI.BTN_PREV_SYNTHER:
                if(SOUND_SYNTHER >= 1){
                    SOUND_SYNTHER--;
                    TempFreePianoUI.setTypeName(finalMidiHelper.getInstrument());
                }else{
                    SOUND_SYNTHER = 127;
                }
                break;
            case TempFreePianoUI.BTN_TILE:
                Tile t = null;
                Object obj = e.getSource();
                if (obj instanceof Tile) {
                    t = (Tile) obj;
                }
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
}