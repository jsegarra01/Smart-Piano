package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Presentation.Ui_Views.SignUpUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.*;
import static Presentation.Ui_Views.SignUpUI.*;

/**
 * SignUpUIManager
 *
 * The "SignUpUIManager" class will contain the different methods that are needed to control the view class "SignUpUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class SignUpUiManager  implements ActionListener {
    private final SignUpUI signUpUI;
    /**
     * Parametrized constructor
     * @param signUpUI signup View
     */
    public SignUpUiManager(SignUpUI signUpUI) {
        this.signUpUI = signUpUI;
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
                //In the case that the Back button is pressed
            case BACK_BUTTON:
                card.show(contenedor, PRE_MENU_UI);
                break;
                //In the case that the Done button is pressed
            case DONE_BUTTON:
                if(BusinessFacadeImp.getBusinessFacade().SignUp(signUpUI.getUsernameSignUp(), signUpUI.getMailSignUp(),
                        signUpUI.getPasswordSignUp(), signUpUI.getPasswordConfirmSignUp())){
                    signUpUI.setUsernameLogin(signUpUI.getUsernameSignUp());
                    BusinessFacadeImp.getBusinessFacade().setSongUser();
                    card.show(contenedor, PIANO_FRAME);
                }
                break;
        }
    }
}

