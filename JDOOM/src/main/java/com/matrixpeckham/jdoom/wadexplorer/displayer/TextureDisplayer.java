/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.Texture;
import com.matrixpeckham.jdoom.doomdata.TextureList;
import com.matrixpeckham.jdoom.r.PlayPal;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class TextureDisplayer extends JPanel {

    TextureList ldlist;

    PatchDisplayer displayer;

    JList<Texture> list;

    public TextureDisplayer(TextureList list, PlayPal pal) {
        this.ldlist = list;
        this.list = new JList<Texture>(ldlist.toArray(new Texture[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof Texture)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                Texture ent = (Texture) value;
                return super.getListCellRendererComponent(list,
                        ent.name,
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    Texture val = TextureDisplayer.this.list.
                            getSelectedValue();
                    displayer.setPatch(val.patch);
                }
            }

        });
        JLabel label = new JLabel("Count: " + ldlist.size());
        JScrollPane scroll = new JScrollPane(this.list);
        scroll.setPreferredSize(new Dimension(200, 800));
        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 800));
        this.add(label, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.WEST);
        displayer = new PatchDisplayer(null, pal);
        this.add(displayer, BorderLayout.CENTER);
    }

}
