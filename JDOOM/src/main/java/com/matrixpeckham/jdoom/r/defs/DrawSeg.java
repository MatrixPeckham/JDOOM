/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 *
 * @author matri
 */
public class DrawSeg {

    public static final int SIL_NONE = 0;

    public static final int SIL_BOTTOM = 1;

    public static final int SIL_TOP = 2;

    public static final int SIL_BOTH = 3;

    public static final int MAX_DRAW_SEGS = 256;

    public Seg curLine;

    public int x1, x2;

    public int scale1, scale2, scaleStep;

    //0=none, 1=bottom, 2=top, 3=both
    public int silhouette;

    //do not clip sprites above this
    public int bSilHeight;

    //do not clip sprites Below this
    public int tSilHeight;

    //java doesn't have pointer arithmetic these
    //will not be pointers to lists with x1 as it's first value
    public short[] sprTopClip;

    public int sprTopOffset;

    public short[] sprBottomClip;

    public int sprBottomOffset;

    public short[] maskedTextureCol;

    public int maskedTextureOffset;

}
