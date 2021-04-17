import java.io.File;
import java.sql.Blob;
import java.util.Date;

public class Song {
    private String songId;
    private String songName;
    private String authorName;
    private float duration;
    private Date recordingDate;
    private boolean publicBoolean;
    private String  songFile;
    private String creator;
    private float minPlayed;
    private int timesPlayed;

    public Song(String songId, String songName, String authorName, float duration, Date recordingDate, boolean publicBoolean, String songFile, String creator){
        this.songId = songId;
        this.songName = songName;
        this.authorName = authorName;
        this.duration = duration;
        this.recordingDate = recordingDate;
        this.publicBoolean = publicBoolean;
        this.songFile = songFile;
        this.creator = creator;
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
}
