package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.Entities.*;
import Business.BusinessFacadeImp;
import Business.MidiHelper;
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


/**
 * SpotiFrameManager
 *
 * The "SpotiFrameManager" class will contain the different methods that are needed to control the view class "SpotiUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 23 May 2021
 *
 */
public class SpotiFrameManager extends AbstractAction implements ActionListener, MouseListener {
    private final ImageIcon playIcon; //Icon played
    private final ImageIcon pauseIcon; //Icon pause

    /*
    Defines if there is a song being played
     */
    private static boolean play = false;
    private static long startMin = 0;
    private static boolean top5 = false;

    /*
    Defines if we are adding a song or not
     */
    private static boolean addSong = false;

    /*
    Defines the playlist where the song that is being played is
     */
    private static Playlist playlist;
    private static Song songPlay;
    private static boolean loop = false;

    /*
    Defines if the music player is in shuffle mode
     */
    private static boolean shuffle = false;

    /*
    Defines a true if it is being played from the playlist, a false if it is from a song
     */
    private static boolean wherePlay = false;

    public static int count_song = 0;

    /*
    Event that will control if the end of the track has been reached
     */
    private final MetaEventListener listener = meta -> {
        if (meta.getType() == 47) {
            playSongTime();
        }
    };

    /*
    MidiHelper which will control the music playing in the music player
     */
    private final MidiHelper finalMidiHelper;

