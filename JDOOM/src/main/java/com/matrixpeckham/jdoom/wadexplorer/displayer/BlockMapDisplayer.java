/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.WAD;
import com.matrixpeckham.jdoom.p.BlockMap;
import com.matrixpeckham.jdoom.wadexplorer.MapViewer;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class BlockMapDisplayer extends JPanel {

    BlockMap ldlist;

    JList<Integer> list;

    public BlockMapDisplayer(BlockMap blockmap, WAD.DirectoryEntry entry) {
        this.ldlist = blockmap;
        Integer[] li = new Integer[ldlist.blocklist.length];
        for (int i = 0; i < li.length; i++) {
            li[i] = i;
        }
        this.list = new JList<>(li);
        this.list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof Integer)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus);

                }
                int i = (Integer) value;
                return super.getListCellRendererComponent(list,
                        i + " Row: " + i / ldlist.cols + " Col: " + i
                        % ldlist.cols,
                        index, isSelected, cellHasFocus
                );
            }

        });
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    Integer val = BlockMapDisplayer.this.list.
                            getSelectedValue();
                    MapViewer.get().showBlock(ldlist, val, entry);
                }
            }

        });
        JLabel label = new JLabel("Count: " + ldlist.blocklist.length
                + " Rows: " + ldlist.rows + " Cols: " + ldlist.cols);
        JScrollPane scroll = new JScrollPane(this.list);
        scroll.setPreferredSize(new Dimension(200, 800));
        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 800));
        this.add(label, BorderLayout.NORTH);
        this.add(scroll);

    }

}
