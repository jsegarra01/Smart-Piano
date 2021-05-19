package Presentation.Ui_Views;

//Imports all necessary libraries
import Business.Entities.Observer;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static Presentation.DictionaryPiano.*;

/**
 * TempFreePianoUI
 *
 * The "TempFreePianoUI" class will contain the different methods to create the view class card layout "TempFreePianoUI" and TempFreePiano interface
 *
 * @author OOPD 20-21 ICE5
 * @version 1.5 1 May 2021
 *
 */
public class PianoFrame extends JPanel {
    public static MainFrame mainFrame;

    public static JButton freePiano = new JButton(FREE_PIANO);
    public static JButton playSong = new JButton(PLAY_A_SONG);
    public static JButton musicPlayer = new JButton(MUSIC_PLAYER);
    public static int actionTimer = 0;
    private static List<Observer> observers = new ArrayList<Observer>();

    public static JPanel centralPanel = new JPanel(new CardLayout());

    PianoTilesUISelector pianoTilesUISelector;
    SpotiUI spotiUI;
    FreePianoUI freePianoUI;



    public PianoFrame(){}
    /**
     * Constructor for the TempFreePianoUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public PianoFrame(final MainFrame mainFrame) {
        super();
        PianoFrame.mainFrame =mainFrame;

        pianoTilesUISelector = new PianoTilesUISelector(mainFrame);
        spotiUI = new SpotiUI();
        freePianoUI = new FreePianoUI();
        initialize();
    }

    public PianoFrame(int state) {
        actionTimer = state;
        notifyAllObservers();
        System.out.println("ds");
    }

    /**
     * The initialize function that creates the card layout for the TempFreePianoUI
     */
    private void initialize() {
        mainFrame.setTitle("SmartPiano");
        mainFrame.setSize(1000, 800);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 45)));
        freePiano.setActionCommand(FREE_PIANO);
        freePiano.setAlignmentX(0.5f);
        freePiano.setBorder(new EmptyBorder(80,40,80,40));
        freePiano.setBackground(Color.getHSBColor(0,0,80.3f));

        playSong.setActionCommand(PLAY_A_SONG);
        playSong.setAlignmentX(0.5f);
        playSong.setBorder(new EmptyBorder(80,37,80,37));
        playSong.setBackground(Color.GRAY);

        musicPlayer.setActionCommand(MUSIC_PLAYER);
        musicPlayer.setAlignmentX(0.5f);
        musicPlayer.setBorder(new EmptyBorder(80,33,80,33));
        musicPlayer.setBackground(Color.GRAY);

        registerController(new PianoFrameManager(new PianoFrame()));

        buttonPanel.add(Box.createRigidArea(new Dimension(150, 17)));
        buttonPanel.add(freePiano);
        buttonPanel.add(Box.createRigidArea(new Dimension(150, 10)));
        buttonPanel.add(playSong);
        buttonPanel.add(Box.createRigidArea(new Dimension(80, 10)));
        buttonPanel.add(musicPlayer);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        buttonPanel.setBackground(Color.getHSBColor(0, 0, 0.2f));

        this.add(buttonPanel, BorderLayout.WEST);

        centralPanel.add(freePianoUI, FREE_PIANO_UI);
        centralPanel.add(pianoTilesUISelector, PIANO_TILES_UI_SELECTOR);
        centralPanel.add(spotiUI, SPOTI_UI);
        this.add(centralPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
        mainFrame.pack();
    }
    /*
    public static void refreshPianoTilesUI() {
        try {
            //         centralPanel.remove(pianoTilesUISelector);
            //   pianoTilesUISelector = new PianoTilesUISelector(mainFrame);
            //       centralPanel.add(pianoTilesUISelector, PIANO_TILES_UI_SELECTOR);
            refresh();
            pianoTilesUISelector.revalidate();
            pianoTilesUISelector.repaint();
            centralPanel.revalidate();
            centralPanel.repaint();
            centralPanel.updateUI();
        } catch (NullPointerException e) {

        }
    }*/

    /**
     * Method to add the action listeners to the buttons
     * @param listener The action listener
     */
    private void registerController(ActionListener listener) {
        freePiano.addActionListener(listener);
        playSong.addActionListener(listener);
        musicPlayer.addActionListener(listener);
    }

    public void attach(Observer observer){
        observers.add(observer);
        System.out.println("fd");
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}