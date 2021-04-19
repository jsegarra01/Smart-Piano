import java.util.ArrayList;

public interface SongDAO {
    void saveSong(Song mySaveSong);
    void updateSong(Song mySong);
    void deleteSong(Song mySong);
    Song getSongByID(String id);
    ArrayList<Song> getAllSongs(User myUser);
    ArrayList<Song> getAllSongs();
}
