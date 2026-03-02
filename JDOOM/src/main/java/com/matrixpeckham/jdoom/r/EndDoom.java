/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.r;

/**
 *
 * @author matri
 */
public class EndDoom {

    public String string;

    public byte[] colors;

    public static EndDoom create(byte[] bytes) {
        EndDoom end = new EndDoom();
        byte[] str = new byte[bytes.length / 2];
        end.colors = new byte[bytes.length / 2];
        int ind = 0;
        for (int i = 0; i < bytes.length - 1; i += 2) {
            end.colors[ind] = bytes[i + 1];
            str[ind] = bytes[i];
            ind++;
        }
        end.string = new String(str);
        return end;
    }

}
