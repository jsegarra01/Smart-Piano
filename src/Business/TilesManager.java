package Business;

import Business.Entities.Keys;
import Persistence.Files.ReadMidi;

import java.util.ArrayList;

/**
 * TilesManager
 *
 * The "TilesManager" class will contain the different methods needed to connect the midi with the tiles from the UI
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 Apr 2021
 *
 */
public class TilesManager {
    private ArrayList<Keys> listTiles;

    /**
     * Constructor of the TilesManager
     */
    public TilesManager() {
        listTiles = new ArrayList<>();
    }

    /**
     * Gets the list of tiles
     * @return List of tiles
     */
    public ArrayList<Keys> getListTiles() {
        return listTiles;
    }

    /**
     * Calls the method to read a midi file and gets a list of keys from it
     * @param songIndex Int. Number of the song to be obtainable
     */
    public void setListTiles(int songIndex) {
        try {

            //PIANO TILES SELECTOR MANAGER.JAVA LINE 276 WHEN THE VALUE HAS CHANGED IN THE LIST.
            listTiles = ReadMidi.readMidi(BusinessFacadeImp.getBusinessFacade().getSongOrdered(songIndex).getSongFile());
        } catch (Exception e) {
            listTiles.clear();
            BusinessFacadeImp.getBusinessFacade().setError(3);
        }
    }

    /**
     * Clears the list of keys
     */
    public void resetKeys() {
        listTiles.clear();
    }
}
