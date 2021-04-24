package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;
import Presentation.Manager.SignUpUiManager;
import Presentation.TextPrompt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.security.KeyPairGenerator;

import static Presentation.Dictionary_login.*;

public class SignUpUI extends JPanel {
    private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private JLabel logInText = new JLabel(SIGN_UP_BUTTON);

    private static JTextField usernameTextField = new JTextField();
    private static JTextField mailTextField = new JTextField();
    private static JPasswordField password = new JPasswordField();
    private static JPasswordField passwordConfirmation = new JPasswordField();
    private JButton back = new JButton(BACK_BUTTON);
    private JButton done = new JButton(DONE_BUTTON);
    private MainFrame mainFrame;

    /**
     * Removes everything that was on the frame and modifies it in order to have the sign up user interface
     * @param  mainFrame frame to modify, this will be the user interface
     */
    public SignUpUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        initialize();
    }

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

        registerController(new SignUpUiManager());

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

    public static String getUsernameSignUp() {
        return usernameTextField.getText();
    }

    public static String getPasswordSignUp() {
        return password.getText();
    }

    public static String getMailSignUp() {
        return mailTextField.getText();
    }

    public static String getPasswordConfirmSignUp() {
        return passwordConfirmation.getText();
    }
}
