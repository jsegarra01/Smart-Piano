package Persistence.SQL.Csv;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Persistence.PlaylistDAO;
import Persistence.SQL.ConnectSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that implements the methods described in {@link PlaylistDAO} interface, and will be used as a way to
 * physically separate the persistence layer from the rest of the application.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public class PlaylistCsvDAO implements PlaylistDAO {


    /**
     *
     * @param playlist
     */
    @Override
    public boolean savePlaylist(String playlist, String username) {
        try {
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("insert into PlaylistT (playlistName, username) values ('" +playlist + "', '" + username + "')");
            st.execute();
            //PreparedStatement st = ConnectSQL.getInstance().prepareStatement("insert into SongPlaylistsT ");
            //st.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param playlist
     */
    @Override
    public boolean deletePlaylist(Playlist playlist) {
        try {
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("delete from SongPlaylistsT where playlistId = '" +
                    playlist.getPlaylistId() + "'");
            st.execute();
            PreparedStatement st2 = ConnectSQL.getInstance().prepareStatement("delete from PlaylistT where playlistId = '" +
                    playlist.getPlaylistId() + "'");
            st2.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    private ArrayList<Song> getSongsForPlaylist(int id) throws SQLException {
        ResultSet myRs2 = ConnectSQL.getInstance().createStatement().executeQuery(
                "select so.* from SongT as so inner join SongPlaylistsT as sp on sp.songId = so.songId and " +
                        "sp.playlistId = " + id);
        ArrayList<Song> songs = new ArrayList<>();
        while(myRs2.next()) {
            songs.add(new Song(
                    //myRs2.getInt("songId"),
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

    /**
     *
     * @param username
     * @return
     */
    @Override
    public ArrayList<Playlist> getPlaylistByUser(String username) {
        try {
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from PlaylistT as p " +
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

    public boolean deleteSongFromPlaylist(String playlistName, String songName){
        try {
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("delete SongPlaylistsT from SongPlaylistsT inner join PlaylistT PT on SongPlaylistsT.playlistId = PT.playlistId inner join SongT ST on SongPlaylistsT.songId = ST.songId " +
                    "where songName like '" + songName+"' and  PT.playlistName like '" + playlistName +"';");
            st.execute();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
    public boolean addSongToPlaylist(String playlistName, String songName){
        try {
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("INSERT INTO SongPlaylistsT VALUES((" +
                    " SELECT s.songId " +
                    " FROM SongT s " +
                    " WHERE s.songName = '" + songName + "'),(" +
                    " SELECT p.playlistId " +
                    " FROM PlaylistT p " +
                    " WHERE p.playlistName = '" + playlistName +"'));");
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

}
