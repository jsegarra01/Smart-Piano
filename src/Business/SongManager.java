package Business;

import Business.Entities.Song;
import Business.Entities.User;
import Persistence.SQL.Csv.LoginUserCsvDAO;
import Persistence.SQL.Csv.SongCsvDAO;
import Persistence.SongDAO;

import java.util.ArrayList;

import static Business.UserManager.getUser;

public class SongManager {
    private static ArrayList<Song> songs;
    private SongCsvDAO songManager = new SongCsvDAO();
    private static ArrayList<String> songNames = new ArrayList<>();

    public ArrayList<String> getSongNames() {
        return songNames;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs() {
        //TODO GET ALL THE PUBLIC SONGS + PRIVATE SONGS IF USER != GUEST
       // songs = songManager.getAllSongs(getUser());
        songs = songManager.getAllSongs();
        for (Song song : songs) {
            songNames.add(song.getSongName());
        }
    }
}
