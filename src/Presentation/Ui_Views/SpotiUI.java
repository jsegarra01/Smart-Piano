package Presentation.Ui_Views;

import Business.BusinessFacadeImp;
import Business.Entities.Playlist;
import Business.PlaylistManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

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
    private StatisticsUI statisticsUI;
    private PlaylistUI playlistUI;
    private TopSongsUI topSongsUI;

    private SpotiFrameManager spotiFrame;

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
    //public static JButton pauseButton = new JButton();


    public static JPanel leftList = new JPanel();


    private static JTextField songNameInputText = new JTextField();
    public static JButton searchButton = new JButton();
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
        //JPanel leftList = new JPanel();
        leftList.setLayout(new BoxLayout(leftList, BoxLayout.Y_AXIS));

        leftList.add(Box.createRigidArea(new Dimension(210, 50)));


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



        showStadistics.setActionCommand(CREATE_STADISTICS);
        confButtonLeft(showStadistics, 0, 115);

        topSongs.setActionCommand(SHOW_TOP_SONGS);
        confButtonLeft(topSongs, 18, 115);

        playlistLabel.setAlignmentX(0.5f);
        playlistLabel.setBorder(new EmptyBorder(10,0,10,140));
        playlistLabel.setBackground(Color.getHSBColor(0,0,0.8f));
        playlistLabel.setForeground(Color.white);

        createPlaylist.setActionCommand(CREATE_PLAYLIST);
        confButtonLeft(createPlaylist, 12, 90);

        JSeparator separator = new JSeparator();

        //JScrollPane scroll = new JScrollPane();
        registerController(new SpotiFrameManager());
        leftList.add(songNameInputText);
        leftList.add(searchButton);
        leftList.add(showStadistics);
        leftList.add(Box.createRigidArea(new Dimension(210, 10)));
        leftList.add(topSongs);
        leftList.add(playlistLabel);
        leftList.add(createPlaylist);
        leftList.add(separator);

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
        playButton .setActionCommand(PLAY_BUTTON);



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

        searchButton.addActionListener(listener);
    }


    public static String getInputedSongName() {
        return songNameInputText.getText();
    }

    //TODO poner bn las boxes (size lateral)
    public static void addPlaylists(ArrayList<Playlist> playlists){
        Playlist myPlaylist;
        String aux;
        if(!playlists.isEmpty()){
            for(int i=0; i<playlists.size(); i++){
                myPlaylist = playlists.get(i);
                JButton buttonAux = new JButton(playlists.get(i).getPlaylistName());
                buttonAux.setName(playlists.get(i).getPlaylistName());
                //buttonAux.setActionCommand(PLAYLIST_LIST);
                buttonAux.setAlignmentX(0.5f);
                if(playlists.size() - i == 1){
                    buttonAux.setBorder(new EmptyBorder(10,0, spotiPanel.getHeight(),0));
                }else{
                    buttonAux.setBorder(new EmptyBorder(10,18,10,115));
                    System.out.println("leftlist: " + leftList.getWidth() + " button: " + buttonAux.getWidth() + "\n");
                }
                buttonAux.setBackground(Color.getHSBColor(0,0,0.8f));
                buttonAux.setForeground(Color.white);
                leftList.add(buttonAux);
                buttonAux.addActionListener(new SpotiFrameManager());
            }
            //JScrollPane scroll = new JScrollPane(leftList);

         //   leftList.add(scroll);
        }
    }

}