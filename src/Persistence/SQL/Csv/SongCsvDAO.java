package Persistence.SQL.Csv;

import Business.Entities.Song;
import Business.Entities.Stadistics;
//import Business.Entities.TopSongs;
import Persistence.SQL.ConnectSQL;
import Persistence.SongDAO;

import java.sql.*;
import java.text.SimpleDateFormat;
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
     *
     * @param myUserString Defines the username of the user who we want to get their songs
     * @return List of the class Song that stores the songs created by that User
     */

    private ArrayList<Song> songFromCsv(String myUserString) throws SQLException {
        if(ConnectSQL.getInstance()!=null) {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongT as s where (s.username " +
                    "                    like '" + myUserString + "' and publicBoolean = false) or publicBoolean = true;");
            ArrayList<Song> songs = myRsToSongs(myRs);
            myRs.close();
            return songs;
        }
        return null;
    }

    /**
     * Method that gets all the songs from the database that are public
     * @return ArrayList of songs
     */
    private ArrayList<Song> songFromCsv() throws SQLException {
        if (ConnectSQL.getInstance() != null) {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongT as s where" +
                    " publicBoolean = true;");
            ArrayList<Song> songs = myRsToSongs(myRs);
            myRs.close();
            return songs;

        }
        return null;
    }


    /**
     * Method that parses the result got from the query and stores it in the list
     *
     * @param myRs Defines the result set in which the information from the query is stored
     * @return List of the class Song that stores the songs created by that User
     * @throws SQLException Throw that makes an exception if there has been any error with the connection to the
     *                      database. It will be handled with the try catch from where it is called.
     */
    private ArrayList<Song> myRsToSongs(ResultSet myRs) throws SQLException {
        ArrayList<Song> songs = new ArrayList<>();
        while (myRs.next()) {
            songs.add(new Song(
                    myRs.getString("songName"),
                    myRs.getString("authorsName"),
                    myRs.getFloat("duration"),
                    myRs.getDate("recordingDate"),
                    myRs.getBoolean("publicBoolean"),
                    myRs.getString("songFile"),
                    myRs.getString("username"),
                    myRs.getInt("numTimesPlayed")));
        }
        return songs;
    }

    /**
     * Method that stores the song in the database
     * @param mySaveSong Defines the song to be stored
     * @return boolean that returns a true if it has been done correctly, false if not
     */
    @Override
    public boolean saveSong(Song mySaveSong) {
        try{
            if(ConnectSQL.getInstance()!=null){
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement(
                        "insert into SongT (songName, authorsName, duration, recordingDate, publicBoolean, songFile, username) values ('" +
                            mySaveSong.getSongName() + "', '" +
                            mySaveSong.getAuthorName() + "', '" +
                            mySaveSong.getDuration() + "', " +
                            "current_date, " +
                            mySaveSong.isPublicBoolean() + ", '" +
                            mySaveSong.getSongFile() + "', '" +
                            mySaveSong.getCreator() + "')");
                st.execute();
                return true;
            }
            return false;
        } catch (SQLException throwable) {
            return false;
        }
    }

    /**
     * Method that stores the song in the database with the date
     * @param mySaveSong Defines the song to be stored
     * @return boolean that returns a true if it has been done correctly, false if not
     */
    public boolean saveSongWithDate(Song mySaveSong) {
        try {
            if(ConnectSQL.getInstance()!=null) {
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement(
                        "insert into SongT (songName, authorsName, duration, recordingDate, publicBoolean, songFile, username, numTimesPlayed) values ('" +
                                mySaveSong.getSongName() + "', '" +
                                mySaveSong.getAuthorName() + "', '" +
                                mySaveSong.getDuration() + "', '" +
                                new SimpleDateFormat("yyyy-MM-dd").format(mySaveSong.getRecordingDate()) + "', " +
                                mySaveSong.isPublicBoolean() + ", '" +
                                mySaveSong.getSongFile() + "', '" +
                                mySaveSong.getCreator() + "'," + mySaveSong.getTimesPlayed() +
                                ")");
                st.execute();
                return true;
            }
            return false;
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
            if(ConnectSQL.getInstance()!=null){
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("delete from SongT where songName = '" +
                        mySong.getSongName() + "'");
                st.execute();
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Method that gets the song by its id
     *
     * @param id Defines the id of the song
     * @return Class that stores the song that has been got from the database
     */
    @Override
    public Song getSongByID(int id) {
        try {
            if(ConnectSQL.getInstance()!=null){

                ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongT as s where s.songId LIKE " + id);
                if (myRs.next()) {
                    Song song = new Song(
                            myRs.getString("songName"),
                            myRs.getString("authorsName"),
                            myRs.getFloat("duration"),
                            myRs.getDate("recordingDate"),
                            myRs.getBoolean("publicBoolean"),
                            myRs.getString("songFile"),
                            myRs.getString("username"),
                            myRs.getInt("numTimesPlayed"));
                    myRs.close();
                    return song;
                }
            }
        } catch (SQLException throwables) {
            return null;
        }
        return null;

    }

    /**
     * Method that gets all the songs belonging to the user
     *
     * @param username Defines the user from which the songs will be got
     * @return List of songs that have been created by the user
     */
    @Override
    public ArrayList<Song> getAllSongs(String username) throws SQLException{
        return songFromCsv(username);
    }

    /**
     * Method that gets all the songs in the database
     *
     * @return List of songs from the database
     */
    @Override
    public ArrayList<Song> getAllSongs() throws SQLException {
        return songFromCsv();
    }

    /**
     * Method that updates the times played that a song has been
     * @param song Defines the song to be updated
     * @return boolean that returns a true if it has been correct, false if not
     */
    @Override
    public boolean updateTimesPlayed(Song song) {
        if(song!=null){
            try {
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("update SongT SET numTimesPlayed = numTimesPlayed + 1 where songName like '" +
                        song.getSongName() + "';");
                st.executeUpdate();
                return true;
            } catch (SQLException throwable) {
                return false;
            }
        }
        return false;

    }


    /**
     * Method that saves the stadistics into the databases
     *
     * @param myStats defines the stadistics of the song (the hour, how many songs have been played and for how much)
     * @return boolean that indicates if the information has been saved correctly
     */
    @Override
    public boolean saveStadistics(Stadistics myStats) {
        try {
            //First we check if there is already information for that particular hour in the databases
            if (getStadisticsHour(myStats.getHour()) == null) {
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("insert into SongStatisticsHourlyT values ('" +
                        myStats.getHour() + "', '" +
                        myStats.getNumPlayed() + "', '" +
                        myStats.getMinPlayed() + "')");
                st.execute();
                return true;
                //If there is already information we do an update instead than an insert
            } else {
                PreparedStatement st2 = ConnectSQL.getInstance().prepareStatement("update SongStatisticsHourlyT SET numPlayed = numPlayed + " +
                        myStats.getNumPlayed() + " where hour = " +
                        myStats.getHour() + ";");
                st2.executeUpdate();
                PreparedStatement st3 = ConnectSQL.getInstance().prepareStatement("update SongStatisticsHourlyT SET minPlayed = minPlayed + " +
                        myStats.getMinPlayed() + " where hour = " +
                        myStats.getHour() + ";");
                st3.executeUpdate();
                return true;
            }

        } catch (SQLException throwable) {
            return false;
        }
    }

    /**
     * Method that gets from the databases the stadistics for a specific hour
     *
     * @param hour integer that indicates the hour
     * @return Stadistics for that hour
     */
    @Override
    public Stadistics getStadisticsHour(int hour) {
        try {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongStatisticsHourlyT as st where st.hour = " + hour);
            if (myRs.next()) {
                Stadistics stadistics = new Stadistics(
                        myRs.getInt("hour"),
                        myRs.getFloat("numPlayed"),
                        myRs.getFloat("minPlayed"));
                myRs.close();
                return stadistics;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            return null;
        }
    }

    /**
     * Method that gets a song by its name
     * @param name Defines the name of the song
     * @return Song to be returned
     */
    @Override
    public Song getSongByName(String name) {
        try {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from SongT as s where s.songName LIKE '" + name + "'");
            if (myRs.next()) {
                Song song = new Song(
                        myRs.getString("songName"),
                        myRs.getString("authorsName"),
                        myRs.getFloat("duration"),
                        myRs.getDate("recordingDate"),
                        myRs.getBoolean("publicBoolean"),
                        myRs.getString("songFile"),
                        myRs.getString("username"),
                        myRs.getInt("numTimesPlayed"));
                myRs.close();
                return song;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            return null;
        }
    }
}