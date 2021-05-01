package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.PIANO_TILES_UI_GAME;
import static Presentation.DictionaryPiano.PIANO_TILES_UI_SELECTOR;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.TempFreePianoUI.centralPanel;


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
    /**
     * Parametrized constructor
     */
    public TempFreePianoUIManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case PROFILE_BUTTON:       //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
                break;
            case LOG_IN_BUTTON:
                CardLayout cc = (CardLayout)(centralPanel.getLayout());
                cc.show(centralPanel, PIANO_TILES_UI_GAME);
                break;
            case SIGN_UP_BUTTON:
                CardLayout c2c = (CardLayout)(centralPanel.getLayout());
                c2c.show(centralPanel, PIANO_TILES_UI_SELECTOR);
                break;
        }
    }
}