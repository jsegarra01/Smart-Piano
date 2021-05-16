package Presentation.Manager;
/*
import Business.BusinessFacadeImp;
        import Business.Entities.MidiHelper;
        import Presentation.Ui_Views.PlaylistUI;

        import javax.sound.midi.MidiUnavailableException;
        import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseListener;
        import java.io.File;

        import static Presentation.DictionaryPiano.SONG_PLAYLIST;

public class PlaylistUIManager extends AbstractAction implements ActionListener, MouseListener {
    private MidiHelper midi;

    {
        try {
            midi = new MidiHelper();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case SONG_PLAYLIST:
                JButton button;
                Object obj = e.getSource();
                if (obj instanceof JButton) {
                    button = (JButton) obj;
                    PlaylistUI.deleteFromPanel(button.getName());
                    new BusinessFacadeImp().deleteSongFromPlaylist(PlaylistUI.getPlaylist().getPlaylistName(),button.getName());
                    PlaylistUI.setSongsPlaylists(PlaylistUI.getPlaylist());
                }
                break;
            /*default:
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.parseInt(e.getActionCommand());
                String hola = (String) table.getModel().getValueAt(modelRow, 0);
                PlaylistUI.getPlaylist().getSongs().remove(Integer.parseInt(e.getActionCommand() ));
                new BusinessFacadeImp().deleteSongFromPlaylist(PlaylistUI.getPlaylist().getPlaylistName(),hola);
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                System.out.println("hola");
                //((DefaultTableModel)PlaylistUI.getTable().getModel()).removeRow(Integer.parseInt(e.getActionCommand()));*/
        /*}
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel song;
        Object obj = e.getSource();
        if (obj instanceof JPanel) {
            song = (JPanel) obj;
            midi.playSong(new File(song.getName()));
        }
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
*/
