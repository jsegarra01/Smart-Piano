package Presentation.Manager;
/*
 * PreMenuUIManager
 *
 * The "PreMenuUIManager" class will contain the different methods that are needed to control the view class "PreMenuUI"
 *
 * Stepan Batllori, Alex Blay, Laura Nuez, Josep Segarra and Sergi Vives
 *
 * Version 21/04/2021
 */

//Imports needed from the dictionary, events and mainframe
import Presentation.Dictionary_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Manager.MainFrame.*;

/**
 * The "PreMenuUIManager" class will contain the different methods that are needed to control the view class "PreMenuUI"
 */
public class PreMenuUIManager implements ActionListener {
    /**
     * Parametrized constructor
     */
    public PreMenuUIManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.LOG_IN_BUTTON:
                setLoginUi();
                break;
            case Dictionary_login.SIGN_UP_BUTTON:
                setSignUpUi();
                break;
            case Dictionary_login.ENTER_AS_GUEST_BUTTON:
                //TODO: ENTER AS A GUEST USER
                setFreePianoUI();
                break;
        }
    }
}