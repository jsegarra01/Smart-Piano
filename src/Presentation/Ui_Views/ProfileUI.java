package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.ProfileUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

/**
 * ProfileUI
 *
 * The "ProfileUI" class will contain the different methods to create the view class card layout "ProfileUI" and Profile interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class ProfileUI extends JPanel{
    private final JLabel pianoText = new JLabel("SMART PIANO");
    private final JLabel profileText = new JLabel("PROFILE");
    private final JButton logOut = new JButton(LOGOUT_BUTTON);
    private final JButton deleteAccount = new JButton(DELETE_BUTTON);
    private final JButton back = new JButton(BACK_BUTTON);

    /**
     * Constructor for the ProfileUI, you need to send the mainframe context and will create a card layout
     */
    public ProfileUI() {

        initialize();
    }

    /**
     * The initialize function that creates the card layout for the ProfileUI
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

        profileText.setAlignmentX(0.5f);
        profileText.setForeground(Color.WHITE);
        userButtons.add(profileText);
        userButtons.add(Box.createRigidArea(new Dimension(10, 35)));

        logOut.setAlignmentX(0.5f);
        logOut.setBorder(new EmptyBorder(10,123,10,125));
        logOut.setOpaque(true);

        deleteAccount.setAlignmentX(0.5f);
        deleteAccount.setBorder(new EmptyBorder(10,96,10,96));
        deleteAccount.setOpaque(true);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        JPanel auxiliarEastPanel = new JPanel();
        auxiliarEastPanel.setLayout(new BoxLayout(auxiliarEastPanel, BoxLayout.Y_AXIS));

        JPanel backBorderLayoutPanel = new JPanel();
        backBorderLayoutPanel.setLayout(new BorderLayout());

        JPanel backBoxLayoutPanel = new JPanel();
        backBoxLayoutPanel.setLayout(new BoxLayout(backBoxLayoutPanel, BoxLayout.Y_AXIS));

        registerController(new ProfileUIManager(/*myFacade*/));

        userButtons.add(logOut);
        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));
        userButtons.add(deleteAccount);

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
        setBackground(Color.getHSBColor(0, 0, 0.1f));

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
        this.setVisible(true);
    }

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(ActionListener listener) {
        logOut.addActionListener(listener);
        deleteAccount.addActionListener(listener);
        back.addActionListener(listener);
    }

}
