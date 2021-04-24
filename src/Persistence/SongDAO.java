package Persistence;
import Business.Entities.Song;
import Business.Entities.User;
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
     *
     * @param mySaveSong
     * @return
     */
    boolean saveSong(Song mySaveSong);


    /**
     *
     * @param mySong
     */
    boolean deleteSong(Song mySong);

    /**
     *
     * @param id
     * @return
     */
    Song getSongByID(int id);

    /**
     *
     * @param myUser
     * @return
     */
    ArrayList<Song> getAllSongs(User myUser);

    /**
     *
     * @return
     */
    ArrayList<Song> getAllSongs();

    /**
     *
     * @return
     */
    ArrayList<Song> getPopularSongs();
}
