package Business.Entities;

public class Stadistics {
    private int hour;
    private float numPlayed;
    private float minPlayed;
   // private boolean countSong;

    public Stadistics(int hour, float numPlayed, float minPlayed/*, boolean countSong*/) {
        this.hour = hour;
        this.numPlayed = numPlayed;
        this.minPlayed = minPlayed;
        //this.countSong = countSong;
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
