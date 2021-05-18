package Business;

import Business.Entities.Keys;
import Business.Entities.ReadMidi;

import java.util.ArrayList;

public class TilesManager {
    private ArrayList<Keys> listTiles = new ArrayList<>();


    public TilesManager() {
    }

    public ArrayList<Keys> getListTiles() {
        return listTiles;
    }

    public void setListTiles(int songIndex) {
        try {
            listTiles = ReadMidi.readMidi(new BusinessFacadeImp().getSong(songIndex).getSongFile());
            System.out.println(listTiles.get(1).getDuration());
        } catch (Exception e) {
            listTiles.clear();
            System.out.println("Error, suposo que no troba la file.");
        }
    }

    public void resetKeys() {
        listTiles.clear();
    }
}
