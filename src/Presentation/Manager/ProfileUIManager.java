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

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;


/**
 * The "ProfileUIManager" class will contain the different methods that are needed to control the view class "ProfileUI"
 */
public class ProfileUIManager implements ActionListener {
    /**
     * Parametrized constructor
     */
    public ProfileUIManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.LOGOUT_BUTTON:
                card.show(contenedor, PRE_MENU_UI);
                break;
            case Dictionary_login.DELETE_BUTTON:
                //TODO: DELETE EVERYTHING FROM THE USER IN OUR SYSTEM
                card.show(contenedor, PRE_MENU_UI);
                break;
            case Dictionary_login.BACK_BUTTON:
                //TODO: CHECK CORRECT INFORMATION, ENTER AS THE SAVED USER
                card.show(contenedor, TEMP_FREE_PIANO_UI);
                break;
        }
    }
}
