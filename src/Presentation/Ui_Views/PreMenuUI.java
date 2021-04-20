package Presentation.Ui_Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

public class PreMenuUI extends JFrame{
    private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private JButton logIn = new JButton(LOG_IN_BUTTON);
    private JButton signUp = new JButton(SIGN_UP_BUTTON);
    private JButton guest = new JButton(ENTER_AS_GUEST_BUTTON);

    /**
     * Removes everything that was on the frame and modifies it in order to have the sign up user interface
     * @param frame The frame to modify, this will be the user interface
     */
    public PreMenuUI(JFrame frame) {
        frame.getContentPane().removeAll();

        setTitle(SMART_PIANO_TEXT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


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

        frame.add(background);
        frame.pack();
        frame.setSize(600, 400);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
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

        signUp.setActionCommand(SIGN_UP_BUTTON);
        signUp.setAlignmentX(0.5f);
        signUp.setBorder(new EmptyBorder(12,116,12,116));

        guest.setActionCommand(ENTER_AS_GUEST_BUTTON);
        guest.setAlignmentX(0.5f);
        guest.setBorder(new EmptyBorder(12,88,12,88));

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
    public void registerController(ActionListener listener) {
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);
        guest.addActionListener(listener);
    }

}
