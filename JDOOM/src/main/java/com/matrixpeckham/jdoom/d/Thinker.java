/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.d;

/**
 *
 * @author matri
 */
public abstract class Thinker {

    public Thinker next = null;

    public Thinker prev = null;

    boolean nulled = false;

    public boolean isNull() {
        return nulled;
    }

    public abstract void think();

    public void remove() {
        nulled = true;
    }

}
