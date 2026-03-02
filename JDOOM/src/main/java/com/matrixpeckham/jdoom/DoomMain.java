/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom;

import com.matrixpeckham.jdoom.d.Event;

import static com.matrixpeckham.jdoom.ArgV.checkParm;
import static com.matrixpeckham.jdoom.DoomDef.GameMission.DOOM;
import static com.matrixpeckham.jdoom.DoomDef.GameMode.COMMERCIAL;
import static com.matrixpeckham.jdoom.DoomDef.GameMode.INDETERMINED;
import static com.matrixpeckham.jdoom.DoomDef.Language.ENGLISH;

import com.matrixpeckham.jdoom.DoomDef.GameMission;
import com.matrixpeckham.jdoom.DoomDef.GameMode;
import com.matrixpeckham.jdoom.DoomDef.Language;
import com.matrixpeckham.jdoom.DoomDef.Skill;
import java.io.File;

/**
 *
 * @author matri
 */
public class DoomMain {

    WAD wad;

    Game game;

    Menu menu;

    public static final int MAX_WAD_FILES = 20;

    public static String[] wadfiles = new String[MAX_WAD_FILES];

    boolean devparm = false;

    boolean nomonsters = false;

    boolean respoawnparm = false;

    boolean fstparm = false;

    boolean drone = false;

    boolean singletics = false;//cancel adaptiveness

    //boolean inhelpscreens is in menus and will be referenced that way
    //Constructor replaces function.
    Skill startSkill;

    int startEpisode;

    int startMap;

    boolean autostart = false;

    File debugFile;

    boolean advanceDemo = false;

    String wadFile;//primary wad file

    String mapDir;//directory of development maps

    String baseDefault;//default file

    public Event[] events = new Event[Event.MAX_EVENTS];

    static GameMode gameMode = INDETERMINED;

    static GameMission gameMission = DOOM;

    static Language language = ENGLISH;

    static boolean modifiedGame = false;

    private int eventHead = 0;

    private int eventTail = 0;

    public void postEvent(Event e) {
        events[eventHead] = e;
        eventHead = (++eventHead) & (Event.MAX_EVENTS - 1);
    }

    public void processEvents() {
        if ((gameMode == COMMERCIAL) && (wad.checkNumForName("map01") < 0)) {
            return;
        }
        Event ev;
        for (; eventTail != eventHead; eventTail = (++eventTail)
                & (Event.MAX_EVENTS - 1)) {
            ev = events[eventTail];
            if (menu.responder(ev)) {
                continue;
            }
            game.responder(ev);
        }
    }

    public DoomMain() {

    }

    public void doomLoop() {
        if (game.demoRecording) {
            game.beginRecording();
        }
        if (checkParm("-debugfile") != 0) {
            String str = String.format("debug%d", game.consolePlayer);
        }
    }

    public void addFile(String file) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

}
