/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.MapSeg;
import com.matrixpeckham.jdoom.doomdata.SegList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class SegDisplayer extends JPanel {

    SegList ldlist;

    JList<MapSeg> list;

    public SegDisplayer(SegList list) {
        this.ldlist = list;
        this.list = new JList<MapSeg>(ldlist.toArray(new MapSeg[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof MapSeg)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                MapSeg ent = (MapSeg) value;
                return super.getListCellRendererComponent(list,
                        "V1:" + ent.v1 + " V2:" + ent.v2 + " Angle:"
                        + ent.angle + " LineDef:" + ent.linedef
                        + " Direction:" + ent.side + " Offset:" + ent.offset,
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    MapSeg val = SegDisplayer.this.list.
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
