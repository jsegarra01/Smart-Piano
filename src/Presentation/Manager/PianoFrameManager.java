package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Presentation.Dictionary_login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.*;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.PianoFrame.*;


/**
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class PianoFrameManager implements ActionListener {


    /**
     * Parametrized constructor
     */
    public PianoFrameManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        CardLayout cc = (CardLayout) (centralPanel.getLayout());

        freePiano.setBackground(Color.GRAY);
        playSong.setBackground(Color.GRAY);
        musicPlayer.setBackground(Color.GRAY);
        switch (e.getActionCommand()) {
            case Dictionary_login.PROFILE_BUTTON:       //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
            case FREE_PIANO:
                cc.show(centralPanel, FREE_PIANO_UI);
                freePiano.setBackground(Color.getHSBColor(0,0,80.3f));
                break;
            case PLAY_A_SONG:
                cc.show(centralPanel, PIANO_TILES_UI_SELECTOR);
                playSong.setBackground(Color.getHSBColor(0,0,80.3f));
                break;
            case MUSIC_PLAYER:
                cc.show(centralPanel, SPOTI_UI);
                musicPlayer.setBackground(Color.getHSBColor(0,0,80.3f));

                break;
        }
    }

}