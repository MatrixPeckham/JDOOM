/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.d.player;

import static com.matrixpeckham.jdoom.DoomDef.MAX_PLAYERS;

/**
 *
 * @author matri
 */
public class WBStart {

    public int epsd; //episode # 0-2

    //if true splash the secret level
    public boolean didSecret;

    //previous and next levels, origin 0
    public int last;

    public int next;

    ////

    public int maxKills;

    public int maxItems;

    public int maxSecret;

    public int maxFrags;

    //the par time
    public int parTime;

    //index of this player in the game
    public int pnum;

    public WBPlayer[] plyr = new WBPlayer[MAX_PLAYERS];

}
