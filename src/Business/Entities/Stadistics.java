package Business.Entities;

/**
 * Stadistics
 *
 * The "Stadistics" class will contain the different methods needed to set and access the stadistics
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class Stadistics {

    /*
    Defines the hour of that statistics
     */
    private int hour;

    /*
    Defines the number of songs played in that hour
     */
    private float numPlayed;

    /*
    Defines the minutes played in that hour
     */
    private float minPlayed;

    /**
     * Constructor of the class stadistics
     * @param hour Hour where the stadistics are from
     * @param numPlayed Number of times a song has been played
     * @param minPlayed Minutes a song has been played
     */
    public Stadistics(int hour, float numPlayed, float minPlayed) {
        this.hour = hour;
        this.numPlayed = numPlayed;
        this.minPlayed = minPlayed;
    }

    /**
     * Gets the hour
     * @return The hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the hour
     * @param myHour The desired hour
     */
    public void setHour(int myHour) {
        this.hour = myHour;
    }

    /**
     * Gets the number of times a song has been played
     * @return The number of times a song has been played
     */
    public float getNumPlayed() {
        return numPlayed;
    }

    /**
     * Sets the number of times a song has been played
     * @param numPlayed The number of times a song has been played
     */
    public void setNumPlayed(float numPlayed) {
        this.numPlayed = numPlayed;
    }

    /**
     * Gets the minutes a song has been played
     * @return The minutes a song has been played
     */
    public float getMinPlayed() {
        return minPlayed;
    }

    /**
     * Sets the minutes a song has been played
     * @param minPlayed The minutes a song has been played
     */
    public void setMinPlayed(float minPlayed) {
        this.minPlayed = minPlayed;
    }

}
