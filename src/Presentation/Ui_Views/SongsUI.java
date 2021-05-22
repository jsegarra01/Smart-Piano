package Presentation.Ui_Views;

import Business.Entities.ButtonColumn;
import Business.Entities.Song;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * SongsUI
 *
 * The "SongsUI" class will generates the tables with the different songs
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class SongsUI extends JPanel {
    private static JTable table;
    private static String[] columnNames; /*{"Name Song", "Author's name", "Duration","Recording Date", ""};*/
    private static JPanel panel = new JPanel();

    private static DecimalFormat df = new DecimalFormat("###.##");

    /**
     * Constructor for the SongsUI
     */
    public SongsUI(){
        setBackground(Color.black);
        panel.setPreferredSize(new Dimension(860, 550));
        add(panel);
    }

    /**
     * Creates a table of songs depending on the action
     * @param songs List of songs to be added to the table
     * @param action Whether if we want the table for the top 5 songs or the table with all the songs available for the user
     */
    public static void initTable(ArrayList<Song> songs, String action){
        panel.removeAll();
        Object[][] data = new Object[songs.size()][5];
        for(int i = 0; i< songs.size();i++){
            data[i][0] = songs.get(i).getSongName();
            data[i][1] = songs.get(i).getAuthorName();
            data[i][2] = df.format(songs.get(i).getDuration());
            data[i][3] = songs.get(i).getRecordingDate();
            if(!action.equals("topFive")){
                data[i][4] = action;
                columnNames= new String[]{"Name Song", "Author's name", "Duration", "Recording Date", ""};
                //data[i][4]  = songs.get(i).getTimesPlayed();
            }else {
                data[i][4]  = songs.get(i).getTimesPlayed();
                columnNames= new String[]{"Name Song", "Author's name", "Duration", "Recording Date", "Times Played"};
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            /**
             * TODO: ALEX WUTUFUK IS THIS
             * @param row
             * @param column
             * @return
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        table = new JTable(model);
        table.setBackground(Color.darkGray);
        table.setGridColor(Color.lightGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.setFont( new Font(table.getFont().getName(),Font.PLAIN, (int) (table.getFont().getSize()*1.5)));

        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(Color.black);
        sp.setPreferredSize(new Dimension(860, 540));
        sp.setWheelScrollingEnabled(true);
       /* sp.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
            }
        });*/

        registerController(new SpotiFrameManager());
        if(!action.equals("topFive")){
            registerController(new SpotiFrameManager());
        }
        panel.add(sp);
        panel.revalidate();
        panel.repaint();
    }

    /**
     *
     * @param listener
     */
    private static void registerController(Action listener) {
          new ButtonColumn(table, listener, 4);
    }
}
