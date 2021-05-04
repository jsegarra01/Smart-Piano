package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Presentation.DictionaryPiano.*;
import static Presentation.Ui_Views.Tile.resizeIcon;


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
    TopSongsUI topSongsUI;

    public static JPanel spotiPanel = new JPanel(new CardLayout());
    public static JButton homeButton = new JButton(HOME_BUTTON);
    public static JButton createPlaylist = new JButton(CREATE_PLAYLIST);
    public static JButton showStadistics = new JButton(CREATE_STADISTICS);
    public static JButton topSongs = new JButton(SHOW_TOP_SONGS);
    public static JLabel playlistLabel = new JLabel(PLAYLIST_LABEL);
    public static JButton shuffleButton = new JButton();
    public static JButton backButton = new JButton();
    public static JButton playButton = new JButton();
    public static JButton nextButton = new JButton();
    public static JButton loopButton = new JButton();
    /**
     * Constructor for the SpotiUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public SpotiUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        statisticsUI = new StatisticsUI(mainFrame);
        playlistUI = new PlaylistUI(mainFrame);
        topSongsUI = new TopSongsUI(mainFrame);

        initialize();
    }

    /**
     * The initialize function that creates the card layout for the SpotiUI
     */
    private void initialize() {
        setLayout(new BorderLayout());

        spotiPanel.add(statisticsUI, STATISTICS_UI);
        spotiPanel.add(topSongsUI, TOPSONGS_UI);
        spotiPanel.add(playlistUI, PLAYLIST_UI);
        add(spotiPanel, BorderLayout.CENTER);

        //left buttons
        JPanel leftList = new JPanel();
        leftList.setLayout(new BoxLayout(leftList, BoxLayout.Y_AXIS));

        leftList.add(Box.createRigidArea(new Dimension(210, 50)));


        showStadistics.setActionCommand(CREATE_STADISTICS);
        confButtonLeft(showStadistics, 0, 115);
        /*showStadistics.setAlignmentX(0.5f);
        showStadistics.setBorder(new EmptyBorder(10,0,10,115));
        showStadistics.setBackground(Color.getHSBColor(0,0,0.8f));
        showStadistics.setForeground(Color.white);*/

        topSongs.setActionCommand(SHOW_TOP_SONGS);
        confButtonLeft(topSongs, 18, 115);
        /*topSongs.setAlignmentX(0.5f);
        topSongs.setBorder(new EmptyBorder(10,18,10,115));
        topSongs.setBackground(Color.getHSBColor(0,0,0.8f));
        topSongs.setForeground(Color.white);*/

        playlistLabel.setAlignmentX(0.5f);
        playlistLabel.setBorder(new EmptyBorder(10,0,10,140));
        playlistLabel.setBackground(Color.getHSBColor(0,0,0.8f));
        playlistLabel.setForeground(Color.white);

        createPlaylist.setActionCommand(CREATE_PLAYLIST);
        confButtonLeft(createPlaylist, 12, 90);
        /*createPlaylist.setAlignmentX(0.5f);
        createPlaylist.setBorder(new EmptyBorder(10,12,10,90));
        createPlaylist.setBackground(Color.getHSBColor(0,0,0.8f));
        createPlaylist.setForeground(Color.white);*/
        JSeparator separator = new JSeparator();

        registerController(new SpotiFrameManager());

        leftList.add(showStadistics);
        leftList.add(Box.createRigidArea(new Dimension(210, 10)));
        leftList.add(topSongs);
        leftList.add(playlistLabel);
        leftList.add(createPlaylist);
        leftList.add(separator);
        leftList.setBackground(Color.getHSBColor(10,0,0.2f));
        add(leftList, BorderLayout.WEST);

        //Bottom panel
        shuffleButton.setIcon(new ImageIcon("Files/drawable/shuffleWhite.png"));
        shuffleButton.setIcon(resizeIcon((ImageIcon) shuffleButton.getIcon(), (int) Math.round(shuffleButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(shuffleButton.getIcon().getIconHeight()*0.05)));
        confButtonsBar(shuffleButton, 10, 60);

        backButton.setIcon(new ImageIcon("Files/drawable/backwardtrackWhite.png"));
        backButton.setIcon(resizeIcon((ImageIcon) backButton.getIcon(), (int) Math.round(backButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(backButton.getIcon().getIconHeight()*0.05)));
        confButtonsBar(backButton, 0, 60);

        playButton.setIcon(new ImageIcon("Files/drawable/playbuttonWhite.png"));
        playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
        confButtonsBar(playButton, 0, 60);

        nextButton.setIcon(new ImageIcon("Files/drawable/fordwardtrackWhite.png"));
        nextButton.setIcon(resizeIcon((ImageIcon) nextButton.getIcon(), (int) Math.round(nextButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(nextButton.getIcon().getIconHeight()*0.05)));
        confButtonsBar(nextButton, 0, 60);

        loopButton.setIcon(new ImageIcon("Files/drawable/exchangeWhite.png"));
        loopButton.setIcon(resizeIcon((ImageIcon) loopButton.getIcon(), (int) Math.round(loopButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(loopButton.getIcon().getIconHeight()*0.05)));
        confButtonsBar(loopButton, 0, 10);



        JPanel musicPlayer = new JPanel();

        musicPlayer.setLayout(new GridBagLayout());
        musicPlayer.setPreferredSize(new Dimension(10,90));
        musicPlayer.setBackground(Color.getHSBColor(10,0,0.3f));
        musicPlayer.add(shuffleButton);
        musicPlayer.add(backButton);
        musicPlayer.add(playButton);
        musicPlayer.add(nextButton);
        musicPlayer.add(loopButton);
        add(musicPlayer, BorderLayout.SOUTH);
    }


    private void confButtonLeft(JButton button, int left, int right){
        button.setAlignmentX(0.5f);
        button.setBorder(new EmptyBorder(TB_SIZE,left,TB_SIZE,right));
        button.setBackground(Color.getHSBColor(0,0,0.8f));
        button.setForeground(Color.white);
    }

    private void confButtonsBar(JButton button, int left, int right){
        button.add(Box.createRigidArea(new Dimension(button.getIcon().getIconWidth(), button.getIcon().getIconHeight())));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(new EmptyBorder(TB_SIZE,left,TB_SIZE,right));
    }

    private void registerController(SpotiFrameManager listener) {
        showStadistics.addActionListener(listener);
        topSongs.addActionListener(listener);
        createPlaylist.addActionListener(listener);

        shuffleButton.addActionListener(listener);
        backButton.addActionListener(listener);
        playButton.addActionListener(listener);
        nextButton.addActionListener(listener);
        loopButton.addActionListener(listener);
    }
}