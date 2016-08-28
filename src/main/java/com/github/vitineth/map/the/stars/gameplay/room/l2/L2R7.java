package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L2R7 extends Room {

    public L2R7(Level parent) {
        super("Hallucination Bedroom", "it never occurred to you that you were heading for your bedroom. It was always a place of comfort, but can anything really be of comfort in this world? What is going on? Is any of this real? Is this hell? Heaven? Questions swirl and the pounding in your head reaches a crescendo. The world slowly fades to black as you collapse on the floor of your room.", "l2r7", parent);
        tryImageLoad();
    }

    @Override
    public void enterRoom() {
        super.enterRoom();
        MapTheStars.getMtsMainWindow().getInputArea().setLocked(true);
        MapTheStars.getMtsMainWindow().getOutputPane().setLocked(true);
        MapTheStars.getMtsMainWindow().getInteractivePanel().setImage(null); //Credits?
        System.out.println("Game over! Thanks for playing!");
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {

    }
}
