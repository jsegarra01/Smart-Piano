import java.io.File;
import java.util.Date;

public class Song {
    private String songId;
    private String songName;
    private String authorName;
    private float duration;
    private Date recordingDate;
    private boolean publicBoolean;
    private File songFile;
    private float minPlayed;
    private int timesPlayed;

    public Song(String songId, String songName, String authorName, float duration, Date recordingDate, boolean publicBoolean, File songFile, float minPlayed, int timesPlayed){
        this.songId = songId;
        this.songName = songName;
        this.authorName = authorName;
        this.duration = duration;
        this.recordingDate = recordingDate;
        this.publicBoolean = publicBoolean;
        this.songFile = songFile;
        this.minPlayed = minPlayed;
        this.timesPlayed = timesPlayed;
    }
    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
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

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public boolean isPublicBoolean() {
        return publicBoolean;
    }

    public void setPublicBoolean(boolean publicBoolean) {
        this.publicBoolean = publicBoolean;
    }

    public File getSongFile() {
        return songFile;
    }

    public void setSongFile(File songFile) {
        this.songFile = songFile;
    }

    public float getMinPlayed() {
        return minPlayed;
    }

    public void setMinPlayed(float minPlayed) {
        this.minPlayed = minPlayed;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
}
