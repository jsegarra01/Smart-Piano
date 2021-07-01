package Presentation.Ui_Views;

//Imports all the necessary libraries
import Presentation.Manager.SignUpUiManager;
import Presentation.TextPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import static Presentation.Dictionary_login.*;

/**
 * SignUpUI
 *
 * The "SignUpUI" class will contain the different methods to create the view class card layout "SignUpUI" and SignUp interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class SignUpUI extends JPanel {
    private final JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private final JLabel logInText = new JLabel(SIGN_UP_BUTTON);

    private final JTextField usernameTextField = new JTextField();
    private final JTextField mailTextField = new JTextField();
    private final JPasswordField password = new JPasswordField();
    private final JPasswordField passwordConfirmation = new JPasswordField();
    private final JButton back = new JButton(BACK_BUTTON);
    private final JButton done = new JButton(DONE_BUTTON);
    //private final BusinessFacadeImp myFacade;

    /**
     * Constructor for the SignUpUI, you need to send the mainframe context and will create a card layout
     */
    public SignUpUI(/*BusinessFacadeImp myFacade*/) {
        //this.myFacade = myFacade;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the SignUpUI
     */
    private void initialize() {
        setLayout(new BorderLayout());

        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 10)));

        //Text configurations
        Font currentFont = pianoText.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0F); //change font size
        pianoText.setFont(newFont);
        pianoText.setAlignmentX(0.5f);
        pianoText.setForeground(Color.WHITE);
        userButtons.add(pianoText);

        userButtons.add(Box.createRigidArea(new Dimension(10, 10))); //white space

        logInText.setAlignmentX(0.5f);
        logInText.setForeground(Color.WHITE);
        userButtons.add(logInText);

        userButtons.add(Box.createRigidArea(new Dimension(10, 35))); //white space

        usernameTextField.setAlignmentX(0.5f);
        usernameTextField.setPreferredSize(new Dimension(300,40));
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize()); //set text field size
        usernameTextField.setBackground(Color.WHITE);

        TextPrompt usernamePrompt = new TextPrompt("Username: ", new JTextField(), TextPrompt.Show.ALWAYS);

        usernamePrompt.setAlignmentX(0.5f);
        usernamePrompt.setPreferredSize(new Dimension(300,20));
        usernamePrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        usernamePrompt.setForeground(Color.WHITE);

        userButtons.add(usernamePrompt);
        userButtons.add(usernameTextField);

        userButtons.add(Box.createRigidArea(new Dimension(10, 20))); //white space

        mailTextField.setAlignmentX(0.5f);
        mailTextField.setPreferredSize(new Dimension(300,40));
        mailTextField.setMaximumSize(usernameTextField.getPreferredSize());
        mailTextField.setBackground(Color.WHITE);

        TextPrompt mailPrompt = new TextPrompt("Mail: ", new JTextField(), TextPrompt.Show.ALWAYS);

        mailPrompt.setAlignmentX(0.5f);
        mailPrompt.setPreferredSize(new Dimension(300,20));
        mailPrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        mailPrompt.setForeground(Color.WHITE);

        userButtons.add(mailPrompt);
        userButtons.add(mailTextField);

        userButtons.add(Box.createRigidArea(new Dimension(10, 20))); //white space

        password.setAlignmentX(0.5f);
        password.setPreferredSize(new Dimension(300,40));
        password.setMaximumSize(usernameTextField.getPreferredSize());
        TextPrompt passwordPrompt = new TextPrompt("Password: ", new JTextField(), TextPrompt.Show.ALWAYS);

        passwordPrompt.setAlignmentX(0.5f);
        passwordPrompt.setPreferredSize(new Dimension(300,20));
        passwordPrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        passwordPrompt.setForeground(Color.WHITE);

        userButtons.add(passwordPrompt);
        userButtons.add(password);

        userButtons.add(Box.createRigidArea(new Dimension(10, 20))); //white space

        passwordConfirmation.setAlignmentX(0.5f);
        passwordConfirmation.setPreferredSize(new Dimension(300,40));
        passwordConfirmation.setMaximumSize(passwordConfirmation.getPreferredSize());

        TextPrompt confirmationPrompt = new TextPrompt("Confirm password: ", new JTextField(), TextPrompt.Show.ALWAYS);

        confirmationPrompt.setAlignmentX(0.5f);
        confirmationPrompt.setPreferredSize(new Dimension(300,20));
        confirmationPrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        confirmationPrompt.setForeground(Color.WHITE);

        userButtons.add(confirmationPrompt);
        userButtons.add(passwordConfirmation);

        userButtons.add(Box.createRigidArea(new Dimension(10, 100))); //white space

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        back.setAlignmentX(0.5f);
        done.setActionCommand(BACK_BUTTON);

        done.setAlignmentX(0.5f);
        done.setActionCommand(DONE_BUTTON);

        registerController(new SignUpUiManager(this));

        buttons.add(back);
        buttons.add(Box.createRigidArea(new Dimension(400, 15)));
        buttons.add(done);

        buttons.setBackground(Color.getHSBColor(0, 0, 0.1f));
        userButtons.add(buttons);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel auxBL = new JPanel(); //Panel for the gray lines on the sides
        auxBL.setLayout(new BorderLayout());

        auxBL.add(userButtons, BorderLayout.CENTER);
        auxBL.setBackground(Color.getHSBColor(0, 0, 0.1f));

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

        this.add(auxBL);
        this.setSize(600, 600);

        this.revalidate();
        this.repaint();
    }

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(ActionListener listener) {
        back.addActionListener(listener);
        done.addActionListener(listener);
    }

    /**
     * Obtains the username in the text field
     * @return username string
     */
    public String getUsernameSignUp() {
        return usernameTextField.getText();
    }

    /**
     * Obtains the mail in the text field
     * @return mail string
     */
    public String getMailSignUp() {
        return mailTextField.getText();
    }

    /**
     * Obtains the password in the text field
     * @return password string
     */
    public String getPasswordSignUp() {
        return String.valueOf(password.getPassword());
    }

    /**
     * Obtains the passwordConfirm in the text field
     * @return passwordConfirm string
     */
    public String getPasswordConfirmSignUp() { return String.valueOf(passwordConfirmation.getPassword()); }

    /**
     * Resets the information inside the SignUpUI
     */
    public void resetUISignUpUI() {
        usernameTextField.setText("");
        mailTextField.setText("");
        password.setText("");
        passwordConfirmation.setText("");
    }

    /**
     * Method that sets the username set in the sign up
     * @param usernameLogin Defines the username to be signed up
     */
    public void setUsernameLogin(String usernameLogin) {
        usernameTextField.setText(usernameLogin);
    }
}
