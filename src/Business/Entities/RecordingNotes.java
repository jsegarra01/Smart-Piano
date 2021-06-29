package Business.Entities;

/**
 * RecordingNotes
 *
 * The "RecordingNotes" class will contain the different methods needed to set and access the attributes of the recorded notes
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class RecordingNotes {

    /*
    Defines the key of the recording note
     */
    private final String key;

    /*
    Defines the time it has started to be pressed
     */
    private final float time;

    /*
    Defines the duration to be pressed
     */
    private float duration = 0;

    /**
     * Constructor of the class RecordingNotes
     * @param key Defines the key of the recorded note
     * @param startTime Defines the time when it has been pressed
     */
    public RecordingNotes (String key, float startTime) {
        this.key = key;
        this.time = startTime;
    }

    /**
     * Method that sets the duration of a key (the time it has been pressed)
     * @param endTime Defines the duration of the key
     */
    public void setDuration (float endTime) {
        this.duration = endTime;
    }

    /**
     * Method that gets the time a key has been pressed
     * @return Float that stores the time the key has been pressed
     */
    public float getDuration() {
        return duration;
    }

    /**
     * Method that gets the moment a key has been pressed
     * @return Float that stores when the key has been pressed
     */
    public float getTime() {
        return time;
    }

    /**
     * Method that gets the key
     * @return String that stores the key
     */
    public String getKey() {
        return key;
    }
}
