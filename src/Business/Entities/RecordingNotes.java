package Business.Entities;

public class RecordingNotes {
    private final char key;
    private final float time;
    private float duration = 0;

    public RecordingNotes (char key, float startTime) {
        this.key = key;
        this.time = startTime;
    }

    public void setDuration (float endTime) {
        this.duration = endTime;
    }

    public float getDuration() {
        return duration;
    }

    public float getTime() {
        return time;
    }

    public char getKey() {
        return key;
    }
}
