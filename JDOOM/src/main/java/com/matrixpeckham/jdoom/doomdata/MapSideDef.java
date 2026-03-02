/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapSideDef {

    public short textureOffset;

    public short rowOffset;

    public String topTexture;

    public String bottomTexture;

    public String midTexture;

    public short sector;

    public MapSideDef(short tx, short ty, String top, String bottom, String mid,
            short sect) {
        textureOffset = tx;
        rowOffset = ty;
        topTexture = top;
        bottomTexture = bottom;
        midTexture = mid;
        sector = sect;
    }

}
