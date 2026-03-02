/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.MapSubSector;
import com.matrixpeckham.jdoom.doomdata.SubSectorList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class SubSectorDisplayer extends JPanel {

    SubSectorList ldlist;

    JList<MapSubSector> list;

    public SubSectorDisplayer(SubSectorList list) {
        this.ldlist = list;
        this.list = new JList<MapSubSector>(ldlist.toArray(new MapSubSector[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof MapSubSector)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                MapSubSector ent = (MapSubSector) value;
                return super.getListCellRendererComponent(list,
                        "Count: " + ent.numSegs + " First Segment:"
                        + ent.firstSeg,
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    MapSubSector val = SubSectorDisplayer.this.list.
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
