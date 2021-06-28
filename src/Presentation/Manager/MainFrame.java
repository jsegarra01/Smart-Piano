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
 * @version 2.0 23 May 2021
 *
 */
public class MainFrame extends JFrame {
    public static CardLayout card; //TODO Not static and not public Do not initialize, that in the constructor:(
    public static Container contenedor; //TODO Not static and not public


    /**
     * Parametrized constructor, creates the content pane plus the different card layouts available for the user interface
     */
    public MainFrame() {
        card = new CardLayout();
        contenedor = this.getContentPane();

        LoginUI loginUI = new LoginUI(); //TODO
        ProfileUI profileUI = new ProfileUI(); //TODO
        SignUpUI signUpUI = new SignUpUI(); //TODO
        PreMenuUI preMenuUI = new PreMenuUI(loginUI,signUpUI); //TODO
        PianoFrame pianoFrame = new PianoFrame(this); //TODO

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
    public void setCard(Container parent, String name) {card.show(parent, name);}
}
