import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignUpUI extends JFrame{

    private JLabel pianoText = new JLabel("SMART PIANO");
    private JLabel logInText = new JLabel("SIGN UP");

    private JTextField usernameTextField = new JTextField();
    private JTextField mailTextField = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JPasswordField passwordConfirmation = new JPasswordField();
    private JButton back = new JButton("Back");
    private JButton done = new JButton("Done");


    public SignUpUI() {


        setTitle("SMART PIANO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


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

        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));

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

        userButtons.add(Box.createRigidArea(new Dimension(10, 100)));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        back.setAlignmentX(0.5f);
        buttons.add(back);

        buttons.add(Box.createRigidArea(new Dimension(410, 15)));

        done.setAlignmentX(0.5f);
        buttons.add(done);

        buttons.setBackground(Color.getHSBColor(0, 0, 0.1f));
        userButtons.add(buttons);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel auxBL = new JPanel();
        auxBL.setLayout(new BorderLayout());

        auxBL.add(userButtons, BorderLayout.CENTER);
        //auxBL.add(buttons, BorderLayout.SOUTH);
        auxBL.setBackground(Color.getHSBColor(0, 0, 0.1f));

        /*JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        JPanel auxiliarEastPanel = new JPanel();
        auxiliarEastPanel.setLayout(new BoxLayout(auxiliarEastPanel, BoxLayout.Y_AXIS));

        JPanel backBorderLayoutPanel = new JPanel();
        backBorderLayoutPanel.setLayout(new BorderLayout());

        JPanel backBoxLayoutPanel = new JPanel();
        backBoxLayoutPanel.setLayout(new BoxLayout(backBoxLayoutPanel, BoxLayout.Y_AXIS));

        backBoxLayoutPanel.add(Box.createRigidArea(new Dimension(30, 500)));
        backBoxLayoutPanel.add(back);
        backBoxLayoutPanel.setBackground(Color.getHSBColor(0, 0, 0.1f));

        auxiliarEastPanel.add(Box.createRigidArea(new Dimension(80, 35)));
        auxiliarEastPanel.setBackground(Color.getHSBColor(0, 0, 0.1f));

        backBorderLayoutPanel.add(backBoxLayoutPanel, BorderLayout.WEST);
        backBorderLayoutPanel.add(auxiliarEastPanel, BorderLayout.EAST);
        backBorderLayoutPanel.add(userButtons, BorderLayout.CENTER);
        backBorderLayoutPanel.setBackground(Color.getHSBColor(0, 0, 0.1f));

        background.add(backBorderLayoutPanel, BorderLayout.CENTER);
        setBackground(Color.getHSBColor(0, 0, 0.1f));*/

        JPanel pN = new JPanel();
        pN.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pN, BorderLayout.NORTH);

        JPanel pS = new JPanel();
        pS.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pS, BorderLayout.SOUTH);

        JPanel pE = new JPanel();
        pE.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pE, BorderLayout.EAST);

        JPanel pW = new JPanel();
        pW.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pW, BorderLayout.WEST);

        add(auxBL);
        pack();

        setSize(600, 600);
        setVisible(true);
    }
}
