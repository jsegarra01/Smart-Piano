package Business.Entities;

import java.util.ArrayList;

/**
 * SongRecorded
 *
 * The "SongRecorded" class will contain the different methods needed to set and access the attributes of a recorded song
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
//They are necessary to be in the class, as they are going to be saved in the midi
public class SongRecorded {

    /*
    Defines all the recorded notes
     */
    private final ArrayList<RecordingNotes> recordingNotes;

    /*
    Defines the name of the song
     */
    private final String SongName;

    /*
    Defines if the song is private or not
     */
    private final boolean isPrivate;

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

}
