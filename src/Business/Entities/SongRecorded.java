package Business.Entities;

import java.util.ArrayList;
import java.util.Date;

public class SongRecorded {
    private ArrayList<RecordingNotes> recordingNotes;
    private String SongName;
    private boolean isPrivate;

    public SongRecorded (ArrayList<RecordingNotes> recordingNotes, String SongName, boolean isPrivate) {
        this.isPrivate = isPrivate;
        this.recordingNotes = recordingNotes;
        this.SongName = SongName;
    }

    public ArrayList<RecordingNotes> getRecordingNotes() {
        return recordingNotes;
    }

    public void setRecordingNotes(ArrayList<RecordingNotes> recordingNotes) {
        this.recordingNotes = recordingNotes;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
