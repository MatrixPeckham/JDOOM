/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.r.PlayPal;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author matri
 */
public class PlayPalDisplayer extends JPanel {

    PlayPal palette;

    JLabel palLabel;

    JPanel display;

    public PlayPalDisplayer(PlayPal p) {
        palette = p;
        display = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                int wid = this.getWidth() / 16;
                int hig = this.getHeight() / 16;
                int y = 0;
                int i = 0;
                for (int yInd = 0; yInd < 16; yInd++) {
                    int x = 0;
                    for (int xInd = 0; xInd < 16; xInd++) {
                        g.setColor(palette.getColor(i));
                        g.fillRect(x, y, wid, hig);
                        x += wid;
                        i++;
                    }
                    y += hig;
                }
            }

        };
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BorderLayout());
        JPanel top = new JPanel();
        JButton next = new JButton("Next Palette");
        next.addActionListener(((e) -> {
            if (palette.getCurPalette() < palette.getNumPalettes() - 1) {
                palette.setCurPalette(palette.getCurPalette() + 1);
                palLabel.setText((palette.getCurPalette() + 1) + "/" + palette.
                        getNumPalettes());
                display.repaint();
            }
        }));
        palLabel = new JLabel("1/" + palette.getNumPalettes());
        JButton prev = new JButton("Previous Palette");
        prev.addActionListener(((e) -> {
            if (palette.getCurPalette() > 0) {
                palette.setCurPalette(palette.getCurPalette() - 1);
                palLabel.setText((palette.getCurPalette() + 1) + "/" + palette.
                        getNumPalettes());
                display.repaint();
            }
        }));
        top.add(prev);
        top.add(palLabel);
        top.add(next);
        add(top, BorderLayout.NORTH);
        add(display, BorderLayout.CENTER);
        repaint();
    }

}
