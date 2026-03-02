/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapNode {

    public static final int NF_SUBSECTOR = (int) 0x8000;

    public short x, y, dx, dy;

    /**
     * [0 right 1 left][0] upper y
     * [0 right 1 left][1] lower y
     * [0 right 1 left][2] lower x
     * [0 right 1 left][3] upper x
     */
    public short[][] bbox = new short[2][4];

    /**
     * [0] right
     * [1] left
     */
    public int[] children = new int[2];

    public MapNode(short x, short y, short dx, short dy, short ruy, short rly,
            short rlx, short rux, short luy, short lly, short llx, short lux,
            short rightChild, short leftChild) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        bbox[0][0] = ruy;
        bbox[0][1] = rly;
        bbox[0][2] = rlx;
        bbox[0][3] = rux;
        bbox[1][0] = luy;
        bbox[1][1] = lly;
        bbox[1][2] = llx;
        bbox[1][3] = lux;

        children[0] = rightChild & 0xFFFF;
        children[1] = leftChild & 0xFFFF;
    }

}
