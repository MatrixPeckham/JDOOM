/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 * Posts are runs of non masked source pixels
 *
 * @author matri
 */
public class Post {

    public byte topDelta; //-1 is the last post in a column

    public byte length;

    public byte[] payload;

}
