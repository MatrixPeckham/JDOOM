/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.r.EndDoom;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;

/**
 *
 * @author matri
 */
public class EndDoomDisplayer extends JPanel {

    EndDoom ed;

    public EndDoomDisplayer(EndDoom e) {
        ed = e;
        JTextPane textPane = new JTextPane();
        textPane.setEditorKit(new StyledEditorKit() {

            @Override
            public ViewFactory getViewFactory() {
                return new ViewFactory() {

                    public View create(Element elem) {
                        String kind = elem.getName();
                        if (kind != null) {
                            if (kind.equals(AbstractDocument.ContentElementName)) {
                                return new LabelView(elem);
                            } else if (kind.equals(
                                    AbstractDocument.ParagraphElementName)) {
                                return new ParagraphView(elem) {

                                    @Override
                                    public int getBreakWeight(int axis,
                                            float len) {
                                        return View.ExcellentBreakWeight;
                                    }

                                };
                            } else if (kind.equals(
                                    AbstractDocument.SectionElementName)) {
                                return new BoxView(elem, View.Y_AXIS);
                            } else if (kind.equals(
                                    StyleConstants.ComponentElementName)) {
                                return new ComponentView(elem);
                            } else if (kind.equals(
                                    StyleConstants.IconElementName)) {
                                return new IconView(elem);
                            }
                        }

                        // default to text display
                        return new LabelView(elem);
                    }

                };

            }

        });
        StyledDocument doc = textPane.getStyledDocument();
        String[] splitString = ed.string.split("");
        Style def = StyleContext.getDefaultStyleContext().getStyle(
                StyleContext.DEFAULT_STYLE);
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(regular, "Monospaced");

        for (int i = 0; i < splitString.length; i++) {
            Style s = doc.addStyle("i" + i, regular);
            StyleConstants.setForeground(s, getForegroundColorForByte(
                    e.colors[i]));
            StyleConstants.setBackground(s, getBackgroundColorForByte(
                    e.colors[i]));
            try {
                doc.insertString(doc.getLength(), splitString[i], s);
            } catch (BadLocationException ex) {
                Logger.getLogger(EndDoomDisplayer.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
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
