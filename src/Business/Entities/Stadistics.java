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
    private final int hour;

    /*
    Defines the number of songs played in that hour
     */
    private final float numPlayed;

    /*
    Defines the minutes played in that hour
     */
    private final float minPlayed;

    /**
     * Constructor of the class stadistics
     * @param hour Defines the hour that the statistics belong to
     * @param numPlayed Defines the amount of times a song has been played
     * @param minPlayed Defines the number of minutes a song has been played
     */
    public Stadistics(int hour, float numPlayed, float minPlayed) {
        this.hour = hour;
        this.numPlayed = numPlayed;
        this.minPlayed = minPlayed;
    }

    /**
     * Method that gets the hour of the statistic
     * @return Int that stores the hour
     */
    public int getHour() {
        return hour;
    }


    /**
     * Method that gets the number of times a song has been played
     * @return Float that stores the number of times a song has been played
     */
    public float getNumPlayed() {
        return numPlayed;
    }

    /**
     * Method that gets the minutes a song has been played
     * @return Float that stores the minutes a song has been played
     */
    public float getMinPlayed() {
        return minPlayed;
    }

}
