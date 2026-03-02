/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.doomdata.MapVertex;
import com.matrixpeckham.jdoom.doomdata.VertexList;
import com.matrixpeckham.jdoom.wadexplorer.MapViewer;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class VertexesDisplayer extends JPanel {

    VertexList vlist;

    JList<MapVertex> list;

    public VertexesDisplayer(VertexList list) {
        this.vlist = list;

        this.list = new JList<MapVertex>(vlist.toArray(new MapVertex[0]));
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof MapVertex)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                MapVertex ent = (MapVertex) value;
                return super.getListCellRendererComponent(list, "X: " + ent.x
                        + " Y:" + ent.y, index,
                        isSelected, cellHasFocus);
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    MapVertex val = VertexesDisplayer.this.list.
                            getSelectedValue();
                    MapViewer.get().showVertex(val);

                }
            }

        });
        JLabel label = new JLabel("Count: " + vlist.size());
        JScrollPane scroll = new JScrollPane(this.list);
        scroll.setPreferredSize(new Dimension(200, 800));
        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 800));
        this.add(label, BorderLayout.NORTH);
        this.add(scroll);

    }

}
