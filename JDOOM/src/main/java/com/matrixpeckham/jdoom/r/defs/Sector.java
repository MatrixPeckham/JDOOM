/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

import com.matrixpeckham.jdoom.d.Thinker;
import com.matrixpeckham.jdoom.p.MObj;

/**
 * The sectors record, at runtime
 * stores things/mobjs
 *
 * @author matri
 */
public class Sector {

    public int floorHeight, ceilingHeight;

    public short floorPic, ceilingPic;

    public short lightLevel;

    public short special;

    public short tag;

    //0=untraversed, 1,2-sndlines-1
    public int soundTraversed;

    //thing that made a sound or null
    public MObj soundTarget;

    //map block bounding box fo height changes
    public int[] blockBox = new int[4];

    //origin for any sounds played by the sector
    public DegenmObj soundOrg;

    // if == validcount, already checked
    public int validCound;

    //list of mobjs in secotr
    public MObj thingList;

    //thinker for reversable actions
    public Thinker specialData;

    public int lineCount;

    public Line[] lines;

}
