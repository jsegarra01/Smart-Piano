package Business.Entities;

//Import the Date format
import java.util.Date;

/**
 * Song
 *
 * The "Song" class will contain the different methods needed to set and access the songs attributes
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class Song {
    private String songName;
    private final String authorName;
    private final float duration;
    private Date recordingDate;
    private final boolean publicBoolean;
    private final String songFile;
    private final String creator;
    private int timesPlayed;

    /**
     * Constructor of the Song
     * @param songName Name of the song
     * @param authorName Name of the author of the song
     * @param duration Duration of the song
     * @param recordingDate Date when the song has been recorded
     * @param publicBoolean Public if true, false if not
     * @param songFile Path of the file where the song is
     * @param creator User who downloaded the song
     * @param timesPlayed Times the song has been played
     */
    public Song(String songName, String authorName, float duration, Date recordingDate, boolean publicBoolean, String songFile, String creator, Integer timesPlayed){
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
     * Constructor of the Song
     * @param songName Name of the song
     * @param authorName Name of the author of the song
     * @param duration Duration of the song
     * @param publicBoolean Public if true, false if not
     * @param songFile Path of the file where the song is
     * @param creator User who downloaded the song
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
     * Gets the name of the song
     * @return The name of the song
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Sets the name of the song
     * @param songName The desired name
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Gets the name of the author of the song
     * @return The name of the author of the song
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Gets the duration of the song
     * @return The duration of the song
     */
    public float getDuration() {
        return duration;
    }

    /**
     * Gets the duration of the song
     * @return The duration of the song
     */
    public Date getRecordingDate() {
        return recordingDate;
    }

    /**
     * Gets if the song is public
     * @return True if public, false if private
     */
    public boolean isPublicBoolean() {
        return publicBoolean;
    }

    /**
     * Gets the path where the song is stored
     * @return The path where the song is stored
     */
    public String getSongFile() {
        return songFile;
    }

    /**
     * Gets the creator of the song
     * @return The creator of the song
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Gets the times the song has been played
     * @return The times the song has been played
     */
    public int getTimesPlayed() {
        return timesPlayed;
    }

    /**
     * Sets the times the song has been played
     * @param timesPlayed The desired times
     */
    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
}
