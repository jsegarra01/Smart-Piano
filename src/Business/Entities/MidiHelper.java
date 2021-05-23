package Business.Entities;
import Presentation.Manager.SpotiFrameManager;

import javax.sound.midi.*;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;

/**
 * MidiHelper
 *
 * The "MidiHelper" class will contain the different methods needed to set, access and manage the midi files
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class MidiHelper {
    private final MidiChannel[] midiChannels;
    private final Instrument[] instruments;
    private int whatInstrumentIsPlayed;
    private static Sequencer sequencer;

    static {
        try {
            sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private boolean donePlaying;
    private static String fileSong = "";
    private static Sequence sequencePlay;

    /**
     * TODO
     * @throws MidiUnavailableException
     */
    public MidiHelper() throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        long startTime = System.nanoTime();
        synth.open();
        long estimatedTime = System.nanoTime() - startTime;
        midiChannels = synth.getChannels();
        instruments = synth.getDefaultSoundbank().getInstruments();
        sequencer.open();
        //sequencer.addMetaEventListener(new SpotiFrameManager());
    }

    /**
     * TODO
     * @param listener
     * @throws MidiUnavailableException
     */
    public MidiHelper(MetaEventListener listener) throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        long startTime = System.nanoTime();
        synth.open();
        long estimatedTime = System.nanoTime() - startTime;
        midiChannels = synth.getChannels();
        instruments = synth.getDefaultSoundbank().getInstruments();
        sequencer.addMetaEventListener(listener);
        sequencer.open();
        //sequencer.addMetaEventListener(new SpotiFrameManager());
    }

    /**
     * Plays the desired note in the desired instrument
     * @param noteValueToPlay Value of the note to play
     * @param whatInstrumentToPlay Instrument to play
     */
    public void playSomething(int noteValueToPlay, int whatInstrumentToPlay){
        this.whatInstrumentIsPlayed = whatInstrumentToPlay;
        midiChannels[0].programChange(whatInstrumentToPlay);
        midiChannels[0].noteOn(noteValueToPlay,100);
    }

    /**
     * Stops playing the desired note in the desired instrument
     * @param noteValueToPlay Value of the note to stop playing
     * @param whatInstrumentToPlay Instrument to stop playing
     */
    public void stopPlaying(int noteValueToPlay, int whatInstrumentToPlay){
        this.whatInstrumentIsPlayed = whatInstrumentToPlay;
        midiChannels[0].noteOff(noteValueToPlay,15);
    }

    /**
     * Plays the song stored in the file
     * @param filename File where the song is stored
     */
    public void playSong(String filename){
        try {
            if(!(filename.equals(fileSong))){
                restartSong(filename);
            }
             // Open device
            // Create sequence, the File must contain MIDI file data.
            sequencer.setSequence(sequencePlay); // load it into sequencer
            sequencer.start();               // start the playback

        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restarts the song stored in the file
     * @param filename File where the song is stored
     */
    public void restartSong(String filename){
        try {
            sequencePlay = MidiSystem.getSequence(new File(filename));
        } catch (InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
        fileSong = filename;
    }

    /**
     * Gets which instrument is being played
     * @return The instrument being played
     */
    public String getInstrument(){
        return this.instruments[whatInstrumentIsPlayed].getName();
    }

    /**
     * Mutes the song
     * @return True if muted, false if not
     */
    public void muteSong() {

        sequencer.setTrackMute(0, !sequencer.getTrackMute(0));
        sequencer.setTrackMute(1, !sequencer.getTrackMute(1));
        sequencer.setTrackMute(2, !sequencer.getTrackMute(2));
        sequencer.setTrackMute(3, !sequencer.getTrackMute(3));
        sequencer.setTrackMute(4, !sequencer.getTrackMute(4));
        sequencer.setTrackMute(5, !sequencer.getTrackMute(5));
        sequencer.setTrackMute(6, !sequencer.getTrackMute(6));
        sequencer.setTrackMute(7, !sequencer.getTrackMute(7));
        sequencer.setTrackMute(8, !sequencer.getTrackMute(8));
        sequencer.setTrackMute(9, !sequencer.getTrackMute(9));

        //return sequencer.getTrackMute(0);
    }

    /**
     * Stops playing whatever is playing
     */
    public void stopSong(){
        sequencer.stop();
    }

    /**
     * Gets if a song has finished playing
     * @return True if finished, false if not
     */
    public boolean isDonePlaying() {
        return donePlaying;
    }

    /**
     * Sets if a song has finsished
     * @param donePlaying True if finished, false if not
     */
    public void setDonePlaying(boolean donePlaying) {
        this.donePlaying = donePlaying;
    }

    public float getDuration(String filename) throws InvalidMidiDataException {
        restartSong(filename);
        sequencer.setSequence(sequencePlay);
        return sequencer.getMicrosecondLength();
    }
}
