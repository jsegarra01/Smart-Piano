package Persistence;
import Business.Entities.Song;
import Business.Entities.Stadistics;
//import Business.Entities.TopSongs;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface that abstracts the persistence of groups from upper layers.
 *
 * <p>In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public interface SongDAO {

    /**
     * Method that stores the song in the database
     * @param mySaveSong Defines the song to be stored
     */
    boolean saveSong(Song mySaveSong);

    /**
     * Method that stores the song in the database with the date
     * @param mySaveSong Defines the song to be stored
     * @return boolean that returns a true if it has been done correctly, false if not
     */
    boolean saveSongWithDate(Song mySaveSong);

    /**
     * Method that deletes the song depending on the id from the database
     * @param mySong Defines the song to be deleted from the database
     */
    boolean deleteSong(Song mySong);

    /**
     * Method that gets the song by its id
     *
     * @param id Defines the id of the song
     * @return Class that stores the song that has been got from the database
     */
    Song getSongByID(int id);

    /**
     * Method that gets all the songs belonging to the user
     *
     * @param username Defines the user from which the songs will be got
     * @return List of songs that have been created by the user
     */
    ArrayList<Song> getAllSongs(String username) throws SQLException;

    /**
     * Method that gets all the songs in the database
     *
     * @return List of songs from the database
     */
    ArrayList<Song> getAllSongs() throws SQLException;

    /**
     * Method that updates the times played that a song has been
     * @param song Defines the song to be updated
     * @return boolean that returns a true if it has been correct, false if not
     */
    boolean updateTimesPlayed(Song song);

    /**
     * Method that saves the stadistics into the databases
     *
     * @param myStats defines the stadistics of the song (the hour, how many songs have been played and for how much)
     * @return boolean that indicates if the information has been saved correctly
     */
    boolean saveStadistics(Stadistics myStats);

    /**
     * Method that gets from the databases the stadistics for a specific hour
     *
     * @param hour integer that indicates the hour
     * @return Stadistics for that hour
     */
    Stadistics getStadisticsHour(int hour);

    /**
     * Method that gets a song by its name
     * @param name Defines the name of the song
     * @return Song to be returned
     */
    Song getSongByName(String name);

    /**
     * Method that deletes the file of a song
     * @param path Defines the path in which the file can be found
     */
    void deleteSongFile(String path);
}
