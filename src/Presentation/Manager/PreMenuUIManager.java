package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Presentation.Ui_Views.PreMenuUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Presentation.Dictionary_login.*;

/**
 * PreMenuUIManager
 *
 * The "PreMenuUIManager" class will contain the different methods that are needed to control the view class "PreMenuUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class PreMenuUIManager implements ActionListener {
    private final PreMenuUI preMenuUI;
    /**
     * Parametrized constructor
     * @param preMenuUI premenu view
     */
    public PreMenuUIManager(PreMenuUI preMenuUI) {
        this.preMenuUI = preMenuUI;
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case LOG_IN_BUTTON:
                preMenuUI.resetLoginUI();
                preMenuUI.setMainCard(LOGIN_UI);
                //BusinessFacadeImp.getBusinessFacade().logInStartup();//In the case that the LogIn button is pressed
                break;
            case SIGN_UP_BUTTON:
                preMenuUI.resetSignUpUI();
                preMenuUI.setMainCard(SIGN_UP_UI);
                //BusinessFacadeImp.getBusinessFacade().singUpStartup();//In the case that the SignUp button is pressed
                break;
            case ENTER_AS_GUEST_BUTTON:
                if (BusinessFacadeImp.getBusinessFacade().logIn("guest", "password")) {
                    preMenuUI.setUsernameLogin("guest");
                    BusinessFacadeImp.getBusinessFacade().enterAsAGuest();
                    preMenuUI.setMainCard(PIANO_FRAME);
                }
                break;
        }
    }
}
