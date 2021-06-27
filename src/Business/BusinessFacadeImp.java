package Business;

import Business.Entities.Playlist;
import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.*;
import Business.Threads.WebScrapping;
import Presentation.Manager.ErrorsManager;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import static Presentation.DictionaryPiano.PLAYLIST_UI;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.PianoTilesUISelector.setKeys;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" class will contain the implementation of the BusinessFacade interface to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 23 May 2021
 *
 */
public class BusinessFacadeImp implements Business.BusinessFacade {
    private static UserManager loginUserManager;
    private static SongManager songManager;
    private static PlaylistManager playlistManager;
    private static TilesManager tilesManager;
    private static ErrorsManager errorManager;
    private static BusinessFacade businessFacade;


    public static BusinessFacade getBusinessFacade(){
        if(businessFacade==null){
            businessFacade = new BusinessFacadeImp();
            loginUserManager = new UserManager();
            songManager = new SongManager();
            playlistManager = new PlaylistManager();
            tilesManager = new TilesManager();
            errorManager = new ErrorsManager();
        }
        return businessFacade;
    }

    /**
     * Logs in to the user "guest" and shows the piano UI layout from the card layout
     */
    @Override
    public void enterAsAGuest(){
            playlistManager.setPlaylists(UserManager.getUser().getUserName());
            SpotiFrameManager.addPlaylists(playlistManager.getPlaylists());
            setSong();
    }

    /**
     * Calls the method to log in
     * @param username Username string which the user has inputted while logging in
     * @param password Password string which the user has inputted while logging in
     * @return True if logged in, false if not
     */
    @Override
    public boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }

    /**
     * Calls the method to sign up
     * @param username Username string which the user has inputted while signing up
     * @param mail Mail string which the user has inputted while signing up
     * @param password Password string which the user has inputted while signing up
     * @param passwordConfirm PasswordConfirmation string which the user has inputted while signing up
     * @return True if correctly signed up, false if not
     */
    @Override
    public boolean SignUp(String username, String mail, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        try {
            if (loginUserManager.signUser(username,mail,password)) {
                return true;
            }
            else {
                setError(1);
                return false;
            }
        } catch (SQLException e) {
            setError(0);
            return false;
        }
    }

    /**
     * Calls the method to delete a user
     */
    @Override
    public void deleteAccount() {
        for(int i = 0; i< songManager.getSongs().size();i++){
           if(songManager.getSongs().get(i).getCreator().equals(UserManager.getUser().getUserName()) ||
                   songManager.getSongs().get(i).getAuthorName().equals(UserManager.getUser().getUserName())){
               new File(songManager.getSongs().get(i).getSongFile()).delete();
           }
        }
        try {
            if (!loginUserManager.deleteUser()) {
                setError(0);
            }
        } catch (SQLException e) {
            setError(0);
        }
    }

    @Override
    public void noteRecordingUpdate(ArrayList<RecordingNotes> recordingNotes, float recordingTime){
            JPanel myPanel = new JPanel();
            JTextField titleField = new JTextField(20);
            myPanel.add(titleField);
            JCheckBox box = new JCheckBox("is public?");
            myPanel.add(box);

            JOptionPane.showMessageDialog(null, myPanel, "Enter a title for the song", JOptionPane.INFORMATION_MESSAGE);

            recordedNotesSend(recordingNotes, titleField.getText(), box.isSelected(), recordingTime);
    }

    @Override
    public boolean modifyKey(String tileSelected, KeyEvent e, int KeyExisted){
        if(KeyExisted == -1) {
            JOptionPane.showMessageDialog(contenedor,
                    "This key is already assigned!", "Modify keys error" , JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;

    }

    @Override
    public Playlist createPlaylist(){
        Playlist myPlayList = null;

        String myStr = (String)JOptionPane.showInputDialog(
                null, "Which name is your playlist going to have?",
                "Playlist Creator", JOptionPane.PLAIN_MESSAGE, null, null, "New Playlist");

        if(myStr != null && myStr.length() > 0 && myStr.indexOf('\'') == -1){
            new BusinessFacadeImp().newPlaylist(myStr);
            myPlayList = playlistManager.getFromName(myStr);
            //PlaylistUI.setSongsPlaylists(myPlayList);
            SpotiFrameManager.addPlaylists(playlistManager.getPlaylists());
        } else {
            JOptionPane.showMessageDialog(null,
                    "The input is not correct!", "Create Playlist Error" ,
                    JOptionPane.ERROR_MESSAGE);
        }
        return myPlayList;
    }

    @Override
    public void setAllKeys(){
        setKeys(tilesManager.getListTiles());
    }

    /**
     * Saves a song created by the user to a midi file
     * @param recordedNotes List of notes played
     * @param songName Name of the song
     * @param isPublic True if public, false if private
     * @param endtime Duration of the song
     */
    @Override
    public void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        songManager.saveRecording(recordedNotes,songName,isPublic,endtime);
    }

    /**
     * Gets the playlist manager
     * @return The playlist manager
     */
    @Override
    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    /**
     * Gets a playlist based on its name
     * @param name name of the playlist we want to get
     * @return The desired playlist
     */
    @Override
    public Playlist getPlaylist(String name){
        return playlistManager.getFromName(name);
    }

    /**
     * Deletes a song from a playlist
     * @param playlistName Name of the playlist we want to delete the song from
     * @param songName Song we want to delete
     * @return True if deleted, false if not
     */
    @Override
    public boolean deleteSongFromPlaylist(String playlistName, String songName){
        return playlistManager.eliminateSongFromPlaylist(playlistName, songName);
    }

    /**
     * Adds a song to a playlist
     * @param playlistName Name of the playlist we want to add the song to
     * @param songName Name of the song we want to add
     * @return True if added, false if not
     */
    @Override
    public boolean addSongToPlaylist(String playlistName, String songName){
        return playlistManager.addSongToPlaylist(playlistName, songName);
    }

    /**
     * Sets the songs from the song manager depending on the user
     */
    @Override
    public void setSongUser() {
        songManager.setSongs(UserManager.getUser().getUserName());
    }

    /**
     * Sets the songs from the song manager
     */
    @Override
    public void setSong(){

        songManager.setSongs();
    }

    @Override
    public ArrayList<String> getSongName() {
        return songManager.getSongNames();
    }

    @Override
    public Song getSong(int index) {
        return songManager.getSong(index);
    }

    @Override
    public SongManager getSongManager() {
        return songManager;
    }

    @Override
    public void deleteSong(int i){
        if (!songManager.deleteSong(getSong(i))) {
            setError(3);
        }
    }

    @Override
    public boolean newPlaylist(String playlist){
        return playlistManager.newPlaylist(playlist, UserManager.getUser().getUserName());
    }

    @Override
    public void setTileArray(int songIndex) {
        tilesManager.setListTiles(songIndex);
    }

    @Override
    public ArrayList<Keys> getTiles() {
        return tilesManager.getListTiles();
    }

    @Override
    public void resetTilesKeys(){
        tilesManager.resetKeys();
    }

    @Override
    public void initializeWebScrapping(){
        new WebScrapping();
    }

    @Override
    public void setError(int errorFound) {
        errorManager.errorFound(errorFound);
    }

    @Override
    public ArrayList<Song> getSongs(){
        return songManager.getSongs();
    }

    @Override
    public Stadistics getStats(int i){
        return songManager.gettingStadistics(i);
    }

    @Override
    public void updateSong(Song song){
        songManager.updateSongPlayed(song);
    }

    @Override
    public ArrayList<Song> getTopFive(){ return songManager.getTopFive(); }




}
