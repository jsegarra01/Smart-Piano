package Business;

import Business.Entities.RecordingNotes;
import Business.Entities.Song;
import Business.Entities.SongRecorded;
import Persistence.SQL.Csv.LoginUserCsvDAO;
import Persistence.SQL.Csv.SongCsvDAO;

import java.util.ArrayList;
import java.util.Date;

import static Business.Entities.SongToJson.writeMidi;

public class SongManager {
    private Song song;
    private SongCsvDAO songCsvDAO = new SongCsvDAO();
    LoginUserCsvDAO loginUserCsvDAO = new LoginUserCsvDAO();

    public void saveRecording(ArrayList<RecordingNotes> recordedNotes, String songName, boolean isPublic, float endtime) {
        writeMidi(songName, new SongRecorded(recordedNotes,songName, isPublic).getRecordingNotes(), endtime);
        song = new Song(songName, UserManager.getUser().getUserName(), endtime, isPublic, "Songs/" + songName + ".mid", UserManager.getUser().getUserName());
        songCsvDAO.saveSong(song);
    }
}
