package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.MainFrame;
import Presentation.Manager.TempFreePianoUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

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
public class TempFreePianoUI extends JPanel {
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
    public TempFreePianoUI(final MainFrame mainFrame) {
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
        mainFrame.setTitle("I am a piano");
        mainFrame.setSize(1000, 400);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 45)));
        freePiano.setActionCommand(FREE_PIANO);
        freePiano.setAlignmentX(0.5f);
        freePiano.setBorder(new EmptyBorder(12,120,12,120));

        playSong.setActionCommand(PLAY_A_SONG);
        playSong.setAlignmentX(0.5f);
        playSong.setBorder(new EmptyBorder(12,116,12,116));

        musicPlayer.setActionCommand(MUSIC_PLAYER);
        musicPlayer.setAlignmentX(0.5f);
        musicPlayer.setBorder(new EmptyBorder(12,88,12,88));

        registerController(new TempFreePianoUIManager());

        buttonsPanel.add(freePiano);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(playSong);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(musicPlayer);

        this.add(buttonsPanel, BorderLayout.WEST);

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
    private void registerController(TempFreePianoUIManager listener) {
        freePiano.addActionListener(listener);
        playSong.addActionListener(listener);
        musicPlayer.addActionListener(listener);
    }
}