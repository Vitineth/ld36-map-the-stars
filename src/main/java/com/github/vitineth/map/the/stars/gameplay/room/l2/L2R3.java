package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.AttackPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.util.Callback;

import java.util.Random;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L2R3 extends Room {

    private Random random;

    public L2R3(Level parent) {
        super("Hallucination Hidden Room", "you walk through the north door and somehow find yourself back in the facility. You are in Fei's workshop, it was just like before except bottles and pots are spilt everywhere. The liquids flow on the ground despite the floor being level. Upon closer inspection you see that it isn't just the liquid from the bottles on the ground. The various chemicals in an array of colours have formed into serpents that crawl around the floor. One suddenly notices you and rears it head, ready to attack", "l2r3", parent);
//        tryImageLoad();
        this.random = new Random();
        tryImageLoad();
    }

    @Override
    public void enterRoom() {
        super.enterRoom();
        MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AttackPuzzle("l2r1", getClass().getResourceAsStream("/combat/combat.snake.png"), 10));
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {
    }

    @Override
    protected void setupCommands() {
        commands.put(CommandDefaults.EXIT.getRegex(), new Callback() {
            @Override
            public void callback() {
                if (random.nextInt(5) == 0) {
                    MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AttackPuzzle("l2r1", getClass().getResourceAsStream("/combat/combat.snake.png"), 10));
                } else {
                    moveToRoom("l2r4");
                }
            }
        });
    }
}
