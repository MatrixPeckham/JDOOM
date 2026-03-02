/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.p;

import com.matrixpeckham.jdoom.d.Thinker;

/**
 *
 * @author matri
 */
public class Tick {

    public static int levelTime = 0;

    public static void removeThinker(Thinker thinker) {
        thinker.remove();
    }

}
