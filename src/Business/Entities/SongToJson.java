package Business.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import javax.sound.midi.*;

public class SongToJson {

    public static void writeJSONsong (SongRecorded songRecorded, String songName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = null;

        try {
            writer = Files.newBufferedWriter(Paths.get("song/" + songName + ".json"));
            //Defining to which file we will write.
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson.toJson(songRecorded, writer); //Writing the content of readCompetition in the file.

        try {
            writer.close(); //Closing the writer.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeMidi(String title, ArrayList<RecordingNotes> recordingNotes, float endtime) {
        System.out.println("midifile begin ");
        try
        {
//****  Create a new MIDI sequence with 24 ticks per beat  ****
            Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);

//****  Obtain a MIDI track from the sequence  ****
            Track t = s.createTrack();

//****  General MIDI sysex -- turn on General MIDI sound set  ****
            byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
            SysexMessage sm = new SysexMessage();
            sm.setMessage(b, 6);
            MidiEvent me = new MidiEvent(sm,(long)0);
            t.add(me);

//****  set tempo (meta event)  ****
            MetaMessage mt = new MetaMessage();
            byte[] bt = {0x02, (byte)0x00, 0x00};
            mt.setMessage(0x51 ,bt, 3);
            me = new MidiEvent(mt,(long)0);
            t.add(me);

//****  set track name (meta event)  ****
            mt = new MetaMessage();
            String TrackName = new String(title);
            mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
            me = new MidiEvent(mt,(long)0);
            t.add(me);

//****  set omni on  ****
            ShortMessage mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7D,0x00);
            me = new MidiEvent(mm,(long)0);
            t.add(me);

//****  set poly on  ****
            mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7F,0x00);
            me = new MidiEvent(mm,(long)0);
            t.add(me);

//****  set instrument to Piano  ****
            mm = new ShortMessage();
            mm.setMessage(0xC0, 0x00, 0x00);
            me = new MidiEvent(mm,(long)0);
            t.add(me);

            for (RecordingNotes recordingNote: recordingNotes) {
                mm = new ShortMessage();
                mm.setMessage(0x90,Translator.getNumberNoteFromName(recordingNote.getKey()),0x60);
                me = new MidiEvent(mm,(long)(recordingNote.getTime()*184));
                t.add(me);

                mm = new ShortMessage();
                mm.setMessage(0x90,Translator.getNumberNoteFromName(recordingNote.getKey()),0x00);
                me = new MidiEvent(mm,(long)((recordingNote.getTime() + recordingNote.getDuration())*184));
                t.add(me);
            }


//****  set end of track (meta event) 19 ticks later  ****
            mt = new MetaMessage();
            byte[] bet = {}; // empty array
            mt.setMessage(0x2F,bet,0);
            me = new MidiEvent(mt, (long)endtime);
            t.add(me);

//****  write the MIDI sequence to a MIDI file  ****
            File f = new File("Songs/" + title + ".mid");
            MidiSystem.write(s,1,f);
        } //try
        catch(Exception e)
        {
            System.out.println("Exception caught " + e.toString());
        } //catch
        System.out.println("midifile end ");
    }
}