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
public class PCSoundEffect extends SoundEffect {

    public static final float sampleRate = 11025;

    public static final double[] frequencies = {
        0,
        175.00,// //F-3
        180.02, 185.01, ////F#3
            190.02, 196.02, //G-3
        202.02, 208.01, //G#3
        214.02, 220.02, //A-3
        226.02, 233.04, //A#3
        240.02, 247.03, //B-3
        254.03, 262.00, //C-4
        269.03, 277.03, //C#4
        285.04, 294.03, //D-4
        302.07, 311.04, //D#4
        320.05, 330.06, //E-4
        339.06, 349.08, //F-4
        359.06, 370.09, //F#4
        381.08, 392.10, //G-4
        403.10, 415.01, //G#4
        427.05, 440.12, //A-4
        453.16, 466.08, //A#4
        480.15, 494.07, //B-4
        508.16, 523.09, //C-5
        539.16, 554.19, //C#5
        571.17, 587.19, //D-5
        604.14, 622.09, //D#5
        640.11, 659.21, //E-5
        679.10, 698.17, //F-5
        719.21, 740.18, //F#5
        762.41, 784.47, //G-5
        807.29, 831.48, //G#5
        855.32, 880.57, //A-5
        906.67, 932.17, //A#5
        960.69, 988.55, //B-5
        1017.20, 1046.64, //C-6
        1077.85, 1109.93, //C#6
        1141.79, 1175.54, //D-6
        1210.12, 1244.19, //D#6
        1281.61, 1318.43, //E-6
        1357.42, 1397.16, //F-6
        1439.30, 1480.37, //F#6
        1523.85, 1569.97, //G-6
        1614.58, 1661.81, //G#6
        1711.87, 1762.45, //A-6
        1813.34, 1864.34, //A#6
        1921.38, 1975.46, //B-6
        2036.14, 2093.29, //C-7
        2157.64, 2217.80, //C#7
        2285.78, 2353.41, //D-7
        2420.24, 2490.98, //D#7
        2565.97, 2639.77, //E-7
        0
    };

    public static final double TIME_PER_BYTE = 1 / 140.0;

    public static int makeSquareWave(byte[] buffer, int offset,
            double frequency, int samplesPerSecond) {
        int samples = (int) (samplesPerSecond * TIME_PER_BYTE);
        int samplesOn = (int) (samplesPerSecond / frequency) / 2;
        int s = 0;
        for (int i = offset; i < offset + samples; i++) {
            buffer[i] = (byte) -((s++ / samplesOn) % 2);
        }

        return offset + samples;
    }

    public static PCSoundEffect create(byte[] bytes) {
        PCSoundEffect ret = new PCSoundEffect();
        try {
            int i = 0;
            //skip 0 byte
            i += 2;
            int numSamples = 0;
            numSamples |= bytes[i++] & 0xff;
            numSamples |= (bytes[i++] & 0xFF) << 8;
            Mixer mixer = AudioSystem.getMixer(null);
            AudioFormat format = new AudioFormat(Encoding.PCM_UNSIGNED,
                    sampleRate, 8, 1, 1, sampleRate, false);
            Info info = new Info(Clip.class, format);
            Clip clip = (Clip) mixer.getLine(info);

            byte[] genBytes = new byte[(int) (sampleRate * TIME_PER_BYTE
                    * numSamples)];
            int offset = 0;
            for (int ind = 0; ind < numSamples; ind++) {
                offset = makeSquareWave(genBytes, offset, frequencies[bytes[i++]
                        & 0xFF], (int) sampleRate);
            }
            clip.open(format, genBytes, 0, genBytes.length);
            ret.sound = clip;
        } catch (LineUnavailableException lue) {
            throw new IllegalArgumentException("Can't make clip");
        }
        return ret;
    }

}
