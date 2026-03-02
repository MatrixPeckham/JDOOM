/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.s.SoundEffect;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author matri
 */
public class SoundEffectDisplayer extends JPanel {

    SoundEffect sound;

    public SoundEffectDisplayer(SoundEffect ss) {
        sound = ss;

        addHierarchyListener(new HierarchyListener() {

            public void hierarchyChanged(HierarchyEvent e) {
                if (e.getID() == HierarchyEvent.HIERARCHY_CHANGED
                        && (e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED)
                        != 0) {
                    if (sound != null) {
                        sound.stop();
                    }
                }
            }

        });

        JButton play = new JButton("Play");
        play.addActionListener(a -> sound.start());
        add(play);
        JButton stop = new JButton("Stop");
        stop.addActionListener(a -> sound.stop());
        add(stop);

    }

}
