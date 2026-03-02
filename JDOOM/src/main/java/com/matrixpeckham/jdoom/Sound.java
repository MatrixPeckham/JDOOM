/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom;

import com.matrixpeckham.jdoom.p.MObj;

/**
 *
 * @author matri
 */
public class Sound {

    private static Sound inst;

    public static Sound get() {
        if (inst == null) {
            inst = new Sound();
        }
        return inst;
    }

    //Initialize Sound System
    public void init(int sfxVolume, int musicVolume) {
        //TODO: make sound system
    }

    //per level startup code kills playing sound at start of level
    //determines music if any and changes it
    public void start() {
        //TODO: make sound system
    }

    //start sound for thing at origin using sound id
    public void startSound(MObj source, int sound) {
        //TODO: make sound System
    }

    //will start a sound at a given volume
    public void startSoundAtVolume(MObj origin, int sound, int volume) {
        //TODO: make sound system
    }

    //stop sound for thing at origin
    public void stopSound(MObj origin) {
        //TODO: make sound system
    }

    //start music
    public void startMusic(int music) {
        //TODO: make sound system
    }

    //start music and set looping
    public void changeMusic(int music, int looping) {
        //TODO: make sound system
    }

    //stop music
    public void stopMusic() {
        //TODO: make sound system
    }

    //Stop and resume music, during game pause
    public void pauseSound() {
    }

    public void resumeSound() {
    }

    //updates music and sounds
    public void updateSound(Object listener) {
    }

    public void setMusicVolume(int volume) {
    }

    public void setSfxVoluem(int volume) {
    }

}
