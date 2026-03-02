/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r;

/**
 *
 * @author matri
 */
public class Flat {

    public short[][] data;

    public short width = 64;

    public short height = 64;

    public short leftOffset = 0;

    public short topOffset = 0;

    private Flat() {

    }

    public static Flat create(byte[] bytes) {
        Flat ret = new Flat();
        int i = 0;
        ret.data = new short[ret.width][ret.height];

        for (int row = 0; row < ret.height; row++) {
            for (int col = 0; col < ret.width; col++) {
                ret.data[row][col] = (short) (bytes[i++] & 0xFF);
            }
        }

        return ret;
    }

}
