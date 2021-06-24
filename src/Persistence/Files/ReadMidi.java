package Persistence.Files;

import Business.Entities.Keys;

import java.io.File;
import java.util.ArrayList;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * ReadMidi
 *
 * The "ReadMidi" class will contain the method to read from a midi file
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class ReadMidi {
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    /**
     * Reads a midi file and stores the keys pressed, together with how much time they have been pressed and when, and returns the list of keys
     * @param fileName Path of the file
     * @return The list of keys with its corresponding information
     * @throws Exception If the file is not found, it will throw a file not found exception.
     */
    public static ArrayList<Keys> readMidi(String fileName) throws Exception {
        ArrayList<Keys> keys = new ArrayList<>();
        long playedOn = 0;
        long playedOff = 0;
        Sequence sequence = MidiSystem.getSequence(new File(fileName));

        int trackNumber = 0;
        for (Track track :  sequence.getTracks()) {
            trackNumber++;
            //System.out.println("Track " + trackNumber + ": size = " + track.size());
            //System.out.println();
            for (int i=0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                //System.out.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                  //  System.out.print("Channel: " + sm.getChannel() + " ");
                    if (sm.getCommand() == NOTE_ON && sm.getChannel() == 0 && sm.getData2() > 0) {
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        int velocity = sm.getData2();
                        playedOn = event.getTick();
                       // System.out.println("Note on, " + noteName + octave + " key=" + key + "duration " + playedOn  + " velocity: " + velocity);
                    } else if (sm.getCommand() == NOTE_ON && sm.getData2() == 0 && sm.getChannel() == 0) {
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        int velocity = sm.getData2();
                        playedOff = event.getTick();
                        keys.add(new Keys(key, (playedOff - playedOn), playedOn));
                      //  System.out.println("Note off, " + noteName + octave + " key=" + key + " duration " + playedOff + " velocity: " + velocity);
                    } else {
                        //System.out.println("Command:" + sm.getCommand());
                    }
                } else {
                    //System.out.println("Other message: " + message.getClass());
                }
            }

            System.out.println();
        }
        return keys;
    }
}