package Presentation.Ui_Views;

import Presentation.Manager.FreePianoUIManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.TempFreePianoUIManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Presentation.Dictionary_login.PROFILE_BUTTON;

public class FreePianoUI extends JPanel {
    private MainFrame mainFrame;

    public static final String BTN_RETURN = "BTN_RETURN";
    public static final String BTN_RECORD = "BTN_RECORD";
    public static final String BTN_SUSTAIN_SOUND = "BTN_SUSTAIN_SOUND";
    public static final String BTN_SYNTH_SOUND = "BTN_SYNTH_SOUND";
    public static final String BTN_TILE = "SOUND";
    public static final String BTN_NEXT_SYNTHER = "++";
    public static final String BTN_PREV_SYNTHER = "--";
    public static final String JLAB_SYNTH_TYPE = "Classic Piano";
    private static Label soundType;


    private JButton returnB = new JButton(BTN_RETURN);
    private JButton recordB = new JButton(BTN_RECORD);
    private JButton pianoSoundB = new JButton(BTN_SUSTAIN_SOUND);
    private JButton synthSoundB = new JButton(BTN_SYNTH_SOUND);
    private JButton nextSynther = new JButton(BTN_NEXT_SYNTHER);
    private JButton prevSynther = new JButton(BTN_PREV_SYNTHER);
    private JButton profile = new JButton(PROFILE_BUTTON);

    /**
     * private JTextField hey;
     */

    private ArrayList<Tile> keyboard;
    public static String whiteTileLoc = "Files/drawable/white-key.png";
    public static String blackTileLoc = "Files/drawable/black-key.png";
    /*
    private String[] whiteKeys =
            { "C", "D", "E", "F", "G", "A", "B"};
    private String[] blackKeys =
            { "C#", "D#", "F#", "G#", "A#"};*/
    public static final String[] whiteNotes =
            {"2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c"};
    public static final String[] blackNotes =
            {"2c#", "2d#", "", "2f#", "2g#", "2a#", "", "3c#", "3d#", "", "3f#", "3g#", "3a#"};
    private Color[] colors =
            {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.pink};

    /**
     * Constructor for the FreePianoUI, you need to send the mainframe context and will create a card layout
     *
     * @param mainFrame context necessary to create the card layout
     */
    public FreePianoUI(final MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.keyboard = new ArrayList<>();
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the FreePianoUI
     */
    private void initialize() {
        this.add(configurePanel());
    }

    private JPanel configurePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.black);
        panel.add(initWhiteKeys(15), BorderLayout.CENTER);
        panel.add(initMenu(), BorderLayout.PAGE_START);

        return panel;
    }

    private JPanel initWhiteKeys(int a) {
        GridBagConstraints c = new GridBagConstraints();
        JPanel Tiles = new JPanel();
        Tile tile;
        Tiles.setLayout(new GridLayout());
        Tiles.setBackground(Color.black);
        c.gridy = 0;
        for (int i = 0; i < a; i++) {
            c.gridx = i;
            tile = new Tile(whiteNotes[i], colors[i % 7], whiteTileLoc);
            tile.setActionCommand(BTN_TILE);
            this.keyboard.add(tile);
            Tiles.add(this.keyboard.get(i), c);
        }
        Tiles.setBackground(Color.black);
        return Tiles;
    }

    private JPanel initMenu() {
        JPanel menu = new JPanel();
        menu.setBackground(Color.black);

        soundType = new Label(JLAB_SYNTH_TYPE);
        soundType.setBackground(Color.WHITE);

        //profile.setAlignmentX(0.5f);
        //profile.setBorder(new EmptyBorder(12,120,12,120));

        menu.add(profile);
        menu.add(returnB);
        menu.add(recordB);
        menu.add(pianoSoundB);
        menu.add(synthSoundB);
        menu.add(nextSynther);
        menu.add(prevSynther);
        menu.add(soundType);

        registerController3(new FreePianoUIManager());
        return menu;
    }

    private void registerController3(FreePianoUIManager listener) {
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
    }
}