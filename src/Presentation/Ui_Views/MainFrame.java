package Presentation.Ui_Views;

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
    private final CardLayout card;
    private final Container contenedor;

    private final JPanel centralPanel;

    /**
     * Parametrized constructor, creates the content pane plus the different card layouts available for the user interface
     */
    public MainFrame() {
        card = new CardLayout();
        contenedor = this.getContentPane();
        centralPanel = new JPanel(new CardLayout());

        LoginUI loginUI = new LoginUI(this);
        ProfileUI profileUI = new ProfileUI(this);
        SignUpUI signUpUI = new SignUpUI(this);
        PreMenuUI preMenuUI = new PreMenuUI(loginUI,signUpUI, this);
        PianoFrame pianoFrame = new PianoFrame(this);

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

    public void setCard(String name) {card.show(contenedor, name);}

    public JPanel getCentralPanel() {
        return centralPanel;
    }
}
