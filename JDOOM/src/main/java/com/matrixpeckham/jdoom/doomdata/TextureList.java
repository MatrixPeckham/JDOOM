/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.doomdata;

import com.matrixpeckham.jdoom.WAD;
import com.matrixpeckham.jdoom.r.Patch;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author matri
 */
public class TextureList extends ArrayList<Texture> {

    HashMap<String, Integer> lookup = new HashMap<>();

    public Texture get(String name) {
        if (!lookup.containsKey(name)) {
            return get(lookup.get(name));
        }
        return null;
    }

    @Override
    public boolean add(Texture e) {
        lookup.put(e.name, size());
        return super.add(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public static TextureList create(byte[] bytes, WAD wad) {
        TextureList ret = new TextureList();
        int i = 0;
        int count = 0;
        count |= (bytes[i++] & 0xFF);
        count |= (bytes[i++] & 0xFF) << 8;
        count |= (bytes[i++] & 0xFF) << 16;
        count |= (bytes[i++] & 0xFF) << 24;

        for (int ind = 0; ind < count; ind++) {
            int offset = 0;
            offset |= (bytes[i++] & 0xFF);
            offset |= (bytes[i++] & 0xFF) << 8;
            offset |= (bytes[i++] & 0xFF) << 16;
            offset |= (bytes[i++] & 0xFF) << 24;
            addTexture(ret, bytes, offset, wad);
        }

        return ret;
    }

    private static void addTexture(TextureList ret, byte[] bytes, int i, WAD wad) {
        String name = new String(bytes, i, 8).toUpperCase();
        i += 8;
        //skip padding
        i += 4;
        short width = 0;
        width |= bytes[i++] & 0xFF;
        width |= (short) ((bytes[i++] & 0xFF) << 8);

        short height = 0;
        height |= bytes[i++] & 0xFF;
        height |= (short) ((bytes[i++] & 0xFF) << 8);

        Patch texture = new Patch(width, height);

        //skip padding
        i += 4;

        short patchCount = 0;
        patchCount |= bytes[i++] & 0xFF;
        patchCount |= (short) ((bytes[i++] & 0xFF) << 8);

        for (int ind = 0; ind < patchCount; ind++) {
            i = addPatch(texture, bytes, i, wad);
        }

        Texture text = new Texture();
        text.name = name;
        text.patch = texture;
        ret.add(text);

    }

    private static int addPatch(Patch texture, byte[] bytes, int i, WAD wad) {
        short xOffset = 0;
        xOffset |= bytes[i++] & 0xFF;
        xOffset |= (short) ((bytes[i++] & 0xFF) << 8);
        short yOffset = 0;
        yOffset |= bytes[i++] & 0xFF;
        yOffset |= (short) ((bytes[i++] & 0xFF) << 8);

        int patchNum = 0;
        patchNum |= bytes[i++] & 0xFF;
        patchNum |= (short) ((bytes[i++] & 0xFF) << 8);

        PNames nameLookup = (PNames) wad.getLump("PNAMES\0\0");
        String patchName = nameLookup.get(patchNum);

        Patch p = (Patch) wad.getLump(patchName);

        blitPatch(texture, p, xOffset, yOffset);

        //skip padding
        i += 4;

        return i;
    }

    private static void blitPatch(Patch dest, Patch src, short xo, short yo) {
        for (int x = 0; x < src.width; x++) {
            for (int y = 0; y < src.height; y++) {
                if (x + xo >= 0 && x + xo < dest.width) {
                    if (y + yo >= 0 && y + yo < dest.height) {
                        short d = src.data[x][y];
                        if (d != 0x100) {
                            dest.data[x + xo][y + yo] = d;
                        }
                    }
                }
            }
        }
    }

    public void appendLump(byte[] bytes, WAD wad) {
        TextureList next = create(bytes, wad);
        addAll(next);
    }

}
