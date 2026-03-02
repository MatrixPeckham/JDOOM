/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.r.ColorMap;
import com.matrixpeckham.jdoom.r.PlayPal;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author matri
 */
public class ColorMapDisplayer extends JPanel {

    ColorMap colormap;

    PlayPal pal;

    JLabel palLabel;

    JPanel display;

    public ColorMapDisplayer(ColorMap map, PlayPal p) {
        colormap = map;
        pal = p;
        display = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                int wid = this.getWidth() / colormap.getNumMaps();
                double hig = this.getHeight() / 256.0;
                double y = 0;
                int i = 0;
                for (int yInd = 0; yInd < 256; yInd++) {
                    int x = 0;
                    for (int xInd = 0; xInd < colormap.getNumMaps(); xInd++) {
                        g.setColor(pal.getColor(map.map(xInd, i)));
                        g.fillRect(x, (int) (y - 0.5), wid, (int) (hig + 0.5));
                        x += wid;
                    }
                    i++;
                    y += hig;
                }
            }

        };
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
        repaint();
    }

}
