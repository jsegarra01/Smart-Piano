package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Business.ChangeTime;
import Business.MidiHelper;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.PianoFrame;

import javax.sound.midi.MidiUnavailableException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.*;
import static Presentation.Dictionary_login.*;
import static Presentation.Ui_Views.MainFrame.card;
import static Presentation.Ui_Views.MainFrame.contenedor;


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
    private final PianoFrame pianoFrame;

    /**
     * Parametrized constructor
     * @param pianoFrame view of the pianoFrame
     */
    public PianoFrameManager(PianoFrame pianoFrame) {
        MidiHelper midiHelper1;
        try {
            midiHelper1 = new MidiHelper();
        } catch (MidiUnavailableException e) {
            new BusinessFacadeImp().setError(8);
            midiHelper1 = null;
        }
        this.midiHelper = midiHelper1;
        this.pianoFrame = pianoFrame;
        this.pianoTilesUISelectorManager = pianoFrame.getPianoTilesUIManager();
        new ChangeTime(this.pianoTilesUISelectorManager);
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        CardLayout cc = (CardLayout) (pianoFrame.getCentralPanel().getLayout());
        CardLayout cc2 = (CardLayout) (pianoFrame.getSpotiPanel().getLayout());

        pianoFrame.setBackgroundFreePiano(Color.GRAY);
        pianoFrame.setBackgroundPlaySong(Color.GRAY);
        pianoFrame.setBackgroundMusicPlayer(Color.GRAY);

        new ChangeTime(0);
        //midiHelper.stopSong();

        switch (e.getActionCommand()) {
            case Dictionary_login.PROFILE_BUTTON:       //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
            case FREE_PIANO:
                cc.show(pianoFrame.getCentralPanel(), FREE_PIANO_UI);
                pianoFrame.setBackgroundFreePiano(Color.getHSBColor(0,0,80.3f));
                break;
            case PLAY_A_SONG:
                try {
                   pianoTilesUISelectorManager.refreshPianoTilesUI();
                } catch (NullPointerException h) {
                    pianoFrame.setBackgroundPlaySong(Color.getHSBColor(0,0,80.3f));
                }
                cc.show(pianoFrame.getCentralPanel(), PIANO_TILES_UI_SELECTOR);
                pianoFrame.setBackgroundPlaySong(Color.getHSBColor(0,0,80.3f));
                break;
            case MUSIC_PLAYER:
                pianoFrame.getSpotiFrameManager().addPlaylists(BusinessFacadeImp.getBusinessFacade().getPlaylists());
                pianoFrame.getSpotiFrameManager().resetSongs();
                cc.show(pianoFrame.getCentralPanel(), SPOTI_UI);
                cc2.show(pianoFrame.getSpotiPanel(), SONGS_UI);
                pianoFrame.setBackgroundMusicPlayer(Color.getHSBColor(0,0,80.3f));
                break;
        }
    }

}