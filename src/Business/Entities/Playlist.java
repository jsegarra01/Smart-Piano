package Business.Entities;

import java.util.ArrayList;

/**
 * Playlist
 *
 * The "Playlist" class will contain the different methods needed to set and access the playlists
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class Playlist {

    private int playlistId;
    private final String playlistName;
    private final ArrayList<Song> songs;
    private final String user;

    /**
     * Constructor of the Playlist
     * @param playlistId Id of the playlist
     * @param playlistName Name of the playlist
     * @param songs List of songs of the playlist
     * @param user Creator of the playlist
     */
    public Playlist(int playlistId, String playlistName, ArrayList<Song> songs, String user) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songs = songs;
        this.user = user;
    }

    /**
     * Constructor of the playlist
     * @param playlistName Name of the playlist
     * @param user Creator of the playlist
     */
    public Playlist(String playlistName, String user) {
        this.playlistName = playlistName;
        this.user = user;
        songs = new ArrayList<>();
    }

    /**
     * Gets the id of the playlist
     * @return The id of the playlist
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Gets the name of the playlist
     * @return The name of the playlist
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Gets the songs of a playlist
     * @return The songs of a playlist
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
}
