package Persistence.SQL.Csv;

import Business.Entities.Playlist;
import Business.Entities.Song;
import Persistence.PlaylistDAO;
import Persistence.SQL.ConnectSQL;

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
     * Method that inserts a new playlist into the database
     * @param playlist Defines the name of the playlist to be inserted
     * @param username Defines the name of the username that has created the playlist
     */
    @Override
    public boolean savePlaylist(String playlist, String username) {
        try {
            if(ConnectSQL.getInstance()!=null){
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("insert into PlaylistT (playlistName, username) values ('" +playlist + "', '" + username + "')");
                st.execute();
                return true;
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    /**
     * Method that gets a song from a playlist defined by an id
     * @param id Defines the id of the playlist which the song is got
     * @return ArrayList of songs from the playlist with the id defined in the parameter
     * @throws SQLException Throws an exception if there has been any errorduring the connection
     */
    private ArrayList<Song> getSongsForPlaylist(int id) throws SQLException {
        if(ConnectSQL.getInstance()!=null){

            ResultSet myRs2 = ConnectSQL.getInstance().createStatement().executeQuery(
                    "select so.* from SongT as so inner join SongPlaylistsT as sp on sp.songId = so.songId and " +
                            "sp.playlistId = " + id);
            ArrayList<Song> songs = new ArrayList<>();
            while(myRs2.next()) {
                songs.add(new Song(
                        myRs2.getString("songName"),
                        myRs2.getString("authorsName"),
                        myRs2.getFloat("duration"),
                        myRs2.getDate("recordingDate"),
                        myRs2.getBoolean("publicBoolean"),
                        myRs2.getString("songFile"),
                        myRs2.getString("username"),
                        myRs2.getInt("numTimesPlayed")));
            }
            myRs2.close();
            return songs;
        }
        return null;
    }

    /**
     * Method that gets the array of playlists from a user
     * @param username Defines the username of the playlist
     * @return ArrayList of playlists whose author is the user defined in the parameter
     */
    @Override
    public ArrayList<Playlist> getPlaylistByUser(String username) {
        try {
            if(ConnectSQL.getInstance()!=null){
                ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from PlaylistT as p " +
                        "where p.username like '" + username + "'");
                ArrayList<Playlist> playlists = new ArrayList<>();
                while(myRs.next()){
                    playlists.add(new Playlist(
                            myRs.getString("playlistName"),
                            getSongsForPlaylist(myRs.getInt("playlistId")),
                            myRs.getString("username")));
                }
                myRs.close();

                return playlists;
            }

        } catch (SQLException throwables) {
            return null;
        }
        return null;
    }

    /**
     * Method that deletes a song from a playlist from the database
     * @param playlistName Defines the name of the playlist
     * @param songName Defines the name of the song to be deleted
     */
    @Override
    public void deleteSongFromPlaylist(String playlistName, String songName) throws SQLException{
        if(ConnectSQL.getInstance()!=null){
            PreparedStatement st = ConnectSQL.getInstance().prepareStatement("delete SongPlaylistsT from SongPlaylistsT inner join PlaylistT PT on SongPlaylistsT.playlistId = PT.playlistId inner join SongT ST on SongPlaylistsT.songId = ST.songId " +
                    "where songName like '" + songName+"' and  PT.playlistName like '" + playlistName +"';");
            st.execute();
        }
    }

    /**
     * Method that adds a song into a playlist
     * @param playlistName Defines the name of the playlist
     * @param songName Defines the name of the song to be added
     * @return True if there has been no error, false if there has been
     */
    public boolean addSongToPlaylist(String playlistName, String songName){
        try {
            if(ConnectSQL.getInstance()!=null){
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("INSERT INTO SongPlaylistsT VALUES((" +
                        " SELECT s.songId " +
                        " FROM SongT s " +
                        " WHERE s.songName = '" + songName + "'),(" +
                        " SELECT p.playlistId " +
                        " FROM PlaylistT p " +
                        " WHERE p.playlistName = '" + playlistName +"'));");
                st.execute();
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }

}
