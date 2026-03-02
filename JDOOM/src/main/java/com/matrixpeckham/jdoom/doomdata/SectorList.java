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
public class SectorList extends ArrayList<MapSector> {

    public static SectorList create(byte[] bytes) {
        SectorList ret = new SectorList();
        int count = bytes.length / 26;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short floor = 0;
            floor |= bytes[i++] & 0xff;
            floor |= bytes[i++] << 8;
            short ceil = 0;
            ceil |= bytes[i++] & 0xff;
            ceil |= bytes[i++] << 8;
            String fpic = new String(bytes, i, 8).toUpperCase();
            i += 8;
            String cpic = new String(bytes, i, 8).toUpperCase();
            i += 8;
            short light = 0;
            light |= bytes[i++] & 0xff;
            light |= bytes[i++] << 8;
            short spec = 0;
            spec |= bytes[i++] & 0xff;
            spec |= bytes[i++] << 8;
            short tag = 0;
            tag |= bytes[i++] & 0xff;
            tag |= bytes[i++] << 8;
            ret.add(new MapSector(floor, ceil, fpic, cpic, light, spec, tag));
        }
        return ret;
    }

}
