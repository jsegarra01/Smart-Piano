package Presentation.Ui_Views;

//import data from the different libraries
import Business.Entities.Playlist;
import Business.Entities.Song;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static Presentation.DictionaryPiano.*;

/**
 * PlaylistUI
 *
 * The "PlaylistUI" class will contain the different methods to create the view class card layout "PlaylistUI" and login interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class PlaylistUI extends JPanel {
    private final JPanel panel = new JPanel(new BorderLayout());
    private Playlist playlistGeneral;


    /**
     * Constructor for the PlaylistUI, you need to send the mainframe context and will create a card layout
     *
    // * @param mainFrame context necessary to create the card layout
     */
    public PlaylistUI() {
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the PlaylistUI
     */
    private void initialize() {
        this.add(panel);
        this.setBackground(Color.black);
    }

    /**
     * Creates the panel for the playlist with its basic configurations
     * @param namePlaylist The name of the playlist we want to create the panel for
     * @return The panel of the playlist created
     */
    private JPanel initGeneral(String namePlaylist){
        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(Color.black);
        JLabel playlistName = new JLabel(PLAYLIST_NAME);
        playlistName.setBackground(Color.black);
        playlistName.setOpaque(false);
        playlistName.repaint();
        playlistName.setForeground(Color.WHITE);
        playlistName.setFont(playlistName.getFont().deriveFont(playlistName.getFont().getSize() * 3.0F));
        playlistName.setText(namePlaylist);
        layout.add(playlistName, BorderLayout.LINE_START);
        layout.add(addSong(), BorderLayout.EAST);


        return layout;
    }

    /**
     * Method that sets all the song inside the playlist in the view
     * @param playlist Defines the playlist in which the song are stored
     */
    public void setSongsPlaylists(Playlist playlist){

        if(playlist.equals(playlistGeneral)) {
            updatePanel(playlist);
        }else {
            playlistGeneral = playlist;

            panel.removeAll();
            //panel.repaint();
            panel.setBackground(Color.black);
            panel.add(Box.createRigidArea(new Dimension(10, 50)));
            JPanel panelSongs = new JPanel();

            panel.add(initGeneral(playlist.getPlaylistName()), BorderLayout.NORTH);

            panelSongs.setBackground(Color.black);
            panelSongs.add(Box.createRigidArea(new Dimension(50, 10)));

            BoxLayout boxLayout = new BoxLayout(panelSongs, BoxLayout.Y_AXIS);
            panelSongs.setLayout(boxLayout);
            JPanel panel1;
            for (int i = 0; i < playlist.getSongs().size(); i++) {
                panel1 = setPlaylist(playlist.getSongs().get(i));
                panelSongs.add(panel1);
            }

            JScrollPane areaScrollPane = new JScrollPane(panelSongs);
            areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            areaScrollPane.setPreferredSize(new Dimension(860, 510));
            areaScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
            areaScrollPane.setBorder(BorderFactory.createEmptyBorder());
            panel.add(areaScrollPane, BorderLayout.CENTER);
        }
        panel.revalidate();
        panel.repaint();

    }

    /**
     * Method that creates a JPanel for the song
     * @param song Defines the song to appear in the JPanel
     * @return JPanel that stores the information about the song
     */
    private JPanel setPlaylist(Song song){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        JPanel panel1 = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel1, BoxLayout.X_AXIS);
        panel1.setLayout(boxLayout);
        JLabel label;

        panel1.setBorder(new LineBorder(Color.gray));
        panel1.setPreferredSize(new Dimension(840,50));
        panel1.setMaximumSize(panel1.getPreferredSize());
        panel1.setBackground(Color.black);

        label = new JLabel(song.getSongName());
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(400, 50));
        label.setMaximumSize(label.getPreferredSize());
        panel1.add(label);

        label = new JLabel(song.getAuthorName());
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(160,50));
        label.setMaximumSize(label.getPreferredSize());
        panel1.add(label);

        label = new JLabel((((int)(song.getDuration()/60))) + ":" + (Math.round(((song.getDuration())) -(int)(song.getDuration()/60))));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(60,50));
        label.setMaximumSize(label.getPreferredSize());
        panel1.add(label);

        label = new JLabel(sdf.format(song.getRecordingDate()));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(100,50));
        label.setMaximumSize(label.getPreferredSize());
        panel1.add(label);

        JButton button = new JButton(song.getSongName());
        button.setName(song.getSongName());
        button.setActionCommand(SONG_PLAYLIST);
        button.setText("Delete");
        button.addActionListener(spotiFrameManager);
        panel1.add(button);
        panel1.addMouseListener(spotiFrameManager);
        panel1.setName(song.getSongFile());
        return panel1;
    }

    /**
     * Creates a button to add a song to a playlist
     * @return The add button
     */
    private JButton addSong(){
        JButton add = new JButton(ADD_SONG);
        add.setActionCommand(ADD_SONG_COMM);
        add.addActionListener(spotiFrameManager);
        add.setText("+");
        return add;
    }
    /**
     * Method that updates the panel, checking the things that already existed
     * @param playlist Defines the playlist to be checked
     */
    private void updatePanel(Playlist playlist){
        for (Component jc : panel.getComponents()) {
            if (jc instanceof JScrollPane scrollPane) {
                for (Component jc2 : scrollPane.getComponents()) {
                    if (jc2 instanceof JViewport viewport) {
                        for (Component jc3 : viewport.getComponents()) {
                            if (jc3 instanceof JPanel panelSongs) {
                                for(int j = 0; j< panelSongs.getComponentCount();j++){
                                    if (panelSongs.getComponent(j) instanceof JPanel song) {
                                        int i = 0;
                                        boolean foundSong = false;
                                        while (i < playlist.getSongs().size() && !foundSong) {
                                            if(song.getName().equals(playlist.getSongs().get(i).getSongFile())){
                                                foundSong = true;
                                            }else{
                                                i++;
                                            }
                                        }
                                        if(!foundSong){
                                            panelSongs.remove(j);
                                        }
                                    }
                                }
                                int i;
                                boolean foundSong;
                                for(int k = 0; k< playlist.getSongs().size();k++){
                                    i=0;
                                    foundSong = false;
                                    while (i < panelSongs.getComponentCount() && !foundSong){
                                        if (panelSongs.getComponent(i) instanceof JPanel song) {
                                            if(song.getName().equals(playlist.getSongs().get(k).getSongFile())){
                                                foundSong = true;
                                            }else{
                                                i++;
                                            }
                                        }else{
                                            i++;
                                        }
                                    }
                                    if(!foundSong){
                                        panelSongs.add(setPlaylist(playlist.getSongs().get(k)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}