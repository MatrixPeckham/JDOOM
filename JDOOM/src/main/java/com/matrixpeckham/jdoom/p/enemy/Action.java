/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.jdoom.p.enemy;

import static com.matrixpeckham.jdoom.SoundEnum.*;
import static com.matrixpeckham.jdoom.info.MobJType.MT_CYBORG;
import static com.matrixpeckham.jdoom.info.MobJType.MT_SPIDER;
import static com.matrixpeckham.jdoom.p.MObj.MF_AMBUSH;
import static com.matrixpeckham.jdoom.p.MObj.MF_SHOOTABLE;
import static com.matrixpeckham.jdoom.p.Sight.checkSight;
import static com.matrixpeckham.jdoom.p.Util.pRandom;

import com.matrixpeckham.jdoom.Sound;
import com.matrixpeckham.jdoom.d.player.Player;
import com.matrixpeckham.jdoom.p.MObj;
import com.matrixpeckham.jdoom.p.PSPDef;
import com.matrixpeckham.jdoom.util.Procedure;

/**
 *
 * @author matri
 */
public class Action {

    public static boolean lookForPlayers(MObj actor, boolean fov) {
        return false;
    }

    public static final Procedure A_Look = new Procedure() {

        @Override
        public void call(MObj actor) {
            actor.threshold = 0;
            MObj targ = actor.subsector.sector.soundTarget;
            if (targ != null && ((targ.flags & MF_SHOOTABLE) != 0)) {
                actor.target = targ;
                if ((actor.flags & MF_AMBUSH) != 0) {
                    if (checkSight(actor, actor.target)) {
                        seeYou(actor, targ);
                    }
                } else {
                    seeYou(actor, targ);
                }
            }
            if (!Action.lookForPlayers(actor, false)) {
                return;
            }
            seeYou(actor, targ);
        }

        public void seeYou(MObj actor, MObj targ) {
            int sound = 0;
            if (actor.info.seesound != 0) {
                switch (actor.info.seesound) {
                    case sfx_posit1:
                    case sfx_posit2:
                    case sfx_posit3:
                        sound = sfx_posit1 + pRandom();
                        break;
                    case sfx_bgsit1:
                    case sfx_bgsit2:
                        sound = sfx_bgsit1 + pRandom();
                        break;
                    default:
                        sound = actor.info.seesound;
                        break;
                }
            }
            if (actor.type == MT_SPIDER || actor.type == MT_CYBORG) {
                //full volume
                Sound.get().startSound(null, sound);
            } else {
                Sound.get().startSound(actor, sound);
            }
            actor.setMobjState(actor.info.seestate);
        }

        @Override
        public void call(Player pl, PSPDef psp) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    };

}
