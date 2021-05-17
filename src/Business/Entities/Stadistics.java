package Business.Entities;

public class Stadistics {
    private int hour;
    private float numPlayed;
    private float minPlayed;

    public Stadistics(int hour, float numPlayed, float minPlayed) {
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

    public float getNumPlayed() {
        return numPlayed;
    }

    public void setNumPlayed(float numPlayed) {
        this.numPlayed = numPlayed;
    }

    public float getMinPlayed() {
        return minPlayed;
    }

    public void setMinPlayed(float minPlayed) {
        this.minPlayed = minPlayed;
    }

}
