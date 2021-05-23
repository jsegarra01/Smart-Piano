package Business.Entities;

/**
 * Keys
 *
 * The "Keys" class will contain the different methods needed to set and access the Keys
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class Keys {
    private int keyCode;
    private String tileKey;
    private String nameKey;
    private boolean pressed = false;
    private long duration;
    private long startTime;

    /**
     * Constructor of a key
     * @param keyCode Code of the key
     * @param tileKey Tile of the key
     * @param nameKey Name of the key
     */
    public Keys(int keyCode, String tileKey, String nameKey){
        this.keyCode = keyCode;
        this.tileKey = tileKey;
        this.nameKey = nameKey;
    }

    /**
     * Constructor of a key
     * @param keyCode Code of the key
     * @param duration Time the key has been pressed
     * @param startTime Time when the key has been pressed
     */
    public Keys(int keyCode, long duration, long startTime){
        this.keyCode = keyCode;
        this.duration = duration;
        this.startTime = startTime;
    }

    /**
     * Gets the code of the key
     * @return The code of the key
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Sets the code of the key
     * @param keyCode The desired code
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Gets the tile
     * @return The tile
     */
    public String getTileKey() {
        return tileKey;
    }

    /**
     * Gets if the key is pressed
     * @return True if pressed, false if not
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Sets if the key is pressed
     * @param pressed True if pressed, false if not
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Gets the name of the key
     * @return The name of the key
     */
    public String getNameKey() {
        return nameKey;
    }

    /**
     * Gets the duration of a key
     * @return The duration of a key being pressed
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Gets the start time of the key
     * @return The time the key has been pressed
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the name of the key
     * @param nameKey The desired name
     */
    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }
}
