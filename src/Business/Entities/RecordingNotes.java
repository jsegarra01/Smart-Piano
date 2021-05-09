package Business.Entities;

public class RecordingNotes {
    private final String key;
    private final float time;
    private float duration = 0;

    public RecordingNotes (String key, float startTime) {
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

    public String getKey() {
        return key;
    }
}
