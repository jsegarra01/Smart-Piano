package Presentation.Ui_Views;

//import data from the different libraries
import Business.Entities.Graph;
import Presentation.Manager.MainFrame;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static Presentation.Dictionary_login.*;

/**
 * StatisticsUI
 *
 * The "StatisticsUI" class will contain the different methods to create the view class card layout "StatisticsUI" and login interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class StatisticsUI extends JPanel {

    private static Graph graph1;
    private static Graph graph2;

    private static LinkedList<Float> numMin = new LinkedList<>();
    private static LinkedList<Float> numSongs = new LinkedList<>();

    private static JPanel panel = new JPanel(new BorderLayout());
    private static JPanel panelGraphs = new JPanel(new BorderLayout());

    /**
     * Constructor for the StatisticsUI, you need to send the mainframe context and will create a card layout
     *
     */
    public StatisticsUI(/*, LinkedList<Float> numSongs, LinkedList<Float> numMin*/) {
        super();
        //this.mainFrame = mainFrame;
        this.add(panel);
        //his.add(panelGraphs);
        //this.numSongs = numSongs;
        //this.numMin = numMin;

        //initialize();
    }


    /**
     * The initialize function that creates the card layout for the StatisticsUI
     */
    private static void initialize() {
        panel.removeAll();
        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(Color.black);
        JLabel title = new JLabel("myLABEL");
        title.setBackground(Color.black);
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 3.0F));
        title.setText(" Statistics");

        layout.add(title);
        panel.add(layout, BorderLayout.NORTH);
        createAndShowGui();
    }

    private static void createAndShowGui() {
        graph1  = new Graph(numSongs, "Number of times played", true);
        JLabel title1 = new JLabel("# Songs");
        title1.setHorizontalAlignment(JLabel.CENTER);
        graph1.add(title1);
        graph2 = new Graph(numMin, "Number of Minutes", false);
        JLabel title2 = new JLabel("# Minutes");
        title2.setHorizontalAlignment(JLabel.CENTER);
        graph2.add(title2);
        JPanel panel2 = new JPanel();
        BoxLayout box = new BoxLayout(panel2, BoxLayout.X_AXIS);
        panel2.setLayout(box);
        panel2.setPreferredSize(new Dimension(840, 540));
        panel2.setMaximumSize(panel2.getPreferredSize());

        panel2.setBackground(Color.BLACK);
        panel2.add(Box.createHorizontalStrut(30));
        panel2.add(graph1);

        panel2.add(Box.createHorizontalStrut(35));
        panel2.add(graph2);
        panel.add(panel2, BorderLayout.CENTER);

        JPanel jpanel = new JPanel();
        jpanel.add(Box.createRigidArea(new Dimension(20,100)));
        jpanel.setBackground(Color.black);
        panel.add(jpanel, BorderLayout.EAST);

        panel.revalidate();
        panel.repaint();
    }

    public static void setNumMin(LinkedList<Float> min){
        numMin=min;
    }

    public static void setNumSongs(LinkedList<Float> songs){
        numSongs=songs;
    }

    public static void letsInitializeGraphs(LinkedList<Float> numMin, LinkedList<Float> numSongs){
        setNumMin(numMin);
        setNumSongs(numSongs);
        initialize();
    }
}