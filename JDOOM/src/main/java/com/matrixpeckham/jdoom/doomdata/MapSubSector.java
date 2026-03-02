/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapSubSector {

    public short numSegs;

    public short firstSeg;

    public MapSubSector(short numSegs, short firstSeg) {
        this.numSegs = numSegs;
        this.firstSeg = firstSeg;
    }

}
