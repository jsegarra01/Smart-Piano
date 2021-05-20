package Persistence;
import Business.Entities.Song;
import Business.Entities.Stadistics;
//import Business.Entities.TopSongs;
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
     * @param username
     * @return
     */
    ArrayList<Song> getAllSongs(String username);

    /**
     *
     * @return
     */
    ArrayList<Song> getAllSongs();

    /**
     *
     * @return
     */
    boolean updateTimesPlayed(Song song);

    /*
    ArrayList<Song> getPopularSongs();*/

    boolean saveStadistics(Stadistics myStats);

    Stadistics getStadisticsHour(int hour);

    //boolean saveListenedSongs(TopSongs topSongs);

    //TopSongs getListenedSongs(String name);
    Song getSongByName(String name);
}
