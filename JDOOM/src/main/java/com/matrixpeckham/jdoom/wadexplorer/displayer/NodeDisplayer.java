/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.MapNode;
import com.matrixpeckham.jdoom.doomdata.NodeList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class NodeDisplayer extends JPanel {

    NodeList ldlist;

    JList<MapNode> list;

    public NodeDisplayer(NodeList list) {
        this.ldlist = list;
        this.list = new JList<MapNode>(ldlist.toArray(new MapNode[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof MapNode)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                MapNode ent = (MapNode) value;
                return super.getListCellRendererComponent(list,
                        "<html>X:" + ent.x + " Y:" + ent.y + " DX:"
                        + ent.dx + " DY:" + ent.dy + "<br>"
                        + "Right BB: (" + ent.bbox[0][2] + "," + ent.bbox[0][1]
                        + ")-(" + ent.bbox[0][3] + "," + ent.bbox[0][0]
                        + ")<br>"
                        + "Left BB: (" + ent.bbox[1][2] + "," + ent.bbox[1][1]
                        + ")-(" + ent.bbox[1][3] + "," + ent.bbox[1][0]
                        + ")<br>"
                        + " Right: " + Integer.toHexString(ent.children[0])
                        + " Left: " + Integer.toHexString(ent.children[1])
                        + "</html>",
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    MapNode val = NodeDisplayer.this.list.
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
