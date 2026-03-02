/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.matrixpeckham.jdoom.util;

import com.matrixpeckham.jdoom.d.player.Player;
import com.matrixpeckham.jdoom.p.MObj;
import com.matrixpeckham.jdoom.p.PSPDef;

/**
 *
 * @author matri
 */
public interface Procedure {

    public void call(MObj obj);

    public void call(Player pl, PSPDef psp);

}
