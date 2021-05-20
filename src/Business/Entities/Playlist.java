package Business.Entities;

import java.util.ArrayList;

public class Playlist {

    private int playlistId;
    private final String playlistName;
    private final ArrayList<Song> songs;
    private final String user;

    public Playlist(int playlistId, String playlistName, ArrayList<Song> songs, String user) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songs = songs;
        this.user = user;
    }
    public Playlist(String playlistName, String user) {
        this.playlistName = playlistName;
        this.user = user;
        songs = new ArrayList<>();
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
