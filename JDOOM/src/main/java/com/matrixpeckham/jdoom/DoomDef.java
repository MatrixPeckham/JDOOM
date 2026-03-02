/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom;

/**
 * Global parameters and definitions.
 *
 * @author matri
 */
public class DoomDef {

    //To identify version of game
    public static enum GameMode {
        SHAREWARE,//doom shareware
        REGISTERED,//doom registered
        COMMERCIAL,//doom 2 retail
        //doom 2 german not handled
        RETAIL,//doom retail
        INDETERMINED//not found

    }

    //mission packs
    public static enum GameMission {
        DOOM, DOOM2, PACK_TNT, PACK_PLUT, NONE

    }

    //language for localization
    public static enum Language {
        ENGLISH, FRENCH, GERMAN, UNKNOWN

    }

    //For resize of screen at start of game will not work dynamically
    //TODO: for now this isnt changing at all on this version
    public static final int BASE_WIDTH = 320;

    //Scale
    //TODO: find better comment for these
    public static final int SCREEN_MUL = 1;

    public static final float INV_ASPECT_RATIO = 0.625f;

    public static final int SCREENWIDTH = 320;

    //SCREEN_MUL*BASE_WIDTH
    public static final int SCREEN_HEIGHT = 200;
    //SCREEN_MUL*BASE_WIDTH*INV_ASPECT_RATIO

    //Max multiplayer players
    public static final int MAX_PLAYERS = 4;

    //State Updates number of ticks per second
    public static final int TIC_RATE = 35;

    //Game State
    public static enum GameState {
        LEVEL, INTERMISSION, FINALE, DEMOSCREEN

    }

    //skill flags
    public static final int MTF_EASY = 1;

    public static final int MTF_NORMAL = 2;

    public static final int MTF_HARD = 4;

    //Deaf onsters/do not react to sound
    public static final int MTF_AMBUSH = 8;

    public static enum Skill {
        BABY, EASY, MEDIUM, HARD, NIGHTMARE

    }

    //Key cards
    public static enum Card {
        BLUE_CARD,
        YELLOW_CARD,
        RED_CARD,
        BLUE_SKULL,
        YELLOW_SKULL,
        RED_SKULL,
        NUM_CAREDS;

    }

    //Defined weapons including a marker indicating user has not changed weapon
    public static enum WeaponType {
        FIST, PISTOL, SHOTGUN, CHAINGUN, MISSILE, PLASMA, BFG, CHAINSAW, SUPERSHOTGUN,
        NUM_WEAPONS,
        NO_CHANGE

    }

    //Ammunition types defined
    public static enum AmmoType {
        CLIP,//pistol/chaingun
        SHELL,//shotgun/double barreled shotgun
        CELL,//plasma rifle bfg
        MISL,//missile launcher
        NUMAMMO,
        NOAMMO//unlimited

    }

    //powerup artifacts
    public static enum PowerType {
        INVULNERABILITY,
        STRENGTH,
        INVISIBILITY,
        IRONFEET,
        INFRARED,
        NUMPOWERS

    }

    //power up durations
    public static class PowerDuration {

        public static final int INVULNTICS = 30 * TIC_RATE;

        public static final int INVISTICS = 60 * TIC_RATE;

        public static final int INFRATICS = 120 * TIC_RATE;

        public static final int IRONTICS = 60 * TIC_RATE;

    }

}
