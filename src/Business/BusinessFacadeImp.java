package Business;

import Business.Entities.RecordingNotes;
import Business.Entities.SongRecorded;
import Business.Entities.SongToJson;

import java.util.ArrayList;

import static Business.Entities.SongToJson.writeJSONsong;

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
    UserManager loginUserManager = new UserManager();

    @Override
    public Boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }

    @Override
    public Boolean SignUp(String username, String mail, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        return loginUserManager.signUser(username,mail,password);
    }

    @Override
    public void deleteAccount(String username) {
        loginUserManager.deleteUser(username);
    }

    @Override
    public void recordedNotesSend(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPrivate) {
        SongRecorded songRecorded = new SongRecorded(recordedNotes,songName, isPrivate);
        writeJSONsong(songRecorded, songName);
    }
}
