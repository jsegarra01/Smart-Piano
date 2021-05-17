package Presentation.Manager;

//Imports needed from the dictionary, events and mainframe
import Business.Entities.*;
import Business.BusinessFacadeImp;
import Presentation.Ui_Views.SpotiUI;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.File;

import static Presentation.DictionaryPiano.*;
import static Presentation.Ui_Views.SpotiUI.*;
import static Presentation.Ui_Views.Tile.resizeIcon;


/**
 * FreePianoUIManager
 *
 * The "FreePianoUIManager" class will contain the different methods that are needed to control the view class "FreePianoUI"
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class SpotiFrameManager implements ActionListener {

    private String URLRoute = "https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano";
    private String path = "Files";
    private boolean play=false;
    private ImageIcon playIcon = new ImageIcon("Files/drawable/playbuttonWhite.png");
    private ImageIcon pauseIcon = new ImageIcon("Files/drawable/pauseWhite.png");
    private float minPlayed;
    private long startMin=0;
    private long lastMin=0;
    private Stadistics stadistics;


    //private Timer min  = new Timer(10, this);
    private Date date = new Date();

    private MidiHelper finalMidiHelper;

    //HashMap<Integer, List<Stadistics>> hashMap;

    {
        try {
            finalMidiHelper = new MidiHelper();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    webHandler myWebHandlingTool = new webHandler(path, URLRoute, "result%s.txt", "?startat=%s&");

    /**
     * Parametrized constructor
     */
    public SpotiFrameManager() {

    }

    /**
     * Method that will be called every time a button is pressed, overriden from the interface to provide an implementation.
     * @param e ActionEvent from the UI
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // We distinguish between our buttons.
        CardLayout cc = (CardLayout) (spotiPanel.getLayout());

        //min.setActionCommand(RECORDING_TIMER);

        switch (e.getActionCommand()) {
            case CREATE_STADISTICS:
                addStadistics(getNumSongs(), getMinPlayed());
                cc.show(spotiPanel, STATISTICS_UI);
                break;
            case SHOW_TOP_SONGS:
                cc.show(spotiPanel, TOPSONGS_UI);
                break;
            case CREATE_PLAYLIST:
                cc.show(spotiPanel, PLAYLIST_UI);
                break;
            case SEARCH_SONG:
                this.myWebHandlingTool.doStuff(SpotiUI.getInputedSongName(), "by");
                cc.show(spotiPanel, PLAYLIST_UI);
                break;
            case PLAY_BUTTON:
                if(!play){
                    playButton.setIcon(pauseIcon);
                    playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                            (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
                    startMin = System.currentTimeMillis();
                    finalMidiHelper.playSong(new File(new BusinessFacadeImp().getPlaylistManager().getPlaylists().get(0).getSongs().get(0).getSongFile()));
                    play = true;
                }
                else{
                    playButton.setIcon(playIcon);
                    playButton.setIcon(resizeIcon((ImageIcon) playButton.getIcon(), (int) Math.round(playButton.getIcon().getIconWidth()*0.09),
                            (int) Math.round(playButton.getIcon().getIconHeight()*0.09)));
                    lastMin = System.currentTimeMillis();
                    // String lastSong =
                    minPlayed = (float)(lastMin - startMin)/60000;
                    Stadistics stats = new Stadistics(date.getHours(), (float)1, minPlayed);
                    new BusinessFacadeImp().getSongManager().addingStadistics(stats);
                    play = false;
                    finalMidiHelper.stopSong();
                }
                break;
            case PLAYLIST_INFO:
                JButton button;
                Object obj = e.getSource();
                if (obj instanceof JButton) {
                    button = (JButton) obj;
                }
                break;
        }
    }
    public static void addPlaylists(ArrayList<Playlist> playlists){
        SpotiUI.addPlaylists(playlists);
    }
/*
    public static void addFirstStadistics(List<Integer> numSongs){
        SpotiUI.addStadistics(numSongs);

    }*/

    public static LinkedList<Float> getNumSongs(){
        LinkedList<Float> numSongs = new LinkedList<Float>();
        for(int i=0; i<24; i++ ){
            if(new BusinessFacadeImp().getSongManager().gettingStadistics(i) == null){
                numSongs.add((float) 0);
            } else {
                numSongs.add(new BusinessFacadeImp().getSongManager().gettingStadistics(i).getNumPlayed());
            }
        }
        return numSongs;
    }

    public LinkedList<Float> getMinPlayed(){
        LinkedList<Float> numMin = new LinkedList<Float>();
        for(int i=0; i<24; i++ ){
            if(new BusinessFacadeImp().getSongManager().gettingStadistics(i) == null){
                numMin.add((float)0);
            } else {
                numMin.add(new BusinessFacadeImp().getSongManager().gettingStadistics(i).getMinPlayed());
            }
        }
        return numMin;
    }
}