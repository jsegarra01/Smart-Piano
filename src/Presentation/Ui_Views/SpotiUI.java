package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Presentation.DictionaryPiano.*;


//import static Presentation.DictionaryPiano.*;

/**
 * SpotiUI
 *
 * The "SpotiUI" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class SpotiUI extends JPanel {
    private MainFrame mainFrame;
    StatisticsUI statisticsUI;
    PlaylistUI playlistUI;

    public static JPanel spotiPanel = new JPanel(new CardLayout());
    public static JButton homeButton = new JButton(HOME_BUTTON);
    public static JButton createPlaylist = new JButton(CREATE_PLAYLIST);
    /**
     * Constructor for the SpotiUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public SpotiUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        statisticsUI = new StatisticsUI(mainFrame);
        playlistUI = new PlaylistUI(mainFrame);
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the SpotiUI
     */
    private void initialize() {
        setLayout(new BorderLayout());

        spotiPanel.add(statisticsUI, STATISTICS_UI);
        spotiPanel.add(playlistUI, PLAYLIST_UI);
        add(spotiPanel, BorderLayout.CENTER);

        //left buttons
        JPanel leftList = new JPanel();
        leftList.setLayout(new BoxLayout(leftList, BoxLayout.Y_AXIS));

        leftList.add(Box.createRigidArea(new Dimension(210, 50)));

        homeButton.setActionCommand(HOME_BUTTON);
        homeButton.setAlignmentX(0.5f);
        homeButton.setBorder(new EmptyBorder(10,85,10,85));
        homeButton.setBackground(Color.getHSBColor(0,0,0.8f));

        createPlaylist.setActionCommand(CREATE_PLAYLIST);
        createPlaylist.setAlignmentX(0.5f);
        createPlaylist.setBorder(new EmptyBorder(10,55,10,55));
        createPlaylist.setBackground(Color.getHSBColor(0,0,0.8f));

        registerController(new SpotiFrameManager());

        leftList.add(homeButton);
        leftList.add(Box.createRigidArea(new Dimension(210, 10)));
        leftList.add(createPlaylist);
        leftList.setBackground(Color.getHSBColor(10,0,0.2f));
        add(leftList, BorderLayout.WEST);

        //Bottom panel
        JButton playButton = new JButton();
        playButton.add(Box.createRigidArea(new Dimension(10, 90)));

        JPanel musicPlayer = new JPanel();
        musicPlayer.setBackground(Color.getHSBColor(10,0,0.3f));
        musicPlayer.add(playButton);
        add(musicPlayer, BorderLayout.SOUTH);
    }

    private void registerController(SpotiFrameManager listener) {
        homeButton.addActionListener(listener);
        createPlaylist.addActionListener(listener);
    }
}