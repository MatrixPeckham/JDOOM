/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapSeg {

    public short v1, v2, angle, linedef, side, offset;

    public MapSeg(short v1, short v2, short angle, short linedef, short side,
            short offset) {
        this.v1 = v1;
        this.v2 = v2;
        this.angle = angle;
        this.linedef = linedef;
        this.side = side;
        this.offset = offset;
    }

}
