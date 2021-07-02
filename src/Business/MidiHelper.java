package Business;
import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

/**
 * MidiHelper
 *
 * The "MidiHelper" class will contain the different methods needed to set, access and manage the midi files
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
@SuppressWarnings("unused") //We know they are "unused", but without them getting the instruments, the midi wouldn't sound so they are actually used
public class MidiHelper {
    private final MidiChannel[] midiChannels;
    private final Instrument[] instruments;
    private int whatInstrumentIsPlayed;
    private static Sequencer sequencer;
    static{
    try {
        sequencer = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
        BusinessFacadeImp.getBusinessFacade().setError(3);
    }
    }
    private String fileSong = "";
    private Sequence sequencePlay;

    /**
     * Constructor of the MidiHelper class
     * @throws MidiUnavailableException exception that will be thrown in case there has been an exception in the midi
     */
    public MidiHelper() throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        midiChannels = synth.getChannels();
        instruments = synth.getDefaultSoundbank().getInstruments();
        sequencer.open();
    }

    /**
     * Constructor of the MidiHelper class that sets in the parameter the listener
     * @param listener Defines a listener of the class
     * @throws MidiUnavailableException exception that will be thrown in case there has been an exception in the midi
     */
    public MidiHelper(MetaEventListener listener) throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        midiChannels = synth.getChannels();
        instruments = synth.getDefaultSoundbank().getInstruments();

        sequencer.addMetaEventListener(listener);
        sequencer.open();
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
                if(!restartSong(filename)){
                    return;
                }
            }

             // Open device
            // Create sequence, the File must contain MIDI file data.
            sequencer.setSequence(sequencePlay); // load it into sequencer
            sequencer.setLoopStartPoint(0);
            sequencer.start();               // start the playback

        } catch (InvalidMidiDataException e) {
            BusinessFacadeImp.getBusinessFacade().setError(8);
        }
    }

    /**
     * Restarts the song stored in the file
     * @param filename File where the song is stored
     */
    public boolean restartSong(String filename){
        fileSong = filename;
        try {
            sequencePlay = MidiSystem.getSequence(new File(filename));
            return true;
        } catch (InvalidMidiDataException | IOException e) {
            BusinessFacadeImp.getBusinessFacade().setError(8);
            return false;
        }
    }

    /**
     * Mutes the song
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
    }

    /**
     * Stops playing whatever is playing
     */
    public void stopSong(){
        sequencer.stop();
    }

    /**
     * Method that gets the duration of the song
     * @param filename Defines the name of the file that the song is stored in
     * @return Float that stores the duration of the song
     */
    public float getDuration(String filename){
        try {
            sequencer.setSequence(MidiSystem.getSequence(new File(filename)));
        } catch (InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
        return sequencer.getMicrosecondLength();
    }
}
