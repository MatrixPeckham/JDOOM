/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 *
 * @author matri
 */
public class Seg {

    public Vertex v1;

    public Vertex v2;

    public int offset;

    public int angle;

    public Side sideDef;

    public Line lineDef;

    //sector references
    //could be retrieved from linedef too
    //backsector is null for one sided lines
    public Sector frontSector;

    public Sector backSector;

}
