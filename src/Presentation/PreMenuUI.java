package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

public class PreMenuUI extends JFrame{

    private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);

    private JButton logIn = new JButton(LOG_IN_BUTTON);
    private JButton signUp = new JButton(SIGN_UP_BUTTON);
    private JButton guest = new JButton(ENTER_AS_GUEST);

    public PreMenuUI() {
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

        createButtons(userButtons);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        background.add(userButtons, BorderLayout.CENTER);
        setBackground(Color.BLACK);


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

    private JPanel createButtons(JPanel userButtons) {
        userButtons.add(Box.createRigidArea(new Dimension(10, 25)));
        logIn.setActionCommand(LOG_IN_BUTTON);
        logIn.setAlignmentX(0.5f);
        logIn.setBorder(new EmptyBorder(10,100,10,100));

        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));
        signUp.setActionCommand(SIGN_UP_BUTTON);
        signUp.setAlignmentX(0.5f);
        signUp.setBorder(new EmptyBorder(10,96,10,96));

        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));
        guest.setActionCommand(ENTER_AS_GUEST);
        guest.setAlignmentX(0.5f);
        guest.setBorder(new EmptyBorder(10,68,10,68));

        userButtons.add(logIn);
        userButtons.add(signUp);
        userButtons.add(guest);

        return userButtons;
    }

    public void registerController(ActionListener listener) {
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);
        guest.addActionListener(listener);
    }
}
