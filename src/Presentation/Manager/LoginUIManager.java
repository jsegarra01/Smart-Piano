package Presentation.Manager;
/*
 * LoginUIManager
 *
 * The "LoginUIManager" class will contain the different methods that are needed to control the view class "LoginUI"
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
 * The "LoginUIManager" class will contain the different methods that are needed to control the view class "LoginUI"
 */
public class LoginUIManager implements ActionListener {
    // Attribute storing the view, for managing purposes. We will modify
    //  it after we get notified that a button has been pressed.

    // Parametrized constructor, receiving the view.
    public LoginUIManager() {
    }

    // Method that will be called every time a button is pressed, overriden
    //  from the interface to provide an implementation.
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.BACK_BUTTON:
                setPreMenuUi();
                break;
            case Dictionary_login.DONE_BUTTON:
                //TODO: CHECK CORRECT INFORMATION, ENTER AS THE SAVED USER
                setFreePianoUI();
                break;
        }
    }
}
