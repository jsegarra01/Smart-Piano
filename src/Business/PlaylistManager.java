package Business;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Business.Entities.User;
import Persistence.SQL.Csv.PlaylistCsvDAO;
import Persistence.SQL.Csv.SongCsvDAO;

import java.util.ArrayList;

public class PlaylistManager {
    private ArrayList<Playlist> playlists;
    private final PlaylistCsvDAO playlistCsvDAO = new PlaylistCsvDAO();
    private final SongCsvDAO songCsvDAO = new SongCsvDAO();


    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String username) {
        playlists = playlistCsvDAO.getPlaylistByUser(username);
    }
    public boolean eliminateSongFromPlaylist(String playlistName, String songName){
        boolean check = deleteFromPlaylist(playlistName, songName);
        if(check){
            return playlistCsvDAO.deleteSongFromPlaylist(playlistName, songName);
        }
        return false;
    }
    public boolean addSongToPlaylist(String playlistName, String songName){
        boolean check = addToPlaylist(playlistName, songName);
        if(check){
            return playlistCsvDAO.addSongToPlaylist(playlistName, songName);
        }
        return false;


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
        playlists.add(new Playlist(playlist, username));
        return playlistCsvDAO.savePlaylist( playlist, username);
    }

    private boolean addToPlaylist(String playlist, String songName){
        int i = 0;
        boolean found = false;
        while(i< playlists.size() && !found){
            if(playlists.get(i).getPlaylistName().equals(playlist)){
                found = true;
            }else{
                i++;
            }
        }
        if(found){
            Song song = songCsvDAO.getSongByName(songName);
            if(song!=null){
                playlists.get(i).getSongs().add(song);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    private boolean deleteFromPlaylist(String playlist, String songName){
        int i = 0;
        int j = 0;
        boolean found1 = false;
        boolean found2 = false;
        while(i< playlists.size() && !found1){
            if(playlists.get(i).getPlaylistName().equals(playlist)){
                found1 = true;
            }else{
                i++;
            }
        }
        if(found1){
            while(j<playlists.get(i).getSongs().size() && !found2){
                if(playlists.get(i).getSongs().get(j).getSongName().equals(songName)){
                    found2 = true;
                }else{
                    j++;
                }
                if(found2){
                    playlists.get(i).getSongs().remove(j);
                    return true;
                }
            }
        }
        return false;
    }


}
