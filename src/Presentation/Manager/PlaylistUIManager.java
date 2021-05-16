package Presentation.Manager;

        import Business.BusinessFacadeImp;
        import Presentation.Ui_Views.PlaylistUI;

        import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseListener;

public class PlaylistUIManager extends AbstractAction implements ActionListener, MouseListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Button":
                System.out.println("funciona hostia");
                break;
            default:
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.parseInt( e.getActionCommand() );
                String hola = (String) table.getModel().getValueAt(modelRow, 0);
                PlaylistUI.getPlaylist().getSongs().remove(Integer.parseInt(e.getActionCommand() ));
                new BusinessFacadeImp().deleteSongFromPlaylist(PlaylistUI.getPlaylist().getPlaylistName(),hola);
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                System.out.println("hola");
                //((DefaultTableModel)PlaylistUI.getTable().getModel()).removeRow(Integer.parseInt(e.getActionCommand()));
                }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pot ser que aixo funcioni");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

