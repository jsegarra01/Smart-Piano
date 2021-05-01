package Business.Entities;
import java.awt.event.KeyEvent;
/**
 * The traduction will accur as follows:
 * --2-3------5--6--7      ||  --2c#-2d#-----2f#-2g#-2a#
 * Q--W--E--R--T--Y--U     ||  2c--2d--2e--2f--2g--2a--2b
 * ---S-D-----G--H--J      ||  ---3c#-3d#-----3f#-3g#-3a#
 * -Z--X--C--V--B--N--M    ||  -3c--3d--3e--3f--3g--3a--3b
 */
public class Translator {
    public Translator(){}
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
            case KeyEvent.VK_COMMA -> "4c";
            default -> throw new IllegalStateException("Unexpected value: "/* + key.getExtendedKeyCode()*/);
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
}