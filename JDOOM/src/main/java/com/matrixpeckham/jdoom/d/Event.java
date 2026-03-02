/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.d;

/**
 *
 * @author matri
 */
public class Event {

    public static enum Type {
        KEY_DOWN,
        KEY_UP,
        MOUSE,
        JOYSTICK//probably not implemented

    }

    public Type type;

    public int data1;

    public int data2;

    public int data3;

    public static enum GameAction {
        NOTHING,
        LOAD_LEVEL,
        NEW_GAME,
        LOAD_GAME,
        SAVE_GAME,
        PLAY_DEMO,
        COMPLETED,
        VICTORY,
        WORLD_DONE,
        SCREENSHOT

    }
//Not in enum so we can use the constants directly with arithmetic

    public static final int BT_ATTACK = 1;//Fire button

    public static final int BT_USE = 1;//use button
    //flag game events not buttons

    public static final int BT_SPECIAL = 128;

    public static final int BT_SPECIAL_MASK = 3;

    //flag change weapon pending, next 3 bits hold weapon num
    public static final int BT_CHANGE = 4;

    //3 bit weapon mask and shift
    public static final int BT_WEAPON_MASK = (4 + 8 + 16);

    public static final int BT_WEAPON_SHIFT = 3;

    //pause the game
    public static final int BTS_PAUSE = 1;

    //save game at each console
    public static final int BTS_SAVE_GAME = 2;

    //savegame slot numbers occupy the second byte of buttons;
    public static final int BTS_SAVE_MASK = (4 + 8 + 16);

    public static final int BTS_SAVE_SHIFT = 2;

    public static final int MAX_EVENTS = 64;

}
