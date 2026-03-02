/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.info;

import com.matrixpeckham.jdoom.util.Procedure;

/**
 *
 * @author matri
 */
public class State {

    public int sprite;

    public long frame;

    public long tics;

    public Procedure action;

    public int nextState;

    public long misc1, misc2;

    public State() {
        throw new UnsupportedOperationException();
    }

    public State(int sprite, long frame, long tics, Procedure action,
            int nextState, int misc1, int misc2) {
        this.sprite = sprite;
        this.frame = frame;
        this.tics = tics;
        this.action = action;
        this.nextState = nextState;
        this.misc1 = misc1;
        this.misc2 = misc2;
    }

}
