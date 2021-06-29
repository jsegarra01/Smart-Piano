package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Business.ChangeTime;
import Business.MidiHelper;
import Presentation.Dictionary_login;

import javax.sound.midi.MidiUnavailableException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.*;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.PianoFrame.*;
import static Presentation.Ui_Views.SpotiUI.spotiPanel;


/**
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 23 May 2021
 *
 */
public class PianoFrameManager implements ActionListener {
    private final MidiHelper midiHelper;
    private final PianoTilesUISelectorManager pianoTilesUISelectorManager;

    /**
     * Parametrized constructor
     */
    public PianoFrameManager() {
        MidiHelper midiHelper1;
        try {
            midiHelper1 = new MidiHelper();
        } catch (MidiUnavailableException e) {
            new BusinessFacadeImp().setError(8);
            midiHelper1 = null;
        }
        this.midiHelper = midiHelper1;

        pianoTilesUISelectorManager = new PianoTilesUISelectorManager(midiHelper);
        new ChangeTime(pianoTilesUISelectorManager);
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        CardLayout cc = (CardLayout) (centralPanel.getLayout());
        CardLayout cc2 = (CardLayout) (spotiPanel.getLayout());

        freePiano.setBackground(Color.GRAY);
        playSong.setBackground(Color.GRAY);
        musicPlayer.setBackground(Color.GRAY);
        new ChangeTime(0);
        midiHelper.stopSong();

        switch (e.getActionCommand()) {
            case Dictionary_login.PROFILE_BUTTON:       //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
            case FREE_PIANO:
                cc.show(centralPanel, FREE_PIANO_UI);
                freePiano.setBackground(Color.getHSBColor(0,0,80.3f));
                break;
            case PLAY_A_SONG:
                try {
                   pianoTilesUISelectorManager.refreshPianoTilesUI();
                } catch (NullPointerException h) {
                    playSong.setBackground(Color.getHSBColor(0,0,80.3f));
                }
                cc.show(centralPanel, PIANO_TILES_UI_SELECTOR);
                playSong.setBackground(Color.getHSBColor(0,0,80.3f));
                break;
            case MUSIC_PLAYER:
                SpotiFrameManager.resetSongs();
                cc.show(centralPanel, SPOTI_UI);
                cc2.show(spotiPanel, SONGS_UI);
                musicPlayer.setBackground(Color.getHSBColor(0,0,80.3f));
                break;
        }
    }

}