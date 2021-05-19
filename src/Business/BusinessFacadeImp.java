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

    public void singUpStartup(){
        resetUISignUpUI();
        card.show(contenedor, SIGN_UP_UI);
    }

    public void logInStartup(){
        resetUILogin();
        card.show(contenedor, LOGIN_UI);
    }

    public void enterAsAGuest(String name, String psw){
        if(logIn("guest", "password")){
            setUsernameLogin("guest");
        }
        card.show(contenedor,PIANO_FRAME);
        setSongUser();
    }

    @Override
    public boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }

    @Override
    public boolean SignUp(String username, String mail, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        return loginUserManager.signUser(username,mail,password);
    }

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

    @Override
    public boolean deleteAccount() {
        return loginUserManager.deleteUser();
    }

    @Override
    public void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        songManager.saveRecording(recordedNotes,songName,isPublic,endtime);
    }

    @Override
    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    @Override
    public Playlist getPlaylist(String name){
        return playlistManager.getFromName(name);
    }

    @Override
    public boolean deleteSongFromPlaylist(String playlistName, String songName){
        return playlistManager.eliminateSongFromPlaylist(playlistName, songName);
    }

    @Override
    public boolean addSongToPlaylist(String playlistName, String songName){
        return playlistManager.addSongToPlaylist(playlistName, songName);
    }

    @Override
    public void setSongUser() {songManager.setSongs();}

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
