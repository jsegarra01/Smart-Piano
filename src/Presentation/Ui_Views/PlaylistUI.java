package Presentation.Ui_Views;

//import data from the different libraries
import Business.Entities.ButtonColumn;
import Business.Entities.Playlist;
import Presentation.Manager.MainFrame;
//import Presentation.Manager.PlaylistUIManager;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;

import static Presentation.DictionaryPiano.PLAYLIST_NAME;
import static Presentation.DictionaryPiano.SONG_PLAYLIST;
import static Presentation.Dictionary_login.*;

/**
 * PlaylistUI
 *
 * The "PlaylistUI" class will contain the different methods to create the view class card layout "PlaylistUI" and login interface
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class PlaylistUI extends JPanel {
    private static JTable table;//  = new JTable(new TablePlaylists());
    private Object[][] rows;
    private static JPanel panel = new JPanel(new BorderLayout());
    private MainFrame mainFrame;
    private static Playlist playlistP;


    public static JTable getTable() {
        return table;
    }

    /**
     * Constructor for the PlaylistUI, you need to send the mainframe context and will create a card layout
     *
     * @param mainFrame context necessary to create the card layout
     */
    public PlaylistUI(final MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the PlaylistUI
     */
    private void initialize() {
        this.add(panel);
        this.setBackground(Color.black);
    }
    //private static void registerController(PlaylistUIManager listener) {
      //  new ButtonColumn(table, listener, 2);

    //}
    private static JPanel initGeneral(String namePlaylist){
        JPanel layout = new JPanel(new BorderLayout());
        layout.setBackground(Color.black);
        JLabel playlistName = new JLabel(PLAYLIST_NAME);
        playlistName.setOpaque(false);
        playlistName.repaint();
        playlistName.setForeground(Color.WHITE);
        playlistName.setFont(playlistName.getFont().deriveFont(playlistName.getFont().getSize() * 3.0F));
        playlistName.setText(namePlaylist);
        layout.add(playlistName, BorderLayout.LINE_START);


        return layout;
    }

    public JScrollPane setTable(){
        String[] columnNames = {"Name Song", "Author's name", ""};
        Object[][] data =
                {
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
         table = new JTable(model);
         table.removeEditor();
         table.enableInputMethods(false);

        //ButtonColumn buttonColumn = new ButtonColumn(table, playlistManager, 2);
        //buttonColumn.setMnemonic(KeyEvent.VK_D);
        //TableCellRenderer tableRenderer;
        //table = new JTable(new JTableButtonModel());
        //((JButton)(table.getValueAt(0,1))).addActionListener(new PlaylistManager());
        //tableRenderer = table.getDefaultRenderer(JButton.class);
        //table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        JScrollPane sp = new JScrollPane(table);
        //sp.setBackground(Color.LIGHT_GRAY);
        //table.setFillsViewportHeight(true);
/*
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Button1").setCellRenderer(buttonRenderer);
        table.getColumn("Button2").setCellRenderer(buttonRenderer);*/
        return sp;
    }

    public static void setInfo(Playlist playlist){
        /*if(table !=null){
            for(int i = 0; i<table.getRowCount();i++){
                ((DefaultTableModel)table.getModel()).removeRow(i);
            }
        }*/

       /* PlaylistUI.playlist = playlist;
        playlistName.setText(PlaylistUI.playlist.getPlaylistName());
        Object[][] data = new Object[PlaylistUI.playlist.getSongs().size()][3];
        String[] columnNames = {"Name Song", "Author's name", ""};
        for(int i = 0; i< PlaylistUI.playlist.getSongs().size();i++){
            data[i][0] = PlaylistUI.playlist.getSongs().get(i).getSongName();
            data[i][1] = PlaylistUI.playlist.getSongs().get(i).getAuthorName();
            data[i][2]  = "Delete";
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        table = new JTable(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.lightGray);
        table.setForeground(Color.RED);
        table.setRowHeight(30);
        //table.setFont( new Font(table.getFont().getName(),Font.PLAIN, (int) (table.getFont().getSize()*1.5)));
        JScrollPane sp = new JScrollPane(table);
        //sp.setBackground(Color.lightGray);
        panel.add(sp, BorderLayout.SOUTH);
        registerController(new PlaylistUIManager());*/
    }

    public static Playlist getPlaylist() {
        return playlistP;
    }

    public static void setSongsPlaylists(Playlist playlist){
        playlistP = playlist;
        panel.removeAll();
        panel.repaint();
        //panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);
        panel.add(Box.createRigidArea(new Dimension(10, 50)));
        panel.add(initGeneral(playlist.getPlaylistName()), BorderLayout.NORTH);
        JPanel panelSongs = new JPanel();
        panelSongs.setBackground(Color.black);
        panelSongs.add(Box.createRigidArea(new Dimension(50,10)));
        //panelSongs.setPreferredSize(new Dimension(5,400));

        BoxLayout boxLayout = new BoxLayout(panelSongs, BoxLayout.Y_AXIS);
        panelSongs.setLayout(boxLayout);
        JPanel panel1;
        JLabel label;
        for(int i = 0; i<playlist.getSongs().size(); i++){
            panel1 = setPlaylist(playlist, i);
            panelSongs.add(panel1);
        }

        JScrollPane areaScrollPane = new JScrollPane(panelSongs);
        areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(800, 520));
        panelSongs.repaint();
        areaScrollPane.repaint();

        panel.add(areaScrollPane, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();

    }

    public static void deleteFromPanel(String nameSong){

        boolean found = false;
        int i = 0;
        while(i<playlistP.getSongs().size() && !found){
            if(playlistP.getSongs().get(i).getSongName()!=null){
                if(playlistP.getSongs().get(i).getSongName().equals(nameSong)){
                    found = true;
                }else{
                    i++;
                }
            }else{
                i++;
            }
        }
        if(found){
            playlistP.getSongs().remove(i);
        }

    }
    private static JPanel setPlaylist(Playlist playlist, int i){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        JPanel panel1 = new JPanel();
        JLabel label;

        panel1.setBorder(new LineBorder(Color.gray));
        panel1.setPreferredSize(new Dimension(800,50));
        panel1.setMaximumSize(panel1.getPreferredSize());
        panel1.setBackground(Color.black);

        label = new JLabel(playlist.getSongs().get(i).getSongName());
        label.setForeground(Color.WHITE);
        panel1.add(label);
        panel1.add(Box.createHorizontalStrut(60));

        label = new JLabel(playlist.getSongs().get(i).getAuthorName());
        label.setForeground(Color.WHITE);
        panel1.add(label);
        panel1.add(Box.createHorizontalStrut(40));

        label = new JLabel(String.valueOf(playlist.getSongs().get(i).getDuration()));
        label.setForeground(Color.WHITE);
        panel1.add(label);
        panel1.add(Box.createHorizontalStrut(40));

        label = new JLabel(sdf.format(playlist.getSongs().get(i).getRecordingDate()));
        label.setForeground(Color.WHITE);
        panel1.add(label);

        JButton button = new JButton(playlist.getSongs().get(i).getSongName());
        button.setName(playlist.getSongs().get(i).getSongName());
        button.setActionCommand(SONG_PLAYLIST);
        button.setText("Delete");
        button.addActionListener(new SpotiFrameManager());
        panel1.add(Box.createHorizontalStrut(40));
        panel1.add(button);
        panel1.addMouseListener(new SpotiFrameManager());
        panel1.setName(playlist.getSongs().get(i).getSongFile());
        return panel1;
    }
}