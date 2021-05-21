package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacade;
import Business.BusinessFacadeImp;
import Business.UserManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.FREE_PIANO_UI;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.*;
import static Presentation.Ui_Views.LoginUI.resetUILogin;
import static Presentation.Ui_Views.LoginUI.setUsernameLogin;
import static Presentation.Ui_Views.PianoFrame.centralPanel;
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

        CardLayout cc = (CardLayout) (centralPanel.getLayout());
       // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case LOG_IN_BUTTON -> myFacade.logInStartup();//In the case that the LogIn button is pressed
            case SIGN_UP_BUTTON -> myFacade.singUpStartup();//In the case that the SignUp button is pressed
            case ENTER_AS_GUEST_BUTTON -> {
                myFacade.enterAsAGuest("guest", "password");//In the case that the Guest button is pressed
                new BusinessFacadeImp().getPlaylistManager().setPlaylists(UserManager.getUser().getUserName());
                SpotiFrameManager.addPlaylists(new BusinessFacadeImp().getPlaylistManager().getPlaylists());
                cc.show(centralPanel, FREE_PIANO_UI);
            }
        }
    }
}
