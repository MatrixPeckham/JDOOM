/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.matrixpeckham.jdoom;

import com.matrixpeckham.jdoom.wadexplorer.WadViewer;
import javax.swing.JFrame;

/**
 *
 * @author matri
 */
public class JDOOM {

    public static void main(String[] args) {
        WadViewer hex = new WadViewer();
        hex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hex.pack();
        hex.setVisible(true);
        ArgV.myargv = args;
//        new DoomMain().doomLoop();
    }

}
