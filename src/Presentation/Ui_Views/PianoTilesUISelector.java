package Presentation.Ui_Views;

import Business.Entities.Translator;
import Presentation.Manager.MainFrame;

import javax.swing.*;
import java.awt.*;


/**
 * PianoTilesUISelector
 *
 * The "PianoTilesUISelector" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
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
        this.keyboard = new ArrayList<>();
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
        panel.add(Box.createRigidArea(new Dimension(10, 350)), BorderLayout.CENTER);

        SIZE_MULT_HEIGHT = 0.9f;
        panel.add(makeKeys(), BorderLayout.SOUTH);
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

    public JLayeredPane makeKeys(){
        int heightBlack = 120;
        int widthBlack = 35;
        int yBlack = 0;
        int separationBlack = 455;
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,250));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));

        Tile tile;

        for (int i = 0; i < numWhiteKeys; i++) {
            tile = new Tile(whiteNotes[i], Color.WHITE, whiteTileLoc);
            tile.setActionCommand(BTN_TILE);
            tile.setBounds(55 + 65*i,0,65,250);
            ImageIcon imageIcon = new ImageIcon("Files/drawable/white-key-down.png");
            tile.setPressedIcon(resizeIcon(imageIcon, Math.round(imageIcon.getIconWidth()*SIZE_MULT_WIDTH), Math.round(imageIcon.getIconHeight()*SIZE_MULT_HEIGHT)));
            //tile.setKeyEvent(Translator.getNumberNoteFromName(Objects.requireNonNull(tile).getName()));
            this.keyboard.add(tile);
            keyBoard.add(this.keyboard.get(i), Integer.valueOf(1));
            keyBoard.add(Box.createRigidArea(new Dimension(2, 0)));
        }

        LinkedList<Tile> tiles = new LinkedList<>();
        for (int i = 0; i< numBlackKeys; i++){
            tiles.add(new Tile(blackNotes[i], Color.BLACK, blackTileLoc));
            tiles.getLast().setActionCommand(BTN_TILE);
            ImageIcon imageIcon = new ImageIcon("Files/drawable/black-key-down.png");
            tiles.getLast().setPressedIcon(resizeIcon(imageIcon, Math.round(imageIcon.getIconWidth()*SIZE_MULT_WIDTH),
                    Math.round(imageIcon.getIconHeight()*SIZE_MULT_HEIGHT)));
        }
        for (int i = 0; i < 2; i++) {
            tiles.get(i*5).setBounds(102+(separationBlack*i),yBlack,widthBlack,heightBlack);
            this.keyboard.add(tiles.get(i*5));
            keyBoard.add(tiles.get(i*5), Integer.valueOf(2));

            tiles.get(1+i*5).setBounds(167+(separationBlack*i),yBlack,widthBlack,heightBlack);
            this.keyboard.add(tiles.get(1+i*5));
            keyBoard.add(tiles.get(1+i*5), Integer.valueOf(2));

            tiles.get(2+i*5).setBounds(297+(separationBlack*i),yBlack,widthBlack,heightBlack);
            this.keyboard.add(tiles.get(2+i*5));
            keyBoard.add(tiles.get(2+i*5), Integer.valueOf(2));

            tiles.get(3+i*5).setBounds(362+(separationBlack*i),yBlack,widthBlack,heightBlack);
            this.keyboard.add(tiles.get(3+i*5));
            keyBoard.add(tiles.get(3+i*5), Integer.valueOf(2));

            tiles.get(4+i*5).setBounds(428+(separationBlack*i),yBlack,widthBlack,heightBlack);
            this.keyboard.add(tiles.get(4+i*5));
            keyBoard.add(tiles.get(4+i*5), Integer.valueOf(2));

        }

        return keyBoard;
    }
}