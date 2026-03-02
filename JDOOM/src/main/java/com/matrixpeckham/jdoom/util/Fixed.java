/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.util;

/**
 *
 * @author matri
 */
public class Fixed {

    public static final int FRAC_BITS = 16;

    public static final int FRAC_UNIT = (1 << FRAC_BITS);

    public static int fixedMul(int a, int b) {
        return (int) ((long) a * (long) b) >> FRAC_BITS;
    }

    public static int fixedDiv(int a, int b) {
        if ((Math.abs(a) >> 14) >= Math.abs(b)) {
            return (a ^ b) < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return fixedDiv2(a, b);
    }

    public static int fixedDiv2(int a, int b) {
        long c = ((long) a << 16) / (long) b;
        return (int) c;
    }

}
