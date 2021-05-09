package Presentation.Ui_Views;

import Presentation.Manager.FreePianoUIManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoTilesUISelectorManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Ui_Views.Tile.*;

public abstract class Piano extends JPanel {
    protected MainFrame mainFrame;

    public static final String BTN_RETURN = "BTN_RETURN";
    public static final String BTN_RECORD = " ";
    public static final String BTN_TILE = "SOUND";
    protected static final String JLAB_SYNTH_TYPE = "Classic Piano";
    public static final String MODIFY = "MODIFY_KEYS";
    protected static Label soundType;


    protected JButton returnB = new JButton(BTN_RETURN);
    protected ImageIcon iconRec = new ImageIcon("Files/drawable/recIcon.png");
    protected JButton recordB = new JButton(BTN_RECORD, iconRec);
    protected JButton profile = new JButton(PROFILE_BUTTON);
    //protected JButton modifyKeys = new JButton();
    protected JToggleButton modifyKeys = new JToggleButton(MODIFY);

    /**
     * private JTextField hey;
     */

    public static ArrayList<Tile> keyboard;
    public static String whiteTileLoc = "Files/drawable/white-key.png";
    public static String blackTileLoc = "Files/drawable/black-key.png";
    /*
    private String[] whiteKeys =
            { "C", "D", "E", "F", "G", "A", "B"};
    private String[] blackKeys =
            { "C#", "D#", "F#", "G#", "A#"};*/
    protected static final int numWhiteKeys = 14;
    protected static final int numBlackKeys = 10;
    protected static final String[] whiteNotes =
            {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b"};
    protected static final String[] blackNotes =
            {"2c#", "2d#", "2f#", "2g#", "2a#", "3c#", "3d#", "3f#", "3g#", "3a#"};
    protected Color[] colors =
            {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.pink};
    protected static final String[] whiteLabels ={
            "Do", "Re", "Mi","Fa", "Sol", "La", "Si","Do", "Re", "Mi","Fa", "Sol", "La", "Si"
    };
    protected static final String[] blackLabels = {
            "Do#", "Re#", "Fa#", "Sol#", "La#", "Do#", "Re#", "Fa#", "Sol#", "La#"
    };
    protected static final String[] keyWhite = {"Q","W","E","R","T","Y","U","Z","X","C","V","B","N","M"};
    protected static final String[] keyBlack = {"2","3","5","6","7","S","D","G","H","J"};

    protected static JLayeredPane layeredPane;
    protected static ImageIcon iconPressed = new ImageIcon("Files/drawable/selected.png");
    protected static ImageIcon iconResetWhite = new ImageIcon(whiteTileLoc);
    protected static ImageIcon iconResetBlack  = new ImageIcon(blackTileLoc);
    protected static ImageIcon iconPressedDown = new ImageIcon("Files/drawable/white-key-down.png");


    public static ArrayList<Tile> getKeyboard() {
        return keyboard;
    }


    /*
    public Piano(final MainFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
        this.keyboard = new ArrayList<>();
        initialize();

    }
/**
 * The initialize function that creates the card layout for the FreePianoUI
 *//*
protected void initialize() {
        this.add(configurePanel());
        this.setBackground(Color.getHSBColor(0,0,0.2f));
    }

    private JPanel configurePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.getHSBColor(0,0,0.2f));

        //All information will go inside here
        panel.add(Box.createRigidArea(new Dimension(10, 200)), BorderLayout.CENTER);

        SIZE_MULT_HEIGHT = 1.31f;
        panel.add(initWhiteKeys(14), BorderLayout.SOUTH);
        panel.add(initMenu(), BorderLayout.PAGE_START);

        return panel;
    }

    private JPanel initWhiteKeys(int a) {
        GridBagConstraints c = new GridBagConstraints();
        JPanel Tiles = new JPanel();
        Tile tile;
        Tiles.setLayout(new GridLayout());
        c.gridy = 0;
        for (int i = 0; i < a; i++) {
            c.gridx = i;
            tile = new Tile(whiteNotes[i], colors[i % 7], whiteTileLoc);
            ImageIcon imageIcon = new ImageIcon("Files/drawable/white-key-down.png");
            tile.setPressedIcon(resizeIcon(imageIcon, Math.round(imageIcon.getIconWidth()*SIZE_MULT_WIDTH), Math.round(imageIcon.getIconHeight()*SIZE_MULT_HEIGHT)));
            tile.setActionCommand(BTN_TILE);
            this.keyboard.add(tile);
            Tiles.add(this.keyboard.get(i), c);
        }

        Tiles.setBorder(new EmptyBorder(4,4,4,4));
        Tiles.setBackground(Color.black);

        return Tiles;
    }

    private JPanel initMenu() {
        JPanel layout = new JPanel(new BorderLayout());
        JPanel menu = new JPanel();
        menu.setBackground(Color.getHSBColor(0,0,80.3f));

        soundType = new Label(JLAB_SYNTH_TYPE);
        soundType.setBackground(Color.WHITE);

        profile.setBackground(Color.black);
        profile.setIcon(new ImageIcon("Files/drawable/profile-picture.png"));
        profile.setIcon(resizeIcon((ImageIcon) profile.getIcon(), (int) Math.round(profile.getIcon().getIconWidth()*0.15), (int) Math.round(profile.getIcon().getIconHeight()*0.15)));

        menu.add(profile);
        menu.add(returnB);
        menu.add(recordB);
        menu.add(pianoSoundB);
        menu.add(synthSoundB);
        menu.add(nextSynther);
        menu.add(prevSynther);
        menu.add(soundType);

        registerController(new FreePianoUIManager());
        layout.add(menu, BorderLayout.WEST);
        layout.setBackground(Color.getHSBColor(0,0,0.2f));
        return layout;
    }

    private void registerController(FreePianoUIManager listener) {
        profile.addActionListener(listener);
        returnB.addActionListener(listener);
        recordB.addActionListener(listener);
        pianoSoundB.addActionListener(listener);
        synthSoundB.addActionListener(listener);
        nextSynther.addActionListener(listener);
        prevSynther.addActionListener(listener);
        this.addKeyListener(listener.getKeyListener());
        for (Tile tile : keyboard) {
            tile.addActionListener(listener);
            tile.addKeyListener(listener.getKeyListener());
        }
    }

    public static void setTypeName(String name) {
        soundType.setText(name);
    }*/


    protected JLayeredPane makeTiles(JLayeredPane keyBoard, int heightBlack, int heightBounds, int yLabel, ArrayList<Tile> keyboard, int whiteLabel, int whiteY){
        int widthBlack = 35;
        int yBlack = 0;
        int separationBlack = 455;
        Tile tile;
        JLabel label;
        //keyboard = new ArrayList<>();
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
