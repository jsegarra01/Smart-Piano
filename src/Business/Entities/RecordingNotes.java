package Business.Entities;

public class RecordingNotes {
    private final char key;
    private final long startTime;
    private long endTime;

    public RecordingNotes (char key, long startTime) {
        this.key = key;
        this.startTime = startTime;
    }

    public void setEndTime (long endTime) {
        this.endTime = endTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public char getKey() {
        return key;
    }
}
