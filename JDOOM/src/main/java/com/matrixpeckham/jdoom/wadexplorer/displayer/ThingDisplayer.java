/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.MapThing;
import com.matrixpeckham.jdoom.doomdata.ThingList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class ThingDisplayer extends JPanel {

    ThingList ldlist;

    JList<MapThing> list;

    public ThingDisplayer(ThingList list) {
        this.ldlist = list;
        this.list = new JList<MapThing>(ldlist.toArray(new MapThing[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof MapThing)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                MapThing ent = (MapThing) value;
                return super.getListCellRendererComponent(list,
                        "X:" + ent.x + " Y:" + ent.y + " Angle:"
                        + ent.angle + " Type:" + ent.type
                        + " Options:" + ent.options,
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    MapThing val = ThingDisplayer.this.list.
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
