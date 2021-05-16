package Presentation.Ui_Views;

//import data from the different libraries
import Business.Entities.ButtonColumn;
import Business.Entities.Playlist;
import Presentation.Manager.MainFrame;
import Presentation.Manager.PlaylistUIManager;

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
    //private static JLabel playlistName = new JLabel(PLAYLIST_NAME);
    private JLabel logInText = new JLabel(LOG_IN_TEXT);
    private static JTable table;//  = new JTable(new TablePlaylists());
    private Object[][] rows;
    private static JPanel panel = new JPanel(new BorderLayout());
    private static JTextField usernameTextField = new JTextField();
    private static JPasswordField password = new JPasswordField();
    private JButton back = new JButton(BACK_BUTTON);
    private JButton done = new JButton(DONE_BUTTON);
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
        configurePanel();
        this.add(panel);
        this.setBackground(Color.black);
       // setLayout(new BorderLayout());
    }
    private void configurePanel() {
        //JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        //panel.setBackground(Color.getHSBColor(0,0,0.2f));
        panel.setOpaque(true);
        panel.repaint();

        //All information will go inside here
        //panel.add(Box.createRigidArea(new Dimension(10, 50)));
        //panel.add(initGeneral(), BorderLayout.NORTH);

        //JPanel songs = songs();
        //panel.add(songs, BorderLayout.CENTER);
        /*JScrollPane areaScrollPane = new JScrollPane(songs);
        areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(600, 400));
        panel.add(areaScrollPane, BorderLayout.CENTER);
        *///setTable();
        //panel.add(setTable(), BorderLayout.SOUTH);
        //registerController(new PlaylistManager());

    }
    private static void registerController(PlaylistUIManager listener) {
        new ButtonColumn(table, listener, 2);

    }
    private static JPanel initGeneral(String namePlaylist){
        JPanel layout = new JPanel(new BorderLayout());
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
        JPanel panelSongs = new JPanel();
        panelSongs.add(Box.createRigidArea(new Dimension(50,10)));
        BoxLayout boxLayout = new BoxLayout(panelSongs, BoxLayout.Y_AXIS);
        panelSongs.setLayout(boxLayout);
        //panelSongs.setPreferredSize(new Dimension(200, 200));
        //panelSongs.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        //panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));

        // Define new buttons
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("HOLA"));
        jPanel.add(new JButton("Button 1"));

        JPanel jPanel2 = new JPanel();
        jPanel2.add(new JLabel("HOLA2"));
        jPanel2.add(new JButton("Button 2"));

        JPanel jPanel3 = new JPanel();
        jPanel3.add(new JLabel("HOLA"));
        jPanel3.add(new JButton("Button 3"));

        JPanel jPanel4 = new JPanel();
        jPanel4.add(new JLabel("HOLA"));
        jPanel4.add(new JButton("Button 1"));

        JPanel jPanel5 = new JPanel();
        jPanel5.add(new JLabel("HOLA2"));
        jPanel5.add(new JButton("Button 2"));

        JPanel jPanel6 = new JPanel();

        jPanel6.add(new JLabel("HOLA"));
        jPanel6.add(new JButton("Button 3"));
        JPanel panel1;
        for(int i = 0; i<15; i++){
            panel1 = new JPanel();
            panel1.add(new JLabel("HOLA " + i));
            panel1.add(new JButton("Button " + i));
            panelSongs.add(panel1);
        }

        /*JButton jb1 = new JButton("Button 1");
        JButton jb2 = new JButton("Button 2");
        JButton jb3 = new JButton("Button 3");*/
        panelSongs.add(jPanel);
        panelSongs.add(jPanel2);
        panelSongs.add(jPanel3);
        panelSongs.add(jPanel4);
        panelSongs.add(jPanel5);
        panelSongs.add(jPanel6);
        JScrollPane sp = new JScrollPane(panelSongs);
        sp.setVisible(true);
        //panel.add(sp, BorderLayout.SOUTH);
        // Add buttons to the frame (and spaces between buttons)
        //panel.add(jb1);
        //panel.add(Box.createRigidArea(new Dimension(0, 60)));
        //panel.add(jb2);
        //panel.add(Box.createRigidArea(new Dimension(0, 60)));
        //panel.add(jb3);

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
        panel.setLayout(new BorderLayout());
        panel.add(Box.createRigidArea(new Dimension(10, 50)));
        panel.add(initGeneral(playlist.getPlaylistName()), BorderLayout.NORTH);
        JPanel panelSongs = new JPanel();
        panelSongs.add(Box.createRigidArea(new Dimension(50,10)));
        //panelSongs.setPreferredSize(new Dimension(5,400));

        BoxLayout boxLayout = new BoxLayout(panelSongs, BoxLayout.Y_AXIS);
        panelSongs.setLayout(boxLayout);
        JPanel panel1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for(int i = 0; i<playlist.getSongs().size(); i++){
            panel1 = new JPanel();
            panel1.setBorder(new LineBorder(Color.white));
            panel1.add(new JLabel(playlist.getSongs().get(i).getSongName()));
            panel1.add(Box.createHorizontalStrut(60));
            panel1.add(new JLabel(playlist.getSongs().get(i).getAuthorName()));
            panel1.add(Box.createHorizontalStrut(40));
            panel1.add(new JLabel(String.valueOf(playlist.getSongs().get(i).getDuration())));
            panel1.add(new JLabel(sdf.format(playlist.getSongs().get(i).getRecordingDate())));
            JButton button = new JButton(playlist.getSongs().get(i).getSongName());
            button.setName(playlist.getSongs().get(i).getSongName());
            button.setActionCommand(SONG_PLAYLIST);
            button.setText("Delete");
            button.addActionListener(new PlaylistUIManager());
            panel1.add(button);
            panelSongs.add(panel1);
        }

        JScrollPane areaScrollPane = new JScrollPane(panelSongs);
        areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(600, 400));
        panelSongs.repaint();
        areaScrollPane.repaint();

        panel.add(areaScrollPane, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();


        //return panelSongs;
    }

    public static void deleteFromPanel(String nameSong){
        /*boolean found = false;
        int i = 0;
        while(i<panel.getComponentCount() && !found){
            if(panel.getComponent(i).getName()!=null){
                if(panel.getComponent(i).getName().equals(nameSong)){
                    found = true;
                }else{
                    i++;
                }
            }else{
                i++;
            }
        }
        if(found){
            panel.remove(i);
        }
        panel.revalidate();
        panel.repaint();*/
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
}