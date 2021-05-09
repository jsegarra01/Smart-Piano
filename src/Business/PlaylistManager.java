package Business;

import Business.Entities.Playlist;
import Business.Entities.User;
import Persistence.SQL.Csv.PlaylistCsvDAO;

import java.util.ArrayList;

public class PlaylistManager {
    private ArrayList<Playlist> playlists;
    private PlaylistCsvDAO playlistCsvDAO = new PlaylistCsvDAO();


    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String username) {
        playlists = playlistCsvDAO.getPlaylistByUser(username);
    }
}
