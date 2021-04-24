package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;


/**
 * ProfileUIManager
 *
 * The "ProfileUIManager" class will contain the different methods that are needed to control the view class "ProfileUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 21 Apr 2021
 *
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
            case Dictionary_login.LOGOUT_BUTTON:                                //In the case that the Logout button is pressed
                //TODO: check if we need to do something else
                card.show(contenedor, PRE_MENU_UI);
                break;
            case Dictionary_login.DELETE_BUTTON:                                //In the case that the Delete button is pressed
                new BusinessFacadeImp().deleteAccount(LoginUI.getUsernameLogin());
                card.show(contenedor, PRE_MENU_UI);
                break;
            case Dictionary_login.BACK_BUTTON:                                  //In the case that the Back button is pressed
                card.show(contenedor, TEMP_FREE_PIANO_UI);
                break;
        }
    }
}
