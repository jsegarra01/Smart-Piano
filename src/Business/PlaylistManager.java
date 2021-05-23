package Business;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Business.Entities.User;
import Persistence.SQL.Csv.PlaylistCsvDAO;
import Persistence.SQL.Csv.SongCsvDAO;

import java.util.ArrayList;

/**
 * PlaylistManager
 *
 * The "PlaylistManager" class will contain the different methods that are needed to control the playlists
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class PlaylistManager {
    private ArrayList<Playlist> playlists;
    private final PlaylistCsvDAO playlistCsvDAO = new PlaylistCsvDAO();
    private final SongCsvDAO songCsvDAO = new SongCsvDAO();

    /**
     * Gets all the playlists
     * @return List of playlists
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Gets all the playlists of a user
     * @param username The user from whom we will get the playlists
     */
    public void setPlaylists(String username) {
        playlists = playlistCsvDAO.getPlaylistByUser(username);
    }

    /**
     * Deletes a song from a playlist
     * @param playlistName The playlist to delete from
     * @param songName The song to delete
     * @return True if delete, false if not
     */
    public boolean eliminateSongFromPlaylist(String playlistName, String songName){
        boolean check = deleteFromPlaylist(playlistName, songName);
        if(check){
            return playlistCsvDAO.deleteSongFromPlaylist(playlistName, songName);
        }
        return false;
    }

    /**
     * Adds a song to a playlist
     * @param playlistName The playlist where we will add the song
     * @param songName The song to add
     * @return True if added, false if not
     */
    public boolean addSongToPlaylist(String playlistName, String songName){
        boolean check = addToPlaylist(playlistName, songName);
        if(check){
            return playlistCsvDAO.addSongToPlaylist(playlistName, songName);
        }
        return false;


    }

    /**
     * Gets a playlist from the list of playlists
     * @param name Name of the playlist we want to get
     * @return The playlist
     */
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

    /**
     * Creates a new playlist
     * @param playlist Name of the playlist
     * @param username Name of the creator
     * @return True if created, false if not
     */
    public boolean newPlaylist(String playlist, String username){
        playlists.add(new Playlist(playlist, username));
        return playlistCsvDAO.savePlaylist( playlist, username);
    }

    /**
     * TODO: quina es la diferencia entre aquesta i la de 3 funcions amunt
     * @param playlist
     * @param songName
     * @return
     */
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

    /**
     * TODO: quina es la diferencia entre aquesta i la de 3 funcions amunt
     * @param playlist
     * @param songName
     * @return
     */
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
