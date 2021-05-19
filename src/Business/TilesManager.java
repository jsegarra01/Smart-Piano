package Business;

import Business.Entities.Keys;
import Business.Entities.ReadMidi;

import java.util.ArrayList;

public class TilesManager {
    private ArrayList<Keys> listTiles = new ArrayList<Keys>();


    public TilesManager() {
    }

    public ArrayList<Keys> getListTiles() {
        return listTiles;
    }

    public void setListTiles(int songIndex) {
        try {
            listTiles = ReadMidi.readMidi(new BusinessFacadeImp().getSong(songIndex).getSongFile());
            for (Keys key: listTiles) {
                System.out.println(key.getDuration());
            }
            //System.out.println(listTiles.get(1).getDuration());
        } catch (Exception e) {
            listTiles.clear();
            e.printStackTrace();
            System.out.println("Error, suposo que no troba la file.");
        }
    }

    public void resetKeys() {
        listTiles.clear();
    }
}
