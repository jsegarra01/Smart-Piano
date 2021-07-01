package Presentation.Ui_Views;

//Imports needed from the dictionary, events and mainframe
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static Presentation.DictionaryPiano.*;
import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Ui_Views.Tile.*;

/**
 * Piano
 *
 * Abstract class that will be used in order to reuse the piano, so that for the FreePiano and the PianoTiles it can
 * use the same methods and attributes
 *
 * @author OOPD 20-21 ICE5
 * @version 1.5 23 May 2021
 *
 */
public abstract class Piano extends JPanel {
    private static final String whiteTileLoc = "Files/drawable/white-key.png";
    private static final String blackTileLoc = "Files/drawable/black-key.png";
    protected static final int numWhiteKeys = 14;
    protected static final int numBlackKeys = 10;

    public static final ImageIcon iconPressed = new ImageIcon("Files/drawable/selected.png");
    public static final ImageIcon iconResetWhite = new ImageIcon(whiteTileLoc);
    public static final ImageIcon iconResetBlack  = new ImageIcon(blackTileLoc);
    public static final ImageIcon iconPressedDown = new ImageIcon("Files/drawable/white-key-down.png");

    private final ImageIcon iconRec = new ImageIcon("Files/drawable/recIcon.png");
    private final JButton recordB = new JButton(BTN_RECORD, iconRec);
    private final JButton profile = new JButton(PROFILE_BUTTON);
    private final JToggleButton modifyKeys = new JToggleButton(MODIFY);
    private ArrayList<Tile> keyboard;

    private final String[] whiteNotes =
            {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b"};
    private final String[] blackNotes =
            {"2c#", "2d#", "2f#", "2g#", "2a#", "3c#", "3d#", "3f#", "3g#", "3a#"};
    private final String[] whiteLabels ={
            "Do", "Re", "Mi","Fa", "Sol", "La", "Si","Do", "Re", "Mi","Fa", "Sol", "La", "Si"
    };
    private final String[] blackLabels = {
            "Do#", "Re#", "Fa#", "Sol#", "La#", "Do#", "Re#", "Fa#", "Sol#", "La#"
    };

    private JLayeredPane layeredPane;



    public void setLayeredPane (JLayeredPane layeredPane1) {
        layeredPane = layeredPane1;
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    /**
     * Gets the current keyboard
     * @return ArrayList of Tile keyboard. Returns the keyboard we currently have
     */
    public ArrayList<Tile> getKeyboard() {
        return keyboard;
    }

    /**
     * Creates the Tiles for the keyboard, depending on various parameters.
     * @param keyBoard JLayeredPane Where we want the keyboard to be placed.
     * @param heightBlack int The height we want the black tiles to have.
     * @param heightBounds int The maximum height we want for the tiles to have.
     * @param yLabel int The different key names for the y
     * @param keyboard  Current keyboard we have to create the tiles in based on the different tiles
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

    /**
     * Method that gets the icon of recording
     * @return Image icon storing the icon of the recording
     */
    public ImageIcon getIconRec() {
        return iconRec;
    }

    /**
     * Method that gets the button to record a song
     * @return JButton corresponding to the recording
     */
    public JButton getRecordB() {
        return recordB;
    }

    /**
     * Method that gets the button of the profile
     * @return JButton corresponding to the profile
     */
    public JButton getProfilePiano() {
        return profile;
    }

    /**
     * Method that gets the toggle button of the modify keys
     * @return JToggleButton corresponding to the modify keys
     */
    public JToggleButton getModifyKeys(){
        return modifyKeys;
    }

    /**
     * Method that sets the keyboard from the piano
     * @param newKeyboard Defines the keyboard to be set
     */
    public void setKeyboardPiano(ArrayList<Tile> newKeyboard) {
        keyboard = newKeyboard;
    }

}

