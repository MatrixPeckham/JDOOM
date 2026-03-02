/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

import com.matrixpeckham.jdoom.d.Thinker;

/**
 *
 * @author matri
 */
public class Line {

    // Vertices from v1 to v2;
    public Vertex v1;

    public Vertex v2;

    //precalculated v2-v1 for side checking
    public int dx;

    public int dy;

    //Animation related
    public short flags;

    public short special;

    public short tag;

    //Visual appearance: sidedefs
    // sideNum[1] will be -1 if one sided
    public short[] sideNum = new short[2];

    //Neat. another bounding box, for the extend of the linedef
    public int[] bbox = new int[4];

    //to aid in move clipping
    //front and back sector
    //note: redundant can be retrieved from sidedefs
    public Sector frontSector, backSector;

    //if == validcount, already checked
    public int validCount;

    //thinker for reversable actions
    public Thinker specialData;

}
