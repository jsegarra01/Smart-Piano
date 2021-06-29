package Presentation.Ui_Views;

//Imports needed from the dictionary, events and mainframe
import Business.Entities.Keys;
import Business.Translator;
import Presentation.Manager.FreePianoUIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static Presentation.Ui_Views.Tile.*;


/**
 * FreePianoUI
 *
 * The "FreePianoUI" class will different views for the PianoUI
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class FreePianoUI extends Piano {
    //private final BusinessFacadeImp myFacade;
    /**
     * Constructor for the FreePianoUI, you need to send the mainframe context and will create a card layout
     *
     //* @param mainFrame context necessary to create the card layout
     */
    public FreePianoUI(/*BusinessFacadeImp myFacade*/) {
        setKeyboardPiano(new ArrayList<>());
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the FreePianoUI
     */
    private void initialize() {
        this.add(configurePanel());
        this.setBackground(Color.getHSBColor(0,0,0.2f));
    }

    /**
     * Configures the main Panel for the PianoUI
     * @return Returns the JPanel configured for the general settings of the PianoUI
     */
    private JPanel configurePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.getHSBColor(0,0,0.2f));


        panel.add(Box.createRigidArea(new Dimension(10, 210)), BorderLayout.CENTER);
        JLayeredPane layeredPane = makeKeys();
        layeredPane.requestFocus();
        setLayeredPane(layeredPane);
        panel.add(layeredPane, BorderLayout.SOUTH);
        panel.requestFocus();

        panel.add(initMenu(), BorderLayout.PAGE_START);

        return panel;
    }

    /**
     * Initializes the Menu of the main pianoUI panel gets configured
     * @return Menu panel of the piano UI configured
     */
    private JPanel initMenu() {
        JPanel layout = new JPanel(new BorderLayout());
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));

        menu.add(Box.createRigidArea(new Dimension(50,10)));

        setUpSoundType();

        getProfilePiano().setBackground(Color.black);
        getProfilePiano().setIcon(new ImageIcon("Files/drawable/profile-picture.png"));
        getProfilePiano().setIcon(resizeIcon((ImageIcon) getProfilePiano().getIcon(), (int) Math.round(getProfilePiano().getIcon().getIconWidth()*0.15), (int) Math.round(getProfilePiano().getIcon().getIconHeight()*0.15)));
        getRecordB().setPreferredSize(new Dimension((int) Math.round(getIconRec().getIconWidth()*1.15),
                (int) Math.round(getIconRec().getIconHeight()*1.2)));

        menu.add(getProfilePiano());
        menu.add(Box.createRigidArea(new Dimension(350,10)));
        menu.add(getRecordB());
        menu.add(getModifyKeys());
        menu.setBackground(Color.getHSBColor(0,0,0.2f));

        registerController(new FreePianoUIManager(this));
        layout.add(menu, BorderLayout.NORTH);
        layout.setBackground(Color.getHSBColor(0,0,0.2f));
        return layout;
    }

    /**
     * Registers the different buttons with the controller FreePianoUIManager
     * @param listener Gets which controller will listen to the different buttons
     */
    private void registerController(FreePianoUIManager listener) {
        getProfilePiano().addActionListener(listener);
        getRecordB().addActionListener(listener);
        getModifyKeys().addActionListener(listener);

        this.addKeyListener(listener.getKeyListener());
        for (Tile tile : getKeyboard()) {
            tile.addMouseListener(listener);
            tile.addKeyListener(listener.getKeyListener());
        }
    }

    /**
     * Generates the keys in a JLayeredPane.
     * @return JLayeredPane. Contains all the different keys and tiles for our piano keyboard
     */
    public JLayeredPane makeKeys(){
        int heightBlack = 150;
        int widthBlack = 35;
        int separationBlack = 455;
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,600));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));

        JLabel label;
        keyBoard = makeTiles(keyBoard, heightBlack, 330, 60, getKeyboard(), 50, 275);

        for (int i = 0; i < numWhiteKeys; i++) {
            label = new JLabel(Translator.getInstance().get(i).getNameKey());
            label.setName(Translator.getInstance().get(i).getNameKey());
            label.setBounds(65*(i+1)+12,170,widthBlack,40);
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN,label.getFont().getSize()*2));
            label.setVisible(false);
            keyBoard.add(label,Integer.valueOf(4));

        }
        int yLabel = 10;
        int add = 12;
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

    /**
     * Makes the label for each key (which key in the keyboard is that tile) in the Piano to appear
     * @param modify Boolean. Checks if we need to modify the keys or not
     */
    public void labelAppear(boolean modify){
        for(int i =0;i<24;i++){
            getLayeredPane().getComponent(i).setVisible(modify);
        }
        ImageIcon icon;
        if(modify){
            icon = iconPressed;
        }else{
            icon = iconPressedDown;

        }
        for(int i = 0; i<14;i++){
            getKeyboard().get(i).setSelectedIcon(iconResetWhite);
            getKeyboard().get(i).setPressedIcon(resizeIcon(icon, Math.round(icon.getIconWidth()*SIZE_MULT_WIDTH), Math.round(icon.getIconHeight()*SIZE_MULT_HEIGHT)));
        }
        for(int i = 14; i<getKeyboard().size();i++){
            getKeyboard().get(i).setSelectedIcon(iconResetBlack);
            getKeyboard().get(i).setPressedIcon(resizeIcon(icon, Math.round(icon.getIconWidth()*SIZE_MULT_WIDTH), Math.round(icon.getIconHeight()*SIZE_MULT_HEIGHT)));
        }
    }

    /**
     * Sets a color for the Tile
     * @param tile Sets the color for the tile to appear
     */
    public void setTileColor(Tile tile){
        boolean found = false;
        int i  = 0;
        while(i< getKeyboard().size() && !found){
            if(tile.equals(getKeyboard().get(i))){
                found = true;
            }else{
                i++;
            }
        }
        if(found){
            tile.setSelectedIcon(iconPressed);
        }
    }

    /**
     * Enables the modification of the key selected in order to select which key is that tile.
     * @param key new key that we want to modify to the old tile
     * @param keyEvent keyEvent listener we want to be modified and selected for the new key
     */
    public void modifyKey(Keys key, KeyEvent keyEvent){
        int i = 0;
        boolean found=false;
        JLayeredPane layeredPane = getLayeredPane();

        while(i<24 && !found){
            if(layeredPane.getComponent(i).getName().equals(key.getNameKey())){
                found=true;
            }else{
                i++;
            }
        }
        if (found){
            if(layeredPane.getComponent(i) instanceof JLabel){
                layeredPane.getComponent(i).setName(KeyEvent.getKeyText(keyEvent.getKeyCode()));
                ((JLabel)layeredPane.getComponent(i)).setText(KeyEvent.getKeyText(keyEvent.getKeyCode()));
            }
        }
    }
}

