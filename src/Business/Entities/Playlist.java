package Business.Entities;

import java.util.ArrayList;

public class Playlist {

    private int playlistId;
    private String playlistName;
    private ArrayList<Song> songs;
    private String user;

    public Playlist(int playlistId, String playlistName, ArrayList<Song> songs, String user) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songs = songs;
        this.user = user;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
