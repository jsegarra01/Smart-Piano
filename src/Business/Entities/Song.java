package Business.Entities;

//Import the Date format
import java.util.Date;

public class Song {
    private String songName;
    private final String authorName;
    private final float duration;
    private Date recordingDate;
    private final boolean publicBoolean;
    private final String songFile;
    private final String creator;
    private int timesPlayed;

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

    public Song(String songName, String authorName, float duration, boolean publicBoolean, String songFile, String creator){
        this.songName = songName;
        this.authorName = authorName;
        this.duration = duration;
        this.publicBoolean = publicBoolean;
        this.songFile = songFile;
        this.creator = creator;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public float getDuration() {
        return duration;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public boolean isPublicBoolean() {
        return publicBoolean;
    }

    public String getSongFile() {
        return songFile;
    }

    public String getCreator() {
        return creator;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
}
