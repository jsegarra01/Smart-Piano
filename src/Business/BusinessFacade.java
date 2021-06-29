package Business;

import Business.Entities.*;

import java.awt.event.KeyEvent;
import java.awt.image.Kernel;
import java.util.ArrayList;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" interface will contain the different methods that are needed to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 24 Apr 2021
 *
 */
public interface BusinessFacade {


    //void singUpStartup();
    /**
     * Checks if the user can log in or not
     * @param username Username string which the user has inputted while logging in
     * @param password Password string which the user has inputted while logging in
     * @return Boolean: 1 if it can log in, 0 if it cannot log in
     */
    boolean logIn(String username, String password);

    /**
     * Checks if the user can do the sign up or not
     * @param username Username string which the user has inputted while signing up
     * @param mail Mail string which the user has inputted while signing up
     * @param password Password string which the user has inputted while signing up
     * @param passwordConfirm PasswordConfirmation string which the user has inputted while signing up
     * @return Boolean: 1 if it can sign up in, 0 if it cannot sign up
     */
    boolean SignUp(String username, String mail, String password, String passwordConfirm);


    /**
     * Deletes the account and everything involved with the user introduced
     */

    void deleteAccount();

    /**
     * Gets the arraylist of recorded notes from the UI
     * @param recordedNotes array with the notes
     */
    void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPrivate, float endtime);


    /**
     * Obtains and saves the songs of the user logged
     */
    void setSongUser();

    void setSong();

    /**
     * Gets the all the songs of the user
     * @return String with all the name of the songs
     */
    ArrayList<String> getSongName();

    boolean modifyKey(int KeyExisted);

    //void noteRecordingUpdate(ArrayList<RecordingNotes> recordingNotes, float recordingTime);

    void enterAsAGuest(/*String name, String psw*/);

    //void logInStartup();

    Playlist createPlaylist(String myStr);

    Song getSong(int index);

    Playlist getPlaylist(String name);

    boolean deleteSongFromPlaylist(String playlistName, String songName);

    boolean addSongToPlaylist(String playlistName, String songName);

    void deleteSong(int i);

    void setTileArray(int songIndex);

    ArrayList<Keys> getTiles();

    boolean newPlaylist(String playlist);

    void resetTilesKeys();

    void initializeWebScrapping();

    /**
     * If an exception throws an error, sends the message directly to the errorManager
     * @param errorFound int. type of error found
     */
    void setError(int errorFound);

    ArrayList<Song> getSongs();

    Stadistics getStats(int i);

    void updateSong(Song song);

    ArrayList<Song> getTopFive();

    void addStats(Stadistics stats);

    void setPlaylists();

    ArrayList<Playlist> getPlaylists();

}
