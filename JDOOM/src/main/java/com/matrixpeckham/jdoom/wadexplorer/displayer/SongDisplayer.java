/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.wadexplorer.displayer;

import com.matrixpeckham.jdoom.s.DavidMusicModule;
import com.matrixpeckham.jdoom.s.Song;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author matri
 */
public class SongDisplayer extends JPanel {

    Song sound;

    DavidMusicModule music = DavidMusicModule.getInstance();

    public SongDisplayer(Song ss) {
        sound = ss;

        addHierarchyListener(new HierarchyListener() {

            public void hierarchyChanged(HierarchyEvent e) {
                if (e.getID() == HierarchyEvent.HIERARCHY_CHANGED
                        && (e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED)
                        != 0) {
                    if (sound != null) {
                        music.StopSong(0);
                    }
                }
            }

        });

        JButton play = new JButton("Play");
        play.addActionListener(a -> {
            music.setSong(sound);
            music.PlaySong(0, false);
        });
        add(play);
        JButton stop = new JButton("Stop");
        stop.addActionListener(a -> music.StopSong(0));
        add(stop);

    }

}
