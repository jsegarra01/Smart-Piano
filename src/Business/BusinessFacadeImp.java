package Business;

import Business.Entities.Playlist;
import Business.Entities.RecordingNotes;
import Business.Entities.SongRecorded;
import Business.Entities.SongToJson;

import java.util.ArrayList;

import static Business.Entities.SongToJson.writeJSONsong;
import static Business.Entities.SongToJson.writeMidi;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" class will contain the implementation of the BusinessFacade interface to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 24 Apr 2021
 *
 */
public class BusinessFacadeImp implements Business.BusinessFacade {
    private static UserManager loginUserManager = new UserManager();
    private static SongManager songpanager = new SongManager();
    private static PlaylistManager playlistManager = new PlaylistManager();

    @Override
    public boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }

    @Override
    public boolean SignUp(String username, String mail, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        return loginUserManager.signUser(username,mail,password);
    }

    @Override
    public boolean deleteAccount() {
        return loginUserManager.deleteUser();
    }

    @Override
    public void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPrivate, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPrivate).getRecordingNotes(), endtime);
    }
    @Override
    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    @Override
    public Playlist getPlaylist(String name){
        return playlistManager.getFromName(name);
    }

    public boolean deleteSongFromPlaylist(String playlistName, String songName){
        return playlistManager.eliminateSongFromPlaylist(playlistName, songName);
    }
}
