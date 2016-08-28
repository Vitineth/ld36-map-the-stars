package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandController;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.AttackPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.util.Callback;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class L2R2 extends Room {

    public L2R2(Level parent) {
        super("Hallucination Palace", "You stumble through the east door only to find yourself tripping into a large palace hall. Around you stands the statues of various gods and man, looking down upon you. In front of one of the statues a wolf sits patiently licking its paw. It spots you out of the corner of it's eye and begins to stand up ready to pounce. To the north is a small door", "l2r2", parent);
        tryImageLoad();
    }

    @Override
    public void enterRoom() {
        super.enterRoom();
        MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AttackPuzzle("l2r1", getClass().getResourceAsStream("/combat/combat.wolf.png"), 20));
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {
        commands.put("(" + CommandDefaults.EXIT + ")|(" + CommandDefaults.DOOR + "(NORTH|TOP|FRONT|FORWARD) DOOR)", new Callback() {
            @Override
            public void callback() {
                moveToRoom("l2r3");
            }
        });
    }
}
