/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

import static com.matrixpeckham.jdoom.DoomDef.SCREENWIDTH;

/**
 * Visplanes
 *
 * @author matri
 */
public class VisPlane {

    public int height;

    public int picNum;

    public int lightLevel;

    public int minX;

    public int maxX;

    //leave pads for [minX-1]/[maxX+1]
    public byte pad1;

    //this is the problem for resizing dynamically
    public byte[] top = new byte[SCREENWIDTH];

    byte pad2;

    byte pad3;

    //see above
    public byte[] bottom = new byte[SCREENWIDTH];

    byte pad4;

}
