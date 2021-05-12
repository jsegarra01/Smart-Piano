package Presentation.Ui_Views;

import Business.Entities.MidiHelper;
import Business.Entities.Translator;
import Presentation.Manager.MainFrame;

import javax.swing.*;
import java.awt.*;


import Presentation.Manager.PianoTilesUISelectorManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Ui_Views.Tile.*;


/**
 * PianoTilesUISelector
 *
 * The "PianoTilesUISelector" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
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

    private JPanel configurePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.getHSBColor(0,0,0.2f));

        //This will be another card layout, which we will have to divide between the free piano or the song piano in order use the same piano for both
        panel.add(Box.createRigidArea(new Dimension(10, 320)), BorderLayout.CENTER);
        panel.add(createGamePane(), BorderLayout.CENTER);
        layeredPane = makeKeys();
        panel.add(layeredPane, BorderLayout.SOUTH);
        panel.add(initMenu(), BorderLayout.PAGE_START);

        return panel;
    }

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

    private void registerController(PianoTilesUISelectorManager listener) {
        profile.addActionListener(listener);
        returnB.addActionListener(listener);
        recordB.addActionListener(listener);
        this.addKeyListener(listener.getKeyListener());
        for (Tile tile : keyboard) {
            tile.addMouseListener(listener);
            tile.addKeyListener(listener.getKeyListener());
        }
    }

    private JLayeredPane createGamePane(){
        JLayeredPane panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(1025,320));
        panel.add(Box.createRigidArea(new Dimension(55, 320)));

        int widthBlack = 35;
        int yBlack = 0;
        int separationBlack = 455;

        for (int i = 0; i < numWhiteKeys; i++) {
            JPanel paneel = new JPanel();
            paneel.setBounds(55 + 65*i,0,65,320);
            panel.add(paneel, Integer.valueOf(1));
            panel.add(Box.createRigidArea(new Dimension(2, 0)));
        }

        int add = 8;
        for (int i = 0; i < 2; i++) {
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();
            JPanel panel4 = new JPanel();
            JPanel panel5 = new JPanel();

            panel1.setBounds(102+(separationBlack*i),yBlack,widthBlack,PianoTilesUISelectorManager.recordingTime);
            panel1.setBackground(Color.BLACK);
            panel.add(panel1, Integer.valueOf(2));

            panel2.setBounds(167+(separationBlack*i),yBlack,widthBlack,320);
            panel2.setBackground(Color.BLACK);
            panel.add(panel2, Integer.valueOf(2));

            panel3.setBounds(297+(separationBlack*i),yBlack,widthBlack,320);
            panel3.setBackground(Color.BLACK);
            panel.add(panel3, Integer.valueOf(2));

            panel4.setBounds(362+(separationBlack*i),yBlack,widthBlack,320);
            panel4.setBackground(Color.BLACK);
            panel.add(panel4, Integer.valueOf(2));

            panel5.setBounds(428+(separationBlack*i),yBlack,widthBlack,320);
            panel5.setBackground(Color.BLACK);
            panel.add(panel5, Integer.valueOf(2));

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    mainFrame.repaint();  // repaint(), etc. according to changed states
                }
            });
        }
        return panel;
    }

    public static void setTypeName(String name) {
        soundType.setText(name);
    }

    public JLayeredPane makeKeys(){
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,250));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));

        return makeTiles(keyBoard, 120, 250, 45, keyboard, 20, 210);
    }


}