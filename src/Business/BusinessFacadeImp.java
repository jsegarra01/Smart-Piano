package Business;

import Business.Entities.Playlist;
import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.*;
import Business.Threads.WebScrapping;
import Presentation.Manager.SpotiFrameManager;
import Presentation.Ui_Views.FreePianoUI;
import Presentation.Ui_Views.PlaylistUI;
import Presentation.Ui_Views.SpotiUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import static Presentation.DictionaryPiano.FREE_PIANO_UI;
import static Presentation.DictionaryPiano.PLAYLIST_UI;
import static Presentation.Dictionary_login.*;
import static Presentation.Manager.MainFrame.card;
import static Presentation.Manager.MainFrame.contenedor;
import static Presentation.Ui_Views.LoginUI.resetUILogin;
import static Presentation.Ui_Views.LoginUI.setUsernameLogin;
import static Presentation.Ui_Views.PianoFrame.centralPanel;
import static Presentation.Ui_Views.PianoFrame.mainFrame;
import static Presentation.Ui_Views.PianoTilesUISelector.setKeys;
import static Presentation.Ui_Views.SignUpUI.*;
import static Presentation.Ui_Views.SpotiUI.spotiPanel;

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
    private static final UserManager loginUserManager = new UserManager();
    private static final SongManager songManager = new SongManager();
    private static final PlaylistManager playlistManager = new PlaylistManager();
    private static final TilesManager tilesManager = new TilesManager();


    public void singUpStartup(){
        resetUISignUpUI();
        card.show(contenedor, SIGN_UP_UI);
    }

    public void logInStartup(){
        resetUILogin();
        card.show(contenedor, LOGIN_UI);
    }

    public void enterAsAGuest(String name, String psw){
        if(logIn(name, psw)){
            setUsernameLogin("guest");
            playlistManager.setPlaylists(UserManager.getUser().getUserName());
            SpotiFrameManager.addPlaylists(new BusinessFacadeImp().getPlaylistManager().getPlaylists());
            setSong();
            card.show(contenedor, PIANO_FRAME);
        }
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

    public boolean deleteAccount() {
        for(int i = 0; i< songManager.getSongs().size();i++){
           if(songManager.getSongs().get(i).getCreator().equals(UserManager.getUser().getUserName()) ||
                   songManager.getSongs().get(i).getAuthorName().equals(UserManager.getUser().getUserName())){
               new File(songManager.getSongs().get(i).getSongFile()).delete();
           }
        }
        return loginUserManager.deleteUser();

    }

    public boolean noteRecordingUpdate(ArrayList<RecordingNotes> recordingNotes, float recordingTime){
            JPanel myPanel = new JPanel();
            JTextField titleField = new JTextField(20);
            myPanel.add(titleField);
            JCheckBox box = new JCheckBox("is public?");
            myPanel.add(box);

            JOptionPane.showMessageDialog(null, myPanel, "Enter a title for the song", JOptionPane.INFORMATION_MESSAGE);

            recordedNotesSend(recordingNotes, titleField.getText(), box.isSelected(), recordingTime);
        return false;
    }

    public boolean startRecordingNote(){
        return true;
    }

    public boolean modifyKey(String tileSelected, KeyEvent e, int KeyExisted){
        if(KeyExisted == -1) {
            JOptionPane.showMessageDialog(contenedor,
                    "This key is already assigned!", "Modify keys error" , JOptionPane.ERROR_MESSAGE);
            return true;
        }else{
            FreePianoUI.modifyKey(Translator.getFromTile(tileSelected), e);
            Translator.setKeys(KeyExisted, e.getExtendedKeyCode());
            return false;
        }
    }

    public void readingMidiFiles(int index){
        setKeys(tilesManager.getListTiles());
        try {
            setKeys(ReadMidi.readMidi(songManager.getSong(index).getSongName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Playlist createPlaylist(){
        Playlist myPlayList = null;

        String myStr = (String)JOptionPane.showInputDialog(
                null, "Which name is your playlist going to have?",
                "Playlist Creator", JOptionPane.PLAIN_MESSAGE, null, null, "New Playlist");

        if(myStr != null && myStr.length() > 0 && myStr.indexOf('\'') == -1){
            new BusinessFacadeImp().newPlaylist(myStr);
            myPlayList = playlistManager.getFromName(myStr);
            PlaylistUI.setSongsPlaylists(myPlayList);
            CardLayout myCard = (CardLayout)spotiPanel.getLayout();
            myCard.show(spotiPanel, PLAYLIST_UI);
            SpotiUI.addPlaylists(playlistManager.getPlaylists());
        } else {
            JOptionPane.showMessageDialog(null,
                    "The input is not correct!", "Create Playlist Error" ,
                    JOptionPane.ERROR_MESSAGE);
        }
        return myPlayList;
    }

    public void setAllKeys(){
        setKeys(tilesManager.getListTiles());
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
    public void setSongUser() {songManager.setSongs(UserManager.getUser().getUserName());}

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

    @Override
    public void initializeWebScrapping(){
        new WebScrapping();
    }



}
