package Business;

import Business.Entities.Playlist;
import Business.Entities.User;
import Persistence.SQL.Csv.PlaylistCsvDAO;

import java.util.ArrayList;

public class PlaylistManager {
    private ArrayList<Playlist> playlists;
    private final PlaylistCsvDAO playlistCsvDAO = new PlaylistCsvDAO();


    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String username) {
        playlists = playlistCsvDAO.getPlaylistByUser(username);
    }
    public boolean eliminateSongFromPlaylist(String playlistName, String songName){
        return playlistCsvDAO.deleteSongFromPlaylist(playlistName, songName);
    }
    public boolean addSongToPlaylist(String playlistName, String songName){
        return playlistCsvDAO.addSongToPlaylist(playlistName, songName);
    }

    public Playlist getFromName(String name){
        int i = 0;
        boolean found = false;
        while(i< playlists.size() && !found){
            if(playlists.get(i).getPlaylistName().equals(name)){
                found = true;
            }else{
                i++;
            }
        }
        if(found){
            return playlists.get(i);
        }else{
            return null;
        }
    }

    public boolean newPlaylist(String playlist, String username){
        return playlistCsvDAO.savePlaylist( playlist, username);
    }
}
