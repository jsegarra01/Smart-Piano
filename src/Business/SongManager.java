package Business;

import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.SongRecorded;
import Persistence.SQL.Csv.LoginUserCsvDAO;
import Persistence.SQL.Csv.SongCsvDAO;
import Business.Entities.User;
import Persistence.SQL.Csv.LoginUserCsvDAO;
import Persistence.SQL.Csv.SongCsvDAO;
import Persistence.SongDAO;

import java.util.ArrayList;
import java.util.Date;

import static Business.Entities.SongToMidi.writeMidi;

import static Business.UserManager.getUser;

public class SongManager {
    private Song song;
    private SongCsvDAO songManager = new SongCsvDAO();
    LoginUserCsvDAO loginUserCsvDAO = new LoginUserCsvDAO();
    private static ArrayList<Song> songs;
    private static ArrayList<String> songNames = new ArrayList<>();

    public ArrayList<String> getSongNames() {
        return songNames;
    }

    public void saveRecording(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPublic).getRecordingNotes(), endtime);
        song = new Song(songName, UserManager.getUser().getUserName(), endtime, isPublic, "Songs/" + songName + ".mid", UserManager.getUser().getUserName());
        songManager.saveSong(song);
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song getSong(int index) {
        return songs.get(index);
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
