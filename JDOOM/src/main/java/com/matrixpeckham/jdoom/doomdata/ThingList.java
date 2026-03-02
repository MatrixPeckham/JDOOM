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
public class ThingList extends ArrayList<MapThing> {

    public static ThingList create(byte[] bytes) {
        ThingList ret = new ThingList();
        int count = bytes.length / 10;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short x = 0;
            x |= bytes[i++] & 0xff;
            x |= bytes[i++] << 8;
            short y = 0;
            y |= bytes[i++] & 0xff;
            y |= bytes[i++] << 8;
            short angle = 0;
            angle |= bytes[i++] & 0xff;
            angle |= bytes[i++] << 8;
            short type = 0;
            type |= bytes[i++] & 0xff;
            type |= bytes[i++] << 8;
            short options = 0;
            options |= bytes[i++] & 0xff;
            options |= bytes[i++] << 8;

            ret.add(new MapThing(x, y, angle, type, options));
        }
        return ret;
    }

}
