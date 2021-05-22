package Business.Entities;
import Presentation.Manager.SpotiFrameManager;

import javax.sound.midi.*;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;

/**
 * Piano:
 * 1 Acoustic Grand Piano
 * 2 Bright Acoustic Piano
 * 3 Electric Grand Piano
 * 4 Honky-tonk Piano
 * 5 Electric Piano 1
 * 6 Electric Piano 2
 * 7 Harpsichord
 * 8 Clavinet
 *
 * Chromatic Percussion:
 * 9 Celesta
 * 10 Glockenspiel
 * 11 Music Box
 * 12 Vibraphone
 * 13 Marimba
 * 14 Xylophone
 * 15 Tubular Bells
 * 16 Dulcimer
 *
 * Organ:
 * 17 Drawbar Organ
 * 18 Percussive Organ
 * 19 Rock Organ
 * 20 Church Organ
 * 21 Reed Organ
 * 22 Accordion
 * 23 Harmonica
 * 24 Tango Accordion
 *
 *
 *
 * Guitar:
 * 25 Acoustic Guitar (nylon)
 * 26 Acoustic Guitar (steel)
 * 27 Electric Guitar (jazz)
 * 28 Electric Guitar (clean)
 * 29 Electric Guitar (muted)
 * 30 Overdriven Guitar
 * 31 Distortion Guitar
 * 32 Guitar harmonics
 *
 * Bass:
 * 33 Acoustic Bass
 * 34 Electric Bass (finger)
 * 35 Electric Bass (pick)
 * 36 Fretless Bass
 * 37 Slap Bass 1
 * 38 Slap Bass 2
 * 39 Synth Bass 1
 * 40 Synth Bass 2
 *
 *
 *
 * Strings:
 * 41 Violin
 * 42 Viola
 * 43 Cello
 * 44 Contrabass
 * 45 Tremolo Strings
 * 46 Pizzicato Strings
 * 47 Orchestral Harp
 * 48 Timpani
 *
 * Strings (continued):
 * 49 String Ensemble 1
 * 50 String Ensemble 2
 * 51 Synth Strings 1
 * 52 Synth Strings 2
 * 53 Choir Aahs
 * 54 Voice Oohs
 * 55 Synth Voice
 * 56 Orchestra Hit
 *
 * Brass:
 * 57 Trumpet
 * 58 Trombone
 * 59 Tuba
 * 60 Muted Trumpet
 * 61 French Horn
 * 62 Brass Section
 * 63 Synth Brass 1
 * 64 Synth Brass 2
 *
 * Reed:
 * 65 Soprano Sax
 * 66 Alto Sax
 * 67 Tenor Sax
 * 68 Baritone Sax
 * 69 Oboe
 * 70 English Horn
 * 71 Bassoon
 * 72 Clarinet
 *
 * Pipe:
 * 73 Piccolo
 * 74 Flute
 * 75 Recorder
 * 76 Pan Flute
 * 77 Blown Bottle
 * 78 Shakuhachi
 * 79 Whistle
 * 80 Ocarina
 *
 * Synth Lead:
 * 81 Lead 1 (square)
 * 82 Lead 2 (sawtooth)
 * 83 Lead 3 (calliope)
 * 84 Lead 4 (chiff)
 * 85 Lead 5 (charang)
 * 86 Lead 6 (voice)
 * 87 Lead 7 (fifths)
 * 88 Lead 8 (bass + lead)
 *
 * Synth Pad:
 * 89 Pad 1 (new age)
 * 90 Pad 2 (warm)
 * 91 Pad 3 (polysynth)
 * 92 Pad 4 (choir)
 * 93 Pad 5 (bowed)
 * 94 Pad 6 (metallic)
 * 95 Pad 7 (halo)
 * 96 Pad 8 (sweep)
 *
 * Synth Effects:
 * 97 FX 1 (rain)
 * 98 FX 2 (soundtrack)
 * 99 FX 3 (crystal)
 * 100 FX 4 (atmosphere)
 * 101 FX 5 (brightness)
 * 102 FX 6 (goblins)
 * 103 FX 7 (echoes)
 * 104 FX 8 (sci-fi)
 *
 * Ethnic:
 * 105 Sitar
 * 106 Banjo
 * 107 Shamisen
 * 108 Koto
 * 109 Kalimba
 * 110 Bag pipe
 * 111 Fiddle
 * 112 Shanai
 *
 * Percussive:
 * 113 Tinkle Bell
 * 114 Agogo
 * 115 Steel Drums
 * 116 Woodblock
 * 117 Taiko Drum
 * 118 Melodic Tom
 * 119 Synth Drum
 *
 * Sound effects:
 * 120 Reverse Cymbal
 * 121 Guitar Fret Noise
 * 122 Breath Noise
 * 123 Seashore
 * 124 Bird Tweet
 * 125 Telephone Ring
 * 126 Helicopter
 * 127 Applause
 * 128 Gunshot
 * */

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
    public void playSomething(int noteValueToPlay, int whatInstrumentToPlay){
        this.whatInstrumentIsPlayed = whatInstrumentToPlay;
        midiChannels[0].programChange(whatInstrumentToPlay);
        midiChannels[0].noteOn(noteValueToPlay,100);
    }

    public void stopPlaying(int noteValueToPlay, int whatInstrumentToPlay){
        this.whatInstrumentIsPlayed = whatInstrumentToPlay;
        midiChannels[0].noteOff(noteValueToPlay,15);
    }

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
    public void restartSong(String filename){
        try {
            sequencePlay = MidiSystem.getSequence(new File(filename));
        } catch (InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
        fileSong = filename;
    }

    public String getInstrument(){
        return this.instruments[whatInstrumentIsPlayed].getName();
    }

    public void stopSong(){
        sequencer.stop();
    }

    public boolean isDonePlaying() {
        return donePlaying;
    }

    public void setDonePlaying(boolean donePlaying) {
        this.donePlaying = donePlaying;
    }
}
