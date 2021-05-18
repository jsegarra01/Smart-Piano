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

    private  SpotiFrameManager spotiFrame;
    private  Graph graph1;
    private Graph graph2;
/*
    private int width = 800;
    private int height = 400;

    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 10, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f); // used to draw shape’s outline
    private int pointWidth = 4;
    private int numberYDivisions = 10;*/
    private LinkedList<Float> numMin = new LinkedList<>();
    private LinkedList<Float> numSongs = new LinkedList<>();



    /**
     * Constructor for the StatisticsUI, you need to send the mainframe context and will create a card layout
     */
    public StatisticsUI(LinkedList<Float> numSongs, LinkedList<Float> numMin) {
        this.numSongs = numSongs;
        this.numMin = numMin;
        initialize();
    }


    /**
     * The initialize function that creates the card layout for the StatisticsUI
     */
    private void initialize() {
        setLayout(new BorderLayout());

        createAndShowGui();
        //setBackground(Color.red);
    }

    private void createAndShowGui() {
        graph1  = new Graph(numSongs);
        graph2 = new Graph(numMin);
        //graph.setPreferredSize(new Dimension(900, 800));
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(box);
        panel.setPreferredSize(new Dimension(800, 500));
        panel.setMaximumSize(panel.getPreferredSize());
        //panel.setLayout(new BorderLayout());
        //panel.setLayout(new GridLayout());
        panel.setBackground(Color.BLACK);
        panel.add(graph1);
        panel.add(graph2);
        add(panel, BorderLayout.CENTER);
    }



/*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (yPoints.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxY() - getMinY());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < yPoints.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxY() - yPoints.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (yPoints.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinY() + (getMaxY() - getMinY()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < yPoints.size(); i++) {
            if (yPoints.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (yPoints.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((yPoints.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    private double getMinY() {
        Integer minY = Integer.MAX_VALUE;
        for (Integer myY : yPoints) {
            minY = Math.min(minY, myY);
        }
        return minY;
    }

    private double getMaxY() {
        Integer maxY = Integer.MIN_VALUE;
        for (Integer myY : yPoints) {
            maxY = Math.max(maxY, myY);
        }
        return maxY;
    }*/

}