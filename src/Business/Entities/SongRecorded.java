package Business.Entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * SongRecorded
 *
 * The "SongRecorded" class will contain the different methods needed to set and access the attributes of a recorded song
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class SongRecorded {
    private ArrayList<RecordingNotes> recordingNotes;
    private String SongName;
    private boolean isPrivate;

    /**
     * Constructor of the SongRecorded class
     * @param recordingNotes List of notes that have been played
     * @param SongName Name of the recorded song
     * @param isPrivate True if Public, false if not
     */
    public SongRecorded (ArrayList<RecordingNotes> recordingNotes, String SongName, boolean isPrivate) {
        this.isPrivate = isPrivate;
        this.recordingNotes = recordingNotes;
        this.SongName = SongName;
    }

    /**
     * Gets the list of recorded notes
     * @return List of the recorded notes
     */
    public ArrayList<RecordingNotes> getRecordingNotes() {
        return recordingNotes;
    }

    /**
     * Sets the list of recorded notes
     * @param recordingNotes List of the recorded notes
     */
    public void setRecordingNotes(ArrayList<RecordingNotes> recordingNotes) {
        this.recordingNotes = recordingNotes;
    }

    /**
     * Gets the name of the song
     * @return The name of the song
     */
    public String getSongName() {
        return SongName;
    }

    /**
     * Sets the name of the song
     * @param songName The desired name
     */
    public void setSongName(String songName) {
        SongName = songName;
    }

    /**
     * Gets if a song is public or not
     * @return True if public, false if not
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Sets if a song is public or not
     * @param aPrivate True if public, false if not
     */
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
