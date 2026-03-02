/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.r.Flat;
import com.matrixpeckham.jdoom.r.PlayPal;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author matri
 */
public class FlatDisplayer extends JPanel {

    Flat pat;

    PlayPal pal;

    public FlatDisplayer(Flat patch, PlayPal pall) {
        pat = patch;
        pal = pall;
        JPanel shower = new JPanel() {

            @Override
            public void paint(Graphics g) {
                double width = getWidth() / (double) patch.width;
                double height = getHeight() / (double) patch.height;
                double mult = Math.min(width, height);

                int w = (int) (patch.width * mult);
                int h = (int) (patch.height * mult);

                BufferedImage img = new BufferedImage(patch.width, patch.height,
                        BufferedImage.TYPE_INT_ARGB);
                for (int row = 0; row < patch.width; row++) {
                    for (int col = 0; col < patch.height; col++) {
                        short sh = patch.data[row][col];
                        Color c;
                        if (sh == 0x100) {
                            c = new Color(255, 0, 255, 0);
                        } else {
                            c = pal.getColor(sh);
                        }
                        img.setRGB(row, col, c.getRGB());
                    }
                }

                g.drawImage(img, 0, 0, w, h, null);
            }

        };
        shower.setPreferredSize(new Dimension(patch.width, patch.height));
        JScrollPane scroll = new JScrollPane(shower);
        setLayout(new BorderLayout());
        this.add(scroll);
    }

}
