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
public class PNames extends ArrayList<String> {

    public static PNames create(byte[] bytes) {
        PNames ret = new PNames();
        int i = 0;
        int count = 0;
        count |= (bytes[i++] & 0xFF);
        count |= (bytes[i++] & 0xFF) << 8;
        count |= (bytes[i++] & 0xFF) << 16;
        count |= (bytes[i++] & 0xFF) << 24;
        for (int ind = 0; ind < count; ind++) {
            ret.add(new String(bytes, i, 8).toUpperCase());
            i += 8;
        }
        return ret;
    }

}
