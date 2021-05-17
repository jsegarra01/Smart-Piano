package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.Entities.*;
import Business.BusinessFacadeImp;
import Business.Entities.MidiHelper;
import Business.Entities.webHandler;
import Presentation.Ui_Views.PlaylistUI;
import Presentation.Ui_Views.SongsUI;
import Presentation.Ui_Views.SpotiUI;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.io.File;
import java.util.logging.Handler;

import static Presentation.DictionaryPiano.*;
import static Presentation.Ui_Views.SpotiUI.*;
import static Presentation.Ui_Views.Tile.resizeIcon;


/**
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class SpotiFrameManager extends AbstractAction implements ActionListener, MouseListener {

    private static final String URLRoute = "https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano";
    private static final String path = "Files";
    private boolean play=false;
    private static final ImageIcon playIcon = new ImageIcon("Files/drawable/playbuttonWhite.png");
    private static final ImageIcon pauseIcon = new ImageIcon("Files/drawable/pauseWhite.png");
    private float minPlayed;
    private long startMin=0;
    private long lastMin=0;
    private Stadistics stadistics;
    private static boolean addSong = false;

    private final Date date = new Date();

    private MidiHelper finalMidiHelper;

    {
        try {
            finalMidiHelper = new MidiHelper();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    webHandler myWebHandlingTool = new webHandler(path, URLRoute, "result%s.txt", "?startat=%s&");

    /**
     * Parametrized constructor
     */
    public SpotiFrameManager() {
    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        CardLayout cc = (CardLayout) (spotiPanel.getLayout());
        Object obj = e.getSource();
        switch (e.getActionCommand()) {
            case SHOW_ALL_SONGS:
                addSongsAll(new BusinessFacadeImp().getSongManager().getSongs());
                cc.show(spotiPanel, SONGS_UI);
                break;
            case CREATE_STADISTICS:
                addStadistics(getNumSongs(), getMinPlayed());
                cc.show(spotiPanel, STATISTICS_UI);
                break;
            case SHOW_TOP_SONGS:
                cc.show(spotiPanel, TOPSONGS_UI);
                break;
            case CREATE_PLAYLIST:
                cc.show(spotiPanel, PLAYLIST_UI);
                break;
            case SEARCH_SONG:
                this.myWebHandlingTool.doStuff(SpotiUI.getInputedSongName(), "by");
                cc.show(spotiPanel, PLAYLIST_UI);
                break;
            case PLAY_BUTTON:
                if(!play){
                    playButton.setIcon(pauseIcon);
                    playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                            (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
                    startMin = System.currentTimeMillis();
                    finalMidiHelper.playSong(new File(new BusinessFacadeImp().getPlaylistManager().getPlaylists().get(0).getSongs().get(0).getSongFile()));
                    play = true;
                }
                else{
                    playButton.setIcon(playIcon);
                    playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                            (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
                    lastMin = System.currentTimeMillis();
                    // String lastSong =
                    minPlayed = (float)(lastMin - startMin)/60000;
                    Stadistics stats = new Stadistics(date.getHours(), (float)1, minPlayed);
                    new BusinessFacadeImp().getSongManager().addingStadistics(stats);
                    play = false;
                    finalMidiHelper.stopSong();
                }
                break;
            case PLAYLIST_INFO:
                JButton button;
                if (obj instanceof JButton) {
                    button = (JButton) obj;
                    PlaylistUI.setSongsPlaylists(new BusinessFacadeImp().getPlaylist(button.getName()));
                    cc.show(spotiPanel, PLAYLIST_UI);
                }
                break;
            case SONG_PLAYLIST:
                JButton song;
                if (obj instanceof JButton) {
                    song = (JButton) obj;
                    PlaylistUI.deleteFromPanel(song.getName());
                    boolean errorDeleting = new BusinessFacadeImp().deleteSongFromPlaylist(
                            PlaylistUI.getPlaylist().getPlaylistName(),song.getName());
                    PlaylistUI.setSongsPlaylists(PlaylistUI.getPlaylist());
                }
                break;
            case ADD_SONG_COMM:
                addSongsToPlaylist(new BusinessFacadeImp().getSongManager().getSongs());
                cc.show(spotiPanel, SONGS_UI);
                addSong = true;
                break;
            default:
                if(obj instanceof JTable){

                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.parseInt( e.getActionCommand() );
                    if(addSong){
                        boolean updatingSong = new BusinessFacadeImp().addSongToPlaylist(
                                PlaylistUI.getPlaylist().getPlaylistName(),
                                new BusinessFacadeImp().getSong(modelRow).getSongName());
                        PlaylistUI.setSongsPlaylists(PlaylistUI.getPlaylist());
                        cc.show(spotiPanel, PLAYLIST_UI);
                        addSong = false;
                    }else{
                        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                        //TODO Delete from db
                    }
                    break;
                }

        }
    }
    public static void addPlaylists(ArrayList<Playlist> playlists){
        SpotiUI.addPlaylists(playlists);
    }
/*
    public static void addFirstStadistics(List<Integer> numSongs){
        SpotiUI.addStadistics(numSongs);

    }*/

    public static LinkedList<Float> getNumSongs(){
        LinkedList<Float> numSongs = new LinkedList<>();
        for(int i=0; i<24; i++ ){
            if(new BusinessFacadeImp().getSongManager().gettingStadistics(i) == null){
                numSongs.add((float) 0);
            } else {
                numSongs.add(new BusinessFacadeImp().getSongManager().gettingStadistics(i).getNumPlayed());
            }
        }
        return numSongs;
    }

    public LinkedList<Float> getMinPlayed(){
        LinkedList<Float> numMin = new LinkedList<>();
        for(int i=0; i<24; i++ ){
            if(new BusinessFacadeImp().getSongManager().gettingStadistics(i) == null){
                numMin.add((float)0);
            } else {
                numMin.add(new BusinessFacadeImp().getSongManager().gettingStadistics(i).getMinPlayed());
            }
        }
        return numMin;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel song;
        JTable table;
        Object obj = e.getSource();
        int button = e.getButton();
        if (obj instanceof JPanel) {
            if(play){
                finalMidiHelper.stopSong();
            }
            song = (JPanel) obj;
            playButton.setIcon(pauseIcon);
            playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                    (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
            play = true;
            finalMidiHelper.playSong(new File(song.getName()));
        }else if(obj instanceof  JTable){
            table = (JTable) obj;
            if(table.getEditorComponent() == null){
                //TODO PLAY MUSIC
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
