/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.s;

import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.*;

/**
 *
 * @author matri
 */
public class SoundEffect {

    Clip sound;

    public static SoundEffect create(byte[] bytes) {
        SoundEffect ret = new SoundEffect();
        try {
            int i = 0;
            //skip "3" short
            i += 2;
            int sampleRate = 0;
            sampleRate |= bytes[i++] & 0xff;
            sampleRate |= (bytes[i++] & 0xFF) << 8;
            int numSamples = 0;
            numSamples |= bytes[i++] & 0xff;
            numSamples |= (bytes[i++] & 0xFF) << 8;
            Mixer mixer = AudioSystem.getMixer(null);
            AudioFormat format = new AudioFormat(Encoding.PCM_UNSIGNED,
                    sampleRate, 8, 1, 1, sampleRate, false);
            Info info = new Info(Clip.class, format);
            Clip clip = (Clip) mixer.getLine(info);
            clip.open(format, bytes, i, numSamples);

            ret.sound = clip;
        } catch (LineUnavailableException lue) {
            throw new IllegalArgumentException("Can't make clip");
        }
        return ret;
    }

    public void start() {
        sound.setFramePosition(0);
        sound.start();
    }

    public void stop() {
        sound.stop();
    }

}
