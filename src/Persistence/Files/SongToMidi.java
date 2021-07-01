package Persistence.Files;

//Imports needed from the Gson, files and midis
import Business.BusinessFacadeImp;
import Business.Entities.RecordingNotes;
import Business.Entities.SongRecorded;
import Business.Translator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import javax.sound.midi.*;

/**
 * SongToMidi
 *
 * The "SongToMidi" class will contain the method to write from the different songRecorded to a MIDI file 
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 9 May 2021
 *
 */

public class SongToMidi {


    /**
     * Static method in order to be able to write in a song from the information stored in recordingNotes in a MIDI format
     * @param title String. Name of the song
     * @param recordingNotes ArrayList of RecordingNotes. Information recorded from the song we want to transform into MIDI
     * @param endtime float. Ending time of the song
     */
    public static void writeMidi(String title, ArrayList<RecordingNotes> recordingNotes, float endtime) {
        try {
//****  Create a new MIDI sequence with 24 ticks per beat  ****
            Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);

//****  Obtain a MIDI track from the sequence  ****
            Track t = s.createTrack();

//****  General MIDI sysex -- turn on General MIDI sound set  ****
            byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
            SysexMessage sm = new SysexMessage();
            sm.setMessage(b, 6);
            MidiEvent me = new MidiEvent(sm, 0);
            t.add(me);

//****  set tempo (meta event)  ****
            MetaMessage mt = new MetaMessage();
            byte[] bt = {0x02, (byte)0x00, 0x00};
            mt.setMessage(0x51 ,bt, 3);
            me = new MidiEvent(mt, 0);
            t.add(me);

//****  set track name (meta event)  ****
            mt = new MetaMessage();
            mt.setMessage(0x03 , title.getBytes(), title.length());
            me = new MidiEvent(mt, 0);
            t.add(me);

//****  set omni on  ****
            ShortMessage mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7D,0x00);
            me = new MidiEvent(mm, 0);
            t.add(me);

//****  set poly on  ****
            mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7F,0x00);
            me = new MidiEvent(mm, 0);
            t.add(me);

//****  set instrument to Piano  ****
            mm = new ShortMessage();
            mm.setMessage(0xC0, 0x00, 0x00);
            me = new MidiEvent(mm, 0);
            t.add(me);

            for (RecordingNotes recordingNote: recordingNotes) {
                mm = new ShortMessage();
                mm.setMessage(0x90, Translator.getNumberNoteFromName(recordingNote.getKey()),0x60);
                me = new MidiEvent(mm,(long)(recordingNote.getTime()*184));
                t.add(me);

                mm = new ShortMessage(); // 0x080
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
            BusinessFacadeImp.getBusinessFacade().setError(4);
        } //catch
    }
}