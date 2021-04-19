import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Method that gets the user from the database
     * @param myUserString Defines either the email or the username of the user who we want to get
     * @param state Defines either the attribute email or username, in order to get the desired user.
     * @return Class that stores the User
     */
    private ArrayList<Song> songFromCsv(String myUserString, String state){
        try {
            makeConnection();
            ResultSet myRs = connection.createStatement().executeQuery("select * from Song as s where s." + state + " like '" + myUserString + "'");
            //myRs.close();
            return myRsToSongs(myRs);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
    private ArrayList<Song> myRsToSongs(ResultSet myRs) throws SQLException {
        ArrayList<Song> songs = new ArrayList<>();
        while(myRs.next()){
            JsonParser parser = new JsonParser();
            songs.add(new Song(
                    myRs.getString("songId"),
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

    @Override
    public void saveSong(Song mySaveSong) {

    }

    @Override
    public void updateSong(Song mySong) {

    }

    @Override
    public void deleteSong(Song mySong) {

    }

    @Override
    public Song getSongByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Song> getAllSongs(User myUser) {
        return null;
    }

    @Override
    public ArrayList<Song> getAllSongs() {
        return null;
    }
}
