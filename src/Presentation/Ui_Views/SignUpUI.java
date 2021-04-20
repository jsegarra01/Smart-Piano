package Presentation.Ui_Views;

import Presentation.TextPrompt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

public class SignUpUI extends JFrame{

    private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private JLabel logInText = new JLabel(SIGN_UP_BUTTON);

    private JTextField usernameTextField = new JTextField();
    private JTextField mailTextField = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JPasswordField passwordConfirmation = new JPasswordField();
    private JButton back = new JButton(BACK_BUTTON);
    private JButton done = new JButton(DONE_BUTTON);

    /**
     * Removes everything that was on the frame and modifies it in order to have the sign up user interface
     * @param frame The frame to modify, this will be the user interface
     */
    public SignUpUI(JFrame frame) {
        frame.getContentPane().removeAll(); //removes everything from the frame

        frame.setTitle(SMART_PIANO_TEXT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


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
        buttons.add(back);

        buttons.add(Box.createRigidArea(new Dimension(400, 15)));

        done.setAlignmentX(0.5f);
        done.setActionCommand(DONE_BUTTON);
        buttons.add(done);

        buttons.setBackground(Color.getHSBColor(0, 0, 0.1f));
        userButtons.add(buttons);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel auxBL = new JPanel(); //Panel for the gray lines on the sides
        auxBL.setLayout(new BorderLayout());

        auxBL.add(userButtons, BorderLayout.CENTER);
        //auxBL.add(buttons, BorderLayout.SOUTH);
        auxBL.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel pN = new JPanel();
        pN.setBackground(Color.getHSBColor(0, 0, 0.2f));
        frame.add(pN, BorderLayout.NORTH);

        JPanel pS = new JPanel();
        pS.setBackground(Color.getHSBColor(0, 0, 0.2f));
        frame.add(pS, BorderLayout.SOUTH);

        JPanel pE = new JPanel();
        pE.setBackground(Color.getHSBColor(0, 0, 0.2f));
        frame.add(pE, BorderLayout.EAST);

        JPanel pW = new JPanel();
        pW.setBackground(Color.getHSBColor(0, 0, 0.2f));
        frame.add(pW, BorderLayout.WEST);

        frame.add(auxBL);
        frame.pack();
        frame.setSize(600, 600);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    public void registerController(ActionListener listener) {
        back.addActionListener(listener);
        done.addActionListener(listener);
    }
}
