package Presentation.Manager;

//Imports needed from the dictionary and events
import Business.BusinessFacadeImp;
import Presentation.Ui_Views.*;

import javax.swing.*;
import java.awt.*;

import static Presentation.Dictionary_login.*;
/**
 * MainFrame
 *
 * The "MainFrame" class will contain the different methods that are needed for the views of the login, including a card layout
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 21 Apr 2021
 *
 */
public class MainFrame extends JFrame {
    public static  CardLayout card = new CardLayout();
    public static  Container contenedor;
    PreMenuUI preMenuUI;
    LoginUI loginUI;
    SignUpUI signUpUI;
    ProfileUI profileUI;
    PianoFrame pianoFrame;
    BusinessFacadeImp myFacade;


    /**
     * Parametrized constructor, creates the content pane plus the different card layouts available for the user interface
     */
    public MainFrame(BusinessFacadeImp myFacade) {
        this.myFacade = myFacade;
        contenedor = this.getContentPane();
        preMenuUI=  new PreMenuUI(myFacade);
        profileUI = new ProfileUI(myFacade);
        signUpUI = new SignUpUI(myFacade);
        pianoFrame = new PianoFrame(this, myFacade);
        loginUI = new LoginUI(myFacade);

        card.addLayoutComponent(preMenuUI, PRE_MENU_UI);
        card.addLayoutComponent(profileUI, PROFILE_UI);
        card.addLayoutComponent(signUpUI, SIGN_UP_UI);
        card.addLayoutComponent(pianoFrame, PIANO_FRAME);
        card.addLayoutComponent(loginUI, LOGIN_UI);

        contenedor.add(preMenuUI);
        contenedor.add(profileUI);
        contenedor.add(signUpUI);
        contenedor.add(pianoFrame);
        contenedor.add(loginUI);


        contenedor.setLayout(card);
        contenedor.setSize(1500, 800);
        card.show(contenedor, PRE_MENU_UI);
    }
}