    /**
     * Parametrized constructor
     */
    public SpotiFrameManager() {
        MidiHelper finalMidiHelper1;
        try {
            finalMidiHelper1 = new MidiHelper();
        } catch (MidiUnavailableException e) {
            finalMidiHelper1 = null;
        }
        finalMidiHelper = finalMidiHelper1;

        playIcon = new ImageIcon(PLAYICON);
        pauseIcon = new ImageIcon(PAUSEICON);
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
                top5 = false;
                SongsUI.initTable(BusinessFacadeImp.getBusinessFacade().getSongs(), "Delete");
                cc.show(spotiPanel, SONGS_UI);
                break;
            case CREATE_STADISTICS:
                letsInitializeGraphs(getMinPlayed(), getNumSongs());
                cc.show(spotiPanel, STATISTICS_UI);
                break;
            case SHOW_TOP_SONGS:
                top5 = true;
                //BusinessFacadeImp.getBusinessFacade().updateSong(songPlay);
                //BusinessFacadeImp.getBusinessFacade().setSongUser();
                //updateTable();
                SongsUI.initTable(BusinessFacadeImp.getBusinessFacade().getTopFive(), "topFive");
                cc.show(spotiPanel, SONGS_UI);
                break;
            case CREATE_PLAYLIST:
                String myStr = (String)JOptionPane.showInputDialog(
                        null, "What name is your playlist going to have?",
                        "Playlist Creator", JOptionPane.PLAIN_MESSAGE, null, null, "New Playlist");
                playlist = BusinessFacadeImp.getBusinessFacade().createPlaylist(myStr);
                if(playlist !=null){
                    PlaylistUI.setSongsPlaylists(playlist);
                    cc.show(spotiPanel, PLAYLIST_UI);
                }
                break;

            case SEARCH_SONG:
                if(searchSong(getInputedSongName())){
                    cc.show(spotiPanel, SONGS_UI);
                    top5 = false;
                }
                break;
            case Dictionary_login.PROFILE_BUTTON:           //In the case that the Profile button is pressed
                card.show(contenedor, PROFILE_UI);
                break;
            case SHUFFLE_BUTTON:
                shuffle = !shuffle;
                setIconShuffleActive(shuffle);
                break;
            case MUTE_BUTTON:
                muteSong();
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
                    /*BusinessFacadeImp.getBusinessFacade().updateSong(songPlay);
                    BusinessFacadeImp.getBusinessFacade().setSongUser();
                    SongsUI.initTable(BusinessFacadeImp.getBusinessFacade().getTopFive(), "topFive");*/
                    updateTable();
                    setNumSongs(getNumSongs());
                    setNumMin(getMinPlayed());
                    initialize();
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
                    playlist = BusinessFacadeImp.getBusinessFacade().getPlaylist(button.getName());
                    PlaylistUI.setSongsPlaylists(playlist);
                    cc.show(spotiPanel, PLAYLIST_UI);
                }
                break;
            case SONG_PLAYLIST:
                JButton song;
                addSong = false;
                if (obj instanceof JButton) {
                    song = (JButton) obj;
                    boolean errorDeleting = BusinessFacadeImp.getBusinessFacade().deleteSongFromPlaylist(playlist.getPlaylistName(),song.getName());
                    playlist = BusinessFacadeImp.getBusinessFacade().getPlaylist(playlist.getPlaylistName());
                    PlaylistUI.setSongsPlaylists(playlist);
                }
                break;
            case ADD_SONG_COMM:
                SongsUI.initTable(BusinessFacadeImp.getBusinessFacade().getSongs(), "Add");
                cc.show(spotiPanel, SONGS_UI);
                addSong = true;
                top5 = false;
                break;
            default:
                if(obj instanceof JTable){
                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.parseInt( e.getActionCommand() );
                    if(!top5){
                        if(addSong){
                            if (isAlreadyInPlaylist(BusinessFacadeImp.getBusinessFacade().getSong(modelRow).getSongName())) {
                                BusinessFacadeImp.getBusinessFacade().setError(7);
                            }else{
                                boolean updatingSong = BusinessFacadeImp.getBusinessFacade().
                                        addSongToPlaylist(playlist.getPlaylistName(),
                                                BusinessFacadeImp.getBusinessFacade().getSong(modelRow).getSongName());
                                playlist = BusinessFacadeImp.getBusinessFacade().getPlaylist(playlist.getPlaylistName());
                                PlaylistUI.setSongsPlaylists(playlist);
                                cc.show(spotiPanel, PLAYLIST_UI);
                                addSong = false;
                            }
                        }else{
                            int input = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want to delete " +
                                            BusinessFacadeImp.getBusinessFacade().getSong(modelRow).getSongName() +"?",
                                    "Select an option", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            if(input == JOptionPane.YES_OPTION){
                                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                                BusinessFacadeImp.getBusinessFacade().deleteSong(modelRow);
                                BusinessFacadeImp.getBusinessFacade().setSongUser();
                                BusinessFacadeImp.getBusinessFacade().getPlaylistManager().setPlaylists(UserManager.getUser().getUserName());
                            }

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
            if(BusinessFacadeImp.getBusinessFacade().getStats(i) == null){
                numSongs.add((float) 0);
            } else {
                numSongs.add(BusinessFacadeImp.getBusinessFacade().getStats(i).getNumPlayed());
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
            if(BusinessFacadeImp.getBusinessFacade().getStats(i) == null){
                numMin.add((float)0);
            } else {
                numMin.add(BusinessFacadeImp.getBusinessFacade().getStats(i).getMinPlayed());
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
        if (obj instanceof JPanel) {
            song = (JPanel) obj;
            songPlay = findSong(song.getName());
            wherePlay = true;
            playMusic();
            SpotiUI.setSong(songPlay.getSongName(), songPlay.getAuthorName());
            /*BusinessFacadeImp.getBusinessFacade().updateSong(songPlay);
            BusinessFacadeImp.getBusinessFacade().setSongUser();
            ArrayList<Song> topFive = BusinessFacadeImp.getBusinessFacade().getTopFive();*/
            updateTable();
        }else if(obj instanceof  JTable){
            table = (JTable) obj;
            if(table.getEditorComponent() == null){
                if(top5){
                    songPlay = BusinessFacadeImp.getBusinessFacade().getTopFive().get(table.getSelectedRow());
                }else{
                    songPlay = BusinessFacadeImp.getBusinessFacade().getSong(table.getSelectedRow());
                }
                playMusic();
                setSong(songPlay.getSongName(), songPlay.getAuthorName());
                wherePlay = false;
                updateTable();
            }
        }
    }

    /**
     * Gets the desired song
     * @param file String where the song is stored
     * @return The song with all its information
     */
    private Song findSong(String file){
        ArrayList<Song> arraySong = BusinessFacadeImp.getBusinessFacade().getSongs();
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


    /**
     * Method that gets the following song from the one currently playing
     * @param file Defines the currently song playing
     * @return Song to be played
     */
    private static Song nextSongSongs(String file){
        ArrayList<Song> arraySong = BusinessFacadeImp.getBusinessFacade().getSongs();
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

    /**
     * Method that gets the previous song from the one playing
     * @param file Defines the song currently playing
     * @return Song to be played
     */
    private Song previousSongSongs(String file){
        ArrayList<Song> arraySong = BusinessFacadeImp.getBusinessFacade().getSongs();
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

    /**
     * Method that gets the following song from the one currently playing
     * @param file Defines the file of the current song playing
     * @return Song to be played
     */
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

    /**
     * Method that gets the previous song from the one playing inside the playlist
     * @param file Defines the file of the current song playing
     * @return Song to be played
     */
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
     * Initializes the table of songs
     */
    public static void resetSongs(){
        SongsUI.initTable(BusinessFacadeImp.getBusinessFacade().getSongs(), "Delete");
    }

    /**
     * Plays a song and changes the icon to the pause one
     */
    private void playMusic(){
        count_song = 1;
        playButton.setIcon(pauseIcon);
        startMin = System.currentTimeMillis();
        finalMidiHelper.playSong(songPlay.getSongFile());
        play = true;

    }

    /**
     * Stops playing a song, gets the minutes that has been played and sets the icon back to the play one
     */
    private void stopMusic(){
        playButton.setIcon(playIcon);
        play = false;
        long lastMin = System.currentTimeMillis();
        float minPlayed = (float) (lastMin - startMin) / 60000;
        finalMidiHelper.stopSong();
    }

    /**
     * Calls the method to mute a song
     */
    private void muteSong() {
       finalMidiHelper.muteSong();
    }

    /**
     * Sets the corresponent shuffle button depending if the shuffle option is activated
     * @param active True if active, false if not
     */
    private void setIconShuffleActive(boolean active){
        if(active){
            shuffleButton.setIcon(new ImageIcon("Files/drawable/shuffleAcive.png"));
        }else{
            shuffleButton.setIcon(new ImageIcon("Files/drawable/shuffleWhite.png"));
        }
    }

    /**
     * Sets the corresponent loop button depending if the loop option is activated
     * @param active True if active, false if not
     */
    private void setIconLoopActive(boolean active){
        if(active){
            loopButton.setIcon(new ImageIcon("Files/drawable/exchange.png"));
        }else{
            loopButton.setIcon(new ImageIcon("Files/drawable/exchangeWhite.png"));
        }
    }

    /**
     * Gets the next song to play and plays it
     */
    private void nextSongFromSong(){
        songPlay = nextSongSongs(songPlay.getSongFile());
        playMusicSetLabel();
    }

    /**
     * Gets the previous song to play and plays it
     */
    private void previousSongFromSong(){
        songPlay = previousSongSongs(songPlay.getSongFile());
        playMusicSetLabel();
    }

    /**
     * Gets the next song to play from a playlist and plays it
     */
    private void nextSongFromPlaylist(){
        songPlay = nextSongPlaylist(songPlay.getSongFile());
        playMusicSetLabel();
    }

    /**
     * Gets the previous song to play from a playlist and plays it
     */
    private void previousSongFromPlaylist(){
        songPlay = previousSongPlaylist(songPlay.getSongFile());
        playMusicSetLabel();
    }

    /**
     * Calls the method to play music adn sets the labels for the song
     */
    private void playMusicSetLabel(){
        playMusic();
        SpotiUI.setSong(songPlay.getSongName(), songPlay.getAuthorName());
    }

    /**
     * Gets a random song from a playlist and plays it
     */
    private void randomFromPlaylist(){
        songPlay = playlist.getSongs().get(new Random().nextInt(playlist.getSongs().size()));
        playMusicSetLabel();
    }

    /**
     * Gets a random song from all available songs and plays it
     */
    private void randomFromSongs(){
        songPlay = BusinessFacadeImp.getBusinessFacade().getSongs().get(new Random().nextInt(BusinessFacadeImp.getBusinessFacade().getSongs().size()));
        playMusicSetLabel();
    }

    /**
     * Decides which method to call depending on which option the user has selected
     */
    private void playSongTime(){
        count_song = 1;
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

        updateTable();
        /*BusinessFacadeImp.getBusinessFacade().updateSong(songPlay);
        BusinessFacadeImp.getBusinessFacade().setSongUser();*/
        /*
        setNumSongs(getNumSongs());
        setNumMin(getMinPlayed());
        initialize();
        new BusinessFacadeImp().updateSong(songPlay);
        new BusinessFacadeImp().setSongUser();
        SongsUI.initTable(new BusinessFacadeImp().getTopFive(), "topFive");*/
    }

    /**
     * Method that searches in the database the songs
     * @param songName Defines the string to be searched
     * @return boolean that returns true if it was correct searched, false if not
     */
    private boolean searchSong(String songName){
        if(songName.length()>1){
            ArrayList<Song> songs = BusinessFacadeImp.getBusinessFacade().getSongs();
            ArrayList<Song> songsSearched = new ArrayList<>();
            for (Song song : songs) {
                if (song.getSongName().toLowerCase().contains(songName.toLowerCase())
                        || song.getAuthorName().toLowerCase().contains(songName.toLowerCase())) {
                    songsSearched.add(song);
                }
            }
            SongsUI.initTable(songsSearched, "Delete");
            return true;

        }else{
            BusinessFacadeImp.getBusinessFacade().setError(6);
            return false;
        }

    }
    private void updateTable(){
        BusinessFacadeImp.getBusinessFacade().updateSong(songPlay);
        BusinessFacadeImp.getBusinessFacade().setSongUser();
        if(top5){
            SongsUI.initTable(BusinessFacadeImp.getBusinessFacade().getTopFive(), "topFive");
        }
    }

    public static boolean getPlay() {
        return play;
    }
}
