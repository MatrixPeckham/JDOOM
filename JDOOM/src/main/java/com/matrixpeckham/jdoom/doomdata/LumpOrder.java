/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

/**
 *
 * @author matri
 */
public enum LumpOrder {
    ML_LABEL,//separator, name ExMx or MAPxx
    ML_THINGS,//monsters/items
    ML_LINEDEFS,//linedefs
    ML_SIDEDEFS,//sidedefs
    ML_VERTEXES,//vertices
    ML_SEGS,//line segments
    ML_SSECTORS,//subsectors
    ML_NODES,//BSP Nodes
    ML_SECTORS,//Secotrs
    ML_REJECT,//visibility LUT
    ML_BLOCKMAP//collision LUT

}
