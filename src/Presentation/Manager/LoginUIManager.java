package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Business.UserManager;
import Presentation.Ui_Views.LoginUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.FREE_PIANO_UI;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.PianoFrame.centralPanel;


/**
 * LoginUIManager
 *
 * The "LoginUIManager" class will contain the different methods that are needed to control the view class "LoginUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class LoginUIManager implements ActionListener {
    private LoginUI loginUI;
    /**
     * Parametrized constructor
     * @param loginUI The view of the LoginUI
     */
    public LoginUIManager(LoginUI loginUI) {
        this.loginUI = loginUI;
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
            case BACK_BUTTON:                          //In the case that the Back button is pressed
                card.show(contenedor, PRE_MENU_UI);
                break;
            case DONE_BUTTON:                          //In the case that the Done button is pressed
                if(BusinessFacadeImp.getBusinessFacade().logIn(loginUI.getUsernameLogin(), loginUI.getPasswordLogin())){
                    card.show(contenedor, PIANO_FRAME);
                    cc.show(centralPanel, FREE_PIANO_UI);
                    BusinessFacadeImp.getBusinessFacade().setPlaylists();
                    SpotiFrameManager.addPlaylists(BusinessFacadeImp.getBusinessFacade().getPlaylists());
                    BusinessFacadeImp.getBusinessFacade().setSongUser();
                }
                break;
        }
    }
}
