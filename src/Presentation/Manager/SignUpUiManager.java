package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BussinesFacadeImp;
import Presentation.Dictionary_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.PRE_MENU_UI;
import static Presentation.Dictionary_login.TEMP_FREE_PIANO_UI;
import static Presentation.Manager.MainFrame.*;
import static Presentation.Ui_Views.SignUpUI.*;

/**
 * SignUpUIManager
 *
 * The "SignUpUIManager" class will contain the different methods that are needed to control the view class "SignUpUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class SignUpUiManager  implements ActionListener {
    /**
     * Parametrized constructor
     */
    public SignUpUiManager() {
        }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.BACK_BUTTON:              //In the case that the Back button is pressed
                card.show(contenedor, PRE_MENU_UI);
                break;
            case Dictionary_login.DONE_BUTTON:              //In the case that the Done button is pressed
                if (new BussinesFacadeImp().SignUp(getUsernameSignUp(), getMailSignUp(), getPasswordSignUp(), getPasswordConfirmSignUp())) {card.show(contenedor, TEMP_FREE_PIANO_UI);}
                break;
            }
        }
}

