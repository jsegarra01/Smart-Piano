package Business;

import Business.Entities.Playlist;
import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.*;

import javax.swing.*;
import java.util.ArrayList;

import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.LoginUI.resetUILogin;
import static Presentation.Ui_Views.LoginUI.setUsernameLogin;
import static Presentation.Ui_Views.SignUpUI.*;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" class will contain the implementation of the BusinessFacade interface to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 24 Apr 2021
 *
 */
public class BusinessFacadeImp implements Business.BusinessFacade {
    private static UserManager loginUserManager = new UserManager();
    private static SongManager songManager = new SongManager();
    private static PlaylistManager playlistManager = new PlaylistManager();
    private static TilesManager tilesManager = new TilesManager();

    /**
     * Shows the Sign up UI layout from the card layout
     */
    public void singUpStartup(){
        resetUISignUpUI();
        card.show(contenedor, SIGN_UP_UI);
    }

    /**
     * Shows the Log in UI layout from the card layout
     */
    public void logInStartup(){
        resetUILogin();
        card.show(contenedor, LOGIN_UI);
    }

    /**
     * Logs in to the user "guest" and shows the piano UI layout from the card layout
     * @param name not used
     * @param psw not used
     */
    public void enterAsAGuest(String name, String psw){
        if(logIn("guest", "password")){
            setUsernameLogin("guest");
        }
        card.show(contenedor,PIANO_FRAME);
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
        return loginUserManager.signUser(username,mail,password);
    }

    /**
     * Calls the method to sign up
     * @param username Username string which the user has inputted while signing up
     * @param mail Mail string which the user has inputted while signing up
     * @param password Password string which the user has inputted while signing up
     * @param passwordConfirm PasswordConfirmation string which the user has inputted while signing up
     * @return True if correctly signed up, false if not
     */
    public void finalSignUp(String username, String mail, String password, String passwordConfirm){
        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(contenedor, "Values introduced were not accepted", "SignUp error", JOptionPane.ERROR_MESSAGE);
        }else {
            if(loginUserManager.signUser(username,mail,password)){
                setUsernameLogin(getUsernameSignUp());
                setSongUser();
                card.show(contenedor, PIANO_FRAME);
            } else {
                JOptionPane.showMessageDialog(contenedor, "Values introduced were not accepted", "SignUp error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Calls the method to delete a user
     * @return True if deleted, false if not
     */
    @Override
    public boolean deleteAccount() {
        return loginUserManager.deleteUser();
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
    public void setSongUser() {songManager.setSongs(UserManager.getUser().getUserName());}

    /**
     * Sets the songs from the song manager
     */
    @Override
    public void setSong(){
        songManager.setSongs();
    }

    /**
     *
     * @return
     */
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
    public boolean deleteSong(int i){
        return songManager.deleteSong(getSong(i));
    }

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
}
