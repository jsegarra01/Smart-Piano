package Business;

import Business.Entities.*;

import java.util.ArrayList;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" interface will contain the different methods that are needed to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public interface BusinessFacade {


    /**
     * Method that checks if the user can log in or not
     * @param username Defines the username string which the user has inputted while logging in
     * @param password Defines the password string which the user has inputted while logging in
     * @return Boolean: 1 if it can log in, 0 if it cannot log in
     */
    boolean logIn(String username, String password);

    /**
     * Method that checks if the user can do the sign up or not
     * @param username Defines the username string which the user has inputted while signing up
     * @param mail Defines the mail string which the user has inputted while signing up
     * @param password Defines the password string which the user has inputted while signing up
     * @param passwordConfirm Defines the PasswordConfirmation string which the user has inputted while signing up
     * @return Boolean: 1 if it can sign up in, 0 if it cannot sign up
     */
    boolean SignUp(String username, String mail, String password, String passwordConfirm);

    /**
     * Method that deletes the account and everything involved with the user introduced
     */
    void deleteAccount();

    /**
     * Method that saves the recorded notes
     * @param recordedNotes List of notes played
     * @param songName Name of the song
     * @param isPublic True if public, false if private
     * @param endtime Duration of the song
     */
    void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime);

    /**
     * Method that obtains and saves the songs of the user logged
     */
    void setSongUser();

    /**
     * Method that obtains and saves the songs of the user logged as a guest
     */
    void setSong();

    /**
     * Method that gets the all the name of songs stored
     * @return Arraylist of String that stores all the names of the songs
     */
    ArrayList<String> getSongName();

    /**
     * Method that sets the error in case the parameter determines that the key already existed
     * @param KeyExisted Defines as a -1 if the key existed or not
     * @return Boolean that stores as true if the key existed, a false if not
     */
    boolean modifyKey(int KeyExisted);

    /**
     * Method that logs in to the user "guest" and shows the piano UI layout from the card layout
     */
    void enterAsAGuest();

    /**
     * Method that creates a playlists with the name set in the parameter
     * @param myStr Defines the name which the playlist will be called
     * @return Playlist created with the name set in the parameter. Will return a null if there has been any error
     */
    Playlist createPlaylist(String myStr);

    /**
     * Method that gets the song corresponding to that index
     * @param index Defines the position which the song can be found in the list of songs
     * @return Song that stores the song with that index in the list of songs
     */
    Song getSong(int index);

    /**
     * Method that gets the song corresponding to that index from the list ordered
     * @param index Defines the position which the song can be found in the list of songs
     * @return Song that stores the song with that index in the list of songs
     */
    Song getSongOrdered(int index);

    /**
     * Method that gets the whole playlist with that name
     * @param name Defines the name of the playlist to be found
     * @return Class Playlist that stores the complete information about the playlist found
     */
    Playlist getPlaylist(String name);

    /**
     * Method that deletes a song from a playlist
     * @param playlistName Name of the playlist we want to delete the song from
     * @param songName Song we want to delete
     */
    void deleteSongFromPlaylist(String playlistName, String songName);

    /**
     * Method that adds a song to a playlist
     * @param playlistName Defines the name of the playlist we want to add the song to
     * @param songName Defines the name of the song we want to add
     */
    void addSongToPlaylist(String playlistName, String songName);

    /**
     * Method that deletes a song given its position
     * @param i Defines the position in the array of songs of the song to be deleted
     */
    void deleteSong(int i);

    /**
     * Method that sets the list of tiles
     * @param songIndex Defines the position in the array of songs of the song to be set
     */
    void setTileArray(int songIndex);

    /**
     * Method that gets the tiles from the keyboard of the piano
     * @return Arraylist of the class Keys that stores the whole keys
     */
    ArrayList<Keys> getTiles();

    /**
     * Method that creates a new playlists with the name from the parameter
     * @param playlist Defines the name the playlist will have
     * @return Boolean that returns a true if everything was correct, false if not
     */
    boolean newPlaylist(String playlist);

    /**
     * Method that resets the tiles keys
     */
    void resetTilesKeys();

    /**
     *
     */
    void initializeWebScrapping();

    /**
     * Method that if an exception throws an error, sends the message directly to the errorManager
     * @param errorFound Defines the type of error found
     */
    void setError(int errorFound);

    /**
     * Method that gets all the songs
     * @return Array list of type song that stores all the songs
     */
    ArrayList<Song> getSongs();

    /**
     * Method that gets the statistics corresponding to the particular hour determined in the parameter
     * @param i Defines the hour to get the statistics
     * @return Statistics class that stores stats about the particular hour
     */
    Stadistics getStats(int i);

    /**
     * Method that updates the times a song has been played by adding a 1 in the attribute
     * @param song Defines the song to be updated
     */
    void updateSong(Song song);

    /**
     * Method that gets the 5 songs that have been played the most (the most popular)
     * @return Arraylist of the class song that stores the 5 songs most played
     */
    ArrayList<Song> getTopFive();

    /**
     * Method that adds or updates the information about the corresponding statistics
     * @param stats Defines the statistics to be added or updated
     */
    void addStats(Stadistics stats);

    /**
     * Method that sets the playlists
     */
    void setPlaylists();

    /**
     * Method that gets all the playlists from the particular user
     * @return Arraylist of class Playlists that store the concrete playlists from the user
     */
    ArrayList<Playlist> getPlaylists();

    /**
     * Checks if the user is a guest, if it is, it throws an error
     * @return Boolean that stores a true if it is not a guest, false if it is
     */
    boolean isUserNotGuest();

    /**
     * Check if it is possible to delete the user
     * @param i integer of the song array to pass
     * @return true if it possible to delete, false if not
     */
    boolean checkCanDelete(int i);

}
