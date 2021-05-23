package Business.Entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Graph
 *
 * The "Graph" class will contain the different methods needed to set, get and manage a Graph
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class Graph extends JPanel {
    private int width = 400;
    private int height = 350;
    private int padding = 30;
    private int labelPadding = 25;
    private Color lineColor1 = Color.BLUE;
    private Color lineColor2 = Color.RED;
    private Color pointColor = Color.DARK_GRAY;
    private Color gridColor = Color.GRAY;
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f); // used to draw shape’s outline
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Float> yPoints;
    private String yAxis;
    private boolean graph1;

    /**
     * TODO
     * @param yPoints
     * @param yAxis
     * @param graph1
     */
    public Graph(List<Float> yPoints, String yAxis, boolean graph1) {
        this.yPoints = yPoints;
        this.yAxis = yAxis;
        this.graph1 = graph1;
    }

    /**
     * TODO
     * @param g
     */
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
        if(graph1){
            g2.setColor(lineColor1);
        } else{
            g2.setColor(lineColor2);
        }
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
        for (Point graphPoint : graphPoints) {
            int x = graphPoint.x - pointWidth / 2;
            int y = graphPoint.y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
        g.setColor(Color.black);

        //to write y and x axis
        g.drawString("Hours", width/2, height - 10);
        g2.rotate(-Math.toRadians(90), (float) width/2, (float) height/2);
        g.drawString(yAxis, height/2 - yAxis.length(), -10);
        g2.rotate(Math.toRadians(90), (float) width/2, (float) height/2);
    }

    /**
     * Gets the minimum Y
     * @return The minimum Y
     */
    private double getMinY() {
        float minY = Float.MAX_VALUE;
        for (float myY : yPoints) {
            minY = Math.min(minY, myY);
        }
        return minY;
    }

    /**
     * Gets the maximum Y
     * @return The maximum Y
     */
    private double getMaxY() {
        float maxY = Float.MIN_VALUE;
        for (float myY : yPoints) {
            maxY = Math.max(maxY, myY);
        }
        return maxY;
    }

    /**
     * Gets the width
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width
     * @param width The desired width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height
     * @return The height
     */
    public int getHeight() {
        return height;
    }
}
