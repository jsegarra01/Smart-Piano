package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BussinesFacadeImp;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;


/**
 * LoginUIManager
 *
 * The "LoginUIManager" class will contain the different methods that are needed to control the view class "LoginUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class LoginUIManager implements ActionListener {
    /**
     * Parametrized constructor
     */
    public LoginUIManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.BACK_BUTTON:                          //In the case that the Back button is pressed
                card.show(contenedor, PRE_MENU_UI);
                break;
            case Dictionary_login.DONE_BUTTON:                          //In the case that the Done button is pressed
                BussinesFacadeImp bussinesFacadeImp = new BussinesFacadeImp();
                if(bussinesFacadeImp.logIn(LoginUI.getUsernameLogin(), LoginUI.getPasswordLogin())) card.show(contenedor, TEMP_FREE_PIANO_UI);
                break;
        }
    }
}
