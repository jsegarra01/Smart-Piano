package Business.Entities;

import java.util.ArrayList;

/**
 * Playlist
 *
 * The "Playlist" class will contain the different methods needed to set and access the playlists
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class Playlist {

    /*
    Defines the id of the playlist
     */
    private int playlistId;

    /*
    Defines the name of the playlist
     */
    private final String playlistName;

    /*
    Defines all the songs belonging to the playlist
     */
    private final ArrayList<Song> songs;

    /*
    Defines the user creator of the playlist
     */
    private final String user;

    /**
     * Constructor of the Playlist
     * @param playlistId Defines the id of the playlist
     * @param playlistName Defines the name of the playlist
     * @param songs Defines the list of songs belonging to the playlist
     * @param user Defines the creator of the playlist
     */
    public Playlist(int playlistId, String playlistName, ArrayList<Song> songs, String user) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songs = songs;
        this.user = user;
    }

    /**
     * Constructor of the playlist
     * @param playlistName Defines the name of the playlist
     * @param user Defines the creator of the playlist
     */
    public Playlist(String playlistName, String user) {
        this.playlistName = playlistName;
        this.user = user;
        songs = new ArrayList<>();
    }

    /**
     * Method that gets the id of the playlist
     * @return Int that stores the id of the playlist
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     * Method that gets the name of the playlist
     * @return String that stores the name of the playlist
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Method that gets the songs of a playlist
     * @return Arraylist of class Song storing the songs of a playlist
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
}
