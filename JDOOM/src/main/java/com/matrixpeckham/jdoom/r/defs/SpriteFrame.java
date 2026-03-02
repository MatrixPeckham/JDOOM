/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r.defs;

/**
 * // Sprites are patches with a special naming convention
 * // so they can be recognized by R_InitSprites.
 * // The base name is NNNNFx or NNNNFxFx, with
 * // x indicating the rotation, x = 0, 1-7.
 * // The sprite and frame specified by a thing_t
 * // is range checked at run time.
 * // A sprite is a patch_t that is assumed to represent
 * // a three dimensional object and may have multiple
 * // rotations pre drawn.
 * // Horizontal flipping is used to save space,
 * // thus NNNNF2F5 defines a mirrored patch.
 * // Some sprites will only have one picture used
 * // for all views: NNNNF0
 */
public class SpriteFrame {

    //if false use 0 for any position.
    //note: as eight entries are available,
    //we might as well insert the same name eight times.
    public boolean rotate;

    //lump to use view angles 0-7.
    public short[] lump = new short[8];

    //flip bit (1=flip) to use for view angles 0-7
    byte[] flip = new byte[8];

}
