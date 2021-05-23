package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.BusinessFacade;
import Business.Entities.*;
import Business.BusinessFacadeImp;
import Business.Entities.MidiHelper;
import Business.UserManager;
import Presentation.Dictionary_login;
import Presentation.Ui_Views.PlaylistUI;
import Presentation.Ui_Views.SongsUI;
import Presentation.Ui_Views.SpotiUI;

import javax.sound.midi.MetaEventListener;
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

import static Presentation.DictionaryPiano.*;
import static Presentation.Dictionary_login.PROFILE_UI;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.SpotiUI.*;
import static Presentation.Ui_Views.StatisticsUI.*;
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

    private BusinessFacadeImp myFacade;

    public static final String URLRoute = "https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano";
    private static final String path = "Files/WebScrappingResults";
    private static boolean play=false;
    private static final ImageIcon playIcon = new ImageIcon("Files/drawable/playbuttonWhite.png");
    private static final ImageIcon pauseIcon = new ImageIcon("Files/drawable/pauseWhite.png");
    private static float minPlayed;
    private static long startMin=0;
    private static long lastMin=0;
    private static boolean addSong = false;
    private static Playlist playlist;
    private static ArrayList<Song> topFive = new ArrayList<>();
    private static Song songPlay;
    private static boolean loop = false;
    private static boolean shuffle = false;
    private static boolean wherePlay = false; // if false, from songs, if true, from playlists
    private static final MetaEventListener listener = meta -> {
        if (meta.getType() == 47) {
            playSongTime();
        }
    };

    private static final Date date = new Date();

    private static MidiHelper finalMidiHelper;
    {
        try {
            finalMidiHelper = new MidiHelper(listener);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    WebHandler myWebHandlingTool = new WebHandler(path, URLRoute, "result%s.txt", "?startat=%s&");

    /**
     * Parametrized constructor
     */
    public SpotiFrameManager(BusinessFacadeImp myFacade) {
        this.myFacade = myFacade;
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
                addSong = false;
                //addSongsAll(new BusinessFacadeImp().getSongManager().getSongs());
                SongsUI.initTable(new BusinessFacadeImp().getSongManager().getSongs(), "Delete");
                cc.show(spotiPanel, SONGS_UI);
                break;
            case CREATE_STADISTICS:
                letsInitializeGraphs(getMinPlayed(), getNumSongs());
                cc.show(spotiPanel, STATISTICS_UI);
                break;
            case SHOW_TOP_SONGS:
                SongsUI.initTable(new BusinessFacadeImp().getSongManager().getTopFive(), "topFive");
                cc.show(spotiPanel, /*TOPSONGS_UI*/ SONGS_UI);
                break;
            case CREATE_PLAYLIST:
                playlist = myFacade.createPlaylist();
                break;

            case SEARCH_SONG:
                searchSong(getInputedSongName());
                cc.show(spotiPanel, SONGS_UI);
                break;
            case Dictionary_login.PROFILE_BUTTON:           //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
                break;
            case SHUFFLE_BUTTON:
                shuffle = !shuffle;
                setIconShuffleActive(shuffle);
                break;
            case LAST_BUTTON:
                if(songPlay!=null){
                    if(!loop){
                        if(!shuffle){
                            if(wherePlay){
                                previousSongFromPlaylist();
                            }else{
                                previousSongFromSong();
                            }
                        }else{
                            if(wherePlay){
                                randomFromPlaylist();
                            }else{
                                randomFromSongs();
                            }
                        }
                    }else{
                        finalMidiHelper.restartSong(songPlay.getSongFile());
                        finalMidiHelper.playSong(songPlay.getSongFile());
                    }
                }

                break;
            case NEXT_BUTTON:
                if(songPlay!=null){
                    playSongTime();
                }
                break;
            case LOOP_BUTTON:
                loop = !loop;
                setIconLoopActive(loop);
                break;
            case PLAY_BUTTON:
                letsInitializeGraphs(getMinPlayed(), getNumSongs());
                if(songPlay!=null){
                    if(!play){
                        playMusic();
                    }
                    else{
                        stopMusic();
                    }
                }
                break;

            case PLAYLIST_INFO:
                JButton button;
                if (obj instanceof JButton) {
                    button = (JButton) obj;
                    playlist = new BusinessFacadeImp().getPlaylist(button.getName());
                    PlaylistUI.setSongsPlaylists(playlist);
                    cc.show(spotiPanel, PLAYLIST_UI);
                }
                break;
            case SONG_PLAYLIST:
                JButton song;
                addSong = false;
                if (obj instanceof JButton) {
                    song = (JButton) obj;
                    boolean errorDeleting = new BusinessFacadeImp().deleteSongFromPlaylist(playlist.getPlaylistName(),song.getName());
                    playlist = new BusinessFacadeImp().getPlaylist(playlist.getPlaylistName());
                    PlaylistUI.setSongsPlaylists(playlist);
                }
                break;
            case ADD_SONG_COMM:
                //addSongsToPlaylist(new BusinessFacadeImp().getSongManager().getSongs());
                SongsUI.initTable(new BusinessFacadeImp().getSongManager().getSongs(), "Add");
                cc.show(spotiPanel, SONGS_UI);
                addSong = true;
                break;
            default:
                if(obj instanceof JTable){
                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.parseInt( e.getActionCommand() );
                    if(addSong){
                        if (isAlreadyInPlaylist(new BusinessFacadeImp().getSong(modelRow).getSongName())) {
                            JOptionPane.showMessageDialog(null,
                                    "This song already exists in the playlist!", "Song adding Error" ,
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            boolean updatingSong = new BusinessFacadeImp().addSongToPlaylist(playlist.getPlaylistName(),new BusinessFacadeImp().getSong(modelRow).getSongName());
                            playlist = new BusinessFacadeImp().getPlaylist(playlist.getPlaylistName());
                            PlaylistUI.setSongsPlaylists(playlist);
                            cc.show(spotiPanel, PLAYLIST_UI);
                            addSong = false;
                        }
                    }else{
                        int input = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete " +
                                        new BusinessFacadeImp().getSong(modelRow).getSongName() +"?",
                                "Select an option", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        // 0=yes, 1=no, 2=cancel
                        if(input == JOptionPane.YES_OPTION){
                            ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                            new BusinessFacadeImp().deleteSong(modelRow);
                            new BusinessFacadeImp().setSongUser();
                            new BusinessFacadeImp().getPlaylistManager().setPlaylists(UserManager.getUser().getUserName());
                        }

                    }
                    break;
                }

        }
    }

    /**
     * Gets the playlists and add them to the SpotUI
     * @param playlists List of playlists from the database
     */
    public static void addPlaylists(ArrayList<Playlist> playlists){
        SpotiUI.addPlaylists(playlists);
    }

    /**
     * Gets the amount of songs played in order to make the statistics
     * @return Amount of songs that have been played for each hour
     */
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

    /**
     * Gets the amount of minutes songs have been played in order to make the statistics
     * @return Amount of minutes that have been played for each hour
     */
    public static LinkedList<Float> getMinPlayed(){
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

    /**
     * Detects which song or functionality has been pressed by the user
     * @param e Event performed by the user
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JPanel song;
        JTable table;
        Object obj = e.getSource();
        int button = e.getButton();
        if (obj instanceof JPanel) {
            /*if(play){
                finalMidiHelper.stopSong();
            }*/
            song = (JPanel) obj;
            songPlay = findSong(song.getName());
            wherePlay = true;
            playMusic();
            SpotiUI.setSong(songPlay.getSongName(), songPlay.getAuthorName());
            new BusinessFacadeImp().getSongManager().updateSongPlayed(songPlay);
            new BusinessFacadeImp().setSongUser();
            topFive = new BusinessFacadeImp().getSongManager().getTopFive();
        }else if(obj instanceof  JTable){
            table = (JTable) obj;
            if(table.getEditorComponent() == null){
                songPlay = new BusinessFacadeImp().getSong(table.getSelectedRow());
                playMusic();
                setSong(songPlay.getSongName(), songPlay.getAuthorName());
                wherePlay = false;
            }
        }
    }

    /**
     * Gets the desired song
     * @param file String where the song is stored
     * @return The song with all its information
     */
    private Song findSong(String file){
        ArrayList<Song> arraySong = new BusinessFacadeImp().getSongManager().getSongs();
        int i=0;
        boolean found = false;
        while(!found && i<arraySong.size()){
            if(arraySong.get(i).getSongFile().equals(file)){
                found=true;
            }
            else {
                i++;
            }
        }
        if(found){
            return arraySong.get(i);
        } else{
            return null;
        }
    }


    private static Song nextSongSongs(String file){
        ArrayList<Song> arraySong = new BusinessFacadeImp().getSongManager().getSongs();
        int i=0;
        boolean found = false;
        while(!found && i<arraySong.size()){
            if(arraySong.get(i).getSongFile().equals(file)){
                found=true;
            }
            else {
                i++;
            }
        }
        if(found){
            i++;
            if (i >= arraySong.size()) {
                i = 0;
            }
            return arraySong.get(i);
        } else{
            return null;
        }
    }

    private Song previousSongSongs(String file){
        ArrayList<Song> arraySong = new BusinessFacadeImp().getSongManager().getSongs();
        int i=0;
        boolean found = false;
        while(!found && i<arraySong.size()){
            if(arraySong.get(i).getSongFile().equals(file)){
                found=true;
            }
            else {
                i++;
            }
        }
        if(found){
            i--;
            if (i < 0) {
                i = arraySong.size()-1;
            }
            return arraySong.get(i);
        } else{
            return null;
        }
    }

    private static Song nextSongPlaylist(String file){
        int i=0;
        boolean found = false;
        while(!found && i<playlist.getSongs().size()){
            if(playlist.getSongs().get(i).getSongFile().equals(file)){
                found=true;
            }
            else {
                i++;
            }
        }
        if(found){
            i++;
            if (i >= playlist.getSongs().size()) {
                i = 0;
            }
            return playlist.getSongs().get(i);
        }
        return null;
    }

    private Song previousSongPlaylist(String file){
        int i=0;
        boolean found = false;
        while(!found && i<playlist.getSongs().size()){
            if(playlist.getSongs().get(i).getSongFile().equals(file)){
                found=true;
            }
            else {
                i++;
            }
        }
        if(found){
            i--;
            if (i < 0) {
                i = playlist.getSongs().size()-1;
            }
            return playlist.getSongs().get(i);
        }
        return null;
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

    /**
     * Checks if a song is already in a playlist
     * @param song The song we want to check
     * @return True if the song is in the playlist, false if not
     */
    private boolean isAlreadyInPlaylist(String song){
        int i = 0;
        boolean found = false;
        while(i<playlist.getSongs().size() && !found){
            if(playlist.getSongs().get(i).getSongName().equals(song)){
                found = true;
            }else{
                i++;
            }
        }
        return found;
    }

    /**
     * Creates a dialog to introduce the name of a new playlist
     * @return
     */
    private String createPanelPlaylist(){
        return (String)JOptionPane.showInputDialog(
                null,
                "Which name is your playlist going to have?",
                "Playlist Creator",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "New Playlist"
        );
    }
    public static void resetSongs(){
        SongsUI.initTable(new BusinessFacadeImp().getSongManager().getSongs(), "Delete");
    }

    private static void playMusic(){
        playButton.setIcon(pauseIcon);
        playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
        startMin = System.currentTimeMillis();
        finalMidiHelper.playSong(songPlay.getSongFile());
        play = true;
    }

    private static void stopMusic(){
        playButton.setIcon(playIcon);
        playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
        play = false;
        lastMin = System.currentTimeMillis();
        minPlayed = (float)(lastMin - startMin)/60000;
        new BusinessFacadeImp().getSongManager().addingStadistics(new Stadistics(date.getHours(), (float)1, minPlayed));
        finalMidiHelper.stopSong();
    }

    private void setIconShuffleActive(boolean active){
        if(active){
            shuffleButton.setIcon(new ImageIcon("Files/drawable/shuffleAcive.png"));
        }else{
            shuffleButton.setIcon(new ImageIcon("Files/drawable/shuffleWhite.png"));
        }
        shuffleButton.setIcon(resizeIcon((ImageIcon) shuffleButton.getIcon(), (int) Math.round(shuffleButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(shuffleButton.getIcon().getIconHeight()*0.05)));
    }

    private void setIconLoopActive(boolean active){
        if(active){
            loopButton.setIcon(new ImageIcon("Files/drawable/exchange.png"));
        }else{
            loopButton.setIcon(new ImageIcon("Files/drawable/exchangeWhite.png"));
        }
        loopButton.setIcon(resizeIcon((ImageIcon) loopButton.getIcon(), (int) Math.round(loopButton.getIcon().getIconWidth()*0.05),
                (int) Math.round(loopButton.getIcon().getIconHeight()*0.05)));
    }

    private static  void nextSongFromSong(){
        songPlay = nextSongSongs(songPlay.getSongFile());
        playMusicSetLabel();
    }
    private void previousSongFromSong(){
        songPlay = previousSongSongs(songPlay.getSongFile());
        playMusicSetLabel();
    }

    private static void nextSongFromPlaylist(){
        songPlay = nextSongPlaylist(songPlay.getSongFile());
        playMusicSetLabel();
    }

    private void previousSongFromPlaylist(){
        songPlay = previousSongPlaylist(songPlay.getSongFile());
        playMusicSetLabel();

    }
    private static void playMusicSetLabel(){
        playMusic();
        SpotiUI.setSong(songPlay.getSongName(), songPlay.getAuthorName());
    }

    private static void randomFromPlaylist(){
        songPlay = playlist.getSongs().get(new Random().nextInt(playlist.getSongs().size()));
        playMusicSetLabel();
    }
    private static void randomFromSongs(){
        songPlay = new BusinessFacadeImp().getSongManager().getSongs().get(new Random().nextInt(new BusinessFacadeImp().getSongManager().getSongs().size()));
        playMusicSetLabel();
    }

    private static void playSongTime(){
        if(!loop){
            if(!shuffle){
                if(wherePlay){
                    nextSongFromPlaylist();
                }else{
                    nextSongFromSong();
                }
            }else{
                if(wherePlay){
                    randomFromPlaylist();
                }else{
                    randomFromSongs();
                }
            }
        }else{
            finalMidiHelper.restartSong(songPlay.getSongFile());
            finalMidiHelper.playSong(songPlay.getSongFile());
        }
        setNumSongs(getNumSongs());
        setNumMin(getMinPlayed());
        initialize();
    }

    private void searchSong(String songName){
        if(songName.length()>1){
            //myWebHandlingTool.doStuff(songName, songName);
            ArrayList<Song> songs = new BusinessFacadeImp().getSongManager().getSongs();
            ArrayList<Song> songsSearched = new ArrayList<>();
            for (Song song : songs) {
                if (song.getSongName().toLowerCase().contains(songName.toLowerCase())
                        || song.getAuthorName().toLowerCase().contains(songName.toLowerCase())) {
                    songsSearched.add(song);
                }
            }
            SongsUI.initTable(songsSearched, "Delete");
        }else{
            //TODO SERGI ARREGLA AIXO UWU
            JOptionPane.showMessageDialog(null,
                    "You must input something more!", "Search Song Error" ,
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
