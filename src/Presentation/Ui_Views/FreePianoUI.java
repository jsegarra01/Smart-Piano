package Presentation.Ui_Views;

import Business.Entities.Keys;
import Business.Entities.Translator;
import Presentation.Manager.FreePianoUIManager;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import static Presentation.Dictionary_login.PROFILE_BUTTON;
import static Presentation.Ui_Views.Tile.*;
import static javax.swing.SwingConstants.CENTER;

public class FreePianoUI extends Piano {

    /**
     * Constructor for the FreePianoUI, you need to send the mainframe context and will create a card layout
     *
     * @param mainFrame context necessary to create the card layout
     */
    public FreePianoUI(final MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        keyboard = new ArrayList<>();
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the FreePianoUI
     */
    private void initialize() {
        this.add(configurePanel());
        this.setBackground(Color.getHSBColor(0,0,0.2f));
    }

    private JPanel configurePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.getHSBColor(0,0,0.2f));

        //All information will go inside here
        panel.add(Box.createRigidArea(new Dimension(10, 240)), BorderLayout.CENTER);
        layeredPane = makeKeys();
        layeredPane.requestFocus();
        panel.add(layeredPane, BorderLayout.SOUTH);
        panel.requestFocus();

        panel.add(initMenu(), BorderLayout.PAGE_START);

        return panel;
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
        recordB.setPreferredSize(new Dimension((int) Math.round(iconRec.getIconWidth()*1.15),
                (int) Math.round(iconRec.getIconHeight()*1.2)));

        //recordB.setName(BTN_RECORD);
        menu.add(profile);
        menu.add(returnB);
        menu.add(recordB);
        menu.add(pianoSoundB);
        menu.add(synthSoundB);
        menu.add(nextSynther);
        menu.add(prevSynther);
        menu.add(soundType);
        menu.add(modifyKeys);

        registerController(new FreePianoUIManager());
        layout.add(menu, BorderLayout.NORTH);
        layout.setBackground(Color.getHSBColor(0,0,0.2f));
        return layout;
    }

    private void registerController(FreePianoUIManager listener) {
        profile.addActionListener(listener);
        returnB.addActionListener(listener);
        recordB.addActionListener(listener);
        modifyKeys.addActionListener(listener);

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

    public static JLayeredPane makeKeys(){
        int heightBlack = 150;
        int widthBlack = 35;
        int yBlack = 0;
        int separationBlack = 455;
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,600));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));

        Tile tile;

        JLabel label;
        for (int i = 0; i < numWhiteKeys; i++) {
            tile = new Tile(whiteNotes[i], Color.WHITE, whiteTileLoc);
            tile.setActionCommand(BTN_TILE);
            tile.setBounds(55 + 65*i,0,65,330);
            tile.setPressedIcon(resizeIcon(iconPressedDown, Math.round(iconPressedDown.getIconWidth()*SIZE_MULT_WIDTH),
                    Math.round(iconPressedDown.getIconHeight()*SIZE_MULT_HEIGHT)));
            keyboard.add(tile);
            keyBoard.add(keyboard.get(i), Integer.valueOf(1));
            keyBoard.add(Box.createRigidArea(new Dimension(2, 0)));
            label = new JLabel(whiteLabels[i]);
            label.setBounds(65*(i+1)+15,275,widthBlack,50);
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
        int yLabel = 60;
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

        for (int i = 0; i < numWhiteKeys; i++) {
            label = new JLabel(Translator.getInstance().get(i).getNameKey());
            label.setName(Translator.getInstance().get(i).getNameKey());
            label.setBounds(65*(i+1)+12,170,widthBlack,40);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN,label.getFont().getSize()*2));
            label.setVisible(false);
            keyBoard.add(label,Integer.valueOf(4));

        }
        yLabel = 10;
        add = 12;
        int j = 0;
        for (int i = numWhiteKeys; i < numWhiteKeys+2; i++) {
            label = new JLabel(Translator.getInstance().get(i+4*j).getNameKey());
            label.setName(Translator.getInstance().get(i+4*j).getNameKey());
            label.setBounds(102+(separationBlack*(i-numWhiteKeys))+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            label.setVisible(false);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN, (int) (label.getFont().getSize()*1.7)));
            keyBoard.add(label,Integer.valueOf(4));

            label = new JLabel(Translator.getInstance().get(1+i+4*j).getNameKey());
            label.setName(Translator.getInstance().get(1+i+4*j).getNameKey());
            label.setBounds(167+(separationBlack*(i-numWhiteKeys))+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            label.setVisible(false);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN, (int) (label.getFont().getSize()*1.7)));
            keyBoard.add(label,Integer.valueOf(4));

            label = new JLabel(Translator.getInstance().get(2+i+4*j).getNameKey());
            label.setName(Translator.getInstance().get(2+i+4*j).getNameKey());
            label.setBounds(297+(separationBlack*(i-numWhiteKeys))+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            label.setVisible(false);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN, (int) (label.getFont().getSize()*1.7)));
            keyBoard.add(label,Integer.valueOf(4));

            label = new JLabel(Translator.getInstance().get(3+i+4*j).getNameKey());
            label.setName(Translator.getInstance().get(3+i+4*j).getNameKey());
            label.setBounds(361+(separationBlack*(i-numWhiteKeys))+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            label.setVisible(false);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN, (int) (label.getFont().getSize()*1.7)));
            keyBoard.add(label,Integer.valueOf(4));

            label = new JLabel(Translator.getInstance().get(4+i+4*j).getNameKey());
            label.setName(Translator.getInstance().get(4+i+4*j).getNameKey());
            label.setBounds(430+(separationBlack*(i-numWhiteKeys))+add,yLabel,widthBlack,heightBlack);
            label.setForeground(Color.WHITE);
            label.setVisible(false);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN, (int) (label.getFont().getSize()*1.7)));
            keyBoard.add(label,Integer.valueOf(4));
            j = 1;
        }
        return keyBoard;
    }



}

