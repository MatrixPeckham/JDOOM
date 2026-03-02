/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

import java.util.ArrayList;

/**
 *
 * @author matri
 */
public class SegList extends ArrayList<MapSeg> {

    public static SegList create(byte[] bytes) {
        SegList ret = new SegList();
        int count = bytes.length / 12;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short v1 = 0;
            v1 |= bytes[i++] & 0xff;
            v1 |= bytes[i++] << 8;
            short v2 = 0;
            v2 |= bytes[i++] & 0xff;
            v2 |= bytes[i++] << 8;
            short angle = 0;
            angle |= bytes[i++] & 0xff;
            angle |= bytes[i++] << 8;
            short linedef = 0;
            linedef |= bytes[i++] & 0xff;
            linedef |= bytes[i++] << 8;
            short side = 0;
            side |= bytes[i++] & 0xff;
            side |= bytes[i++] << 8;
            short offset = 0;
            offset |= bytes[i++] & 0xff;
            offset |= bytes[i++] << 8;

            ret.add(new MapSeg(v1, v2, angle, linedef, side, offset));

        }
        return ret;
    }

}
