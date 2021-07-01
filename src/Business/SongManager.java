package Business;

import Business.Entities.*;
import Persistence.SQL.Csv.SongCsvDAO;
import Persistence.SongDAO;

import java.sql.SQLException;
import java.util.*;

import static Persistence.Files.SongToMidi.writeMidi;

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
    private final SongDAO songManager;
    private static ArrayList<Song> songs;
    private static ArrayList<String> songNames;

    public SongManager(){
        songManager = new SongCsvDAO();
        songNames = new ArrayList<>();
    }

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
    public boolean saveRecording(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPublic).getRecordingNotes(), endtime);
        Song song = new Song(songName, UserManager.getUser().getUserName(), endtime, new Date(),isPublic, "Songs/" + songName + ".mid", UserManager.getUser().getUserName(), 0);
        if(!saveSongWithDate(song) || setSongs(UserManager.getUser().getUserName())){
            return false;
        }
        songs.clear();
        setSongs(UserManager.getUser().getUserName());
        return true;
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
    public boolean setSongs() {
        //songs = songManager.getAllSongs(getUser());
        try {
            songNames.clear();
            songs = songManager.getAllSongs();
            ArrayList<Song> aux = new ArrayList<>(songs);
            aux.sort(this::compare);
            for (Song song : aux) {
                songNames.add(song.getSongName());
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    /**
     * Loads all songs and song names from a user
     * @param username The user name we want the songs from
     */
    public boolean setSongs(String username) {
        try {
            songNames.clear();
            songs = songManager.getAllSongs(username);
            ArrayList<Song> aux = new ArrayList<>(songs);
            aux.sort(this::compare);
            for (Song song : aux) {
                songNames.add(song.getSongName());
            }
            return false;
        } catch (SQLException e) {
            return true;
        }
    }

    /**
     * Gets the 5 more played songs
     * @return The 5 more played songs
     */
    public ArrayList<Song> getTopFive(){
        ArrayList<Song> aux = new ArrayList<>(songs);
        aux.sort(this::compare);
        ArrayList<Song> topFive = new ArrayList<>();
        for(int i=0; i<5 && i<aux.size(); i++){
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
    private int compare(Song song1, Song song2) {
        if(song1.getTimesPlayed() < song2.getTimesPlayed()){
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Calls the function to update the times played of a song
     * @param song Defines the song to be updated
     */
    public boolean updateSongPlayed(Song song){
        return songManager.updateTimesPlayed(song);
    }

    /**
     * Saves the desired stadistics
     * @param myStats Stadistics we want to save
     */
    public boolean addingStatistics(Stadistics myStats){
         return songManager.saveStadistics(myStats);
    }

    /**
     * Gets all the stadistics from a given hour
     * @param hour The hour we want the stadistics from
     * @return The desired stadistics
     */
    public Stadistics gettingStadistics(int hour){
        return songManager.getStadisticsHour(hour);
    }

    /**
     * Calls the method to delete a song
     * @param song The song we want to delete
     * @return True if deleted, false if not
     */
    public boolean deleteSong(Song song){
        songManager.deleteSongFile(song.getSongFile());
        return songManager.deleteSong(song);
    }

    /**
     *
     * @param song
     */
    public boolean saveSongWithDate(Song song){
        return songManager.saveSongWithDate(song);
    }

    /**
     *
     * @param i
     */
    public void deleteSongFile(int i){
        songManager.deleteSongFile(songs.get(i).getSongFile());
    }
}
