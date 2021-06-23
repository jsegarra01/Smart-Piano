package Presentation.Manager;

//Imports needed from the dictionary and events
import Business.BusinessFacade;
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
 * @version 2.0 23 May 2021
 *
 */
public class MainFrame extends JFrame {
    public static  CardLayout card = new CardLayout(); //TODO Not static and not public Do not initialize, that in the constructor:(
    public static  Container contenedor; //TODO Not static and not public
    private PreMenuUI preMenuUI;
    private LoginUI loginUI;
    private SignUpUI signUpUI;
    private ProfileUI profileUI;
    private PianoFrame pianoFrame;
    //private BusinessFacade myFacade;


    /**
     * Parametrized constructor, creates the content pane plus the different card layouts available for the user interface
     */
    public MainFrame(/*BusinessFacade myFacade*/) {
        //this.myFacade = myFacade;
        contenedor = this.getContentPane();
        preMenuUI=  new PreMenuUI(/*myFacade*/); //TODO
        profileUI = new ProfileUI(/*myFacade*/); //TODO
        signUpUI = new SignUpUI(/*myFacade*/); //TODO
        pianoFrame = new PianoFrame(this/*, myFacade*/); //TODO
        loginUI = new LoginUI();

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

        BusinessFacadeImp.getBusinessFacade().initializeWebScrapping();
    }
    public BusinessFacade getMyFacade(){return BusinessFacadeImp.getBusinessFacade();} //TODO CHECK
}
