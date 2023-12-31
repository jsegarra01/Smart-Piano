package Presentation.Ui_Views;

//import data from the different libraries

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

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
    private LinkedList<Float> numMin = new LinkedList<>();
    private LinkedList<Float> numSongs = new LinkedList<>();

    private final JPanel panel = new JPanel(new BorderLayout());

    /**
     * Constructor for the StatisticsUI, you need to send the mainframe context and will create a card layout
     *
     */
    public StatisticsUI() {
        this.add(panel);
    }


    /**
     * The initialize function that creates the card layout for the StatisticsUI
     */
    public void initialize() {
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

    /**
     * Creates the graphs with the statistics recolected and displays them
     */
    public void createAndShowGui() {
        Graph graph1 = new Graph(numSongs, "Number of times played", true);
        JLabel title1 = new JLabel("# Songs");
        title1.setHorizontalAlignment(JLabel.CENTER);
        graph1.add(title1);
        Graph graph2 = new Graph(numMin, "Number of Minutes", false);
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

    /**
     * Sets the number of minutes played by hour
     * @param min List of floats where each number in the list is the amount of minutes played in that hour
     */
    public void setNumMin(LinkedList<Float> min){
        numMin = min;
    }

    /**
     * Sets the number of songs played by hour
     * @param songs List of floats where each number in the list is the amount of songs played in that hour
     */
    public void setNumSongs(LinkedList<Float> songs){
        numSongs = songs;
    }

    /**
     * Initializates the graphs depending on the number of minutes and songs played per hour
     * @param numMin Minutes played per hour
     * @param numSongs Songs played per hour
     */
    public void letsInitializeGraphs(LinkedList<Float> numMin, LinkedList<Float> numSongs){
        setNumMin(numMin);
        setNumSongs(numSongs);
        initialize();
    }
}