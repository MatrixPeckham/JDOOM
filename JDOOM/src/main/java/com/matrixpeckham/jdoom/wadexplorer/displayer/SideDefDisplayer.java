/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.MapSideDef;
import com.matrixpeckham.jdoom.doomdata.SideDefList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class SideDefDisplayer extends JPanel {

    SideDefList ldlist;

    JList<MapSideDef> list;

    public SideDefDisplayer(SideDefList list) {
        this.ldlist = list;
        this.list = new JList<MapSideDef>(ldlist.toArray(new MapSideDef[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof MapSideDef)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                MapSideDef ent = (MapSideDef) value;
                return super.getListCellRendererComponent(list,
                        "Texture Offset: " + ent.textureOffset + " Row Offset:"
                        + ent.rowOffset + " Top:"
                        + ent.topTexture + " Middle:"
                        + ent.midTexture + " Bottom:" + ent.bottomTexture
                        + " Sector:"
                        + ent.sector,
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    MapSideDef val = SideDefDisplayer.this.list.
                            getSelectedValue();

                }
            }

        });
        JLabel label = new JLabel("Count: " + ldlist.size());
        JScrollPane scroll = new JScrollPane(this.list);
        scroll.setPreferredSize(new Dimension(200, 800));
        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 800));
        this.add(label, BorderLayout.NORTH);
        this.add(scroll);

    }

}
