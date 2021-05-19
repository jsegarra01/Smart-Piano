package Presentation.Ui_Views;

import Business.BusinessFacadeImp;
import Business.Entities.Playlist;
import Business.Entities.Song;
import Business.PlaylistManager;
import Presentation.DictionaryPiano;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static Presentation.DictionaryPiano.*;
import static Presentation.Ui_Views.StatisticsUI.setNumMin;
import static Presentation.Ui_Views.StatisticsUI.setNumSongs;
import static Presentation.Ui_Views.Tile.resizeIcon;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.WEST;


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
    private final StatisticsUI statisticsUI;
    private final PlaylistUI playlistUI;
    private final TopSongsUI topSongsUI;
    private final SongsUI songsUI;

    public static JPanel spotiPanel = new JPanel(new CardLayout());
    private static final JButton createPlaylist = new JButton(CREATE_PLAYLIST);
    private static final JButton showStadistics = new JButton(CREATE_STADISTICS);
    private static final JButton topSongs = new JButton(SHOW_TOP_SONGS);
    public static final JButton songsList = new JButton(SHOW_ALL_SONGS);
    public static JLabel playlistLabel = new JLabel(PLAYLIST_LABEL);
    public static JButton shuffleButton = new JButton();
    public static JButton backButton = new JButton();
    public static JButton playButton = new JButton();
    public static JButton nextButton = new JButton();
    public static JButton loopButton = new JButton();

    //public static JButton pauseButton = new JButton();


    public static JPanel leftList = new JPanel();
    private static JPanel playlistsPanel = new JPanel();
    private static JScrollPane scroll = new JScrollPane();

    private static final JTextField songNameInputText = new JTextField();
    public static JButton searchButton = new JButton();
    /**
     * Constructor for the SpotiUI, you need to send the mainframe context and will create a card layout
     */
    public SpotiUI() {
        playlistUI = new PlaylistUI();
        statisticsUI = new StatisticsUI();
        topSongsUI = new TopSongsUI();
        songsUI = new SongsUI();

        initialize();
    }

    /**
     * The initialize function that creates the card layout for the SpotiUI
     */
    private void initialize() {
        setLayout(new BorderLayout());

        spotiPanel.add(statisticsUI, STATISTICS_UI);
        spotiPanel.add(topSongsUI, /*TOPSONGS_UI*/SONGS_UI);
        spotiPanel.add(playlistUI, PLAYLIST_UI);
        spotiPanel.add(songsUI, SONGS_UI);
        add(spotiPanel, BorderLayout.CENTER);

        //left buttons
        //JPanel leftList = new JPanel();
        leftList.setLayout(new BoxLayout(leftList, BoxLayout.Y_AXIS));

        //leftList.add(Box.createRigidArea(new Dimension(210, 50)));


        songNameInputText.setAlignmentX(0.5f);
        songNameInputText.setPreferredSize(new Dimension(200,30));
        songNameInputText.setMaximumSize(songNameInputText.getPreferredSize());
        songNameInputText.setBackground(Color.WHITE);
//This two guys should be inside their own damn JPanel with BorderLayout.WEST/CENTER, not going to bother for now, functionality comes first!
        searchButton.setIcon(new ImageIcon("Files/drawable/playbuttonWhite.png"));
        searchButton.setIcon(resizeIcon((ImageIcon) searchButton.getIcon(), (int) Math.round(searchButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(searchButton.getIcon().getIconHeight()*0.05)));
        confButtonsBar(searchButton, 0, 60);
        searchButton.setActionCommand(SEARCH_SONG);
/*

        showStadistics.setPreferredSize(new Dimension(200,30));
        showStadistics.setMaximumSize(songNameInputText.getPreferredSize());

        topSongs.setPreferredSize(new Dimension(200,30));
        topSongs.setMaximumSize(songNameInputText.getPreferredSize());

        songsList.setPreferredSize(new Dimension(200,30));
        songsList.setMaximumSize(songNameInputText.getPreferredSize());

        playlistLabel.setPreferredSize(new Dimension(200,30));
        playlistLabel.setMaximumSize(songNameInputText.getPreferredSize());
*/
        showStadistics.setActionCommand(CREATE_STADISTICS);

        //showStadistics.setBorder(new EmptyBorder(10,0,10,0));
        //showStadistics.setAlignmentX(Component.LEFT_ALIGNMENT);

        //showStadistics.setBackground(Color.getHSBColor(0,0,0.8f));
        //showStadistics.setBackground((Color.green));
        //showStadistics.setForeground(Color.black);

        topSongs.setActionCommand(SHOW_TOP_SONGS);
        //topSongs.setBorder(new EmptyBorder(10,0,10,100));
        //showStadistics.setAlignmentX(Component.RIGHT_ALIGNMENT);
        //topSongs.setBorder(new EmptyBorder(10,0,10,140));
        //topSongs.setBackground(Color.getHSBColor(0,0,0.8f));
        //topSongs.setForeground(Color.black);

        songsList.setActionCommand(SHOW_ALL_SONGS);
        //songsList.setBorder(new EmptyBorder(10,0,10,0));
        //songsList.setAlignmentX(Component.RIGHT_ALIGNMENT);
        //songsList.setBorder(new EmptyBorder(10,0,10,140));
        //songsList.setBackground(Color.getHSBColor(0,0,0.8f));
        //songsList.setForeground(Color.black);
        //confButtonLeft(songsList, 18, 115);
        //songsList.setAlignmentX(Component.LEFT_ALIGNMENT);


        //playlistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistLabel.setBorder(new EmptyBorder(10,20,10,0));
        playlistLabel.setBackground(Color.getHSBColor(0,0,0.8f));
        playlistLabel.setForeground(Color.white);

        createPlaylist.setActionCommand(CREATE_PLAYLIST);
        /*confButtonLeft(createPlaylist, 12, 90);
        createPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);*/
        JSeparator separator = new JSeparator();

        //JScrollPane scroll = new JScrollPane();
        registerController(new SpotiFrameManager());
        leftList.add(Box.createRigidArea(new Dimension(210, 10)));
        leftList.setPreferredSize(new Dimension(200, 200));
        leftList.setMaximumSize(getPreferredSize());

        JPanel panelButtonsSpoti = new JPanel(new GridLayout(5,0));
        //BoxLayout boxLayout = new BoxLayout(panelButtonsSpoti, BoxLayout.Y_AXIS);
        //panelButtonsSpoti.setLayout(boxLayout);
        //BoxLayout boxLayout = new BoxLayout(panelButtonsSpoti, BoxLayout.Y_AXIS);
        //panelButtonsSpoti.setLayout(new BoxLayout(panelButtonsSpoti, BoxLayout.Y_AXIS));
        /*JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.add(songNameInputText);
        panelSearch.add(searchButton,BorderLayout.EAST);
        panelButtonsSpoti.add(panelSearch);*/
        //panelButtonsSpoti.setAlignmentX(LEFT_ALIGNMENT);
        //panelButtonsSpoti.add(showStadistics);
        //panelButtonsSpoti.add(songsList);
        //panelButtonsSpoti.add(topSongs);
        panelButtonsSpoti.add(setButton(showStadistics));
        panelButtonsSpoti.add(setButton(songsList));
        panelButtonsSpoti.add(setButton(topSongs));
        panelButtonsSpoti.add(playlistLabel);
        panelButtonsSpoti.add(setButton(createPlaylist));
        //panelButtonsSpoti.setBackground((Color.red));
        panelButtonsSpoti.setBorder(BorderFactory.createEmptyBorder());
        panelButtonsSpoti.setBackground((Color.getHSBColor(10,0,0.2f)));
        //panelButtonsSpoti.setPreferredSize(new Dimension(170, 250));
        //panelButtonsSpoti.setMaximumSize(panelButtonsSpoti.getPreferredSize());
        //panelButtonsSpoti.setAlignmentX(Component.LEFT_ALIGNMENT);
        /*leftList.add(songNameInputText);
        leftList.add(searchButton);
        leftList.add(showStadistics);
        leftList.add(songsList);
        leftList.add(topSongs);
        leftList.add(playlistLabel);*/
        leftList.add(panelButtonsSpoti);
        //leftList.add(createPlaylist);
        leftList.add(separator);
        leftList.add(scroll);

        leftList.setBackground(Color.getHSBColor(10,0,0.2f));
        add(leftList, BorderLayout.WEST);

        //Bottom panel
        shuffleButton = createConfButtons("Files/drawable/shuffleWhite.png", 10, 60, SHUFFLE_BUTTON);
        backButton    = createConfButtons("Files/drawable/backwardtrackWhite.png", 0, 60, LAST_BUTTON);
        nextButton    = createConfButtons("Files/drawable/fordwardtrackWhite.png", 0, 60, NEXT_BUTTON);
        loopButton    = createConfButtons("Files/drawable/exchangeWhite.png", 0, 10, LOOP_BUTTON);

        playButton.setIcon(new ImageIcon("Files/drawable/playbuttonWhite.png"));
        playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
        confButtonsBar(playButton, 0, 60);
        playButton.setActionCommand(PLAY_BUTTON);



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


    private JButton createConfButtons(String imagePath, int index1, int index2, String label){
        JButton toReturnButton;
        toReturnButton = new JButton();
        toReturnButton.setIcon(new ImageIcon(imagePath));
        toReturnButton.setIcon(resizeIcon((ImageIcon) toReturnButton.getIcon(), (int) Math.round(toReturnButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(toReturnButton.getIcon().getIconHeight()*0.05)));
        confButtonsBar(toReturnButton, index1, index2);
        toReturnButton.setActionCommand(label);
        return toReturnButton;
    }

    private static void confButtonLeft(JButton button, int left, int right){
        //button.setAlignmentX(0.5f);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        //button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setBorder(new EmptyBorder(10, 5, 10, 0));
        //button.setBorder(new EmptyBorder(TB_SIZE,left,TB_SIZE,right));
        button.setBackground(Color.getHSBColor(0,0,0.2f));
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
        songsList.addActionListener(listener);

        shuffleButton.addActionListener(listener);
        backButton.addActionListener(listener);
        playButton.addActionListener(listener);
        nextButton.addActionListener(listener);
        loopButton.addActionListener(listener);

        searchButton.addActionListener(listener);
    }

    public static String getInputedSongName() {
        return songNameInputText.getText();
    }

    /*public static void addSongsAll(ArrayList<Song> songs){
        spotiPanel.add(new SongsUI(songs, "Delete"), SONGS_UI);
    }
    public static void addSongsToPlaylist(ArrayList<Song> songs){
        spotiPanel.add(new SongsUI(songs, "Add"), SONGS_UI);
    }
*/

    //TODO poner bn las boxes (size lateral)
    public static void addPlaylists(ArrayList<Playlist> playlists){
        playlistsPanel.removeAll();
        leftList.remove(scroll);
        BoxLayout boxLayout = new BoxLayout(playlistsPanel, BoxLayout.Y_AXIS);
        playlistsPanel.setLayout(boxLayout);
        playlistsPanel.setBackground(Color.getHSBColor(10,0,0.2f));
        if(playlists!=null && !playlists.isEmpty()){
            for (Playlist playlist : playlists) {
                JButton buttonAux = new JButton(playlist.getPlaylistName());
                buttonAux.setName(playlist.getPlaylistName());
                buttonAux.setActionCommand(DictionaryPiano.PLAYLIST_INFO);
                buttonAux.setAlignmentX(0.5f);
                buttonAux.setBorder(new EmptyBorder(10, 5, 10, 0));
                buttonAux.setBackground(Color.getHSBColor(10,0,0.2f));
                buttonAux.setForeground(Color.white);
                playlistsPanel.add(buttonAux);
                buttonAux.setAlignmentX(Component.LEFT_ALIGNMENT);
                buttonAux.addActionListener(new SpotiFrameManager());
            }
            scroll = new JScrollPane(playlistsPanel);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            //scroll.setBackground(Color.getHSBColor(0,0,0.8f));
            scroll.setOpaque(false);
            scroll.setPreferredSize(new Dimension(70, 250));
            scroll.setBorder(BorderFactory.createEmptyBorder());
            leftList.add(scroll);

            //   leftList.add(scroll);
        }
    }

    private JButton setButton(JButton button){
        //Button.setActionCommand(DictionaryPiano.PLAYLIST_INFO);
        //button.setAlignmentX(0.5f);
        //button.setAlignmentX(0.5f);
        //button.setBorder(new EmptyBorder(10, 5, 10, 0));
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        setOpaque(false);
        button.setBackground(Color.getHSBColor(10,0,0.2f));
        button.setForeground(Color.white);
        //button.setAlignmentX(LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        //playlistsPanel.add(buttonAux);
        return button;

    }
}