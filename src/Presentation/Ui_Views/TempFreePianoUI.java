package Presentation.Ui_Views;

//Imports all necessary libraries
import Presentation.Manager.MainFrame;
import Presentation.Manager.PreMenuUIManager;
import Presentation.Manager.TempFreePianoUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Presentation.Dictionary_login.*;

import static Presentation.DictionaryPiano.*;
/**
 * TempFreePianoUI
 *
 * The "TempFreePianoUI" class will contain the different methods to create the view class card layout "TempFreePianoUI" and TempFreePiano interface
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class TempFreePianoUI extends JPanel {
    private MainFrame mainFrame;

    private JButton logIn = new JButton(LOG_IN_BUTTON);
    private JButton signUp = new JButton(SIGN_UP_BUTTON);
    private JButton guest = new JButton(ENTER_AS_GUEST_BUTTON);

    public static JPanel centralPanel = new JPanel(new CardLayout());

    PianoTilesUISelector pianoTilesUISelector;
    PianoTilesUIGame pianoTilesUIGame;
    SpotiUI spotiUI;



    public static final String BTN_RETURN = "BTN_RETURN";
    public static final String BTN_RECORD = "BTN_RECORD";
    public static final String BTN_SUSTAIN_SOUND = "BTN_SUSTAIN_SOUND";
    public static final String BTN_SYNTH_SOUND = "BTN_SYNTH_SOUND";
    public static final String BTN_TILE = "SOUND";
    public static final String BTN_NEXT_SYNTHER = "++";
    public static final String BTN_PREV_SYNTHER = "--";
    public static final String JLAB_SYNTH_TYPE = "Classic Piano";
    private static Label soundType;

    private JButton returnB;
    private JButton recordB;
    private JButton pianoSoundB;
    private JButton synthSoundB;
    private JButton nextSynther;
    private JButton prevSynther;
    private JButton profile;

    /**private JTextField hey;*/

    private ArrayList<Tile> keyboard;
    public static String whiteTileLoc = "Files/drawable/white-key.png";
    public static String blackTileLoc = "Files/drawable/black-key.png";
    /*
    private String[] whiteKeys =
            { "C", "D", "E", "F", "G", "A", "B"};
    private String[] blackKeys =
            { "C#", "D#", "F#", "G#", "A#"};*/
    public static final String[] whiteNotes =
            { "2c", "2d", "2e", "2f", "2g", "2a", "2b", "3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c"};
    public static final String[] blackNotes =
            { "2c#", "2d#", "", "2f#", "2g#", "2a#", "", "3c#", "3d#", "", "3f#", "3g#", "3a#"};
    private Color[] colors =
            {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.pink};

    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public TempFreePianoUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        pianoTilesUISelector = new PianoTilesUISelector(mainFrame);
        pianoTilesUIGame = new PianoTilesUIGame(mainFrame);
        spotiUI = new SpotiUI(mainFrame);
        this.keyboard = new ArrayList<>();
        this.mainFrame = mainFrame;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the TempFreePianoUI
     */
    private void initialize() {
        mainFrame.setTitle("I am a piano");
        mainFrame.setSize(1000, 400);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 45)));
        logIn.setActionCommand(LOG_IN_BUTTON);
        logIn.setAlignmentX(0.5f);
        logIn.setBorder(new EmptyBorder(12,120,12,120));

        signUp.setActionCommand(SIGN_UP_BUTTON);
        signUp.setAlignmentX(0.5f);
        signUp.setBorder(new EmptyBorder(12,116,12,116));

        guest.setActionCommand(ENTER_AS_GUEST_BUTTON);
        guest.setAlignmentX(0.5f);
        guest.setBorder(new EmptyBorder(12,88,12,88));

        registerController(new TempFreePianoUIManager());

        buttonsPanel.add(logIn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(signUp);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 25)));
        buttonsPanel.add(guest);

        this.add(buttonsPanel, BorderLayout.WEST);

        centralPanel.add(pianoTilesUIGame, PIANO_TILES_UI_GAME);
        centralPanel.add(pianoTilesUISelector, PIANO_TILES_UI_SELECTOR);
        this.add(centralPanel, BorderLayout.CENTER);


        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 15)));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(configurePanel());
        userButtons.add(Box.createRigidArea(new Dimension(10, 45)));
        profile.setActionCommand(PROFILE_BUTTON);
        profile.setAlignmentX(0.5f);
        profile.setBorder(new EmptyBorder(12,120,12,120));

        registerController(new TempFreePianoUIManager());
        userButtons.add(profile);
        this.add(userButtons, BorderLayout.NORTH);
        this.setSize(600, 400);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
        mainFrame.pack();
    }


    private JPanel configurePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.black);
        panel.add(initWhiteKeys(15),BorderLayout.CENTER);
        panel.add(initMenu(),BorderLayout.PAGE_START);
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
            tile = new Tile(whiteNotes[i], colors[i%7], whiteTileLoc);
            tile.setActionCommand(BTN_TILE);
            this.keyboard.add(tile);
            Tiles.add(this.keyboard.get(i),c);
        }
        Tiles.setBackground(Color.black);
        return Tiles;
    }

    private JPanel initMenu(){
        JPanel menu = new JPanel();
        menu.setBackground(Color.black);

        profile = new JButton(PROFILE_BUTTON);
        returnB = new JButton(BTN_RETURN);
        recordB = new JButton(BTN_RECORD);
        pianoSoundB = new JButton(BTN_SUSTAIN_SOUND);
        synthSoundB = new JButton(BTN_SYNTH_SOUND);
        nextSynther = new JButton(BTN_NEXT_SYNTHER);
        prevSynther = new JButton(BTN_PREV_SYNTHER);

        soundType = new Label(JLAB_SYNTH_TYPE);
        soundType.setBackground(Color.WHITE);

        profile.setActionCommand(PROFILE_BUTTON);
        returnB.setActionCommand(BTN_RETURN);
        recordB.setActionCommand(BTN_RECORD);
        pianoSoundB.setActionCommand(BTN_SUSTAIN_SOUND);
        synthSoundB.setActionCommand(BTN_SYNTH_SOUND);
        nextSynther.setActionCommand(BTN_NEXT_SYNTHER);
        prevSynther.setActionCommand(BTN_PREV_SYNTHER);


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

        registerController(new TempFreePianoUIManager());
        return menu;
    }


    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(TempFreePianoUIManager listener) {
        profile.addActionListener(listener);
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);
        guest.addActionListener(listener);

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

    public static void setTypeName(String name){
        soundType.setText(name);
    }
    /*public void setImg(boolean type){
        if(type){
            String path = "Files/drawable/white-key-down.png";
            for (int i = 0; i < keyboard.size(); i++) {
                Tile tileToSet = new Tile(whiteNotes[i], colors[i%7], path);
                keyboard.set(i, tileToSet);
            }

            Tiles.add(this.keyboard.get(i),c);
        }else{
            this.whiteTileLoc = "Files/drawable/white-key.png";
        }
    }*/

}