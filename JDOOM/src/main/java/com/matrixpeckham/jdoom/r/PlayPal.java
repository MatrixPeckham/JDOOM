/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r;

import java.awt.Color;

/**
 * The palettes used by the game
 *
 * @author matri
 */
public class PlayPal {

    Color[][] palettes;

    public static PlayPal create(byte[] bytes) {
        PlayPal pal = new PlayPal();

        int numPalettes = bytes.length / 256 / 3;

        pal.palettes = new Color[numPalettes][256];
        int i = 0;
        for (int ind = 0; ind < numPalettes; ind++) {
            for (int col = 0; col < 256; col++) {
                int red = bytes[i++] & 0xFF;
                int green = bytes[i++] & 0xFF;
                int blue = bytes[i++] & 0xFF;
                pal.palettes[ind][col] = new Color(red, green, blue);
            }
        }

        return pal;
    }

    private int currentPalette = 0;

    public Color getColor(int c) {
        return palettes[currentPalette][c];
    }

    public Color getColor(int palette, int c) {
        return palettes[palette][c];
    }

    public int getNumPalettes() {
        return palettes.length;
    }

    public int getCurPalette() {
        return currentPalette;
    }

    public void setCurPalette(int p) {
        if (p < 0) {
            p = 0;
        }
        if (p >= palettes.length) {
            p = palettes.length - 1;
        }
        currentPalette = p;
    }

}
