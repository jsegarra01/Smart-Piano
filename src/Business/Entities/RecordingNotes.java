package Business.Entities;

/**
 * RecordingNotes
 *
 * The "RecordingNotes" class will contain the different methods needed to set and access the attributes of the recorded notes
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class RecordingNotes {
    private final String key;
    private final float time;
    private float duration = 0;

    /**
     * Constructor of the class RecordingNotes
     * @param key Key of the recorded note
     * @param startTime Time when it has been pressed
     */
    public RecordingNotes (String key, float startTime) {
        this.key = key;
        this.time = startTime;
    }

    /**
     * Sets the duration of a key (the time it has been pressed)
     * @param endTime Duration of the key
     */
    public void setDuration (float endTime) {
        this.duration = endTime;
    }

    /**
     * Gets the time a key has been pressed
     * @return The time a key has been pressed
     */
    public float getDuration() {
        return duration;
    }

    /**
     * Gets the moment a key has been pressed
     * @return When it has been pressed
     */
    public float getTime() {
        return time;
    }

    /**
     * Gets the key
     * @return The key
     */
    public String getKey() {
        return key;
    }
}
