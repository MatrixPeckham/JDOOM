/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 * Sibsector references a sector
 * basically, this is a list of linesegs indicating the visible
 * walls that define all or some sides of a convex bsp leaf
 *
 * @author matri
 */
public class SubSector {

    public Sector sector;

    public short numLines;

    public short firstLine;

}
