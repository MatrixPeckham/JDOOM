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
public class LineDefList extends ArrayList<MapLineDef> {

    public static LineDefList create(byte[] bytes) {
        LineDefList ret = new LineDefList();
        int count = bytes.length / 14;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short v1 = 0;
            v1 |= bytes[i++] & 0xff;
            v1 |= bytes[i++] << 8;
            short v2 = 0;
            v2 |= bytes[i++] & 0xff;
            v2 |= bytes[i++] << 8;
            short flags = 0;
            flags |= bytes[i++] & 0xff;
            flags |= bytes[i++] << 8;
            short special = 0;
            special |= bytes[i++] & 0xff;
            special |= bytes[i++] << 8;
            short tag = 0;
            tag |= bytes[i++] & 0xff;
            tag |= bytes[i++] << 8;
            short side1 = 0;
            side1 |= bytes[i++] & 0xff;
            side1 |= bytes[i++] << 8;
            short side2 = 0;
            side2 |= bytes[i++] & 0xff;
            side2 |= bytes[i++] << 8;
            ret.add(new MapLineDef(v1, v2, flags, special, tag, side1, side2));
        }
        return ret;
    }

}
