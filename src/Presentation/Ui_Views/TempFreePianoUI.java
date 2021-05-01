package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.MainFrame;
import Presentation.Manager.PreMenuUIManager;
import Presentation.Manager.TempFreePianoUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

import static Presentation.DictionaryPiano.*;
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

    private JButton logIn = new JButton(LOG_IN_BUTTON);
    private JButton signUp = new JButton(SIGN_UP_BUTTON);
    private JButton guest = new JButton(ENTER_AS_GUEST_BUTTON);

    public static JPanel centralPanel = new JPanel(new CardLayout());

    PianoTilesUISelector pianoTilesUISelector;
    PianoTilesUIGame pianoTilesUIGame;
    SpotiUI spotiUI;


    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public TempFreePianoUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        pianoTilesUISelector = new PianoTilesUISelector(mainFrame);
        pianoTilesUIGame = new PianoTilesUIGame(mainFrame);
        spotiUI = new SpotiUI(mainFrame);
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the TempFreePianoUI
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

        registerController(new TempFreePianoUIManager());

        buttonsPanel.add(logIn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(signUp);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(guest);

        this.add(buttonsPanel, BorderLayout.WEST);

        centralPanel.add(pianoTilesUIGame, PIANO_TILES_UI_GAME);
        centralPanel.add(pianoTilesUISelector, PIANO_TILES_UI_SELECTOR);
        this.add(centralPanel, BorderLayout.CENTER);


        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 15)));

        userButtons.add(Box.createRigidArea(new Dimension(10, 45)));
        profile.setActionCommand(PROFILE_BUTTON);
        profile.setAlignmentX(0.5f);
        profile.setBorder(new EmptyBorder(12,120,12,120));

        registerController(new TempFreePianoUIManager());
        userButtons.add(profile);
        this.add(userButtons, BorderLayout.NORTH);
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
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);
        guest.addActionListener(listener);

    }
}
