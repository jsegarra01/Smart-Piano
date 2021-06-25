package Presentation.Ui_Views;

//imports needed for the piano tiles
import Business.Entities.Keys;
import Presentation.Manager.MainFrame;
import javax.swing.*;
import java.awt.*;
import Presentation.Manager.PianoTilesUISelectorManager;

import java.util.ArrayList;

import static Presentation.DictionaryPiano.*;
import static Presentation.Ui_Views.Tile.*;


/**
 * PianoTilesUISelector
 *
 * The "PianoTilesUISelector" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class PianoTilesUISelector extends Piano {
    private PianoTilesUISelectorManager pianoTilesUISelectorManager;
    private static ArrayList<Tile> keyboard;
    public static ArrayList<Tile> getKeyboard() {
        return keyboard;
    }
    public static JLayeredPane jLayeredPane = new JLayeredPane();
    public static JPanel scrollPanel = new JPanel();
    public static JButton playButtonTiles = new JButton(PLAY_BUTTON);
    public static ArrayList<Keys> keys = new ArrayList<>();

    public JButton veryEasy = new JButton(VERY_EASY_MODE);
    public JButton easy = new JButton(EASY_MODE);
    public JButton normal = new JButton(NORMAL_MODE);
    public JButton hard = new JButton(HARD_MODE);
    public JButton veryHard = new JButton(VERY_HARD_MODE);
    private static final JButton mute = new JButton(MUTE_BUTTON);


    /**
     * Constructor for the PianoTilesUISelector, you need to send the mainframe context and will create a card layout
     *
     * @param mainFrame context necessary to create the card layout
     */
    public PianoTilesUISelector(final MainFrame mainFrame) {
        super();
        pianoTilesUISelectorManager = new PianoTilesUISelectorManager();
        keyboard = new ArrayList<>();
        initialize();
    }


    /**
     * The initialize function that creates the card layout for the PianoTilesUISelector
     */
    private void initialize() {
        this.add(configurePanel());
        this.setBackground(Color.getHSBColor(0,0,0.2f));
    }

    /**
     * Configures the main Panel for the PianoTilesUISelectorManager
     * @return Returns the JPanel configured for the general settings of the PianoTilesUISelectorManager
     */
    private JPanel configurePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.getHSBColor(0,0,0.2f));

        //This will be another card layout, which we will have to divide between the free piano or the song piano in order use the same piano for both
        // panel.add(Box.createRigidArea(new Dimension(10, 320)), BorderLayout.CENTER);
        panel.add(createGamePane(), BorderLayout.CENTER);

        layeredPane = makeKeys();
        panel.add(layeredPane, BorderLayout.SOUTH);
        panel.add(initMenu(), BorderLayout.PAGE_START);

        return panel;
    }

    /**
     * Initializes the Menu of the main pianoUI panel gets configured
     * @return Menu panel of the piano UI configured
     */
    private JPanel initMenu() {
        JPanel menu = new JPanel();
        menu.setBackground(Color.getHSBColor(0,0,80.3f));

        soundType = new Label(JLAB_SYNTH_TYPE);
        soundType.setBackground(Color.WHITE);

        profile.setBackground(Color.black);
        profile.setIcon(new ImageIcon("Files/drawable/profile-picture.png"));
        profile.setIcon(resizeIcon((ImageIcon) profile.getIcon(), (int) Math.round(profile.getIcon().getIconWidth()*0.15), (int) Math.round(profile.getIcon().getIconHeight()*0.15)));

        mute.setIcon(new ImageIcon("Files/drawable/mute.png"));
        mute.setIcon(resizeIcon((ImageIcon) mute.getIcon(), (int) Math.round(mute.getIcon().getIconWidth()*0.08), (int) Math.round(mute.getIcon().getIconHeight()*0.08)));
        mute.setOpaque(false);
        mute.setContentAreaFilled(false);
        mute.setBorderPainted(false);
        mute.setActionCommand(MUTE_BUTTON);

        playButtonTiles.setBackground(Color.getHSBColor(0,0,80.3f));
        playButtonTiles.setIcon(new ImageIcon("Files/drawable/play-button.png"));
        playButtonTiles.setForeground(Color.getHSBColor(0,0,80.3f));
        playButtonTiles.setOpaque(false);
        playButtonTiles.setContentAreaFilled(false);
        playButtonTiles.setBorderPainted(false);

        menu.add(profile);
        menu.add(mute);
        menu.add(playButtonTiles);
        refreshSongList();
        menu.add(scrollPanel);
        menu.add(Box.createRigidArea(new Dimension(25,10)), BorderLayout.CENTER);
        menu.add(veryEasy);
        menu.add(easy);
        menu.add(normal);
        menu.add(hard);
        menu.add(veryHard);
        registerController(pianoTilesUISelectorManager);
        return menu;
    }

    /**
     * Sets the panel where the tiles will fall
     * @return The panel created
     */
    private JPanel createGamePane() {
        JPanel panelBorderLayout = new JPanel(new BorderLayout());//Sets the lateral borders
        panelBorderLayout.setBackground(Color.getHSBColor(0,0,0.2f));

        JPanel westPane = new JPanel();
        westPane.add(Box.createRigidArea(new Dimension(45, 100)));       //Sets the west column
        westPane.setBackground(Color.getHSBColor(0,0,0.2f));
        panelBorderLayout.add(westPane, BorderLayout.WEST);

        JPanel eastPane = new JPanel();
        eastPane.add(Box.createRigidArea(new Dimension(50, 100)));       //Sets the east column
        eastPane.setBackground(Color.getHSBColor(0,0,0.2f));
        panelBorderLayout.add(eastPane, BorderLayout.EAST);

        initTileGame();

        for (int i = 0; i < 14; i++) {                                              //Vertical lines
            JPanel auxiliar = new JPanel();
            auxiliar.setBounds(i*65 + 65,0,1,300);
            auxiliar.setBackground(Color.WHITE);
            auxiliar.setOpaque(true);
            jLayeredPane.add(auxiliar,Integer.valueOf(1));
        }

        for (int i = 0; i < 100; i++) {                                              //Horitzontal lines
            JPanel auxiliar = new JPanel();
            auxiliar.setBounds(i*10,280,5,1);
            auxiliar.setBackground(Color.WHITE);
            auxiliar.setOpaque(true);
            jLayeredPane.add(auxiliar,Integer.valueOf(1));
        }

        jLayeredPane.revalidate();
        jLayeredPane.repaint();
        panelBorderLayout.add(jLayeredPane, BorderLayout.CENTER);
        return panelBorderLayout;
    }

    /**
     * Registers the different buttons with the controller PianoTilesUISelectorManager
     * @param listener PianoTilesUISelectorManager. Gets which controller will listen to the different buttons
     */
    private void registerController(PianoTilesUISelectorManager listener) {
        profile.addActionListener(listener);
        recordB.addActionListener(listener);
        playButtonTiles.addActionListener(listener);
        veryEasy.addActionListener(listener);
        easy.addActionListener(listener);
        normal.addActionListener(listener);
        hard.addActionListener(listener);
        veryHard.addActionListener(listener);
        mute.addActionListener(listener);

        this.addKeyListener(listener.getKeyListener());
        for (Tile tile : keyboard) {
            tile.addMouseListener(listener);
            tile.addKeyListener(listener.getKeyListener());
        }
    }

    /**
     * Generates the keys in a JLayeredPane.
     * @return JLayeredPane. Contains all the different keys and tiles for our PianoTilesUISelector keyboard
     */
    public JLayeredPane makeKeys(){
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,250));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));

        return makeTiles(keyBoard, 120, 250, 45, keyboard, 20, 210);
    }

    /**
     * Init of the piano tiles functionality creating all the vertical and horizontal tiles that will
     * indicate where will the keys fall from
     */
    public static void initTileGame () {
        jLayeredPane.removeAll();
        //Sets the middle columns, the ones
        jLayeredPane.setLayout(null);                                               //We care about for the pianoTiles
        jLayeredPane.setPreferredSize(new Dimension(10,300));           //Through the jLayeredPane settings
        jLayeredPane.setBackground(Color.black);
        jLayeredPane.setOpaque(true);

        for (int i = 0; i < 14; i++) {                                              //Vertical lines
            JPanel auxiliar = new JPanel();
            auxiliar.setBounds(i*65 + 65,0,1,300);
            auxiliar.setBackground(Color.WHITE);
            auxiliar.setOpaque(true);
            jLayeredPane.add(auxiliar,Integer.valueOf(1));
        }

        for (int i = 0; i < 100; i++) {                                              //Horitzontal lines
            JPanel auxiliar = new JPanel();
            auxiliar.setBounds(i*10,280,5,1);
            auxiliar.setBackground(Color.WHITE);
            auxiliar.setOpaque(true);
            jLayeredPane.add(auxiliar,Integer.valueOf(1));
        }
    }

    /**
     * Refresh the position and size of th falling keys depending on the song and difficulty selected
     */
    public void refreshTiles() {
        float tile_position = 0;

        while (114 < jLayeredPane.getComponentCount()) {
            jLayeredPane.remove(0);
        }

        for (Keys key: keys) {
            float time = key.getStartTime()/18.38f;
            float duration = key.getDuration()/18.38f;
            int timePassed = pianoTilesUISelectorManager.getTimePassed();
            float velocityModifier = pianoTilesUISelectorManager.getVelocityModifier();

            if ((int) ((timePassed - time + duration) * velocityModifier) >= 0 && ((timePassed - time) * velocityModifier) < 400 && key.getKeyCode() >= 48 && key.getKeyCode() < 72) {
                tile_position = (key.getKeyCode() % 24) * 32.5f + 12;
                if (key.getKeyCode()%24 > 4) {
                    tile_position += 32.5f;
                    if (key.getKeyCode()%24 > 11) {
                        tile_position += 32.5f;
                        if (key.getKeyCode()%24 > 16) {
                            tile_position += 32.5f;
                        }
                    }
                }



                JPanel lala = new JPanel();                 //Every 50 pixels = 1 sec if possible
                lala.setBackground(Color.orange);           //initial time                                              //final - initial time
                lala.setBounds((int) tile_position, (int) (((timePassed - time) * velocityModifier) * 7.5), 40, (int) ((duration * velocityModifier) *7.5));
                lala.setVisible(true);
                lala.setOpaque(true);
                lala.revalidate();
                lala.repaint();
                jLayeredPane.add(lala, Integer.valueOf(2));
            }
        }

        jLayeredPane.revalidate();
        jLayeredPane.repaint();
        jLayeredPane.updateUI();
    }

    /**
     * Repaints the scroll panel depending on the new songs that should appear after the user scrolls
     */
    public void refreshSongList() {
        scrollPanel.removeAll();

        //We create the available songs for the user to be played in piano tiles
        ArrayList<String> names = new ArrayList<>(pianoTilesUISelectorManager.getBusinessSongNames());
        JList<String> songNames = new JList<>(names.toArray(new String[0]));
        songNames.addListSelectionListener(pianoTilesUISelectorManager);
        songNames.setSelectionMode(0);
        songNames.setVisibleRowCount(4);

        //We create the view of the list in a scroll pane
        JScrollPane scrollPane = new JScrollPane(songNames);
        scrollPane.setPreferredSize(new Dimension(250, 60));
        scrollPane.setWheelScrollingEnabled(true);

        scrollPanel.add(scrollPane);
        scrollPanel.revalidate();
        scrollPanel.repaint();
        scrollPanel.updateUI();
    }

    /**
     * Sets the keys that will fall for the piano tiles functionality
     * @param newKeys Array of keys we get from the songs we want to play
     */
    public static void setKeys(ArrayList<Keys> newKeys) {
        keys.clear();
        keys = newKeys;
    }

    public PianoTilesUISelector (PianoTilesUISelectorManager pianoTilesUISelectorManager) {
        this.pianoTilesUISelectorManager = pianoTilesUISelectorManager;
    }

}