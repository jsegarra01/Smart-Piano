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
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));

        menu.add(Box.createRigidArea(new Dimension(50,10)));


        soundType = new Label(JLAB_SYNTH_TYPE);
        soundType.setBackground(Color.WHITE);

        profile.setBackground(Color.black);
        profile.setIcon(new ImageIcon("Files/drawable/profile-picture.png"));
        profile.setIcon(resizeIcon((ImageIcon) profile.getIcon(), (int) Math.round(profile.getIcon().getIconWidth()*0.15), (int) Math.round(profile.getIcon().getIconHeight()*0.15)));
        recordB.setPreferredSize(new Dimension((int) Math.round(iconRec.getIconWidth()*1.15),
                (int) Math.round(iconRec.getIconHeight()*1.2)));

        //recordB.setName(BTN_RECORD);
        menu.add(profile);
        menu.add(Box.createRigidArea(new Dimension(350,10)));
        menu.add(recordB);
        menu.add(modifyKeys);
        menu.setBackground(Color.getHSBColor(0,0,0.2f));

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

        this.addKeyListener(listener.getKeyListener());
        for (Tile tile : keyboard) {
            tile.addMouseListener(listener);
            tile.addKeyListener(listener.getKeyListener());
        }
    }

    public static void setTypeName(String name) {
        soundType.setText(name);
    }

    public JLayeredPane makeKeys(){
        int heightBlack = 150;
        int widthBlack = 35;
        int separationBlack = 455;
        // Create layerPane
        JLayeredPane keyBoard = new JLayeredPane();
        keyBoard.setPreferredSize(new Dimension(1025,600));
        keyBoard.add(Box.createRigidArea(new Dimension(55, 0)));



        JLabel label;
        keyBoard = makeTiles(keyBoard, heightBlack, 330, 60, keyboard, 50, 275);

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

    public static void labelAppear(boolean modify){
        for(int i =0;i<24;i++){
            layeredPane.getComponent(i).setVisible(modify);
        }
        ImageIcon icon;
        if(modify){
            icon = iconPressed;
        }else{
            icon = iconPressedDown;

        }
        for(int i = 0; i<14;i++){
            keyboard.get(i).setSelectedIcon(iconResetWhite);
            keyboard.get(i).setPressedIcon(resizeIcon(icon, Math.round(icon.getIconWidth()*SIZE_MULT_WIDTH), Math.round(icon.getIconHeight()*SIZE_MULT_HEIGHT)));
        }
        for(int i = 14; i<keyboard.size();i++){
            keyboard.get(i).setSelectedIcon(iconResetBlack);
            keyboard.get(i).setPressedIcon(resizeIcon(icon, Math.round(icon.getIconWidth()*SIZE_MULT_WIDTH), Math.round(icon.getIconHeight()*SIZE_MULT_HEIGHT)));
        }
    }
    public static void setTileColor(Tile tile){
        boolean found = false;
        int i  = 0;
        while(i< keyboard.size() && !found){
            if(tile.equals(keyboard.get(i))){
                found = true;
            }else{
                i++;
            }
        }
        if(found){
            tile.setSelectedIcon(iconPressed);
        }
    }
    public static void modifyKey(Keys key, KeyEvent keyEvent){
        int i = 0;
        boolean found=false;
        while(i<24 && !found){
            if(layeredPane.getComponent(i).getName().equals(key.getNameKey())){
                found=true;
            }else{
                i++;
            }

        }
        if (found){
            layeredPane.getComponent(i).setName(KeyEvent.getKeyText(keyEvent.getKeyCode()));
            //TODO: Check with Edu if we need to control if Java does not know our char
            if(layeredPane.getComponent(i) instanceof JLabel){
                ((JLabel)layeredPane.getComponent(i)).setText(KeyEvent.getKeyText(keyEvent.getKeyCode()));
            }

        }



    }

}

