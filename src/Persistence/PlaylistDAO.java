package Persistence;

import Business.Entities.Playlist;
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
public interface PlaylistDAO {

    /**
     *
     * @param playlist
     */
    boolean savePlaylist(Playlist playlist);

    /**
     *
     * @param playlist
     */
    boolean deletePlaylist(Playlist playlist);

    /**
     *
     * @param username
     * @return
     */
    ArrayList<Playlist> getPlaylistByUser(String username);

}
