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
    private static final int NOTE_ON = 0x90;

    /**
     * Reads a midi file and stores the keys pressed, together with how much time they have been pressed and when, and returns the list of keys
     * @param fileName Path of the file
     * @return The list of keys with its corresponding information
     * @throws Exception If the file is not found, it will throw a file not found exception.
     */
    public static ArrayList<Keys> readMidi(String fileName) throws Exception {
        ArrayList<Keys> keys = new ArrayList<>();
        long playedOn = 0;
        long playedOff;
        Sequence sequence = MidiSystem.getSequence(new File(fileName));

        int trackNumber = 0;
        for (Track track :  sequence.getTracks()) {
            trackNumber++;
            for (int i=0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage sm) {
                    if (sm.getCommand() == NOTE_ON && sm.getChannel() == 0 && sm.getData2() > 0) {
                        playedOn = event.getTick();
                    } else if (sm.getCommand() == NOTE_ON && sm.getData2() == 0 && sm.getChannel() == 0) {
                        int key = sm.getData1();
                        playedOff = event.getTick();
                        keys.add(new Keys(key, (playedOff - playedOn), playedOn));
                    }
                }
            }

        }
        return keys;
    }
}