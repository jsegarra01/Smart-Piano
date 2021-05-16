package Business;

//Imports needed for the managers and arraylists
import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.SongRecorded;

import java.util.ArrayList;

import static Business.Entities.SongToMidi.writeMidi;

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
    private static SongManager songManager = new SongManager();
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
    public void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        songpanager.saveRecording(recordedNotes,songName,isPublic,endtime);
    }

    @Override
    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }


    @Override
    public void setSongUser() {songManager.setSongs();}

    @Override
    public ArrayList<String> getSongName() {
        return songManager.getSongNames();
    }

    public Song getSong(int index) {
        return songManager.getSong(index);
    }


}
