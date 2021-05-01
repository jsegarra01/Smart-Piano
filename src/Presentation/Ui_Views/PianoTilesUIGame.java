package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;
import Presentation.Manager.TempFreePianoUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Presentation.Dictionary_login.*;


//import static Presentation.DictionaryPiano.*;

/**
 * PianoTilesUIGame
 *
 * The "PianoTilesUIGame" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class PianoTilesUIGame extends JPanel {
    /*
     private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
     private JLabel logInText = new JLabel(LOG_IN_TEXT);
 
     private static JTextField usernameTextField = new JTextField();
     private static JPasswordField password = new JPasswordField();
     private JButton back = new JButton(BACK_BUTTON);
     private JButton done = new JButton(DONE_BUTTON);*/
    private MainFrame mainFrame;
    private JButton logIn = new JButton(LOG_IN_BUTTON);
    private JButton signUp = new JButton(SIGN_UP_BUTTON);
    private JButton guest = new JButton(ENTER_AS_GUEST_BUTTON);

    /**
     * Constructor for the PianoTilesUIGame, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public PianoTilesUIGame(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the PianoTilesUIGame
     */
    private void initialize() {
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 45)));
        logIn.setActionCommand(LOG_IN_BUTTON);
        logIn.setAlignmentX(0.5f);
        logIn.setBorder(new EmptyBorder(12,120,12,120));

        signUp.setActionCommand(SIGN_UP_BUTTON);
        signUp.setAlignmentX(0.5f);
        signUp.setBorder(new EmptyBorder(12,116,12,116));

        guest.setActionCommand(ENTER_AS_GUEST_BUTTON);
        guest.setAlignmentX(0.5f);
        guest.setBorder(new EmptyBorder(12,88,12,88));

        buttonsPanel.add(logIn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(signUp);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(guest);

        this.add(buttonsPanel, BorderLayout.WEST);
    }
}