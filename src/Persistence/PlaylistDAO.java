package Persistence;

import Business.Playlist;

import java.util.ArrayList;

public interface PlaylistDAO {
    void savePlaylist(Playlist playlist);
    void deletePlaylist(Playlist playlist);
    ArrayList<Playlist> getPlaylistByUser(String username);
    Playlist getPlaylistById(int id);
}
