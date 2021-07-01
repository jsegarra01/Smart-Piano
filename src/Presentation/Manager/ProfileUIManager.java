package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe

import Business.BusinessFacadeImp;
import Presentation.Ui_Views.ProfileUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;


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
    private final ProfileUI profileUI;

    /**
     * Parametrized constructor
     * @param profileUI View of the profileUI
     */
    public ProfileUIManager(ProfileUI profileUI) {this.profileUI = profileUI;}

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case LOGOUT_BUTTON -> profileUI.setMainCard(PRE_MENU_UI);//In the case that the Logout button is pressed
            case DELETE_BUTTON -> {
                BusinessFacadeImp.getBusinessFacade().deleteAccount();
                profileUI.setMainCard(PRE_MENU_UI);//In the case that the Delete button is pressed
            }
            case BACK_BUTTON -> profileUI.setMainCard(PIANO_FRAME);//In the case that the Back button is pressed
        }
    }
}
