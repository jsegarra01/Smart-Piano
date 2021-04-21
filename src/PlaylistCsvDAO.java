import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistCsvDAO implements PlaylistDAO {

    private final ConnectSQL connection;

    public PlaylistCsvDAO(){
        connection = new ConnectSQL();
        try {
            connection.makeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void savePlaylist(Playlist playlist) {

    }

    @Override
    public void deletePlaylist(Playlist playlist) {

    }

    @Override
    public ArrayList<Playlist> getPlaylistByUser(String username) {
        try {
            ResultSet myRsinit = connection.getConnection().createStatement().executeQuery();
            ResultSet myRs = connection.getConnection().createStatement().executeQuery("select * from Playlist as p " +
                    "where p.username like '" + username + "'");
            ResultSet myRs2 = connection.getConnection().createStatement().executeQuery(
                    "select so.*, p.* from Song as so inner join SongPlaylists as sp on sp.songId = so.songId inner" +
                            "join Playlist as p on sp.playlistId = p.playlistId "+ "where p.username like '" +
                            username + "'" + "group by p.playlistId");
            ArrayList<Song> songs = new ArrayList<>();
            while(myRs2.next()){
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
            ArrayList<Playlist> playlists = null;
            if(myRs.next()){
                playlists = new Playlist(myRs.getInt("playlistId"),
                        myRs.getString("playlistName"),songs, myRs.getString("username"));
            }
            myRs2.close();
            connection.closeConnection(myRs);
            return ArrayList<Playlist>;
        } catch (SQLException throwables) {
            return null;
        }

    }

    @Override
    public Playlist getPlaylistById(int id) {
        return null;
    }
}
