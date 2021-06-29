package Business.Entities;

/**
 * Keys
 *
 * The "Keys" class will contain the different methods needed to set and access the Keys
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class Keys {

    /*
    Defines the keyCode of the key
     */
    private int keyCode;

    /*
    Defines the tile belonging to the key
     */
    private String tileKey;

    /*
    Defines the name of the key
     */
    private String nameKey;

    /*
    Defines if the key is pressed or not
     */
    private boolean pressed;

    /*
    Defines the duration of the key pressed
     */
    private long duration;

    /*
    Defines when does the key start being pressed
     */
    private long startTime;

    /**
     * Constructor of a key
     * @param keyCode Defines the code of the key
     * @param tileKey Defines the tile of the key
     * @param nameKey Defines the name of the key
     */
    public Keys(int keyCode, String tileKey, String nameKey){
        this.keyCode = keyCode;
        this.tileKey = tileKey;
        this.nameKey = nameKey;
        this.pressed = false;
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
        this.pressed = false;
    }

    /**
     * Method that gets the code of the key
     * @return Int that stores the code of the key
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Method that sets the code of the key
     * @param keyCode Defines the desired key code
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Method that gets the tile
     * @return String that stores the tile
     */
    public String getTileKey() {
        return tileKey;
    }

    /**
     * Method that gets if the key is pressed
     * @return Boolean that stores a true if pressed, false if not
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Method that sets if the key is pressed or not
     * @param pressed Defines as true if pressed, false if not
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Method that gets the name of the key
     * @return String that stores the name of the key
     */
    public String getNameKey() {
        return nameKey;
    }

    /**
     * Method that gets the duration of a key
     * @return Long that stores the duration of a key being pressed
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Method that gets the start time of the key
     * @return Long that stores time the key has been pressed
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Method that sets the name of the key
     * @param nameKey Defines the name of the key
     */
    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }
}
