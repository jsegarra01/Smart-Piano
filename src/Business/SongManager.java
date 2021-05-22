package Business;

import Business.Entities.*;
import Persistence.SQL.Csv.SongCsvDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static Business.Entities.SongToMidi.writeMidi;

/**
 * SongManager
 *
 * The "SongManager" class will contain the different methods that are needed to control the songs
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class SongManager {
    private Song song;
    private final SongCsvDAO songManager = new SongCsvDAO();
    private static ArrayList<Song> songs;
    private static final ArrayList<String> songNames = new ArrayList<>();

    /**
     * Gets all song names
     * @return List of song names
     */
    public ArrayList<String> getSongNames() {
        return songNames;
    }

    /**
     * Saves a song created by the user to a midi file
     * @param recordedNotes List of notes played
     * @param songName Name of the song
     * @param isPublic True if public, false if private
     * @param endtime Duration of the song
     */
    public void saveRecording(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPublic).getRecordingNotes(), endtime);
        song = new Song(songName, UserManager.getUser().getUserName(), endtime, isPublic, "Songs/" + songName + ".mid", UserManager.getUser().getUserName());
        songManager.saveSong(song);
        songs.clear();
        setSongs(UserManager.getUser().getUserName());
    }

    /**
     * Gets all songs
     * @return List of songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Gets a song from a given index
     * @param index Position in the list of the song we want to get
     * @return The song we are looking for
     */
    public Song getSong(int index) {
        return songs.get(index);
    }

    /**
     * Loads all songs and song names from the database
     */
    public void setSongs() {
        //songs = songManager.getAllSongs(getUser());
        songNames.clear();
        songs = songManager.getAllSongs();
        for (Song song : songs) {
            songNames.add(song.getSongName());
        }
    }

    /**
     * Loads all songs and song names from a user
     * @param username The user name we want the songs from
     */
    public void setSongs(String username) {
        //songs = songManager.getAllSongs(getUser());
        songNames.clear();
        songs = songManager.getAllSongs(username);
        for (Song song : songs) {
            songNames.add(song.getSongName());
        }
    }

    /**
     * Gets the 5 more played songs
     * @return The 5 more played songs
     */
    public ArrayList<Song> getTopFive(){
        ArrayList<Song> aux = songs;
        aux.sort(this::compare);
        ArrayList<Song> topFive = new ArrayList<>();
        for(int i=0; i<5; i++){
            topFive.add(aux.get(i));
        }
        return topFive;
    }

    /**
     * Compares two songs on the times they have been played
     * @param song1 First song
     * @param song2 Second song
     * @return 1 if the first song has been played more times than the second, -1 elsewhere
     */
    public int compare(Song song1, Song song2) {
        if(song1.getTimesPlayed() <= song2.getTimesPlayed()){
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Calls the function to update the times played of a song
     * @param song
     */
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
