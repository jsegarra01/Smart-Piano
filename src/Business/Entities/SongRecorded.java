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
}
