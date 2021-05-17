/*
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    }

}*/