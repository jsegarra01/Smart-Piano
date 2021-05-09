package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;
import Business.UserManager;
import Presentation.Ui_Views.LoginUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
            case BACK_BUTTON:                          //In the case that the Back button is pressed
                card.show(contenedor, PRE_MENU_UI);
                break;
            case DONE_BUTTON:                          //In the case that the Done button is pressed
                if(new BusinessFacadeImp().logIn(LoginUI.getUsernameLogin(), LoginUI.getPasswordLogin())){
                    card.show(contenedor, PIANO_FRAME);
                    new BusinessFacadeImp().getPlaylistManager().setPlaylists(UserManager.getUser().getUserName());
                    SpotiFrameManager.addPlaylists(new BusinessFacadeImp().getPlaylistManager().getPlaylists());
                }
                else{JOptionPane.showMessageDialog(contenedor, "Incorrect username or password", "LogIn error" , JOptionPane.ERROR_MESSAGE);}
                break;
        }
    }
}
