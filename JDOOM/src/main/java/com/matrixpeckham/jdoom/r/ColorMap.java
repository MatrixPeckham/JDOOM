/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r;

/**
 *
 * @author matri
 */
public class ColorMap {

    int map[][];

    int curMap = 0;

    public int getNumMaps() {
        return map.length;
    }

    public int getCurMap() {
        return curMap;
    }

    public void setCurMap(int i) {
        curMap = i;
    }

    public int map(int i) {
        return map[curMap][i];
    }

    public int map(int m, int i) {
        return map[m][i];
    }

    public static ColorMap create(byte[] bytes) {
        ColorMap map = new ColorMap();
        int numMaps = bytes.length / 256;
        map.map = new int[numMaps][256];
        int ind = 0;
        for (int m = 0; m < numMaps; m++) {
            for (int i = 0; i < 256; i++) {
                map.map[m][i] = bytes[ind++] & 0xFF;
            }
        }
        return map;
    }

}
