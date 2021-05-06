package Business.Entities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;

/**
 * The traduction will accur as follows:
 * --2-3------5--6--7      ||  --2c#-2d#-----2f#-2g#-2a#
 * Q--W--E--R--T--Y--U     ||  2c--2d--2e--2f--2g--2a--2b
 * ---S-D-----G--H--J      ||  ---3c#-3d#-----3f#-3g#-3a#
 * -Z--X--C--V--B--N--M    ||  -3c--3d--3e--3f--3g--3a--3b
 */
public class Translator {

    private static ArrayList<Keys> keys = new ArrayList<>();
/*
    public static ArrayList<Keys> getInstance(){
        if(keys == null){
            new Translator();
        }
        return keys;
    }*/
    public Translator(){
        if(keys.isEmpty()){
            keys.add(new Keys(KeyEvent.VK_2, "2c#"));
            keys.add(new Keys(KeyEvent.VK_3, "2d#"));
            keys.add(new Keys(KeyEvent.VK_5, "2f#"));
            keys.add(new Keys(KeyEvent.VK_6, "2g#"));
            keys.add(new Keys(KeyEvent.VK_7, "2a#"));
            keys.add(new Keys(KeyEvent.VK_Q, "2c"));
            keys.add(new Keys(KeyEvent.VK_W, "2d"));
            keys.add(new Keys(KeyEvent.VK_E, "2e"));
            keys.add(new Keys(KeyEvent.VK_R, "2f"));
            keys.add(new Keys(KeyEvent.VK_T, "2g"));
            keys.add(new Keys(KeyEvent.VK_Y, "2a"));
            keys.add(new Keys(KeyEvent.VK_U, "2b"));
            keys.add(new Keys(KeyEvent.VK_S, "3c#"));
            keys.add(new Keys(KeyEvent.VK_D, "3d#"));
            keys.add(new Keys(KeyEvent.VK_G, "3f#"));
            keys.add(new Keys(KeyEvent.VK_H, "3g#"));
            keys.add(new Keys(KeyEvent.VK_J, "3a#"));
            keys.add(new Keys(KeyEvent.VK_Z, "3c"));
            keys.add(new Keys(KeyEvent.VK_X, "3d"));
            keys.add(new Keys(KeyEvent.VK_C, "3e"));
            keys.add(new Keys(KeyEvent.VK_V, "3f"));
            keys.add(new Keys(KeyEvent.VK_B, "3g"));
            keys.add(new Keys(KeyEvent.VK_N, "3a"));
            keys.add(new Keys(KeyEvent.VK_M, "3b"));
           // keys.add(new Keys(KeyEvent.VK_COMMA,"4c"));
        }
    }

