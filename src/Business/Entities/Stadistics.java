package Business.Entities;

public class Stadistics {
    private int hour;
    private int numPlayed;
    private float minPlayed;

    public Stadistics(int hour, int numPlayed, float minPlayed) {
        this.hour = hour;
        this.numPlayed = numPlayed;
        this.minPlayed = minPlayed;
    }

    public Stadistics (){

    }

    public int getHour() {
        return hour;
    }

    public void setHour(int myHour) {
        this.hour = myHour;
    }

    public int getNumPlayed() {
        return numPlayed;
    }

    public void setNumPlayed(int numPlayed) {
        this.numPlayed = numPlayed;
    }

    public float getMinPlayed() {
        return minPlayed;
    }

    public void setMinPlayed(float minPlayed) {
        this.minPlayed = minPlayed;
    }

}
