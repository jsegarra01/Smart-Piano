package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacadeImp;

import javax.swing.*;
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
            case BACK_BUTTON:              //In the case that the Back button is pressed
                card.show(contenedor, PRE_MENU_UI);
                break;
            case DONE_BUTTON:              //In the case that the Done button is pressed
                if (new BusinessFacadeImp().SignUp(getUsernameSignUp(), getMailSignUp(), getPasswordSignUp(), getPasswordConfirmSignUp())) {card.show(contenedor, PIANO_FRAME);}
                else{
                    JOptionPane.showMessageDialog(contenedor, "Values introduced were not accepted", "SignUp error" , JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
        }
}

