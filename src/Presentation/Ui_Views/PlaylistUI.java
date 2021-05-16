package Presentation.Ui_Views;

//import data from the different libraries
import Business.Entities.ButtonColumn;
import Business.Entities.Playlist;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PlaylistUIManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static Presentation.DictionaryPiano.PLAYLIST_NAME;
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
    private static JLabel playlistName = new JLabel(PLAYLIST_NAME);
    private JLabel logInText = new JLabel(LOG_IN_TEXT);
    private static JTable table;//  = new JTable(new TablePlaylists());
    private Object[][] rows;
    private static JPanel panel = new JPanel(new BorderLayout());
    private static JTextField usernameTextField = new JTextField();
    private static JPasswordField password = new JPasswordField();
    private JButton back = new JButton(BACK_BUTTON);
    private JButton done = new JButton(DONE_BUTTON);
    private MainFrame mainFrame;
    private static Playlist playlist;


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
        this.add(configurePanel());
        this.setBackground(Color.black);
    }
    private JPanel configurePanel() {
        //JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        //panel.setBackground(Color.getHSBColor(0,0,0.2f));
        panel.setOpaque(true);
        panel.repaint();

        //All information will go inside here
        panel.add(Box.createRigidArea(new Dimension(10, 240)), BorderLayout.CENTER);
        panel.add(initGeneral());
        //setTable();
        //panel.add(setTable(), BorderLayout.SOUTH);
        //registerController(new PlaylistManager());


        return panel;
    }
    private static void registerController(PlaylistUIManager listener) {
        new ButtonColumn(table, listener, 2);

    }
    private JPanel initGeneral(){
        JPanel layout = new JPanel(new BorderLayout());
        playlistName.setOpaque(false);
        playlistName.repaint();
        playlistName.setForeground(Color.WHITE);
        playlistName.setFont(playlistName.getFont().deriveFont(playlistName.getFont().getSize() * 3.0F));
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
        PlaylistUI.playlist = playlist;
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
        registerController(new PlaylistUIManager());
    }

    public static Playlist getPlaylist() {
        return playlist;
    }
}