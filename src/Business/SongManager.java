package Business;

import Business.Entities.Song;
import Business.Entities.Stadistics;
import Persistence.SQL.Csv.SongCsvDAO;

import java.util.ArrayList;

public class SongManager {
    private ArrayList<Song> songs;
    private SongCsvDAO songCsvDAO = new SongCsvDAO();

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public SongCsvDAO getSongCsvDAO() {
        return songCsvDAO;
    }

    public void setSongCsvDAO(SongCsvDAO songCsvDAO) {
        this.songCsvDAO = songCsvDAO;
    }

    public void addingStadistics(Stadistics myStats){
        songCsvDAO.saveStadistics(myStats);
    }

    public Stadistics gettingStadistics(int hour){
        return songCsvDAO.getStadisticsHour(hour);
    }
}
