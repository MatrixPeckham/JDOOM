/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 * BSP node
 *
 * @author matri
 */
public class Node {

    //Partition line
    public int x, y, dx, dy;

    //bounding box for each child
    public int[][] bbox = new int[2][4];

    //if FN_Subsector it'sa subsector
    public int[] children = new int[2];

}
