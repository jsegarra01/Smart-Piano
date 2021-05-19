package Business;

import Business.Entities.*;
import Persistence.SQL.Csv.SongCsvDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static Business.Entities.SongToMidi.writeMidi;

public class SongManager {
    private Song song;
    private final SongCsvDAO songManager = new SongCsvDAO();
    private static ArrayList<Song> songs;
    private static final ArrayList<String> songNames = new ArrayList<>();

    public ArrayList<String> getSongNames() {
        return songNames;
    }

    public void saveRecording(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPublic).getRecordingNotes(), endtime);
        song = new Song(songName, UserManager.getUser().getUserName(), endtime, isPublic, "Songs/" + songName + ".mid", UserManager.getUser().getUserName());
        songManager.saveSong(song);
        songs.clear();
        setSongs();
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song getSong(int index) {
        return songs.get(index);
    }

    public void setSongs() {
        //TODO GET ALL THE PUBLIC SONGS + PRIVATE SONGS IF USER != GUEST
        //songs = songManager.getAllSongs(getUser());
        songNames.clear();
        songs = songManager.getAllSongs();
        for (Song song : songs) {
            songNames.add(song.getSongName());
        }
    }

    public ArrayList<Song> getTopFive(){
        ArrayList<Song> aux = songs;
        aux.sort(this::compare);
        ArrayList<Song> topFive = new ArrayList<>();
        for(int i=0; i<5; i++){
            topFive.add(aux.get(i));
        }
        return topFive;
    }


    public int compare(Song song1, Song song2) {
        if(song1.getTimesPlayed() < song2.getTimesPlayed()){
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
/*
    public void addingInfoSongPlayed(TopSongs songs){
        songManager.saveListenedSongs(songs);
    }*/

    public boolean deleteSong(Song song){
        return songManager.deleteSong(song);
    }
}
