package Business;

import Business.Entities.Playlist;
import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.*;
import Business.Threads.WebScrapping;
import Presentation.Manager.ErrorsManager;

import java.sql.SQLException;
import java.util.ArrayList;



/**
 * BusinessFacade
 *
 * The "BusinessFacade" class will contain the implementation of the BusinessFacade interface to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 28 June 2021
 *
 */
public class BusinessFacadeImp implements Business.BusinessFacade {

    /*
    Defines the user manager, that controls everything user related
     */
    private static UserManager loginUserManager;
    /*
    Defines the song manager, that controls everything song related
     */
    private static SongManager songManager;
    /*
    Defines the playlist manager, that controls everything playlist related
     */
    private static PlaylistManager playlistManager;

    /*
    Defines the tiles manager, that controls everything tiles related
     */
    private static TilesManager tilesManager;

    /*
    Defines the error manager, that controls everything error related
     */
    private static ErrorsManager errorManager;

    /*
    Defines the BusinessFacade, that will be the one responsible to be called when any functionality wants to be implemented
     */
    private static BusinessFacade businessFacade;

    /**
     * Singleton of the business facade, that checks if it is null and initializes all of the attributes and returns
     * the business facade
     * @return BusinessFacade interface that will be used in order to control the different managers
     */
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
     * Method that logs in to the user "guest" and shows the piano UI layout from the card layout
     */
    @Override
    public void enterAsAGuest(){
            setPlaylists();
            setSong();
    }

