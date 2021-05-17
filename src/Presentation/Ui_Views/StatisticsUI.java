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
    private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
    private JLabel logInText = new JLabel(LOG_IN_TEXT);

    private static JTextField usernameTextField = new JTextField();
    private static JPasswordField password = new JPasswordField();
    private JButton back = new JButton(BACK_BUTTON);
    private JButton done = new JButton(DONE_BUTTON);
    private MainFrame mainFrame;

    private  Graph graph1;
    private Graph graph2;

    private LinkedList<Float> numMin = new LinkedList<>();
    private LinkedList<Float> numSongs = new LinkedList<>();



    /**
     * Constructor for the StatisticsUI, you need to send the mainframe context and will create a card layout
     *
     * @param mainFrame context necessary to create the card layout
     */
    public StatisticsUI(final MainFrame mainFrame, LinkedList<Float> numSongs, LinkedList<Float> numMin) {
        super();
        this.mainFrame = mainFrame;
        this.numSongs = numSongs;
        this.numMin = numMin;
        initialize();
    }


    /**
     * The initialize function that creates the card layout for the StatisticsUI
     */
    private void initialize() {
        setLayout(new BorderLayout());
        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(Color.black);
        JLabel title = new JLabel("HOLA");
        title.setBackground(Color.black);
        //title.setOpaque(false);
        //title.repaint();
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 3.0F));
        title.setText(" Statistics");

        layout.add(title);
        add(layout, BorderLayout.NORTH);
        createAndShowGui();
        //setBackground(Color.red);
    }

    private void createAndShowGui() {
        graph1  = new Graph(numSongs, "Number of times played", true);
        JLabel title1 = new JLabel("# Songs");
        title1.setHorizontalAlignment(JLabel.CENTER);
        graph1.add(title1);
        graph2 = new Graph(numMin, "Number of Minutes", false);
        JLabel title2 = new JLabel("# Minutes");
        title2.setHorizontalAlignment(JLabel.CENTER);
        graph2.add(title2);
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(box);
        panel.setPreferredSize(new Dimension(800, 500));
        panel.setMaximumSize(panel.getPreferredSize());
        //panel.setLayout(new BorderLayout());
        //panel.setLayout(new GridLayout());
        panel.setBackground(Color.BLACK);

        panel.add(Box.createHorizontalStrut(15));
        panel.add(graph1);

        panel.add(Box.createHorizontalStrut(35));
        panel.add(graph2);
        add(panel, BorderLayout.CENTER);
    }


    /*
    static class Probatinas extends JPanel {

        public Probatinas(List<Double> scores) {

            setLayout(new BorderLayout());

            JLabel title = new JLabel("Variation of Distance with time");
            title.setFont(new Font("Arial", Font.BOLD, 25));
            title.setHorizontalAlignment(JLabel.CENTER);

            JPanel graphPanel = new GraphPanel(scores);

            VerticalPanel vertPanel = new VerticalPanel();

            HorizontalPanel horiPanel = new HorizontalPanel();

            add(title, BorderLayout.NORTH);
            add(horiPanel, BorderLayout.SOUTH);
            add(vertPanel, BorderLayout.WEST);
            add(graphPanel, BorderLayout.CENTER);

        }

        class VerticalPanel extends JPanel {

            public VerticalPanel() {
                setPreferredSize(new Dimension(25, 0));
            }

            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D gg = (Graphics2D) g;
                gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font font = new Font("Arial", Font.PLAIN, 15);

                String string = "Time (s)";

                FontMetrics metrics = g.getFontMetrics(font);
                int width = metrics.stringWidth(string);
                int height = metrics.getHeight();

                gg.setFont(font);

                drawRotate(gg, getWidth(), (getHeight() + width) / 2, 270, string);
            }

            public void drawRotate(Graphics2D gg, double x, double y, int angle, String text) {
                gg.translate((float) x, (float) y);
                gg.rotate(Math.toRadians(angle));
                gg.drawString(text, 0, 0);
                gg.rotate(-Math.toRadians(angle));
                gg.translate(-(float) x, -(float) y);
            }

        }

        class HorizontalPanel extends JPanel {

            public HorizontalPanel() {
                setPreferredSize(new Dimension(0, 25));
            }

            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D gg = (Graphics2D) g;
                gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font font = new Font("Arial", Font.PLAIN, 15);

                String string = "Distance (m)";

                FontMetrics metrics = g.getFontMetrics(font);
                int width = metrics.stringWidth(string);
                int height = metrics.getHeight();

                gg.setFont(font);

                gg.drawString(string, (getWidth() - width) / 2, 11);
            }

        }*/

}