import java.util.ArrayList;

public interface SongDAO {
    void saveSong(Song mySaveSong);
    void updateSong(Song mySong);
    void deleteSong(Song mySong);
    Song getSongByID(int id);
    ArrayList<Song> getAllSongs(User myUser);
    ArrayList<Song> getAllSongs();
}
