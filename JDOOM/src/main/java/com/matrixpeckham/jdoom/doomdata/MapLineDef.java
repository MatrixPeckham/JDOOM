/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public class MapLineDef {

    //solid
    public static final int ML_BLOCKING = 1;

    //blocks only monsters
    public static final int ML_BLOCKMONSTERS = 2;

    //Backside will not be present at all if not two sided
    public static final int ML_TWOSIDED = 4;

    //if a texture is pegged, the texture will have
    //the end exposed to air held constant at the
    //top or bottom of the texture (stairs or pulled
    //down things) and will move with a height change
    //of one of the neighbor sectors.
    //Unpegged textures allways have the first row of
    //the texture at the top pixel of the line for both
    //top and bottom textures (use next to windows).
    //upper texture unpegged
    public static final int ML_DONTPEGTOP = 8;

    //lower texture unpegged
    public static final int ML_DONTPEGBOTTOM = 16;

    //in automap don't map as two sided it's a secret
    public static final int ML_SECRET = 32;

    //sound rendering don't let sound cross two of these
    public static final int ML_SOUNDBLOCK = 64;

    //don't draw on automap at all
    public static final int ML_DONTDRAW = 128;

    //set if already seen thus drawn in automap
    public static final int ML_MAPPED = 256;

    public short v1, v2, flags, special, tag;

    public short[] sidenum = new short[2];

    public MapLineDef(short v1, short v2, short flags, short special, short tag,
            short side1, short side2) {
        this.v1 = v1;
        this.v2 = v2;
        this.flags = flags;
        this.special = special;
        this.tag = tag;
        this.sidenum[0] = side1;
        this.sidenum[1] = side2;
    }

}
