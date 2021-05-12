package Presentation.Ui_Views;

//imports needed for the piano tiles
import Presentation.Manager.MainFrame;
import javax.swing.*;
import java.awt.*;
import Presentation.Manager.PianoTilesUISelectorManager;
import java.util.ArrayList;

import static Presentation.Ui_Views.Tile.*;


/**
 * PianoTilesUISelector
 *
 * The "PianoTilesUISelector" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 7 May 2021
 *
 */
public class PianoTilesUISelector extends Piano {
    private static ArrayList<Tile> keyboard;

    public static ArrayList<Tile> getKeyboard() {
        return keyboard;
    }

    /**
     * Constructor for the PianoTilesUISelector, you need to send the mainframe context and will create a card layout
     *
     * @param mainFrame context necessary to create the card layout
     */
    public PianoTilesUISelector(final MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
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
        panel.add(Box.createRigidArea(new Dimension(10, 320)), BorderLayout.CENTER);
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

        menu.add(profile);
        registerController(new PianoTilesUISelectorManager());
        return menu;
    }

    /**
     * Registers the different buttons with the controller PianoTilesUISelectorManager
     * @param listener PianoTilesUISelectorManager. Gets which controller will listen to the different buttons
     */
    private void registerController(PianoTilesUISelectorManager listener) {
        profile.addActionListener(listener);
        recordB.addActionListener(listener);
        this.addKeyListener(listener.getKeyListener());
        for (Tile tile : keyboard) {
            tile.addMouseListener(listener);
            tile.addKeyListener(listener.getKeyListener());
        }
    }

    /**
     * Deprecated. Way to put the setTypeName
     * @param name Sets the name for the different type names.
     */
    public static void setTypeName(String name) {
        soundType.setText(name);
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


}