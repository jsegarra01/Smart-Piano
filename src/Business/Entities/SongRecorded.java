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

    /*
    Defines all the recorded notes
     */
    private final ArrayList<RecordingNotes> recordingNotes;

    /*
    Defines the name of the song
     */
    private String SongName;

    /*
    Defines if the song is private or not
     */
    private boolean isPrivate;

    /**
     * Constructor of the SongRecorded class
     * @param recordingNotes Defines a list of notes that have been played
     * @param SongName Defines the name of the recorded song
     * @param isPrivate Defines as true if Public, false if not
     */
    public SongRecorded (ArrayList<RecordingNotes> recordingNotes, String SongName, boolean isPrivate) {
        this.isPrivate = isPrivate;
        this.recordingNotes = recordingNotes;
        this.SongName = SongName;
    }

    /**
     * Method that gets the list of recorded notes
     * @return Array list of class RecordingNotes that stores the recorded notes
     */
    public ArrayList<RecordingNotes> getRecordingNotes() {
        return recordingNotes;
    }

    /**
     * Method that gets the name of the song
     * @return String that stores the name of the song
     */
    public String getSongName() {
        return SongName;
    }

    /**
     * Method that sets the name of the song
     * @param songName Defines the desired name for the song
     */
    public void setSongName(String songName) {
        SongName = songName;
    }

    /**
     * Method that gets if a song is public or not
     * @return Boolean that stores a true if public, false if not
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Method that sets if a song is public or not
     * @param aPrivate Defines as true if public, false if not
     */
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
