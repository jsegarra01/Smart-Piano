package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

import static Presentation.DictionaryPiano.*;

/**
 * TempFreePianoUI
 *
 * The "TempFreePianoUI" class will contain the different methods to create the view class card layout "TempFreePianoUI" and TempFreePiano interface
 *
 * @author OOPD 20-21 ICE5
 * @version 1.5 1 May 2021
 *
 */
public class PianoFrame extends JPanel {
    private MainFrame mainFrame;

    private JButton freePiano = new JButton(FREE_PIANO);
    private JButton playSong = new JButton(PLAY_A_SONG);
    private JButton musicPlayer = new JButton(MUSIC_PLAYER);

    public static JPanel centralPanel = new JPanel(new CardLayout());

    PianoTilesUISelector pianoTilesUISelector;
    PianoTilesUIGame pianoTilesUIGame;
    SpotiUI spotiUI;
    FreePianoUI freePianoUI;




    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public PianoFrame(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        pianoTilesUISelector = new PianoTilesUISelector(mainFrame);
        pianoTilesUIGame = new PianoTilesUIGame(mainFrame);
        spotiUI = new SpotiUI(mainFrame);
        freePianoUI = new FreePianoUI(mainFrame);
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

        playSong.setActionCommand(PLAY_A_SONG);
        playSong.setAlignmentX(0.5f);
        playSong.setBorder(new EmptyBorder(80,37,80,37));
        playSong.setBackground(Color.getHSBColor(0,0,80.3f));

        musicPlayer.setActionCommand(MUSIC_PLAYER);
        musicPlayer.setAlignmentX(0.5f);
        musicPlayer.setBorder(new EmptyBorder(80,33,80,33));
        musicPlayer.setBackground(Color.getHSBColor(0,0,80.3f));

        registerController(new PianoFrameManager());

        buttonPanel.add(freePiano);
        buttonPanel.add(Box.createRigidArea(new Dimension(150, 10)));
        buttonPanel.add(playSong);
        buttonPanel.add(Box.createRigidArea(new Dimension(80, 10)));
        buttonPanel.add(musicPlayer);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        buttonPanel.setBackground(Color.getHSBColor(0, 0, 0.2f));

        this.add(buttonPanel, BorderLayout.WEST);

        centralPanel.add(freePianoUI, FREE_PIANO_UI);
        centralPanel.add(pianoTilesUIGame, PIANO_TILES_UI_GAME);
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