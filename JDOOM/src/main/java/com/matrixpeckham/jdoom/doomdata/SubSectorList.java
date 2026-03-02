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
public class SubSectorList extends ArrayList<MapSubSector> {

    public static SubSectorList create(byte[] bytes) {
        SubSectorList ret = new SubSectorList();
        int count = bytes.length / 4;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short x = 0;
            x |= bytes[i++] & 0xff;
            x |= bytes[i++] << 8;
            short y = 0;
            y |= bytes[i++] & 0xff;
            y |= bytes[i++] << 8;
            ret.add(new MapSubSector(x, y));
        }
        return ret;
    }

}
