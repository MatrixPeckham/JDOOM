/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapThing {

    public short x, y, angle, type, options;

    public MapThing(short x, short y, short angle, short type, short options) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.type = type;
        this.options = options;
    }

}
