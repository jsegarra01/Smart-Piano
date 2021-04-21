import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistCsvDAO implements PlaylistDAO {

    private final Connection connection;

    public PlaylistCsvDAO(){
        connection = ConnectSQL.getInstance();
    }

    @Override
    public void savePlaylist(Playlist playlist) {

    }

    @Override
    public void deletePlaylist(Playlist playlist) {

    }

    private ArrayList<Song> getSongsForPlaylist(int id) throws SQLException {
        ResultSet myRs2 = connection.createStatement().executeQuery(
                "select so.*, p.* from Song as so inner join SongPlaylists as sp on sp.songId = so.songId and " +
                        "sp.playlistId = " + id);
        ArrayList<Song> songs = new ArrayList<>();
        while(myRs2.next()) {
            songs.add(new Song(
                    myRs2.getInt("songId"),
                    myRs2.getString("songName"),
                    myRs2.getString("authorsName"),
                    myRs2.getFloat("duration"),
                    myRs2.getDate("recordingDate"),
                    myRs2.getBoolean("publicBoolean"),
                    myRs2.getString("songFile"),
                    myRs2.getString("username")));
        }
        myRs2.close();
        return songs;
    }
    @Override
    public ArrayList<Playlist> getPlaylistByUser(String username) {
        try {
            ResultSet myRs = connection.createStatement().executeQuery("select * from Playlist as p " +
                    "where p.username like '" + username + "'");
            ArrayList<Playlist> playlists = new ArrayList<>();
            while(myRs.next()){
                playlists.add(new Playlist(myRs.getInt("playlistId"),
                        myRs.getString("playlistName"),
                        getSongsForPlaylist(myRs.getInt("playlistId")),
                        myRs.getString("username")));
            }
            myRs.close();
            return playlists;
        } catch (SQLException throwables) {
            return null;
        }

    }

    @Override
    public Playlist getPlaylistById(int id) {
        return null;
    }
}
