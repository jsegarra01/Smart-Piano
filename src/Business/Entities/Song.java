package Business.Entities;

//Import the Date format
import java.util.Date;

/**
 * Song
 *
 * The "Song" class will contain the different methods needed to set and access the songs attributes
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class Song {

    /*
    Defines the name of the song
     */
    private String songName;

    /*
    Defines the author of the song
     */
    private final String authorName;

    /*
    Defines the duration of the song
     */
    private final float duration;

    /*
    Defines the time the song was recorded
     */
    private Date recordingDate;

    /*
    Defines if the song is public or not
     */
    private final boolean publicBoolean;

    /*
    Defines the path where the song is
     */
    private final String songFile;

    /*
    Defines the creator of the song (not the author, but who added it)
     */
    private final String creator;

    /*
    Defines the time the song has been played
     */
    private int timesPlayed;


     /**
     * Constructor of the class Song
     * @param songName Defines the name of the song
     * @param authorName Defines the name of the author of the song
     * @param duration Defines the duration of the song
     * @param recordingDate Defines the date when the song has been recorded
     * @param publicBoolean Defines if the song is public if true, false if not
     * @param songFile Defines the path of the file where the song is
     * @param creator Defines the user who downloaded the song
     * @param timesPlayed Defines the times the song has been played
     */
    public Song(String songName, String authorName, float duration, Date recordingDate, boolean publicBoolean, String songFile, String creator, int timesPlayed){
        this.songName = songName;
        this.authorName = authorName;
        this.duration = duration;
        this.recordingDate = recordingDate;
        this.publicBoolean = publicBoolean;
        this.songFile = songFile;
        this.creator = creator;
        this.timesPlayed = timesPlayed;
    }

    /**
     * Constructor of the class Song
     * @param songName Defines the name of the song
     * @param authorName Defines the name of the author of the song
     * @param duration Defines the duration of the song
     * @param publicBoolean Defines if the song is public if true, false if not
     * @param songFile Defines the path of the file where the song is
     * @param creator Defines the user who downloaded the song
     */
    public Song(String songName, String authorName, float duration, boolean publicBoolean, String songFile, String creator){
        this.songName = songName;
        this.authorName = authorName;
        this.duration = duration;
        this.publicBoolean = publicBoolean;
        this.songFile = songFile;
        this.creator = creator;
    }

    /**
     * Method that gets the name of the song
     * @return String that stores the name of the song
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Method that sets the name of the song
     * @param songName Defines the the name of the song
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Method that gets the name of the author of the song
     * @return String that stores the name of the author of the song
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Method that gets the duration of the song
     * @return Float that stores the duration of the song
     */
    public float getDuration() {
        return duration;
    }

    /**
     * Method that gets the duration of the song
     * @return The duration of the song
     */
    public Date getRecordingDate() {
        return recordingDate;
    }

    /**
     * Method that gets if the song is public or not
     * @return Boolean that stores true if public, false if private
     */
    public boolean isPublicBoolean() {
        return publicBoolean;
    }

    /**
     * Method that gets the path where the song is stored
     * @return String that stores the path where the song is stored
     */
    public String getSongFile() {
        return songFile;
    }

    /**
     * Method that gets the creator of the song
     * @return String that stores the creator of the song
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Method that gets the times the song has been played
     * @return Int that stores the times the song has been played
     */
    public int getTimesPlayed() {
        return timesPlayed;
    }
}
