package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Presentation.Dictionary_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.*;
import static Presentation.Ui_Views.LoginUI.resetUILogin;
import static Presentation.Ui_Views.SignUpUI.resetUISignUpUI;

/**
 * PreMenuUIManager
 *
 * The "PreMenuUIManager" class will contain the different methods that are needed to control the view class "PreMenuUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 21 Apr 2021
 *
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
            case Dictionary_login.LOG_IN_BUTTON:                    //In the case that the LogIn button is pressed
                resetUILogin();
                card.show(contenedor, LOGIN_UI);
                break;
            case Dictionary_login.SIGN_UP_BUTTON:                   //In the case that the SignUp button is pressed
                resetUISignUpUI();
                card.show(contenedor, SIGN_UP_UI);
                break;
            case Dictionary_login.ENTER_AS_GUEST_BUTTON:            //In the case that the Guest button is pressed
                //TODO: ENTER AS A GUEST USER
                card.show(contenedor,TEMP_FREE_PIANO_UI);
                break;
        }
    }
}
