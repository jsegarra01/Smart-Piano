package Persistence.SQL.Csv;

import Business.Entities.Song;
import Business.Entities.User;
import Persistence.SQL.ConnectSQL;
import Persistence.SongDAO;

import java.sql.*;
import java.util.*;

/**
 * Class that implements the methods described in {@link SongDAO} interface, and will be used as a way to
 * physically separate the persistence layer from the rest of the application.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public class SongCsvDAO implements SongDAO {


    /**
     * Method that gets all the songs from the database created by a user
     * @param myUserString Defines the username of the user who we want to get their songs
     * @return List of the class Song that stores the songs created by that User
     */
    private ArrayList<Song> songFromCsv(String myUserString){
        try {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongT as s where s.username " +
                    "like '" + myUserString + "'");
            ArrayList<Song> songs = myRsToSongs(myRs);
            myRs.close();
            return songs;

        } catch (SQLException throwable) {
            return null;
        }
    }

    /**
     * Method that parses the result got from the query and stores it in the list
     * @param myRs Defines the result set in which the information from the query is stored
     * @return List of the class Song that stores the songs created by that User
     * @throws SQLException Throw that makes an exception if there has been any error with the connection to the
     *                      database. It will be handled with the try catch from where it is called.
     */
    private ArrayList<Song> myRsToSongs(ResultSet myRs) throws SQLException {
        ArrayList<Song> songs = new ArrayList<>();
        while(myRs.next()){
            songs.add(new Song(
                    myRs.getInt("songId"),
                    myRs.getString("songName"),
                    myRs.getString("authorsName"),
                    myRs.getFloat("duration"),
                    myRs.getDate("recordingDate"),
                    myRs.getBoolean("publicBoolean"),
                    myRs.getString("songFile"),
                    myRs.getString("username")));
        }
        return songs;
    }

    /**
     * Method that stores the song in the database
     * @param mySaveSong Defines the song to be stored
     */
    @Override
    public boolean saveSong(Song mySaveSong) {
        try {
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("insert into SongT values (" +
                    mySaveSong.getSongId() + ", '" +
                    mySaveSong.getSongName() + "', '" +
                    mySaveSong.getAuthorName() + "', '" +
                    mySaveSong.getDuration() + "', '" +
                    mySaveSong.getRecordingDate() + "', " +
                    mySaveSong.isPublicBoolean() + ", '" +
                    mySaveSong.getSongFile() + "', '" +
                    mySaveSong.getCreator() + "')");
            st.execute();
            return true;

        } catch (SQLException throwable) {
            return false;
        }
    }


    /**
     * Method that deletes the song depending on the id from the database
     * @param mySong Defines the song to be deleted from the database
     */
    @Override
    public boolean deleteSong(Song mySong) {
        try {
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("delete from SongT where songId = '" +
                    mySong.getSongId() + "'");
            st.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Method that gets the song by its id
     * @param id Defines the id of the song
     * @return Class that stores the song that has been got from the database
     */
    @Override
    public Song getSongByID(int id) {
        try {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongT as s where s.songId = " + id);
            if(myRs.next()){
                Song song = new Song(
                        myRs.getInt("songId"),
                        myRs.getString("songName"),
                        myRs.getString("authorsName"),
                        myRs.getFloat("duration"),
                        myRs.getDate("recordingDate"),
                        myRs.getBoolean("publicBoolean"),
                        myRs.getString("songFile"),
                        myRs.getString("username"));
                myRs.close();
                return song;
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            return null;
        }

    }

    /**
     * Method that gets all the songs belonging to the user
     * @param myUser Defines the user from which the songs will be got
     * @return List of songs that have been created by the user
     */
    @Override
    public ArrayList<Song> getAllSongs(User myUser) {

        return songFromCsv(myUser.getUserName());
    }

    /**
     * Method that gets all the songs in the database
     * @return List of songs from the database
     */
    @Override
    public ArrayList<Song> getAllSongs() {
        return songFromCsv("%");
    }

    @Override
    public ArrayList<Song> getPopularSongs() {
        try {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery(
                    "SELECT s.* " +
                            "FROM SongT as s inner join SongStatisticsGeneralT as ssg on ssg.songId = s.songId " +
                            "ORDER BY MAX(ssg.timesPlayed) DESC LIMIT 5");
            ArrayList<Song> songs = myRsToSongs(myRs);
            myRs.close();
            return songs;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
