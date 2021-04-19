import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistCsvDAO implements PlaylistDAO {

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
    public void savePlaylist(Playlist playlist) {

    }

    @Override
    public void deletePlaylist(Playlist playlist) {

    }

    @Override
    public Playlist getPlaylistByUser(String username) {
        try {
            makeConnection();
            ResultSet myRs = connection.createStatement().executeQuery("select * from Playlist as p " +
                    "where p.username like '" + username + "'");
            ResultSet myRs2 = connection.createStatement().executeQuery(
                    "select so.* from Song as so inner join SongPlaylists as sp on sp.songId = so.songId inner" +
                            "join Playlist as p on sp.playlistId = p.playlistId "+ "where p.username like '" +
                            username + "'" + "group by p.playlistId");
            ArrayList<Song> songs = new ArrayList<>();
            while(myRs2.next()){
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
            Playlist playlist = null;
            if(myRs.next()){
                playlist = new Playlist(myRs.getInt("playlistId"),
                        myRs.getString("playlistName"),songs, myRs.getString("username"));
            }
            myRs.close();
            myRs2.close();
            connection.close();
            return playlist;

        } catch (SQLException throwables) {
            return null;
        }

    }

    @Override
    public Playlist getPlaylistById(int id) {
        return null;
    }
}
