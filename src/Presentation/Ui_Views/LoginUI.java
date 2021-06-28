package Presentation.Ui_Views;

//import data from the different libraries
import Presentation.Manager.LoginUIManager;
import Presentation.TextPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

/**
 * LoginUI
 *
 * The "LoginUI" class will contain the different methods to create the view class card layout "LoginUI" and login interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class LoginUI extends JPanel {
    private final JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private final JLabel logInText = new JLabel(LOG_IN_TEXT);

    private final JTextField usernameTextField = new JTextField();
    private final JPasswordField password = new JPasswordField();
    private final JButton back = new JButton(BACK_BUTTON);
    private final JButton done = new JButton(DONE_BUTTON);

    /**
     * Constructor for the LoginUI, you need to send the mainframe context and will create a card layout
     */
    public LoginUI() {
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the LoginUi
     */
    private void initialize() {
        setLayout(new BorderLayout());

        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 10)));

        Font currentFont = pianoText.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0F);
        pianoText.setFont(newFont);
        pianoText.setAlignmentX(0.5f);
        pianoText.setForeground(Color.WHITE);
        userButtons.add(pianoText);

        userButtons.add(Box.createRigidArea(new Dimension(10, 10)));

        logInText.setAlignmentX(0.5f);
        logInText.setForeground(Color.WHITE);
        userButtons.add(logInText);

        userButtons.add(Box.createRigidArea(new Dimension(10, 35)));

        usernameTextField.setAlignmentX(0.5f);
        usernameTextField.setPreferredSize(new Dimension(300,40));
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize());
        usernameTextField.setBackground(Color.WHITE);

        TextPrompt usernamePrompt = new TextPrompt("Username: ", new JTextField(), TextPrompt.Show.ALWAYS);

        usernamePrompt.setAlignmentX(0.5f);
        usernamePrompt.setPreferredSize(new Dimension(300,20));
        usernamePrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        usernamePrompt.setForeground(Color.WHITE);

        userButtons.add(usernamePrompt);
        userButtons.add(usernameTextField);
        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));

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
        userButtons.add(Box.createRigidArea(new Dimension(10, 60)));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        back.setAlignmentX(0.5f);
        back.setActionCommand(BACK_BUTTON);

        registerController(new LoginUIManager(this));

        done.setAlignmentX(0.5f);
        done.setActionCommand(DONE_BUTTON);

        buttons.add(back);
        buttons.add(Box.createRigidArea(new Dimension(400, 15)));
        buttons.add(done);
        buttons.setBackground(Color.getHSBColor(0, 0, 0.1f));
        userButtons.add(Box.createRigidArea(new Dimension(400, 200)));
        userButtons.add(buttons);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel auxBL = new JPanel();
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
        this.setVisible(true);
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
    public String getUsernameLogin() {
        return usernameTextField.getText();
    }

    /**
     * Sets the username in the text field
     */
    public void setUsernameLogin(String usernameLogin) {
        usernameTextField.setText(usernameLogin);
    }

    /**
     * Obtains the password in the text field
     * @return password string
     */
    public String getPasswordLogin() {
        return String.valueOf(password.getPassword());
    }

    /**
     * Resets the information inside the the loginUI
     */
    public void resetUILogin() {
        usernameTextField.setText("");
        password.setText("");
    }
}