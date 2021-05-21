package Presentation.Ui_Views;

//import data from the different libraries
import Business.Entities.Playlist;
import Business.Entities.Song;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static Presentation.DictionaryPiano.*;

/**
 * PlaylistUI
 *
 * The "PlaylistUI" class will contain the different methods to create the view class card layout "PlaylistUI" and login interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class PlaylistUI extends JPanel {
    private static JPanel panel = new JPanel(new BorderLayout());
    private static Playlist playlistGeneral;
    private static DecimalFormat df = new DecimalFormat("###.##");



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
    //private static void registerController(PlaylistUIManager listener) {
      //  new ButtonColumn(table, listener, 2);

    //}

    /**
     * Creates the panel for the playlist with its basic configurations
     * @param namePlaylist The name of the playlist we want to create the panel for
     * @return The panel of the playlist created
     */
    private static JPanel initGeneral(String namePlaylist){
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
     * TODO: ALEX EXPLICA QUE CONY ES AIXO JAJAJA
     * @param playlist
     */
    public static void setSongsPlaylists(Playlist playlist){

        if(playlist.equals(playlistGeneral)) {
            for (Component jc : panel.getComponents()) {
                if (jc instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) jc;
                    for (Component jc2 : scrollPane.getComponents()) {
                        if (jc2 instanceof JViewport) {
                            JViewport viewport = (JViewport) jc2;
                            for (Component jc3 : viewport.getComponents()) {
                                if (jc3 instanceof JPanel) {
                                    JPanel panelSongs = (JPanel) jc3;
                                    for(int j = 0; j< panelSongs.getComponentCount();j++){

                                        if (panelSongs.getComponent(j) instanceof JPanel) {
                                            JPanel song = (JPanel) panelSongs.getComponent(j) ;
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
                                                foundSong = false;
                                            }
                                        }
                                    }
                                    int i;
                                    boolean foundSong;
                                    for(int k = 0; k< playlist.getSongs().size();k++){
                                        i=0;
                                        foundSong = false;
                                        while (i < panelSongs.getComponentCount() && !foundSong){
                                            if (panelSongs.getComponent(i) instanceof JPanel) {
                                                JPanel song = (JPanel) panelSongs.getComponent(i) ;
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
                                            panelSongs.add(setPlaylist(playlist, k));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
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
            //panelSongs.setPreferredSize(new Dimension(5,400));

            BoxLayout boxLayout = new BoxLayout(panelSongs, BoxLayout.Y_AXIS);
            panelSongs.setLayout(boxLayout);
            JPanel panel1;
            for (int i = 0; i < playlist.getSongs().size(); i++) {
                panel1 = setPlaylist(playlist, i);
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
     * TODO: TAMPOC ACABO D'ENTENDRE QUE FA AQUESTA
     * @param playlist
     * @param i
     * @return
     */
    private static JPanel setPlaylist(Playlist playlist, int i){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        JPanel panel1 = new JPanel(new GridLayout());
        JLabel label;

        panel1.setBorder(new LineBorder(Color.gray));
        panel1.setPreferredSize(new Dimension(840,50));
        panel1.setMaximumSize(panel1.getPreferredSize());
        panel1.setBackground(Color.black);

        //JPanel panelSong = new JPanel();
        //panel1.add(Box.createHorizontalStrut(20));
        label = new JLabel(playlist.getSongs().get(i).getSongName());
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(200, 10));
        label.setMaximumSize(label.getPreferredSize());
        label.setMinimumSize(new Dimension(180,10));
        //label.add(Box.createRigidArea(new Dimension(450,50)));
        //label.setMinimumSize(new Dimension(350, 50));
        panel1.add(label);

        label = new JLabel(playlist.getSongs().get(i).getAuthorName());
        label.setForeground(Color.WHITE);
        label.add(Box.createRigidArea(new Dimension(160,50)));
        label.add(Box.createHorizontalStrut(60));
        panel1.add(label);
        //panel1.add(Box.createHorizontalStrut(40));

        label = new JLabel(String.valueOf(df.format(playlist.getSongs().get(i).getDuration())));
        label.setForeground(Color.WHITE);
        label.add(Box.createRigidArea(new Dimension(50,50)));
        panel1.add(label);
        //panel1.add(Box.createHorizontalStrut(40));

        label = new JLabel(sdf.format(playlist.getSongs().get(i).getRecordingDate()));
        label.setForeground(Color.WHITE);
        label.add(Box.createRigidArea(new Dimension(200,50)));
        panel1.add(label);

        JButton button = new JButton(playlist.getSongs().get(i).getSongName());
        button.setName(playlist.getSongs().get(i).getSongName());
        button.setActionCommand(SONG_PLAYLIST);
        button.setText("Delete");
        button.addActionListener(new SpotiFrameManager());
        panel1.add(Box.createHorizontalStrut(40));
        panel1.add(button);
        panel1.addMouseListener(new SpotiFrameManager());
        panel1.setName(playlist.getSongs().get(i).getSongFile());
        return panel1;
    }

    /**
     * Creates a button to add a song to a playlist
     * @return The add button
     */
    private static JButton addSong(){
        JButton add = new JButton(ADD_SONG);
        add.setActionCommand(ADD_SONG_COMM);
        add.addActionListener(new SpotiFrameManager());
        add.setText("+");
        return add;
    }
}