    public static String getCodeFromKey(KeyEvent key){
        return switch(key.getExtendedKeyCode()){
            case KeyEvent.VK_2 -> "2c#";
            case KeyEvent.VK_3 -> "2d#";
            case KeyEvent.VK_5 -> "2f#";
            case KeyEvent.VK_6 -> "2g#";
            case KeyEvent.VK_7 -> "2a#";
            case KeyEvent.VK_Q -> "2c";
            case KeyEvent.VK_W -> "2d";
            case KeyEvent.VK_E -> "2e";
            case KeyEvent.VK_R -> "2f";
            case KeyEvent.VK_T -> "2g";
            case KeyEvent.VK_Y -> "2a";
            case KeyEvent.VK_U -> "2b";
            case KeyEvent.VK_S -> "3c#";
            case KeyEvent.VK_D -> "3d#";
            case KeyEvent.VK_G -> "3f#";
            case KeyEvent.VK_H -> "3g#";
            case KeyEvent.VK_J -> "3a#";
            case KeyEvent.VK_Z -> "3c";
            case KeyEvent.VK_X -> "3d";
            case KeyEvent.VK_C -> "3e";
            case KeyEvent.VK_V -> "3f";
            case KeyEvent.VK_B -> "3g";
            case KeyEvent.VK_N -> "3a";
            case KeyEvent.VK_M -> "3b";
            //case KeyEvent.VK_COMMA -> "4c";

            default -> throw new IllegalStateException("Unexpected value: " + key.getExtendedKeyCode());
        };
    }
    public static char getKeyFromCode(KeyEvent key){
        return switch(key.getExtendedKeyCode()){
            case KeyEvent.VK_1 -> '1';
            case KeyEvent.VK_2 -> '2';
            case KeyEvent.VK_3 -> '3';
            case KeyEvent.VK_4 -> '4';
            case KeyEvent.VK_5 -> '5';
            case KeyEvent.VK_6 -> '6';
            case KeyEvent.VK_7 -> '7';
            case KeyEvent.VK_8 -> '8';
            case KeyEvent.VK_9 -> '9';
            case KeyEvent.VK_0 -> '0';
            case KeyEvent.VK_Q -> 'Q';
            case KeyEvent.VK_W -> 'W';
            case KeyEvent.VK_E -> 'E';
            case KeyEvent.VK_R -> 'R';
            case KeyEvent.VK_T -> 'T';
            case KeyEvent.VK_Y -> 'Y';
            case KeyEvent.VK_U -> 'U';
            case KeyEvent.VK_I -> 'I';
            case KeyEvent.VK_O -> 'O';
            case KeyEvent.VK_P -> 'P';
            case KeyEvent.VK_A -> 'A';
            case KeyEvent.VK_S -> 'S';
            case KeyEvent.VK_D -> 'D';
            case KeyEvent.VK_F -> 'F';
            case KeyEvent.VK_G -> 'G';
            case KeyEvent.VK_H -> 'H';
            case KeyEvent.VK_J -> 'J';
            case KeyEvent.VK_K -> 'K';
            case KeyEvent.VK_L -> 'L';
            case KeyEvent.VK_Z -> 'Z';
            case KeyEvent.VK_X -> 'X';
            case KeyEvent.VK_C -> 'C';
            case KeyEvent.VK_V -> 'V';
            case KeyEvent.VK_B -> 'B';
            case KeyEvent.VK_N -> 'N';
            case KeyEvent.VK_M -> 'M';
            //case KeyEvent.VK_COMMA -> "4c";

            default -> throw new IllegalStateException("Unexpected value: " + key.getExtendedKeyCode());
        };
    }
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

    public static int getKey(String string){
        return switch(string){
            case "2c#" -> KeyEvent.VK_2;
            case "2d#" -> KeyEvent.VK_3 ;
            case "2f#" -> KeyEvent.VK_5 ;
            case "2g#" -> KeyEvent.VK_6;
            case "2a#" -> KeyEvent.VK_7;
            case "2c"  -> KeyEvent.VK_Q;
            case "2d"  -> KeyEvent.VK_W;
            case "2e"  -> KeyEvent.VK_E;
            case "2f"  -> KeyEvent.VK_R;
            case "2g"  -> KeyEvent.VK_T;
            case "2a"  -> KeyEvent.VK_Y;
            case "2b"  -> KeyEvent.VK_U;
            case "3c#" -> KeyEvent.VK_S;
            case "3d#" -> KeyEvent.VK_D;
            case "3f#" -> KeyEvent.VK_G;
            case "3g#" -> KeyEvent.VK_H;
            case "3a#" -> KeyEvent.VK_J;
            case "3c"  -> KeyEvent.VK_Z;
            case "3d"  -> KeyEvent.VK_X;
            case "3e"  -> KeyEvent.VK_C;
            case "3f"  -> KeyEvent.VK_V;
            case "3g"  -> KeyEvent.VK_B;
            case "3a"  -> KeyEvent.VK_N;
            case "3b"  -> KeyEvent.VK_M;
            case "4c"  -> KeyEvent.VK_COMMA;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }

    public String getFromKey(int key){
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
    public Keys getPressedFromKey(int key){
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
    public void setNewKey(String tile, int keyCode){
        boolean found = false;
        int i = 0;
        while(i<keys.size() && !found){
            if(keys.get(i).getTileKey().equals(tile)){
                found = true;
            }
            else{
                i++;
            }
        }
        if(found){
            keys.get(i).setKeyCode(keyCode);
        }
    }

}
