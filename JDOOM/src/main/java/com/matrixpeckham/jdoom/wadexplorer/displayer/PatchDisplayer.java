/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.r.Patch;
import com.matrixpeckham.jdoom.r.PlayPal;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author matri
 */
public class PatchDisplayer extends JPanel {

    Patch pat;

    PlayPal pal;

    JPanel shower;

    public PatchDisplayer(Patch patc, PlayPal pall) {
        pat = patc;
        pal = pall;
        shower = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (pat == null) {
                    return;
                }

                double width = getWidth() / (double) pat.width;
                double height = getHeight() / (double) pat.height;
                double mult = Math.min(width, height);

                int w = (int) (pat.width * mult);
                int h = (int) (pat.height * mult);

                BufferedImage img = new BufferedImage(pat.width, pat.height,
                        BufferedImage.TYPE_INT_ARGB);
                for (int row = 0; row < pat.width; row++) {
                    for (int col = 0; col < pat.height; col++) {
                        short sh = pat.data[row][col];
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
        if (patc != null) {
            shower.setPreferredSize(new Dimension(patc.width, patc.height));
        }
        JScrollPane scroll = new JScrollPane(shower);
        setLayout(new BorderLayout());
        this.add(scroll);
    }

    public void setPatch(Patch p) {
        this.pat = p;
        if (pat != null) {
            shower.setPreferredSize(new Dimension(p.width, p.height));
        }
        repaint();
    }

}