    /**
     * Method that checks if the user can log in or not
     * @param username Defines the username string which the user has inputted while logging in
     * @param password Defines the password string which the user has inputted while logging in
     * @return Boolean: 1 if it can log in, 0 if it cannot log in
     */
    @Override
    public boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }

    /**
     * Method that checks if the user can do the sign up or not
     * @param username Defines the username string which the user has inputted while signing up
     * @param mail Defines the mail string which the user has inputted while signing up
     * @param password Defines the password string which the user has inputted while signing up
     * @param passwordConfirm Defines the PasswordConfirmation string which the user has inputted while signing up
     * @return Boolean: 1 if it can sign up in, 0 if it cannot sign up
     */
    @Override
    public boolean SignUp(String username, String mail, String password, String passwordConfirm) {
        if(username==null){
            setError(13);
            return false;
        }else if(mail == null){
            setError(14);
            return false;
        }
        if(password.length()>=8){
            if(password.matches("(?=.*[0-9]).*") && password.matches("(?=.*[a-z]).*") && password.matches("(?=.*[A-Z]).*")){
                if (!password.equals(passwordConfirm)) {
                    setError(17);
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
            }else{
                setError(16);
                return false;
            }
        }else{
            setError(15);
            return false;
        }
    }

    /**
     * Method that deletes the account and everything involved with the user introduced
     */
    @Override
    public void deleteAccount() {
        for(int i = 0; i< songManager.getSongs().size();i++){
           if(songManager.getSongs().get(i).getCreator().equals(UserManager.getUser().getUserName()) ||
                   songManager.getSongs().get(i).getAuthorName().equals(UserManager.getUser().getUserName())){
               songManager.deleteSongFile(i);
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

    /**
     * Method that sets the error in case the parameter determines that the key already existed
     * @param KeyExisted Defines as a -1 if the key existed or not
     * @return Boolean that stores as true if the key existed, a false if not
     */
    @Override
    public boolean modifyKey(int KeyExisted){
        if(KeyExisted == -1) {
            setError(11);
            return true;
        }
        return false;

    }

    /**
     * Method that creates a playlists with the name set in the parameter
     * @param myStr Defines the name which the playlist will be called
     * @return Playlist created with the name set in the parameter. Will return a null if there has been any error
     */
    @Override
    public Playlist createPlaylist(String myStr){
        Playlist myPlayList = null;

        if(myStr != null && myStr.length() > 0 && myStr.indexOf('\'') == -1){
            if(!newPlaylist(myStr)){
                setError(20);
            }else{
                myPlayList = playlistManager.getFromName(myStr);
            }
        } else {
            setError(12);
        }
        return myPlayList;
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
        if(!songManager.saveRecording(recordedNotes,songName,isPublic,endtime)){
            setError(0);
        }
    }


    /**
     * Method that gets the whole playlist with that name
     * @param name Defines the name of the playlist to be found
     * @return Class Playlist that stores the complete information about the playlist found
     */
    @Override
    public Playlist getPlaylist(String name){
        return playlistManager.getFromName(name);
    }

    /**
     * Method that deletes a song from a playlist
     * @param playlistName Name of the playlist we want to delete the song from
     * @param songName Song we want to delete
     * @return Boolean that stores a true if deleted, false if not
     */
    @Override
    public boolean deleteSongFromPlaylist(String playlistName, String songName){
        return playlistManager.eliminateSongFromPlaylist(playlistName, songName);
    }

    /**
     * Method that adds a song to a playlist
     * @param playlistName Defines the name of the playlist we want to add the song to
     * @param songName Defines the name of the song we want to add
     */
    @Override
    public void addSongToPlaylist(String playlistName, String songName){
         if(!playlistManager.addSongToPlaylist(playlistName, songName)){
             setError(19);
         }
    }

    /**
     * Method that obtains and saves the songs of the user logged
     */
    @Override
    public void setSongUser() {
        if(!songManager.setSongs(UserManager.getUser().getUserName())){
            setError(0);
        }
    }

    /**
     * Method that obtains and saves the songs of the user logged as a guest
     */
    @Override
    public void setSong(){
        if(!songManager.setSongs()){
            setError(0);
        }
    }

    /**
     * Method that gets the all the name of songs stored
     * @return Arraylist of String that stores all the names of the songs
     */
    @Override
    public ArrayList<String> getSongName() {
        return songManager.getSongNames();
    }

    /**
     * Method that gets the song corresponding to that index
     * @param index Defines the position which the song can be found in the list of songs
     * @return Song that stores the song with that index in the list of songs
     */
    @Override
    public Song getSong(int index) {
        return songManager.getSong(index);
    }

    /**
     * Method that gets the song corresponding to that index
     * @param index Defines the position which the song can be found in the list of songs
     * @return Song that stores the song with that index in the list of songs
     */
    public Song getSongOrdered(int index){
        return songManager.getSongOrdered(index);
    }

    /**
     * Method that deletes a song given its position
     * @param i Defines the position in the array of songs of the song to be deleted
     */
    @Override
    public void deleteSong(int i){
        if (!songManager.deleteSong(getSong(i))) {
            setError(3);
        }
    }

    /**
     * Method that creates a new playlists with the name from the parameter
     * @param playlist Defines the name the playlist will have
     * @return Boolean that returns a true if everything was correct, false if not
     */
    @Override
    public boolean newPlaylist(String playlist){
        return playlistManager.newPlaylist(playlist, UserManager.getUser().getUserName());
    }

    /**
     * Method that sets the list of tiles
     * @param songIndex Defines the position in the array of songs of the song to be set
     */
    @Override
    public void setTileArray(int songIndex) {
        tilesManager.setListTiles(songIndex);
    }

    /**
     * Method that gets the tiles from the keyboard of the piano
     * @return Arraylist of the class Keys that stores the whole keys
     */
    @Override
    public ArrayList<Keys> getTiles() {
        return tilesManager.getListTiles();
    }

    /**
     * Method that resets the tiles keys
     */
    @Override
    public void resetTilesKeys(){
        tilesManager.resetKeys();
    }


    @Override
    public void initializeWebScrapping(){
        new WebScrapping();
    }

    /**
     * Method that if an exception throws an error, sends the message directly to the errorManager
     * @param errorFound Defines the type of error found
     */
    @Override
    public void setError(int errorFound) {
        errorManager.errorFound(errorFound);
    }

    /**
     * Method that gets all the songs
     * @return Array list of type song that stores all the songs
     */
    @Override
    public ArrayList<Song> getSongs(){
        return songManager.getSongs();
    }

    /**
     * Method that gets the statistics corresponding to the particular hour determined in the parameter
     * @param i Defines the hour to get the statistics
     * @return Statistics class that stores stats about the particular hour
     */
    @Override
    public Stadistics getStats(int i){
        return songManager.gettingStadistics(i);
    }

    /**
     * Method that updates the times a song has been played by adding a 1 in the attribute
     * @param song Defines the song to be updated
     */
    @Override
    public void updateSong(Song song){
        if(!songManager.updateSongPlayed(song)){
            setError(9);
        }
    }

    /**
     * Method that gets the 5 songs that have been played the most (the most popular)
     * @return Arraylist of the class song that stores the 5 songs most played
     */
    @Override
    public ArrayList<Song> getTopFive(){ return songManager.getTopFive(); }

    /**
     * Method that adds or updates the information about the corresponding statistics
     * @param stats Defines the statistics to be added or updated
     */
    @Override
    public void addStats(Stadistics stats) {
        if(!songManager.addingStatistics(stats)){
            setError(9);
        }
    }

    /**
     * Method that sets the playlists
     */
    @Override
    public void setPlaylists() {
        playlistManager.setPlaylists(UserManager.getUser().getUserName());
    }

    /**
     * Method that gets all the playlists from the particular user
     * @return Arraylist of class Playlists that store the concrete playlists from the user
     */
    @Override
    public ArrayList<Playlist> getPlaylists() {
        return playlistManager.getPlaylists();
    }


}
