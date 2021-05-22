package Business;

import Business.Entities.Keys;
import Business.Entities.ReadMidi;

import java.util.ArrayList;

/**
 * TilesManager
 *
 * The "TilesManager" class will contain the different methods needed to connect the midi with the tiles from the UI
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class TilesManager {
    private ArrayList<Keys> listTiles = new ArrayList<Keys>();


    /**
     * Constructor of the TilesManager
     */
    public TilesManager() {
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
     * @param songIndex
     */
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

    /**
     * Clears the list of keys
     */
    public void resetKeys() {
        listTiles.clear();
    }
}
