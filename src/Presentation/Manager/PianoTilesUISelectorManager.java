package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Business.Entities.ChangeTime;
import Presentation.DictionaryPiano;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.PianoTilesUISelector;
import Presentation.Ui_Views.Tile;
import Business.Entities.MidiHelper;
import Business.Entities.Translator;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

import static Presentation.DictionaryPiano.RECORDING_TIMER;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.PianoTilesUISelector.*;
import static Presentation.Ui_Views.Tile.resizeIcon;


/**
 * PianoTilesUISelectorManager
 *
 * The "PianoTilesUISelectorManager" class will contain the different methods that are needed to control the view class "PianoTilesUISelector"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 8 May 2021
 *
 */
public class PianoTilesUISelectorManager implements ActionListener, MouseListener, ListSelectionListener {
    public static int SOUND_SYNTHER = 0 ;
    public static int timePassed = 0;
    public static float velocityModifier = 1;
    private static boolean play = true;
    private static boolean songStarted = false;
    private static int songIndex = 0;

    private final ImageIcon playIcon = new ImageIcon("Files/drawable/play-button.png");
    private final ImageIcon pauseIcon = new ImageIcon("Files/drawable/pause-button.png");
    private MidiHelper finalMidiHelper;
    private KeyListener KL;
    private Translator translator = new Translator();

    MidiHelper midiHelper = null;

    /**
     * Parametrized constructor, initializes the recorder and teh different overwrites for when a key is pressed in the keyboard
     */
    public PianoTilesUISelectorManager() {
        //To play the song
        //timer.setActionCommand(RECORDING_TIMER);
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
             * When a key has been pressed it will output the music.
             * @param e Key that has been pressed
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if(translator.getPressedFromKey(e.getExtendedKeyCode()) !=null){
                    if(!translator.getPressedFromKey(e.getExtendedKeyCode()).isPressed()){
                        //finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getCodeFromKey(e)), SOUND_SYNTHER);
                        finalMidiHelper.playSomething(Translator.getNumberNoteFromName(translator.getFromKey(e.getExtendedKeyCode())),SOUND_SYNTHER);
                        translator.getPressedFromKey(e.getExtendedKeyCode()).setPressed(true);
                    }
                    setIconKey(translator.getFromKey(e.getExtendedKeyCode()));
                }
            }
            /**
             * When a key has been pressed it will stop the music.
             * @param e Key that has been pressed
             */
            @Override
            public void keyReleased(KeyEvent e) {
                if (translator.getPressedFromKey(e.getExtendedKeyCode()) != null) {
                    setIconBack(translator.getFromKey(e.getExtendedKeyCode()));
                    translator.getPressedFromKey(e.getExtendedKeyCode()).setPressed(false);
                    finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(translator.getFromKey(e.getExtendedKeyCode())),SOUND_SYNTHER);
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
            case PianoTilesUISelector.BTN_TILE:
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
            case DictionaryPiano.PLAY_BUTTON:
                if (songStarted) {
                    if(play){
                        playButtonTiles.setIcon(pauseIcon);
                        playButtonTiles.setIcon(resizeIcon((ImageIcon) playButtonTiles.getIcon(), (int) Math.round(playButtonTiles.getIcon().getIconWidth()*0.0507),
                                (int) Math.round(playButtonTiles.getIcon().getIconHeight()*0.0507)));
                        new ChangeTime(0);
                    }
                    else{
                        playButtonTiles.setIcon(playIcon);
                        playButtonTiles.setIcon(resizeIcon((ImageIcon) playButtonTiles.getIcon(), (int) Math.round(playButtonTiles.getIcon().getIconWidth()*0.15),
                                (int) Math.round(playButtonTiles.getIcon().getIconHeight()*0.15)));
                        new ChangeTime(1);
                    }
                    play = !play;
                }
                break;
            case DictionaryPiano.VERY_EASY_MODE:
                if (!songStarted) { velocityModifier = 0.5f;}
                break;
            case DictionaryPiano.EASY_MODE:
                if (!songStarted) { velocityModifier = 0.75f;}
                break;
            case DictionaryPiano.NORMAL_MODE:
                if (!songStarted) { velocityModifier = 1;}
                break;
            case DictionaryPiano.HARD_MODE:
                if (!songStarted) { velocityModifier = 1.5f;}
                break;
            case DictionaryPiano.VERY_HARD_MODE:
                if (!songStarted) { velocityModifier = 2.0f;}
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
        while(!string.equals(PianoTilesUISelector.getKeyboard().get(i).getName()) &&
                i<PianoTilesUISelector.getKeyboard().size()){
            i++;
        }
        if(i!=PianoTilesUISelector.getKeyboard().size()){
            PianoTilesUISelector.getKeyboard().get(i).setIcon();
        }
    }

    /**
     *
     * @param string
     */
    private void setIconBack(String string){
        int i = 0;
        while(!string.equals(PianoTilesUISelector.getKeyboard().get(i).getName()) && i<PianoTilesUISelector.getKeyboard().size()){
            i++;
        }
        if(i!=PianoTilesUISelector.getKeyboard().size()){
            if(PianoTilesUISelector.getKeyboard().get(i).getColor()== Color.WHITE){
                PianoTilesUISelector.getKeyboard().get(i).backToWhite();
            }else{
                PianoTilesUISelector.getKeyboard().get(i).backToBlack();
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
     * Event that happens when the mouse has pressed something. Needs to start playing music
     * @param e Event of the mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        finalMidiHelper.playSomething(Translator.getNumberNoteFromName(e.getComponent().getName()), SOUND_SYNTHER);

    }

    /** Event that happens when the mouse has release something. Stops playing the music
     * @param e Event of the mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(e.getComponent().getName()),SOUND_SYNTHER);
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

    /**
     * Gets the names of the songs
     * @return A list with all the song names
     */
    public ArrayList<String> getBusinessSongNames() {
        return new BusinessFacadeImp().getSongName();
    }

    /**
     * Gets the correct song when clicking or scrolling the list of songs
     * @param e The event made by the user when using the scroll
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && !songStarted) {
            if (e.getFirstIndex() <= e.getLastIndex() && songIndex == e.getLastIndex()) {
                songIndex = e.getFirstIndex();
            }
            else {
                songIndex = e.getLastIndex();
            }
            songStarted = true;

            new BusinessFacadeImp().setTileArray(songIndex);                //Sets the tiles to play
            setKeys(new BusinessFacadeImp().getTiles());                    //Gets the tiles to play.
                                                                            //Is this necessary or it
                                                                            //can be in presentation?

            new ChangeTime(2);
            System.out.println("sasa");
        }
    }

    /**
     * Refreshes the Piano Tiles UI in order to make the keys fall correctly
     */
    public void refreshPianoTilesUI () {
        timePassed = 0;
        songStarted = false;
        play = true;
        velocityModifier = 1;
        songIndex = 0;
        new ChangeTime(0);
        new BusinessFacadeImp().resetTilesKeys();
        initTileGame();
        refreshTiles();
        refreshSongList();
    }

    /**
     * Tells the system that some time has passed and the keys must be refreshed
     */
    public static void addTime() {                                                        //When 1000 milliseconds have passed
        if (play && songStarted) {
            timePassed++;
            refreshTiles();
        }
    }
}