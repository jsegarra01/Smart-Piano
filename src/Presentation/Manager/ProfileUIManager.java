package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe

import Business.BusinessFacadeImp;

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
 * @version 2.0 23 May 2021
 *
 */
public class ProfileUIManager implements ActionListener {
    BusinessFacadeImp myFacade;
    /**
     * Parametrized constructor
     */
    public ProfileUIManager(BusinessFacadeImp myFacade) {
        this.myFacade = myFacade;
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case LOGOUT_BUTTON -> card.show(contenedor, PRE_MENU_UI);//In the case that the Logout button is pressed
            case DELETE_BUTTON -> {
                myFacade.deleteAccount();
                card.show(contenedor, PRE_MENU_UI);//In the case that the Delete button is pressed
            }
            case BACK_BUTTON -> card.show(contenedor, PIANO_FRAME);//In the case that the Back button is pressed
        }
    }
}
