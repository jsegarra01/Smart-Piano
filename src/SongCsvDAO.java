import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class SongCsvDAO implements SongDAO {
    /**
     * Stores the information that is being used to connect to the database
     */
    private Connection connection;

    /**
     * Method that is in charge of creating the connection with the database which configuration is in the config.json
     * file
     * @throws SQLException Throw that makes an exception if there has been any error while making the connection.
     *                      It will be handled with the try catch from where it is called.
     */
    private void makeConnection() throws SQLException {
        connection =  DriverManager.getConnection("jdbc:mysql://"+
                        ReadConfigJson.getConfigJson().getIpAddress() + ":" +
                        ReadConfigJson.getConfigJson().getPort()+"/"+ReadConfigJson.getConfigJson().getName(),
                ReadConfigJson.getConfigJson().getUsername(), ReadConfigJson.getConfigJson().getPassword());
    }

    /**
     * Method that gets all the songs from the database created by a user
     * @param myUserString Defines the username of the user who we want to get their songs
     * @return List of the class Song that stores the songs created by that User
     */
    private ArrayList<Song> songFromCsv(String myUserString){
        try {
            makeConnection();
            ResultSet myRs = connection.createStatement().executeQuery("select * from Song as s where s.username like '" + myUserString + "'");
            ArrayList<Song> songs = myRsToSongs(myRs);
            closeConnection(myRs);
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
            JsonParser parser = new JsonParser();
            songs.add(new Song(
                    myRs.getInt("songId"),
                    myRs.getString("songName"),
                    myRs.getString("authorsName"),
                    myRs.getFloat("duration"),
                    myRs.getDate("recordingDate"),
                    myRs.getBoolean("publicBoolean"),
                    (JsonObject) parser.parse(getLargerString(myRs)),
                    myRs.getString("username")));
        }
        return songs;
    }

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static String getLargerString(ResultSet rs) throws SQLException {

        InputStream in;
        int BUFFER_SIZE = 1024;
        try {
            in = rs.getAsciiStream("songFile");
            if (in == null) {
                return "";
            }

            byte[] arr = new byte[BUFFER_SIZE];
            StringBuilder buffer = new StringBuilder();
            int numRead = in.read(arr);
            while (numRead != -1) {
                buffer.append(new String(arr, 0, numRead));
                numRead = in.read(arr);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Method that closes the result set and the connection made with the database
     * @param myRs Defines the result set in which the information from the query is stored
     * @throws SQLException Throw that makes an exception if there has been any error with the connection to the
     *                      database. It will be handled with the try catch from where it is called.
     */
    private void closeConnection (ResultSet myRs) throws SQLException {
        myRs.close();
        connection.close();
    }

    /**
     * Method that stores the song in the database
     * @param mySaveSong Defines the song to be stored
     */
    @Override
    public boolean saveSong(Song mySaveSong) {
        try {
            makeConnection();
            mySaveSong.setSongId(3);
            PreparedStatement st = connection.prepareStatement("insert into Song values (" +
                    mySaveSong.getSongId() + ", '" +
                    mySaveSong.getSongName() + "', '" +
                    mySaveSong.getAuthorName() + "', '" +
                    mySaveSong.getDuration() + "', '" +
                    mySaveSong.getRecordingDate() + "', " +
                    mySaveSong.isPublicBoolean() + ", '" +
                    mySaveSong.getSongFile() + "', '" +
                    mySaveSong.getCreator() + "')");
            st.execute();
            connection.close();
            return true;

        } catch (SQLException throwable) {
            return false;
        }
    }

    /**
     *
     * @param mySong
     */
    @Override
    public void updateSong(Song mySong) {

    }

    /**
     * Method that deletes the song depending on the id from the database
     * @param mySong Defines the song to be deleted from the database
     */
    @Override
    public void deleteSong(Song mySong) {
        try {
            makeConnection();
            PreparedStatement st = connection.prepareStatement("delete from Song where songId = '" + mySong.getSongId() + "'");
            st.execute();
        } catch (SQLException ignored) {
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
            makeConnection();
            ResultSet myRs = connection.createStatement().executeQuery("select * from Song as s where s.songId = " + id);
            if(myRs.next()){
                JsonParser parser = new JsonParser();
                Song song = new Song(
                        myRs.getInt("songId"),
                        myRs.getString("songName"),
                        myRs.getString("authorsName"),
                        myRs.getFloat("duration"),
                        myRs.getDate("recordingDate"),
                        myRs.getBoolean("publicBoolean"),
                        (JsonObject) parser.parse(getLargerString(myRs)),
                        myRs.getString("username"));
                closeConnection(myRs);
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
            makeConnection();
            ResultSet myRs = connection.createStatement().executeQuery(
                    "SELECT s.* " +
                            "FROM Song as s inner join SongStatisticsGeneral as ssg on ssg.songId = s.songId " +
                            "ORDER BY MAX(ssg.timesPlayed) DESC LIMIT 5");
            ArrayList<Song> songs = myRsToSongs(myRs);
            closeConnection(myRs);
            return songs;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
