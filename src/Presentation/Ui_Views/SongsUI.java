package Presentation.Ui_Views;

import Business.ButtonColumn;
import Business.Entities.Song;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * SongsUI
 *
 * The "SongsUI" class will generates the tables with the different songs
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 23 May 2021
 *
 */
public class SongsUI extends JPanel {
    private static JTable table;
    private static final JPanel panel = new JPanel();
    private static SpotiFrameManager myManager;


    /**
     * Constructor for the SongsUI
     */
    public SongsUI(SpotiFrameManager spotiFrameManager) {
        myManager = spotiFrameManager;
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
        String[] columnNames = new String[0];

        panel.removeAll();
        Object[][] data = new Object[songs.size()][5];
        for(int i = 0; i< songs.size();i++){
            data[i][0] = songs.get(i).getSongName();
            data[i][1] = songs.get(i).getAuthorName();
            data[i][2] = ((((int)(songs.get(i).getDuration()/60))) + ":" + (Math.round(((songs.get(i).getDuration())) -(int)(songs.get(i).getDuration()/60))));
            data[i][3] = songs.get(i).getRecordingDate();
            if(!action.equals("topFive")){
                data[i][4] = action;
                columnNames= new String[]{"Name Song", "Author's name", "Duration", "Recording Date", ""};
            }else {
                data[i][4]  = songs.get(i).getTimesPlayed();
                columnNames= new String[]{"Name Song", "Author's name", "Duration", "Recording Date", "Times Played"};
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            /**
             * Makes only 1 cell editable, column = 4, as it needs to edit it as it is the button.
             * The other cells are not editable, because we do not want them to be edited
             * @param row Defines the rows in the table
             * @param column Defines the columns in the table
             * @return Boolean that returns a true if it is editable, false if not
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
        table.setRowHeight(25);
        table.setFont( new Font(table.getFont().getName(),Font.PLAIN, (int) (table.getFont().getSize()*1.25)));

        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(Color.black);
        sp.setPreferredSize(new Dimension(860, 540));
        sp.setWheelScrollingEnabled(true);
        registerController(myManager);
        if(!action.equals("topFive")){
            registerController(myManager);
        }
        panel.add(sp);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Method that calls the class button column, which determines which is the button in the column
     * @param listener Defines the listener that the button will activate
     */
    private static void registerController(Action listener) {
          new ButtonColumn(table, listener, 4);
    }
}
