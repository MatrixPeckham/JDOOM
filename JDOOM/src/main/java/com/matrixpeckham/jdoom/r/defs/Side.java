/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 *
 * @author matri
 */
public class Side {

    //add this to the calculated texture column
    public int textureOffset;

    //add this to the calculated texture top
    public int rowOffset;

    //texture indices
    //don't use names here
    public short topTexture;

    public short bottomTexture;

    public short midTexture;

    //Sector this is facing
    public Sector sector;

}
