/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapSector {

    public short floorHeight;

    public short ceilingHeight;

    public String floorPic;

    public String ceilingPic;

    public short lightLevel;

    public short special;

    public short tag;

    public MapSector(short floorHeight, short ceilingHeight, String floorPic,
            String ceilingPic, short lightLevel, short special, short tag) {
        this.floorHeight = floorHeight;
        this.ceilingHeight = ceilingHeight;
        this.floorPic = floorPic;
        this.ceilingPic = ceilingPic;
        this.lightLevel = lightLevel;
        this.special = special;
        this.tag = tag;
    }

}
