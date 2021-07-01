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
 * @version 2.0 28 June 2021
 *
 */
public class SongManager {
    private final SongDAO songManager;
    private ArrayList<Song> songs;
    private ArrayList<String> songNames;

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
        if(!saveSongWithDate(song) || !setSongs(UserManager.getUser().getUserName()) ){
            return false;
        }
        songs.clear();
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
     * Method that gets a song from a given index from the list of ordered songs
     * @param index Position in the list of the song we want to get
     * @return The song we are looking for
     */
    public Song getSongOrdered(int index){
        int i = 0;
        boolean found = false;
        while(i< songs.size() && !found ){
            if(songNames.get(index).matches(songs.get(i).getSongName())){
                found = true;
            }else{
                i++;
            }
        }
        if(found){
            return songs.get(i);
        }
        return null;
    }

    /**
     * Loads all songs and song names from the database
     */
    public boolean setSongs() {
        try {
            songNames.clear();
            songs = songManager.getAllSongs();
            ArrayList<Song> aux = new ArrayList<>(songs);
            aux.sort((song1, song2) -> Integer.compare(song2.getTimesPlayed(), song1.getTimesPlayed()));
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
            aux.sort((song1, song2) -> Integer.compare(song2.getTimesPlayed(), song1.getTimesPlayed()));
            for (Song song : aux) {
                songNames.add(song.getSongName());
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Gets the 5 more played songs
     * @return The 5 more played songs
     */
    public ArrayList<Song> getTopFive(){
        ArrayList<Song> aux = new ArrayList<>(songs);
        aux.sort((song1, song2) -> Integer.compare(song2.getTimesPlayed(), song1.getTimesPlayed()));
        ArrayList<Song> topFive = new ArrayList<>();
        for(int i=0; i<5 && i<aux.size(); i++){
            topFive.add(aux.get(i));
        }
        return topFive;
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
        return songManager.deleteSong(song);
    }

    /**
     * Method that saves the song with the specific date
     * @param song Defines the song to be saved
     */
    public boolean saveSongWithDate(Song song){
        return songManager.saveSongWithDate(song);
    }

    /**
     * Method that deletes a the file of a song
     * @param i Defines the position of the song to be deleted
     */
    public void deleteSongFile(int i){
        songManager.deleteSongFile(songs.get(i).getSongFile());

    }
}
