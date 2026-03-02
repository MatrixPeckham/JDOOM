/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom;

/**
 *
 * @author matri
 */
public class ArgV {

    public static String[] myargv = null;

    //Checks for the parameter in the command line arguments.
    //returns 0 if not found
    public static int checkParm(String check) {
        if (myargv == null) {
            return 0;
        }
        int i;
        for (i = 1; i < myargv.length; i++) {
            if (check.equalsIgnoreCase(myargv[i])) {
                return i;
            }
        }
        return 0;
    }

}
