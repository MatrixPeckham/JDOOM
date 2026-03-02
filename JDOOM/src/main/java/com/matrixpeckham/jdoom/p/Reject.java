/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.p;

import com.matrixpeckham.jdoom.WAD;
import com.matrixpeckham.jdoom.doomdata.SectorList;

/**
 *
 * @author matri
 */
public class Reject {

    private byte[] table;

    public int numSectors;

    public Reject(byte[] bytes, int sects) {
        table = bytes;
        numSectors = sects;
    }

    public boolean reject(int s1, int s2) {
        int pnum = s1 * numSectors + s2;
        int byteNum = pnum >> 3;
        int bitNum = 1 << (pnum & 7);
        return (table[byteNum] & bitNum) != 0;
    }

    public static Reject create(byte[] bytes, WAD.DirectoryEntry entry, WAD wad) {
        SectorList sectorList = (SectorList) wad.getLump(entry.index - 1);
        return new Reject(bytes, sectorList.size());
    }

}
