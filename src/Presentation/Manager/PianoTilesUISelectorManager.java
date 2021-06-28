package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Business.ChangeTime;
import Business.Entities.Song;
import Presentation.DictionaryPiano;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.PianoTilesUISelector;
import Presentation.Ui_Views.Tile;
import Business.MidiHelper;
import Business.Translator;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

import static Presentation.DictionaryPiano.BTN_TILE;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.PianoTilesUISelector.*;


/**
 * PianoTilesUISelectorManager
 *
 * The "PianoTilesUISelectorManager" class will contain the different methods that are needed to control the view class "PianoTilesUISelector"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class PianoTilesUISelectorManager implements ActionListener, MouseListener, ListSelectionListener {
    private final int SOUND_SYNTH = 0;
    private int timePassed = 0;
    private boolean play = true;
    private boolean songStarted = false;
    private int songIndex = 0;
    private Song song;
    private static float velocityModifier = 1;

    //private final BusinessFacadeImp myFacade;

    private final ImageIcon playIcon = new ImageIcon("Files/drawable/play-button.png"); // icon play
    private final ImageIcon pauseIcon = new ImageIcon("Files/drawable/pause-button.png"); // icon pause

    /*
    Defines where tiles will be played
     */
    private MidiHelper finalMidiHelper;
    private final KeyListener KL;
    private final PianoTilesUISelector pianoTilesUI;


    public PianoTilesUISelectorManager(PianoTilesUISelector pianoTilesUISelector) {
        this.pianoTilesUI = pianoTilesUISelector;
        try {
            finalMidiHelper = new MidiHelper();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
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
                if (Translator.getPressedFromKey(e.getExtendedKeyCode()) !=null) {
                    if (Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).isPressed()) {
                        finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Translator.getFromKey(e.getExtendedKeyCode())), SOUND_SYNTH);
                        Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).setPressed(true);
                    }
                    setIconKey(Objects.requireNonNull(Translator.getFromKey(e.getExtendedKeyCode())));
                }
            }
            /**
             * When a key has been pressed it will stop the music.
             * @param e Key that has been pressed
             */
            @Override
            public void keyReleased(KeyEvent e) {
                if (Translator.getPressedFromKey(e.getExtendedKeyCode()) != null) {
                    setIconBack(Objects.requireNonNull(Translator.getFromKey(e.getExtendedKeyCode())));
                    Objects.requireNonNull(Translator.getPressedFromKey(e.getExtendedKeyCode())).setPressed(false);
                    finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(Translator.getFromKey(e.getExtendedKeyCode())), SOUND_SYNTH);
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
            case BTN_TILE:
                Tile t = null;
                Object obj = e.getSource();
                if (obj instanceof Tile) {
                    t = (Tile) obj;
                }
                finalMidiHelper.playSomething(Translator.getNumberNoteFromName(Objects.requireNonNull(t).getName()), SOUND_SYNTH);

                break;
            case Dictionary_login.PROFILE_BUTTON:       //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
                break;
            case DictionaryPiano.PLAY_BUTTON:
                if (songStarted) {
                    if(play){
                        playButtonTiles.setIcon(pauseIcon);
                        new ChangeTime(0);
                        finalMidiHelper.stopSong();
                    }
                    else{
                        playButtonTiles.setIcon(playIcon);
                        new ChangeTime(1);
                        finalMidiHelper.playSong(song.getSongFile());
                    }
                    play = !play;
                }
                break;
            case DictionaryPiano.VERY_EASY_MODE:
                if (!songStarted) { velocityModifier = 0.5f;}
                break;
            case DictionaryPiano.MUTE_BUTTON:
                finalMidiHelper.muteSong();
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


    /**
     * Method that sets the tile to be pressed
     * @param string Defines the tile that has been pressed
     */
    private void setIconKey(String string){
        IconKey(string, pianoTilesUI.getKeyboard());
    }

    /**
     * Static method to not duplicate the same code as in FreePianoUIManager
     * @param string Icon name
     * @param keyboard Tile of the keyboard pressed
     */
    protected static void IconKey(String string, ArrayList<Tile> keyboard) {  //TODO Check if we can put this in an abstract class from which we can extend it
        int i = 0;
        while(!string.equals(keyboard.get(i).getName()) && i< keyboard.size()){
            i++;
        }
        if(i!= keyboard.size()){
            keyboard.get(i).setIcon();
        }
    }

    /**
     * Sets the icon of a key back to its original one
     * @param string The name of the key that must change its the icon
     */
    private void setIconBack(String string){
        setIconBackTiles(string, pianoTilesUI.getKeyboard());
    }

    /**
     * Static method to not duplicate the same code as in FreePianoUIManager
     * @param string Icon name
     * @param keyboard Tile of the keyboard pressed
     */
    protected static void setIconBackTiles(String string, ArrayList<Tile> keyboard) {   //TODO Check if we can put this in an abstract class from which we can extend it
        int i = 0;
        while(!string.equals(keyboard.get(i).getName()) && i< keyboard.size()){
            i++;
        }
        if(i!= keyboard.size()){
            if(keyboard.get(i).getColor()== Color.WHITE){
                keyboard.get(i).backToWhite();
            }else{
                keyboard.get(i).backToBlack();
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
        finalMidiHelper.playSomething(Translator.getNumberNoteFromName(e.getComponent().getName()), SOUND_SYNTH);

    }

    /** Event that happens when the mouse has release something. Stops playing the music
     * @param e Event of the mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        finalMidiHelper.stopPlaying(Translator.getNumberNoteFromName(e.getComponent().getName()), SOUND_SYNTH);
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
        return BusinessFacadeImp.getBusinessFacade().getSongName();
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

            BusinessFacadeImp.getBusinessFacade().setTileArray(songIndex);                //Sets the tiles to play
            song = BusinessFacadeImp.getBusinessFacade().getSong(songIndex);
            finalMidiHelper.restartSong(song.getSongFile());
            finalMidiHelper.playSong(song.getSongFile());

             //Sets the tiles to play
            setKeys(BusinessFacadeImp.getBusinessFacade().getTiles());
                                                                            //Gets the tiles to play.
                                                                            //Is this necessary or it
                                                                            //can be in presentation?

            new ChangeTime(2);
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
        BusinessFacadeImp.getBusinessFacade().resetTilesKeys();
        initTileGame();
        pianoTilesUI.refreshTiles(timePassed, velocityModifier);
        pianoTilesUI.refreshSongList(getBusinessSongNames());
    }

    /**
     * Tells the system that some time has passed and the keys must be refreshed
     */
    public void addTime() {                               //When 1000 milliseconds have passed
        if (play && songStarted) {
           timePassed++;
           pianoTilesUI.refreshTiles(timePassed,velocityModifier);
        }
    }
}