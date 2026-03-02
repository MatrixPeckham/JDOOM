/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.p;

import static com.matrixpeckham.jdoom.util.Fixed.FRAC_BITS;

import java.util.ArrayList;

/**
 *
 * @author matri
 */
public class BlockMap {

    public int orgX;

    public int orgY;

    public int cols;

    public int rows;

    public int[][] blocklist;

    private BlockMap(byte[] bytes) {
        int i = 0;
        short x = 0;
        x |= bytes[i++] & 0xff;
        x |= bytes[i++] << 8;
        orgX = x << FRAC_BITS;
        short y = 0;
        y |= bytes[i++] & 0xff;
        y |= bytes[i++] << 8;
        orgY = y << FRAC_BITS;
        cols = 0;
        cols |= bytes[i++] & 0xff;
        cols |= bytes[i++] << 8;
        rows = 0;
        rows |= bytes[i++] & 0xff;
        rows |= bytes[i++] << 8;

        int numBlocks = rows * cols;
        blocklist = new int[numBlocks][];
        for (int ind = 0; ind < numBlocks; ind++) {
            short offset = 0;
            offset |= bytes[i++] & 0xff;
            offset |= bytes[i++] << 8;
            blocklist[ind] = loadBlock(offset * 2, bytes);
        }

    }

    private int[] loadBlock(int i, byte[] bytes) {
        ArrayList<Integer> ret = new ArrayList();
        short temp = 0;
        temp |= bytes[i++] & 0xFF;
        temp |= bytes[i++] << 8;
        if (temp != 0) {
            throw new IllegalArgumentException("First item in block is 0");
        }
        do {
            temp = 0;
            temp |= bytes[i++] & 0xFF;
            temp |= bytes[i++] << 8;
            if (temp == -1) {
                break;
            }
            ret.add((int) temp);
        } while (i < bytes.length);
        return ret.stream().mapToInt(t -> t).toArray();
    }

    public static BlockMap create(byte[] bytes) {
        BlockMap ret = new BlockMap(bytes);

        return ret;
    }

}
