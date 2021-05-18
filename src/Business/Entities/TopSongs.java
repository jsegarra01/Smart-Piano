package Business.Entities;

public class TopSongs {
    private String nameSong;
    private Float numPlayed;

    public TopSongs(String songName, float numPlayed) {
        this.nameSong=songName;
        this.numPlayed=numPlayed;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public Float getNumPlayed() {
        return numPlayed;
    }

    public void setNumPlayed(Float numPlayed) {
        this.numPlayed = numPlayed;
    }
}
