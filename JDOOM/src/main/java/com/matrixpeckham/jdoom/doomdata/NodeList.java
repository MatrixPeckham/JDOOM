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
public class NodeList extends ArrayList<MapNode> {

    public static NodeList create(byte[] bytes) {
        NodeList ret = new NodeList();
        int count = bytes.length / 28;
        int i = 0;
        for (int ind = 0; ind < count; ind++) {
            short x = 0;
            x |= bytes[i++] & 0xff;
            x |= bytes[i++] << 8;
            short y = 0;
            y |= bytes[i++] & 0xff;
            y |= bytes[i++] << 8;
            short dx = 0;
            dx |= bytes[i++] & 0xff;
            dx |= bytes[i++] << 8;
            short dy = 0;
            dy |= bytes[i++] & 0xff;
            dy |= bytes[i++] << 8;
            short ruy = 0;
            ruy |= bytes[i++] & 0xff;
            ruy |= bytes[i++] << 8;
            short rly = 0;
            rly |= bytes[i++] & 0xff;
            rly |= bytes[i++] << 8;
            short rlx = 0;
            ruy |= bytes[i++] & 0xff;
            ruy |= bytes[i++] << 8;
            short rux = 0;
            ruy |= bytes[i++] & 0xff;
            ruy |= bytes[i++] << 8;
            short luy = 0;
            luy |= bytes[i++] & 0xff;
            luy |= bytes[i++] << 8;
            short lly = 0;
            lly |= bytes[i++] & 0xff;
            lly |= bytes[i++] << 8;
            short llx = 0;
            luy |= bytes[i++] & 0xff;
            luy |= bytes[i++] << 8;
            short lux = 0;
            luy |= bytes[i++] & 0xff;
            luy |= bytes[i++] << 8;
            short right = 0;
            right |= bytes[i++] & 0xff;
            right |= bytes[i++] << 8;
            short left = 0;
            left |= bytes[i++] & 0xff;
            left |= bytes[i++] << 8;
            ret.add(new MapNode(x, y, dx, dy, ruy, rly, rlx, rux, luy, lly, llx,
                    lux, right, left));
        }
        return ret;
    }

}
