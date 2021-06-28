package Presentation.Ui_Views;

//Imports all necessary libraries
import Business.BusinessFacadeImp;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.*;

/**
 * PianoFrame
 *
 * The "PianoFrame" class will contains the main panel of the whole project, which will have inside the
 * different card layouts.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.5 23 May 2021
 *
 */
public class PianoFrame extends JPanel {
    public static MainFrame mainFrame;

    public static final JButton freePiano = new JButton(FREE_PIANO);
    public static final JButton playSong = new JButton(PLAY_A_SONG);
    public static final JButton musicPlayer = new JButton(MUSIC_PLAYER);

    public static final JPanel centralPanel = new JPanel(new CardLayout());

    private final PianoTilesUISelector pianoTilesUISelector;
    private final SpotiUI spotiUI;
    private final FreePianoUI freePianoUI;


    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public PianoFrame(final MainFrame mainFrame) {
        super();
        PianoFrame.mainFrame =mainFrame;

        pianoTilesUISelector = new PianoTilesUISelector();
        spotiUI = new SpotiUI();
        freePianoUI = new FreePianoUI();
        initialize();
    }


    /**
     * The initialize function that creates the card layout for the TempFreePianoUI
     */
    private void initialize() {
        mainFrame.setTitle("SmartPiano");
        mainFrame.setSize(1000, 800);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 45)));
        freePiano.setActionCommand(FREE_PIANO);
        freePiano.setAlignmentX(0.5f);
        freePiano.setBorder(new EmptyBorder(80,40,80,40));
        freePiano.setBackground(Color.getHSBColor(0,0,80.3f));
        freePiano.setOpaque(true);

        playSong.setActionCommand(PLAY_A_SONG);
        playSong.setAlignmentX(0.5f);
        playSong.setBorder(new EmptyBorder(80,37,80,37));
        playSong.setBackground(Color.GRAY);
        playSong.setOpaque(true);

        musicPlayer.setActionCommand(MUSIC_PLAYER);
        musicPlayer.setAlignmentX(0.5f);
        musicPlayer.setBorder(new EmptyBorder(80,33,80,33));
        musicPlayer.setBackground(Color.GRAY);
        musicPlayer.setOpaque(true);

        registerController(new PianoFrameManager(pianoTilesUISelector.getPianoTilesUISelectorManager()));

        buttonPanel.add(Box.createRigidArea(new Dimension(150, 17)));
        buttonPanel.add(freePiano);
        buttonPanel.add(Box.createRigidArea(new Dimension(150, 10)));
        buttonPanel.add(playSong);
        buttonPanel.add(Box.createRigidArea(new Dimension(80, 10)));
        buttonPanel.add(musicPlayer);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        buttonPanel.setBackground(Color.getHSBColor(0, 0, 0.2f));

        this.add(buttonPanel, BorderLayout.WEST);

        centralPanel.add(freePianoUI, FREE_PIANO_UI);
        centralPanel.add(pianoTilesUISelector, PIANO_TILES_UI_SELECTOR);
        centralPanel.add(spotiUI, SPOTI_UI);
        this.add(centralPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
        mainFrame.pack();
    }

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(ActionListener listener) {
        freePiano.addActionListener(listener);
        playSong.addActionListener(listener);
        musicPlayer.addActionListener(listener);
    }
}