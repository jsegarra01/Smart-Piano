package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;

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
    BusinessFacadeImp myFacade;
    /**
     * Parametrized constructor
     */
    public PreMenuUIManager(BusinessFacadeImp myFacade) {
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
            case LOG_IN_BUTTON -> myFacade.logInStartup();//In the case that the LogIn button is pressed
            case SIGN_UP_BUTTON -> myFacade.singUpStartup();//In the case that the SignUp button is pressed
            case ENTER_AS_GUEST_BUTTON -> myFacade.enterAsAGuest("guest", "password");//In the case that the Guest button is pressed
        }
    }
}
