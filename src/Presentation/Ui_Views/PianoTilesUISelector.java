package Presentation.Ui_Views;

import Business.Entities.Translator;
import Presentation.Manager.MainFrame;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;
import javax.swing.*;
import java.awt.*;


import Presentation.Manager.PianoTilesUISelectorManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Manager.PianoTilesUISelectorManager.timePassed;
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

public class PianoTilesUISelector extends Piano{
    private static ArrayList<Tile> keyboard;
    private static int i = 0;
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
        Piano.mainFrame = mainFrame;
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
       // panel.add(Box.createRigidArea(new Dimension(10, 320)), BorderLayout.CENTER);
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


        menu.add(profile, BorderLayout.WEST);          //400
        menu.add(Box.createRigidArea(new Dimension(200,10)), BorderLayout.CENTER);
        menu.add(songSelector(), BorderLayout.CENTER);
        menu.add(Box.createRigidArea(new Dimension(200,10)), BorderLayout.CENTER);
        registerController(new PianoTilesUISelectorManager());
        return menu;
    }

    private JPanel createGamePane() {
        JPanel panelBorderLayout = new JPanel(new BorderLayout());                  //Sets the lateral borders
        panelBorderLayout.setBackground(Color.getHSBColor(0,0,0.2f));

        JPanel westPane = new JPanel();
        westPane.add(Box.createRigidArea(new Dimension(45, 100)));       //Sets the west column
        westPane.setBackground(Color.getHSBColor(0,0,0.2f));
        panelBorderLayout.add(westPane, BorderLayout.WEST);

        JPanel eastPane = new JPanel();
        eastPane.add(Box.createRigidArea(new Dimension(50, 100)));       //Sets the east column
        eastPane.setBackground(Color.getHSBColor(0,0,0.2f));
        panelBorderLayout.add(eastPane, BorderLayout.EAST);


        //Creates the background for the PianoTiles game

        JLayeredPane jLayeredPane = new JLayeredPane();                             //Sets the middle columns, the ones
        jLayeredPane.setLayout(null);                                               //We care about for the pianoTiles
        jLayeredPane.setPreferredSize(new Dimension(10,300));           //Through the jLayeredPane settings
        jLayeredPane.setBackground(Color.black);
        jLayeredPane.setOpaque(true);

        //Example of how to put things in a jLayeredPane, important to notice the set bounds and opaque thingies
        /*
        JPanel panel = new JPanel();
        panel.setBackground(Color.orange);
        panel.setOpaque(true);
        panel.setBounds(5,100,100,100);
        jLayeredPane.add(panel,Integer.valueOf(1));
        */

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
        try {
            jLayeredPane.add(setTiles(), Integer.valueOf(2));                        //TODO DON'T TOUCH THIS, IS A TRIAL FOR THE PIANO TILES
        } catch (Exception e) {

        }

        panelBorderLayout.add(jLayeredPane, BorderLayout.CENTER);
        return panelBorderLayout;
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

    private JScrollPane songSelector() {

        try {
            //We create the available songs for the user to be played in piano tiles
            ArrayList<String> names = new ArrayList<>(new PianoTilesUISelectorManager().getBusinessSongNames());
            JList<String> songNames = new JList<>(names.toArray(new String[0]));
            songNames.addListSelectionListener(new PianoTilesUISelectorManager());
            songNames.setSelectionMode(0);
            songNames.setVisibleRowCount(4);


            //We create the view of the list in a scroll pane
            JScrollPane scrollPane = new JScrollPane(songNames);
            scrollPane.setPreferredSize(new Dimension(250,60));
            scrollPane.setWheelScrollingEnabled(true);

            return scrollPane;
        } catch (NullPointerException e){
            return new JScrollPane();
        }
    }

    public JLayeredPane makeKeys(){
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,250));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));

        return makeTiles(keyBoard, 120, 250, 45, keyboard, 20, 210);
    }


    public static JPanel setTiles() {       //TODO DON'T TOUCH THIS, IS A TRIAL FOR THE PIANO TILES

        JPanel lala = new JPanel();
        lala.setBackground(Color.orange);
        lala.setBounds(0,0, timePassed*10,50);
        lala.revalidate();
        lala.repaint();
        mainFrame.repaint();
        mainFrame.repaint();
        return lala;
    }
}