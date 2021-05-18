package Presentation.Ui_Views;

//Imports needed from the dictionary, events and mainframe
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Ui_Views.Tile.*;

public abstract class Piano extends JPanel {
    public static final String BTN_RECORD = " ";
    public static final String BTN_TILE = "SOUND";
    protected static final String JLAB_SYNTH_TYPE = "Classic Piano";
    public static final String MODIFY = "MODIFY_KEYS";
    protected static Label soundType;

    protected final ImageIcon iconRec = new ImageIcon("Files/drawable/recIcon.png");
    protected  JButton recordB = new JButton(BTN_RECORD, iconRec);
    protected  JButton profile = new JButton(PROFILE_BUTTON);
    protected  JToggleButton modifyKeys = new JToggleButton(MODIFY);

    /**
     * private JTextField hey;
     */

    protected static ArrayList<Tile> keyboard;
    private final static String whiteTileLoc = "Files/drawable/white-key.png";
    private final static String blackTileLoc = "Files/drawable/black-key.png";
    protected static final int numWhiteKeys = 14;
    protected static final int numBlackKeys = 10;
    protected static final String[] whiteNotes =
            {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b"};
    protected static final String[] blackNotes =
            {"2c#", "2d#", "2f#", "2g#", "2a#", "3c#", "3d#", "3f#", "3g#", "3a#"};
    protected static final String[] whiteLabels ={
            "Do", "Re", "Mi","Fa", "Sol", "La", "Si","Do", "Re", "Mi","Fa", "Sol", "La", "Si"
    };
    protected static final String[] blackLabels = {
            "Do#", "Re#", "Fa#", "Sol#", "La#", "Do#", "Re#", "Fa#", "Sol#", "La#"
    };
    protected static JLayeredPane layeredPane;
    protected final static ImageIcon iconPressed = new ImageIcon("Files/drawable/selected.png");
    protected final static ImageIcon iconResetWhite = new ImageIcon(whiteTileLoc);
    protected final static ImageIcon iconResetBlack  = new ImageIcon(blackTileLoc);
    protected final static ImageIcon iconPressedDown = new ImageIcon("Files/drawable/white-key-down.png");


    /**
     * Gets the current keyboard
     * @return ArrayList<Tile> keyboard. Returns the keyboard we currently have
     */
    public static ArrayList<Tile> getKeyboard() {
        return keyboard;
    }

    /**
     * Creates the Tiles for the keyboard, depending on various parameters.
     * @param keyBoard JLayeredPane Where we want the keyboard to be placed.
     * @param heightBlack int The height we want the black tiles to have.
     * @param heightBounds int The maximum height we want for the tiles to have.
     * @param yLabel int The different key names for the y
     * @param keyboard ArrayList<Tile> Current keyboard we have to create the tiles in based on the different tiles
     * @param whiteLabel int The different key names for the white tiles
     * @param whiteY int The maximum height we want the white tiles to have
     * @return JLayeredPane Returns the Pane in which all the keys have been created.
     */
    protected JLayeredPane makeTiles(JLayeredPane keyBoard, int heightBlack, int heightBounds, int yLabel, ArrayList<Tile> keyboard, int whiteLabel, int whiteY){
        int widthBlack = 35;
        int yBlack = 0;
        int separationBlack = 455;
        Tile tile;
        JLabel label;
        for (int i = 0; i < numWhiteKeys; i++) {
            tile = new Tile(whiteNotes[i], Color.WHITE, whiteTileLoc);
            tile.setActionCommand(BTN_TILE);
            tile.setBounds(55 + 65*i,0,65,heightBounds);
            tile.setPressedIcon(resizeIcon(iconPressedDown, Math.round(iconPressedDown.getIconWidth()*SIZE_MULT_WIDTH),
                    Math.round(iconPressedDown.getIconHeight()*SIZE_MULT_HEIGHT)));
            keyboard.add(tile);
            keyBoard.add(keyboard.get(i), Integer.valueOf(1));
            keyBoard.add(Box.createRigidArea(new Dimension(2, 0)));
            label = new JLabel(whiteLabels[i]);
            label.setBounds(65*(i+1)+15,whiteY,widthBlack,whiteLabel);
            keyBoard.add(label,Integer.valueOf(3));
        }

        LinkedList<Tile> tiles = new LinkedList<>();
        for (int i = 0; i< numBlackKeys; i++){
            tiles.add(new Tile(blackNotes[i], Color.BLACK, blackTileLoc));
            tiles.getLast().setActionCommand(BTN_TILE);
            tiles.getLast().setPressedIcon(resizeIcon(iconPressedDown, Math.round(iconPressedDown.getIconWidth()*SIZE_MULT_WIDTH),
                    Math.round(iconPressedDown.getIconHeight()*SIZE_MULT_HEIGHT)));
        }

        int add = 8;
        for (int i = 0; i < 2; i++) {
            tiles.get(i*5).setBounds(102+(separationBlack*i),yBlack,widthBlack,heightBlack);
            keyboard.add(tiles.get(i*5));
            keyBoard.add(tiles.get(i*5), Integer.valueOf(2));
            label = new JLabel(blackLabels[i*5]);
            label.setBounds(102+(separationBlack*i)+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            keyBoard.add(label,Integer.valueOf(3));

            tiles.get(1+i*5).setBounds(167+(separationBlack*i),yBlack,widthBlack,heightBlack);
            keyboard.add(tiles.get(1+i*5));
            keyBoard.add(tiles.get(1+i*5), Integer.valueOf(2));
            label = new JLabel(blackLabels[1+i*5]);
            label.setBounds(167+(separationBlack*i)+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            keyBoard.add(label,Integer.valueOf(3));

            tiles.get(2+i*5).setBounds(297+(separationBlack*i),yBlack,widthBlack,heightBlack);
            keyboard.add(tiles.get(2+i*5));
            keyBoard.add(tiles.get(2+i*5), Integer.valueOf(2));
            label = new JLabel(blackLabels[2+i*5]);
            label.setBounds(297+(separationBlack*i)+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            keyBoard.add(label,Integer.valueOf(3));

            tiles.get(3+i*5).setBounds(362+(separationBlack*i),yBlack,widthBlack,heightBlack);
            keyboard.add(tiles.get(3+i*5));
            keyBoard.add(tiles.get(3+i*5), Integer.valueOf(2));
            label = new JLabel(blackLabels[3+i*5]);
            label.setBounds(359+(separationBlack*i)+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            keyBoard.add(label,Integer.valueOf(3));

            tiles.get(4+i*5).setBounds(428+(separationBlack*i),yBlack,widthBlack,heightBlack);
            keyboard.add(tiles.get(4+i*5));
            keyBoard.add(tiles.get(4+i*5), Integer.valueOf(2));
            label = new JLabel(blackLabels[4+i*5]);
            label.setBounds(428+(separationBlack*i)+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            keyBoard.add(label,Integer.valueOf(3));
        }
        return keyBoard;
    }
}
