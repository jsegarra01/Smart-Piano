import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogInUI extends JFrame{

    private JLabel pianoText = new JLabel("SMART PIANO");
    private JLabel logInText = new JLabel("LOG IN");

    private JTextField usernameTextField = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton back = new JButton("Back");


    public LogInUI() {


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
        usernameTextField.setPreferredSize(new Dimension(800,40));
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize());
        usernameTextField.setBackground(Color.WHITE);

        TextPrompt usernamePrompt = new TextPrompt("Username: ", new JTextField(), TextPrompt.Show.ALWAYS);

        usernamePrompt.setAlignmentX(0.5f);
        usernamePrompt.setPreferredSize(new Dimension(800,20));
        usernamePrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        usernamePrompt.setForeground(Color.WHITE);

        userButtons.add(usernamePrompt);
        userButtons.add(usernameTextField);

        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));

        password.setAlignmentX(0.5f);
        password.setPreferredSize(new Dimension(800,40));
        password.setMaximumSize(usernameTextField.getPreferredSize());

        TextPrompt passwordPrompt = new TextPrompt("Password: ", new JTextField(), TextPrompt.Show.ALWAYS);

        passwordPrompt.setAlignmentX(0.5f);
        passwordPrompt.setPreferredSize(new Dimension(800,20));
        passwordPrompt.setMaximumSize(usernamePrompt.getPreferredSize());
        passwordPrompt.setForeground(Color.WHITE);

        userButtons.add(passwordPrompt);
        userButtons.add(password);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        JPanel auxiliarEastPanel = new JPanel();
        auxiliarEastPanel.setLayout(new BoxLayout(auxiliarEastPanel, BoxLayout.Y_AXIS));

        JPanel backBorderLayoutPanel = new JPanel();
        backBorderLayoutPanel.setLayout(new BorderLayout());

        JPanel backBoxLayoutPanel = new JPanel();
        backBoxLayoutPanel.setLayout(new BoxLayout(backBoxLayoutPanel, BoxLayout.Y_AXIS));

        backBoxLayoutPanel.add(Box.createRigidArea(new Dimension(30, 300)));
        backBoxLayoutPanel.add(back);
        backBoxLayoutPanel.setBackground(Color.getHSBColor(0, 0, 0.1f));

        auxiliarEastPanel.add(Box.createRigidArea(new Dimension(80, 35)));
        auxiliarEastPanel.setBackground(Color.getHSBColor(0, 0, 0.1f));

        backBorderLayoutPanel.add(backBoxLayoutPanel, BorderLayout.WEST);
        backBorderLayoutPanel.add(auxiliarEastPanel, BorderLayout.EAST);
        backBorderLayoutPanel.add(userButtons, BorderLayout.CENTER);
        backBorderLayoutPanel.setBackground(Color.getHSBColor(0, 0, 0.1f));

        background.add(backBorderLayoutPanel, BorderLayout.CENTER);
        setBackground(Color.getHSBColor(0, 0, 0.1f));

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

        add(background);
        pack();

        setSize(600, 400);
        setVisible(true);
    }
}
