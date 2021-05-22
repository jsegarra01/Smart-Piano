package Business.Entities;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The traduction will accur as follows:
 * --2-3------5--6--7      ||  --2c#-2d#-----2f#-2g#-2a#
 * Q--W--E--R--T--Y--U     ||  2c--2d--2e--2f--2g--2a--2b
 * ---S-D-----G--H--J      ||  ---3c#-3d#-----3f#-3g#-3a#
 * -Z--X--C--V--B--N--M    ||  -3c--3d--3e--3f--3g--3a--3b
 */
public class Translator {

    private static final ArrayList<Keys> keys = new ArrayList<>();

    /**
     * Fills the list of keys with all the usable keys
     * @return The list of keys filled
     */
    public static ArrayList<Keys> getInstance(){
        if(keys.isEmpty()){
            keys.add(new Keys(KeyEvent.VK_Q, "2c", "Q"));
            keys.add(new Keys(KeyEvent.VK_W, "2d", "W"));
            keys.add(new Keys(KeyEvent.VK_E, "2e", "E"));
            keys.add(new Keys(KeyEvent.VK_R, "2f", "R"));
            keys.add(new Keys(KeyEvent.VK_T, "2g", "T"));
            keys.add(new Keys(KeyEvent.VK_Y, "2a", "Y"));
            keys.add(new Keys(KeyEvent.VK_U, "2b", "U"));
            keys.add(new Keys(KeyEvent.VK_Z, "3c", "Z"));
            keys.add(new Keys(KeyEvent.VK_X, "3d", "X"));
            keys.add(new Keys(KeyEvent.VK_C, "3e", "C"));
            keys.add(new Keys(KeyEvent.VK_V, "3f", "V"));
            keys.add(new Keys(KeyEvent.VK_B, "3g", "B"));
            keys.add(new Keys(KeyEvent.VK_N, "3a", "N"));
            keys.add(new Keys(KeyEvent.VK_M, "3b", "M"));
            keys.add(new Keys(KeyEvent.VK_2, "2c#", "2"));
            keys.add(new Keys(KeyEvent.VK_3, "2d#", "3"));
            keys.add(new Keys(KeyEvent.VK_5, "2f#", "5"));
            keys.add(new Keys(KeyEvent.VK_6, "2g#", "6"));
            keys.add(new Keys(KeyEvent.VK_7, "2a#", "7"));
            keys.add(new Keys(KeyEvent.VK_S, "3c#", "S"));
            keys.add(new Keys(KeyEvent.VK_D, "3d#", "D"));
            keys.add(new Keys(KeyEvent.VK_G, "3f#", "G"));
            keys.add(new Keys(KeyEvent.VK_H, "3g#", "H"));
            keys.add(new Keys(KeyEvent.VK_J, "3a#", "J"));
        }

        return keys;
    }

    /**
     * Gets the number of a note from its name
     * @param input Name of the note
     * @return The number of the note
     */
    public static int getNumberNoteFromName(String input){
        return switch (input) {
            case "2c" -> 48;
            case "2c#" -> 49;
            case "2d" -> 50;
            case "2d#" -> 51;
            case "2e" -> 52;
            case "2f" -> 53;
            case "2f#" -> 54;
            case "2g" -> 55;
            case "2g#" -> 56;
            case "2a" -> 57;
            case "2a#" -> 58;
            case "2b" -> 59;
            case "3c" -> 60;
            case "3c#" -> 61;
            case "3d" -> 62;
            case "3d#" -> 63;
            case "3e" -> 64;
            case "3f" -> 65;
            case "3f#" -> 66;
            case "3g" -> 67;
            case "3g#" -> 68;
            case "3a" -> 69;
            case "3a#" -> 70;
            case "3b" -> 71;
            case "4c" -> 72;
            default -> 12;
        };
    }

    /**
     * Gets the name of a key from its keycode
     * @param key The keycode of the key we want to know its name
     * @return The name of the desired key
     */
    public static String getFromKey(int key){
        boolean found = false;
        int i = 0;
        while(i<keys.size() && !found){
            if(keys.get(i).getKeyCode() == key){
                found = true;
            }else{

                i++;
            }
        }
        if(found){
            return keys.get(i).getTileKey();
        }
        return null;
    }

    /**
     * Gets the key from its keycode
     * @param key The keycode of the key we want to know its name
     * @return The desired key
     */
    public static Keys getPressedFromKey(int key){
        boolean found = false;
        int i = 0;
        while(i<keys.size() && !found){
            if(keys.get(i).getKeyCode() == key){
                found = true;
            }
            else{
                i++;
            }
        }
        if(found){
            return keys.get(i);
        }
        return null;
    }

    /**
     * TODO: Que fa exactament aixo
     * @param tile
     * @param keyCode
     * @return
     */
    public static int setNewKey(String tile, int keyCode){
        boolean found = false;
        boolean foundKey = false;
        int i = 0;
        while(i<keys.size() && !found){
            if(keys.get(i).getTileKey().equals(tile)){
                found = true;
            }
            else{
                i++;
            }
        }
        int j=0;
        while(j<keys.size() && !foundKey){
            if(keys.get(j).getKeyCode()==keyCode){
                foundKey = true;
            }else{
                j++;
            }
        }
        if(found && !foundKey){
            return i;
        }else{
            return -1;
        }
    }

    /**
     * Gets a key from its tile key
     * @param tileKey tile key from the key we want to get
     * @return The desired key
     */
    public static Keys getFromTile(String tileKey){
        boolean found = false;
        int i = 0;
        while(i<keys.size() && !found){
            if(keys.get(i).getTileKey().equals(tileKey)){
                found = true;
            }
            else{
                i++;
            }

        }
        if(found){
            return keys.get(i);
        }
        return null;
    }

    /**
     * Sets the name and code from a desired key
     * @param i Index of the key we want to set
     * @param keyCode New key code
     */
    public static void setKeys(int i, int keyCode){
        keys.get(i).setKeyCode(keyCode);
        keys.get(i).setNameKey(KeyEvent.getKeyText(keyCode));
    }
}
