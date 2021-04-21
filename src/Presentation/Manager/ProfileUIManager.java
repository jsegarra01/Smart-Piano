package Presentation.Manager;
/*
 * ProfileUIManager
 *
 * The "ProfileUIManager" class will contain the different methods that are needed to control the view class "ProfileUI"
 *
 * Stepan Batllori, Alex Blay, Laura Nuez, Josep Segarra and Sergi Vives
 *
 * Version 21/04/2021
 */

//Imports needed from the dictionary, events and mainframe
import Presentation.Dictionary_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Manager.MainFrame.setFreePianoUI;
import static Presentation.Manager.MainFrame.setPreMenuUi;

/**
 * The "ProfileUIManager" class will contain the different methods that are needed to control the view class "ProfileUI"
 */
public class ProfileUIManager implements ActionListener {
    // Attribute storing the view, for managing purposes. We will modify
    //  it after we get notified that a button has been pressed.

    // Parametrized constructor, receiving the view.
    public ProfileUIManager() {
    }

    // Method that will be called every time a button is pressed, overriden
    //  from the interface to provide an implementation.
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.LOGOUT_BUTTON:
                setPreMenuUi();
                break;
            case Dictionary_login.DELETE_BUTTON:
                //TODO: DELETE EVERYTHING FROM THE USER IN OUR SYSTEM
                setPreMenuUi();
                break;
            case Dictionary_login.BACK_BUTTON:
                //TODO: CHECK CORRECT INFORMATION, ENTER AS THE SAVED USER
                setFreePianoUI();
                break;
        }
    }
}
