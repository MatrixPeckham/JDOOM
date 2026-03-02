/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.s;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.midi.*;

/**
 *
 * @author matri
 */
public class Song {

    public Sequence sequence;

    public static Song create(byte[] bytes) {
        Song s = new Song();
        ByteArrayInputStream bis;
        try {
            try {
                // If data is a midi file, load it directly
                bis = new ByteArrayInputStream(bytes);
                s.sequence = MidiSystem.getSequence(bis);
            } catch (InvalidMidiDataException ex) {
                // Well, it wasn't. Dude.
                bis = new ByteArrayInputStream(bytes);
                s.sequence = MusReader.getSequence(bis);
            }
        } catch (IOException | InvalidMidiDataException ex) {
            throw new IllegalArgumentException("Could not read music", ex);
        }
        return s;
    }

}
