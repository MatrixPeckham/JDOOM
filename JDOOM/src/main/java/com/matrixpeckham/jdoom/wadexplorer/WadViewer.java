/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer;

import com.matrixpeckham.jdoom.WAD;
import com.matrixpeckham.jdoom.WAD.DirectoryEntry;
import com.matrixpeckham.jdoom.doomdata.*;
import com.matrixpeckham.jdoom.p.BlockMap;
import com.matrixpeckham.jdoom.p.Reject;
import com.matrixpeckham.jdoom.r.*;
import com.matrixpeckham.jdoom.s.Song;
import com.matrixpeckham.jdoom.s.SoundEffect;
import com.matrixpeckham.jdoom.wadexplorer.displayer.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author matri
 */
public class WadViewer extends JFrame implements ActionListener,
        ListSelectionListener {

    WAD wad;

    JList<WAD.DirectoryEntry> lumpList;

    JPanel curViewer = new JPanel();

    JPanel defaultDisplayer = new JPanel();

    JLabel defaultDisplayerLabel = new JLabel();

    public WadViewer() {
        File prevItem = new File("lastFileName.txt");
        if (prevItem.exists()) {
            try {
                Scanner s = new Scanner(prevItem);
                String name = "";
                while (s.hasNext()) {
                    name += s.next() + " ";
                }
                File wadfile = new File(name.trim());
                if (wadfile.exists()) {
                    wad = new WAD(wadfile);
                    initUI();
                } else {
                    System.exit(0);
                }
            } catch (IOException e) {
                System.exit(0);
            }
        } else {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION) {
                System.exit(0);
            }
            wad = new WAD(chooser.getSelectedFile());
            try {
                PrintWriter writer = new PrintWriter(prevItem);
                writer.print(chooser.getSelectedFile().getAbsolutePath());
                writer.close();
            } catch (IOException e) {

            }
            initUI();
        }
    }

    public WadViewer(File f) {
        wad = new WAD(f);
        initUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (lumpList.getSelectedIndex() != -1) {
                displayLump(lumpList.getSelectedValue());
            }
        }
    }

    private void displayLump(DirectoryEntry selectedValue) {
        Object lump = wad.getLump(selectedValue);
        defaultDisplayerLabel.setText(selectedValue.name);
        JPanel displayer = defaultDisplayer;
        switch (lump) {
            case null:
                break;//throw new IllegalArgumentException("Null display lump");
            case PlayPal p:
                displayer = new PlayPalDisplayer(p);
                break;
            case ColorMap c:
                displayer = new ColorMapDisplayer(c, (PlayPal) wad.getLump(
                        "PLAYPAL\0"));
                break;
            case EndDoom ed:
                displayer = new EndDoomDisplayer(ed);
                break;
            case VertexList vd:
                displayer = new VertexesDisplayer(vd);
                MapViewer.get().showVertices(vd);
                if (!MapViewer.get().isVisible()) {
                    MapViewer.get().setVisible(true);
                }
                break;
            case LineDefList ld:
                displayer = new LineDefDisplayer(ld);
                MapViewer.get().showLineDefs(ld, selectedValue);
                if (!MapViewer.get().isVisible()) {
                    MapViewer.get().setVisible(true);
                }
                break;
            case SideDefList sd:
                displayer = new SideDefDisplayer(sd);
                break;
            case SectorList sl:
                displayer = new SectorDisplayer(sl);
                break;
            case SubSectorList ssl:
                displayer = new SubSectorDisplayer(ssl);
                break;
            case SegList segl:
                displayer = new SegDisplayer(segl);
                break;
            case ThingList tl:
                displayer = new ThingDisplayer(tl);
                break;
            case NodeList nl:
                displayer = new NodeDisplayer(nl);
                break;
            case BlockMap bm:
                displayer = new BlockMapDisplayer(bm, selectedValue);
                break;
            case Reject rej:
                displayer = new RejectDisplayer(rej);
                break;
            case PNames pna:
                displayer = new PNameDisplayer(pna);
                break;
            case Patch patch:
                displayer = new PatchDisplayer(patch, (PlayPal) wad.getLump(
                        "PLAYPAL\0"));
                break;
            case Flat flat:
                displayer = new FlatDisplayer(flat, (PlayPal) wad.getLump(
                        "PLAYPAL\0"));
                break;
            case TextureList textureList:
                displayer = new TextureDisplayer(textureList, (PlayPal) wad.
                        getLump(
                                "PLAYPAL\0"));
                break;
            case SoundEffect sound:
                displayer = new SoundEffectDisplayer(sound);
                break;
            case Song song:
                displayer = new SongDisplayer(song);
            default:
                break;

        }
        remove(curViewer);
        curViewer = displayer;
        add(displayer, BorderLayout.CENTER);
        validate();
        curViewer.repaint();
    }

    private void initUI() {
        setTitle("WAD Viewer " + wad.lumps + " Lumps");
        lumpList = new JList<WAD.DirectoryEntry>(
                wad.getDirectory().toArray(
                        new WAD.DirectoryEntry[0])
        );
        lumpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lumpList.addListSelectionListener(this);
        lumpList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                if (!(value instanceof WAD.DirectoryEntry)) {
                    return super.
                            getListCellRendererComponent(list, value, index,
                                    isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                }
                WAD.DirectoryEntry ent = (WAD.DirectoryEntry) value;
                return super.getListCellRendererComponent(list, ent.name
                        + " Size: " + ent.length + " Offset: " + Long.
                                toHexString(ent.offset), index,
                        isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        });
        JScrollPane scroll = new JScrollPane(lumpList);
        scroll.setPreferredSize(new Dimension(500, 800));
        defaultDisplayer.add(new JLabel("Unable to display lump: "));
        defaultDisplayer.add(defaultDisplayerLabel);
        defaultDisplayer.setPreferredSize(new Dimension(200, 800));
        this.add(scroll, BorderLayout.WEST);
        curViewer.setPreferredSize(new Dimension(200, 800));
        this.add(curViewer, BorderLayout.CENTER);
        MapViewer.get(wad).setVisible(true);
    }

}
