/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom;

import static com.matrixpeckham.jdoom.DoomDef.MAX_PLAYERS;
import static com.matrixpeckham.jdoom.d.Net.BACKUP_TICS;

import com.matrixpeckham.jdoom.DoomDef.GameState;
import com.matrixpeckham.jdoom.DoomDef.Skill;
import com.matrixpeckham.jdoom.d.Event;
import com.matrixpeckham.jdoom.d.Event.GameAction;
import com.matrixpeckham.jdoom.d.player.Player;
import com.matrixpeckham.jdoom.d.player.WBStart;

/**
 *
 * @author matri
 */
public class Game {

    public static final int SAVE_GAME_SIZE = 0x2c000;

    public static final int SAVE_STRING_SIZE = 24;

    ////

    public GameAction gameAction;

    public GameState gameState;

    public Skill gameSkill;

    public boolean respawnMonsters;

    public int gameEpisode;

    public int gameMap;

    ////

    public boolean paused;

    public boolean sendPause;//send a pause event next tic

    public boolean sendSave;//send a save event next tic

    public boolean userGame;//ok to save/end game

    ////

    public boolean timingDemo;//if true exit with report on completion

    public boolean noDrawers;//for comparative timing purposes

    public boolean noBlit;//for comparitive timing purposes

    public int starTime;//for comparative timing purposes

    ////

    public boolean viewActive;

    ////

    public boolean deathMatch;//only if started as et deathmatch

    public boolean netGame;//only true if packets are broadcast

    public boolean[] playerInGame = new boolean[MAX_PLAYERS];

    public Player[] players = new Player[MAX_PLAYERS];

    ////

    public int consolePlayer;//player taking events and displaying

    public int displayPlayer;//view being displayed

    public int gameTic;

    public int levelStartTic;//gametic at level start

    //for intermission screen
    public int totalKills;

    public int totalItems;

    public int totalSecret;

    ////

    public String demoName;

    public boolean demoRecording;

    public boolean demoPlayback;

    public boolean netDemo;

    //these were originally pointers, demo_p and demoEnd will probably need to
    //be changed to indices
    byte[] demoBuffer;

    byte[] demo_p;

    byte[] demoEnd;

    boolean singleDemo;//quit after playing a demo from command line

    ////

    public boolean precache = true; //if true load all graphics at start

    ////

    WBStart wmInfo;//parms for world map / intermission

    ////

    public short consistancy[][] = new short[MAX_PLAYERS][BACKUP_TICS];

    ////

    byte[] saveBuffer;

    ////
    ///controls have defaults
    ///
    public int keyRight;

    public int keyLeft;

    public int keyUp;

    public int keyDown;

    public int keyStrafeLeft;

    public int keyStrafeRight;

    public int keyUse;

    public int keyStrafe;

    public int keySpeed;

    public int mouseBFire;

    public int mouseBStrafe;

    public int mouseBForward;

    public int joyBFire;

    public int joyBStrafe;

    public int joyBUse;

    public int joyBSpeed;

    ////

    public static final int TURBO_THRESHOLD = 0x32;

    public int[] forwardMove = new int[]{0x19, 0x32};

    //MAXPLMOVE is (forwardMove[1]) Java has no way of storing a reference
    public int[] sidemove = new int[]{0x18, 0x28};

    public int[] angleTurn = new int[]{640, 1280, 320};//+ slow turn

    public static final int SLOW_TURN_TICS = 6;

    public static final int NUM_KEYS = 256;

    public boolean[] gameKeyDown = new boolean[NUM_KEYS];

    public int turnHeld; //for accelerative turning

    ////

    boolean[] mouseButtons = new boolean[4];
    //java can't reference middle of array to allow -1 index

    //mouse values are used once
    public int mouseX;

    public int mouseY;

    public int dClickTime;

    public int dClickState;

    public int dClicks;

    public int dClickTime2;

    public int dClickState2;

    public int dClicks2;

    //joystick values are repeated
    public int joyXMove;

    public int joyYmove;

    boolean[] joyButtons = new boolean[5];
    //java can't reference middle of array to allow -1 index

    ////

    public int saveGameSlot;

    public String saveDescription;

    ////

    public static final int BODY_QUE_SIZE = 32;

    public boolean responder(Event e) {

        return false;
    }

    public void beginRecording() {
        throw new UnsupportedOperationException("Recording not implemented");
    }

}
