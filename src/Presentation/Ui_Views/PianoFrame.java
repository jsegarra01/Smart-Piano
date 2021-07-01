package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.PianoFrameManager;
import Presentation.Manager.PianoTilesUISelectorManager;
import Presentation.Manager.SpotiFrameManager;

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
    private final MainFrame mainFrame;

    private final JPanel spotiPanel = new JPanel(new CardLayout());
    private final JButton freePiano = new JButton(FREE_PIANO);
    private final JButton playSong = new JButton(PLAY_A_SONG);
    private final JButton musicPlayer = new JButton(MUSIC_PLAYER);

    private final PianoTilesUISelector pianoTilesUISelector;
    private final SpotiFrame spotiFrame;
    private final FreePianoUI freePianoUI;


    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public PianoFrame(final MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        pianoTilesUISelector = new PianoTilesUISelector();
        spotiFrame = new SpotiFrame(this);
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

        registerController(new PianoFrameManager(this));

        buttonPanel.add(Box.createRigidArea(new Dimension(150, 17)));
        buttonPanel.add(freePiano);
        buttonPanel.add(Box.createRigidArea(new Dimension(150, 10)));
        buttonPanel.add(playSong);
        buttonPanel.add(Box.createRigidArea(new Dimension(80, 10)));
        buttonPanel.add(musicPlayer);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        buttonPanel.setBackground(Color.getHSBColor(0, 0, 0.2f));

        this.add(buttonPanel, BorderLayout.WEST);

        mainFrame.getCentralPanel().add(freePianoUI, FREE_PIANO_UI);
        mainFrame.getCentralPanel().add(pianoTilesUISelector, PIANO_TILES_UI_SELECTOR);
        mainFrame.getCentralPanel().add(spotiFrame, SPOTI_UI);
        this.add(mainFrame.getCentralPanel(), BorderLayout.CENTER);

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

    /**
     * Method that sets the color of the background of the free piano
     * @param color Defines the color to be set
     */
    public void setBackgroundFreePiano(Color color){
        freePiano.setBackground(color);
    }

    /**
     * Method that sets the color of the background of the play a song
     * @param color Defines the color to be set
     */
    public void setBackgroundPlaySong(Color color){
        playSong.setBackground(color);
    }

    /**
     * Method that sets the color of the background of the music player
     * @param color Defines the color to be set
     */
    public void setBackgroundMusicPlayer(Color color){
        musicPlayer.setBackground(color);
    }

    /**
     * Method that gets the controller of the piano tiles (play a song)
     * @return PianoTilesUISelectorManager that stores the controller of the play a song
     */
    public PianoTilesUISelectorManager getPianoTilesUIManager () { return pianoTilesUISelector.getPianoTilesUISelectorManager(); }

    /**
     * Method that gets the central panel of the main frame
     * @return JPanel storing the central panel
     */
    public JPanel getCentralPanel() {
        return mainFrame.getCentralPanel();
    }

    /**
     * Method that gets the panel from the music player
     * @return JPanel that stores the view of the music player
     */
    public JPanel getSpotiPanel() {
        return spotiPanel;
    }

    /**
     * Method that gets the controller of the music player
     * @return SpotiFrameManager class that stores the controller of the music player
     */
    public SpotiFrameManager getSpotiFrameManager() {
        return spotiFrame.spotiFrameManager();
    }
}