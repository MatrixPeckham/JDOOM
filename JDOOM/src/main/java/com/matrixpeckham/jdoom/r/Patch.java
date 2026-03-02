/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r;

import java.util.Arrays;

/**
 *
 * @author matri
 */
public class Patch {

    public short[][] data;

    public short width;

    public short height;

    public short leftOffset;

    public short topOffset;

    protected Patch() {

    }

    public Patch(short width, short height) {
        this.width = width;
        this.height = height;
        leftOffset = 0;
        topOffset = 0;
        data = new short[width][height];
    }

    public static Patch create(byte[] bytes) {
        Patch ret = new Patch();
        int i = 0;
        ret.width = 0;
        ret.width |= bytes[i++] & 0xff;
        ret.width |= bytes[i++] << 8;
        ret.height = 0;
        ret.height |= bytes[i++] & 0xff;
        ret.height |= bytes[i++] << 8;
        ret.leftOffset = 0;
        ret.leftOffset |= bytes[i++] & 0xff;
        ret.leftOffset |= bytes[i++] << 8;
        ret.topOffset = 0;
        ret.topOffset |= bytes[i++] & 0xff;
        ret.topOffset |= bytes[i++] << 8;
        ret.data = new short[ret.width][ret.height];
        for (int ind = 0; ind < ret.width; ind++) {
            int offset = 0;
            offset |= (bytes[i++] & 0xFF);
            offset |= (bytes[i++] & 0xFF) << 8;
            offset |= (bytes[i++] & 0xFF) << 16;
            offset |= (bytes[i++] & 0xFF) << 24;
            readCol(ret, bytes, offset, ind);
        }

        return ret;
    }

    private static void readCol(Patch ret, byte[] bytes, int offset,
            int index
    ) {
        Arrays.fill(ret.data[index], (short) 0x100);
        short b = (short) (bytes[offset++] & 0xFF);
        while (b != 0xFF) {
            int y = b;

            short count = (short) (bytes[offset++] & 0xFF);
            //skip pad byte
            offset++;

            for (int i = 0; i < count; i++) {
                ret.data[index][y++] = (short) (bytes[offset++] & 0xFF);
            }

            //skip pad byte
            offset++;

            b = (short) (bytes[offset++] & 0xFF);
        }
    }

}
