package Presentation.Ui_Views;

//Imports all the libraries needed to create the card layout
import Presentation.Manager.PreMenuUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

/**
 * PreMenuUI
 *
 * The "PreMenuUI" class will contain the different methods to create the view class card layout "PreMenuUI" and preMenu interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class PreMenuUI extends JPanel{
    private final JTextField usernameTextField = new JTextField();
    private final JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private final JButton logIn = new JButton(LOG_IN_BUTTON);
    private final JButton signUp = new JButton(SIGN_UP_BUTTON);
    private final JButton guest = new JButton(ENTER_AS_GUEST_BUTTON);

    private final LoginUI loginUI;
    private final SignUpUI signUpUI;

    //private final BusinessFacadeImp myFacade;
    /**
     * Constructor for the PreMenuUI, you need to send the mainframe context and will create a card layout
     * @param loginUI profileUI view
     * @param signUpUI signUpUI view
     */
    public PreMenuUI(LoginUI loginUI, SignUpUI signUpUI) {
        this.loginUI = loginUI;
        this.signUpUI = signUpUI;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the PreMenuUI
     */
    private void initialize() {
        setLayout(new BorderLayout());
        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 15)));

        Font currentFont = pianoText.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0F);
        pianoText.setFont(newFont);
        pianoText.setAlignmentX(0.5f);
        pianoText.setForeground(Color.WHITE);
        userButtons.add(pianoText);

        createButtons(userButtons);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        background.add(userButtons, BorderLayout.CENTER);
        setBackground(Color.BLACK);


        JPanel pN = new JPanel();
        pN.setBackground(Color.getHSBColor(0, 0, 0.2f));
        this.add(pN, BorderLayout.NORTH);

        JPanel pS = new JPanel();
        pS.setBackground(Color.getHSBColor(0, 0, 0.2f));
        this.add(pS, BorderLayout.SOUTH);

        JPanel pE = new JPanel();
        pE.setBackground(Color.getHSBColor(0, 0, 0.2f));
        this.add(pE, BorderLayout.EAST);

        JPanel pW = new JPanel();
        pW.setBackground(Color.getHSBColor(0, 0, 0.2f));
        this.add(pW, BorderLayout.WEST);

        this.add(background);
        this.setSize(600, 400);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    /**
     * Method through which we add the buttons to the panel that will be added afterwards to the frame for the user interface
     * @param userButtons JPanel that will have the buttons in it
     */
    private void createButtons(JPanel userButtons) {
        userButtons.add(Box.createRigidArea(new Dimension(10, 45)));
        logIn.setActionCommand(LOG_IN_BUTTON);
        logIn.setAlignmentX(0.5f);
        logIn.setBorder(new EmptyBorder(12,120,12,120));
        logIn.setOpaque(true);

        signUp.setActionCommand(SIGN_UP_BUTTON);
        signUp.setAlignmentX(0.5f);
        signUp.setBorder(new EmptyBorder(12,116,12,116));
        signUp.setOpaque(true);

        guest.setActionCommand(ENTER_AS_GUEST_BUTTON);
        guest.setAlignmentX(0.5f);
        guest.setBorder(new EmptyBorder(12,88,12,88));
        guest.setOpaque(true);

        registerController(new PreMenuUIManager(this));

        userButtons.add(logIn);
        userButtons.add(Box.createRigidArea(new Dimension(10, 25)));
        userButtons.add(signUp);
        userButtons.add(Box.createRigidArea(new Dimension(10, 25)));
        userButtons.add(guest);

    }

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(ActionListener listener) {
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);
        guest.addActionListener(listener);
    }

    public void setUsernameLogin(String usernameLogin) {
        usernameTextField.setText(usernameLogin);
    }

    public void resetLoginUI() {
        loginUI.resetUILogin();
    }

    public void resetSignUpUI() {
        signUpUI.resetUISignUpUI();
    }
}
