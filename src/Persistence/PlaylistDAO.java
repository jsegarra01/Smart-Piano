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
     * Method that inserts a new playlist into the database
     * @param playlist Defines the name of the playlist to be inserted
     * @param username Defines the name of the username that has created the playlist
     */
    boolean savePlaylist(String playlist, String username);

    /**
     * Method that deletes a playlist
     * @param playlist Defines the playlist to be deleted
     */
    boolean deletePlaylist(Playlist playlist);

    /**
     * Method that gets the array of playlists from a user
     * @param username Defines the username of the playlist
     * @return ArrayList of playlists whose author is the user defined in the parameter
     */
    ArrayList<Playlist> getPlaylistByUser(String username);

    /**
     * Method that deletes a song from a playlist from the database
     * @param playlistName Defines the name of the playlist
     * @param songName Defines the name of the song to be deleted
     * @return True if there has been no error, false if there has been
     */
    boolean deleteSongFromPlaylist(String playlistName, String songName);

    /**
     * Method that adds a song into a playlist
     * @param playlistName Defines the name of the playlist
     * @param songName Defines the name of the song to be added
     * @return True if there has been no error, false if there has been
     */
    boolean addSongToPlaylist(String playlistName, String songName);

}
