/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.p.Reject;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author matri
 */
public class RejectDisplayer extends JPanel {

    Reject ed;

    public RejectDisplayer(Reject e) {
        ed = e;
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        text.append("<table>");
        int count = ed.numSectors;
        text.append("<tr>");
        text.append("<th></th>");
        for (int i = 0; i < count; i++) {
            text.append("<th>");
            text.append(i);
            text.append("</th>");
        }
        text.append("</tr>");
        for (int row = 0; row < count; row++) {
            text.append("<tr><th>");
            text.append(row);
            text.append("</th>");
            for (int col = 0; col < count; col++) {
                text.append("<td>");
                text.append(ed.reject(row, col) ? "1" : "0");
                text.append("</td>");
            }
            text.append("</tr>");
        }
        text.append("</table>");
        text.append("</html>");
        textPane.setText(text.toString());
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(200, 800));
        this.add(scrollPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(200, 800));

    }

    Color[] lookup = new Color[]{
        Color.BLACK,
        new Color(0, 0, 128),
        new Color(0, 128, 0),
        new Color(0, 128, 128),
        new Color(128, 0, 0),
        new Color(128, 0, 128),
        new Color(128, 128, 0),
        new Color(200, 200, 200),
        new Color(128, 128, 128),
        new Color(0, 0, 255),
        new Color(0, 255, 0),
        new Color(0, 255, 255),
        new Color(255, 0, 0),
        new Color(255, 0, 255),
        new Color(255, 255, 0),
        Color.WHITE
    };

    public Color getForegroundColorForByte(byte b) {

        return lookup[b & 0xF];

    }

    public Color getBackgroundColorForByte(byte b) {

        return lookup[(b >> 4) & 0b0111];

    }

}
