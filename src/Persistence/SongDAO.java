package Persistence;
import Business.Song;
import Business.User;

import java.util.ArrayList;

public interface SongDAO {
    boolean saveSong(Song mySaveSong);
    void updateSong(Song mySong);
    void deleteSong(Song mySong);
    Song getSongByID(int id);
    ArrayList<Song> getAllSongs(User myUser);
    ArrayList<Song> getAllSongs();
    ArrayList<Song> getPopularSongs();
}
