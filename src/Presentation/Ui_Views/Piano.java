package Presentation.Ui_Views;

import Presentation.Manager.FreePianoUIManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoTilesUISelectorManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Ui_Views.Tile.*;

public abstract class Piano extends JPanel {
    protected MainFrame mainFrame;

    PianoTilesUISelectorManager listener = new PianoTilesUISelectorManager();

    public static final int NUM_KEYS = 7;
    public static final int NUM_OCTAVES = 2;
    /** Holds the possible notes.*/
    protected String[] notes = {"C","D","E","F","G","A","B"};
    /** Holds the possible sharps.*/
    protected String[] sharps = {"C#","D#","F#","G#","A#"};
    protected String[] octave = {"4","5"};





    public static final String BTN_RETURN = "BTN_RETURN";
    public static final String BTN_RECORD = "BTN_RECORD";
    public static final String BTN_SUSTAIN_SOUND = "BTN_SUSTAIN_SOUND";
    public static final String BTN_SYNTH_SOUND = "BTN_SYNTH_SOUND";
    public static final String BTN_TILE = "SOUND";
    public static final String BTN_NEXT_SYNTHER = "++";
    public static final String BTN_PREV_SYNTHER = "--";
    protected static final String JLAB_SYNTH_TYPE = "Classic Piano";
    protected static Label soundType;


    protected JButton returnB = new JButton(BTN_RETURN);
    protected JButton recordB = new JButton(BTN_RECORD);
    protected JButton pianoSoundB = new JButton(BTN_SUSTAIN_SOUND);
    protected JButton synthSoundB = new JButton(BTN_SYNTH_SOUND);
    protected JButton nextSynther = new JButton(BTN_NEXT_SYNTHER);
    protected JButton prevSynther = new JButton(BTN_PREV_SYNTHER);
    protected JButton profile = new JButton(PROFILE_BUTTON);

    /**
     * private JTextField hey;
     */

    protected ArrayList<Tile> keyboard;
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
            {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c"};
    protected static final String[] blackNotes =
            {"2c#", "2d#", "", "2f#", "2g#", "2a#", "", "3c#", "3d#", "", "3f#", "3g#", "3a#"};
    protected Color[] colors =
            {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.pink};
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
}
