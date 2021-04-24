package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.MainFrame;
import Presentation.Manager.TempFreePianoUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

/**
 * TempFreePianoUI
 *
 * The "TempFreePianoUI" class will contain the different methods to create the view class card layout "TempFreePianoUI" and TempFreePiano interface
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class TempFreePianoUI extends JPanel {
    private JButton profile = new JButton(PROFILE_BUTTON);
    private MainFrame mainFrame;


    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public TempFreePianoUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the TempFreePianoUI
     */
    private void initialize() {
        setLayout(new BorderLayout());

        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 15)));

        userButtons.add(Box.createRigidArea(new Dimension(10, 45)));
        profile.setActionCommand(PROFILE_BUTTON);
        profile.setAlignmentX(0.5f);
        profile.setBorder(new EmptyBorder(12,120,12,120));

        registerController(new TempFreePianoUIManager());
        userButtons.add(profile);
        this.add(userButtons);
        this.setSize(600, 400);

        this.revalidate();
        this.repaint();
        this.setVisible(true);

    }

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(ActionListener listener) {
        profile.addActionListener(listener);
    }
}
