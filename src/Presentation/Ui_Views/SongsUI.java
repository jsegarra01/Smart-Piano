package Presentation.Ui_Views;

import Business.Entities.ButtonColumn;
import Business.Entities.Song;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class SongsUI extends JPanel {
    private static JTable table;
    private final static String[] columnNames = {"Name Song", "Author's name", "Duration","Recording Date", ""};
    private static JPanel panel = new JPanel();
    public SongsUI(){
        initialize();
        panel.setPreferredSize(new Dimension(860, 550));
        add(panel);
    }

    private void initialize() {
        setBackground(Color.black);

    }
    public static void initTable(ArrayList<Song> songs, String action){
        panel.removeAll();
        Object[][] data = new Object[songs.size()][5];
        for(int i = 0; i< songs.size();i++){
            data[i][0] = songs.get(i).getSongName();
            data[i][1] = songs.get(i).getAuthorName();
            data[i][2] = songs.get(i).getDuration();
            data[i][3] = songs.get(i).getRecordingDate();
            data[i][4]  = action;
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        table = new JTable(model);
        table.setPreferredSize(new Dimension(840, 530));
        table.setBackground(Color.darkGray);
        table.setGridColor(Color.lightGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.setFont( new Font(table.getFont().getName(),Font.PLAIN, (int) (table.getFont().getSize()*1.5)));
        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(Color.black);
        sp.setPreferredSize(new Dimension(860, 550));

        registerController(new SpotiFrameManager());
        panel.add(sp);
        panel.revalidate();
        panel.repaint();
    }

    private static void registerController(Action listener) {
          new ButtonColumn(table, listener, 4);
    }
}
