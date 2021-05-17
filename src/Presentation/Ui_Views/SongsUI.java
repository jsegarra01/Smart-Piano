package Presentation.Ui_Views;

import Business.Entities.ButtonColumn;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SongsUI extends JPanel {
    private static JTable table;

    public SongsUI(){
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.green);
        add(initTable());

    }
    private JScrollPane initTable(){
        String[] columnNames = {"Name Song", "Author's name", "HOLA","HOLA2", ""};
        Object[][] data =
                {
                        {"Homer", "Simpson","1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" ,"delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" ,"delete Bart"},
                        {"Lisa",  "Simpson","1" ,"2" ,  "delete Lisa"},
                        {"Homer", "Simpson","1" ,"2" ,  "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson","1" ,"2" ,  "delete Bart"},
                        {"Lisa",  "Simpson","1" ,"2" ,  "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                        {"Homer", "Simpson", "1" ,"2" , "delete Homer"},
                        {"Madge", "Simpson", "1" ,"2" , "delete Madge"},
                        {"Bart",  "Simpson", "1" ,"2" , "delete Bart"},
                        {"Lisa",  "Simpson", "1" ,"2" , "delete Lisa"},
                };
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        table = new JTable(model);
        table.setBackground(Color.darkGray);
        table.setGridColor(Color.lightGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.setFont( new Font(table.getFont().getName(),Font.PLAIN, (int) (table.getFont().getSize()*1.5)));

        registerController(new SpotiFrameManager());
        return new JScrollPane(table);
    }

    private static void registerController(SpotiFrameManager listener) {
          new ButtonColumn(table, listener, 4);

    }
}
