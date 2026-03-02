/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 * VisSprite is a thing
 * that will be drawn during a refresh
 * I.e. a sprite object that is partly visible.
 *
 * @author matri
 */
public class VisSprite {

    //doubly linked list
    public VisSprite next, prev;

    public int x1, x2;

    //for line side calculation
    public int gx, gy;

    //global bottom/top for silhoutte clipping
    public int gz, gzt;

    //horizontal position of x1
    public int startFrac, scale;

    //negative if flipped
    public int xiscale;

    public int textureMid;

    public int patch;

    //for color translation and shadow draw,
    //maxbright frames as well
    //TODO: is a byte pointer in c, not sure how to translate that here;
    LightTable colorMap;

    public int mObjFlags;

}
