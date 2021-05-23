package Business;

import Business.Entities.Keys;
import Business.Entities.ReadMidi;

import java.util.ArrayList;

public class TilesManager {
    private ArrayList<Keys> listTiles = new ArrayList<>();
    private static BusinessFacadeImp businessFacadeImp = new BusinessFacadeImp();

    public TilesManager() {
    }

    public ArrayList<Keys> getListTiles() {
        return listTiles;
    }

    public void setListTiles(int songIndex) {
        try {

            //PIANO TILES SELECTOR MANAGER.JAVA LINE 276 WHEN THE VALUE HAS CHANGED IN THE LIST.
            listTiles = ReadMidi.readMidi(new BusinessFacadeImp().getSong(songIndex).getSongFile());
            //System.out.println(listTiles.get(1).getDuration());
        } catch (Exception e) {
            listTiles.clear();
            businessFacadeImp.setError(3);
        }
    }

    public void resetKeys() {
        listTiles.clear();
    }
}
