package Business;

import Business.Entities.*;
import Persistence.SQL.Csv.SongCsvDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static Business.Entities.SongToMidi.writeMidi;

public class SongManager {
    private final SongCsvDAO songManager = new SongCsvDAO();
    private static ArrayList<Song> songs;
    private static final ArrayList<String> songNames = new ArrayList<>();
    private static final BusinessFacadeImp businessFacade = new BusinessFacadeImp();

    public ArrayList<String> getSongNames() {
        return songNames;
    }

    public void saveRecording(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPublic).getRecordingNotes(), endtime);
        Song song = new Song(songName, UserManager.getUser().getUserName(), endtime, new Date(),isPublic, "Songs/" + songName + ".mid", UserManager.getUser().getUserName(), 0);
        saveSongWithDate(song);
        songs.clear();
        setSongs(UserManager.getUser().getUserName());
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song getSong(int index) {
        return songs.get(index);
    }

    public void setSongs() {
        //songs = songManager.getAllSongs(getUser());
        try {
            songNames.clear();
            songs = songManager.getAllSongs();
            for (Song song : songs) {
                songNames.add(song.getSongName());
            }
        } catch (SQLException e) {
            businessFacade.setError(0);
        }
    }

    public void setSongs(String username){
        //songs = songManager.getAllSongs(getUser());
        try {
            songNames.clear();
            songs = songManager.getAllSongs(username);
            for (Song song : songs) {
                songNames.add(song.getSongName());
            }
        } catch (SQLException e) {
            businessFacade.setError(0);
        }
    }

    public ArrayList<Song> getTopFive(){
        ArrayList<Song> aux = songs;
        aux.sort(this::compare);
        ArrayList<Song> topFive = new ArrayList<>();
        for(int i=0; i<5 && i<aux.size(); i++){
            topFive.add(aux.get(i));
        }
        return topFive;
    }

    public int compare(Song song1, Song song2) {
        if(song1.getTimesPlayed() <= song2.getTimesPlayed()){
            return 1;
        } else {
            return -1;
        }
    }

    public void updateSongPlayed(Song song){
        songManager.updateTimesPlayed(song);
    }

    public void addingStadistics(Stadistics myStats){
        songManager.saveStadistics(myStats);
    }

    public Stadistics gettingStadistics(int hour){
        return songManager.getStadisticsHour(hour);
    }

    public boolean deleteSong(Song song){
        return songManager.deleteSong(song);
    }

    public void saveSong (Song song) {
        songManager.saveSong(song);
    }

    public void saveSongWithDate(Song song){
        songManager.saveSongWithDate(song);
    }

    public Song getSongByName(String name){
        return songManager.getSongByName(name);
    }
}
