package Presentation.Manager;
/*
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * Stepan Batllori, Alex Blay, Laura Nuez, Josep Segarra and Sergi Vives
 *
 * Version 21/04/2021
 */

//Imports needed from the dictionary, events and mainframe
import Presentation.Dictionary_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Manager.MainFrame.setProfileUI;

/**
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 */
public class FreePianoUIManager implements ActionListener {
    /**
     * Parametrized constructor
     */
    public FreePianoUIManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.PROFILE_BUTTON:
                setProfileUI();
                break;
        }
    }
}