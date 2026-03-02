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
public class SideDefList extends ArrayList<MapSideDef> {

    public static SideDefList create(byte[] bytes) {
        SideDefList ret = new SideDefList();
        int count = bytes.length / 30;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short x = 0;
            x |= bytes[i++] & 0xff;
            x |= bytes[i++] << 8;
            short y = 0;
            y |= bytes[i++] & 0xff;
            y |= bytes[i++] << 8;
            String upper = new String(bytes, i, 8).toUpperCase();
            i += 8;
            String lower = new String(bytes, i, 8).toUpperCase();
            i += 8;
            String middle = new String(bytes, i, 8).toUpperCase();
            i += 8;
            short sect = 0;
            sect |= bytes[i++] & 0xff;
            sect |= bytes[i++] << 8;
            ret.add(new MapSideDef(x, y, upper, lower, middle, sect));
        }
        return ret;
    }

}
