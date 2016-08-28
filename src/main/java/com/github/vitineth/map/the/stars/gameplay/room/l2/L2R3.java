package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.AttackPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;

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
        MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AttackPuzzle("l2r1", "Snake", getClass().getResourceAsStream("/combat/combat.snake.png"), 10,
                new String[]{"You grab the snake by its body, carefully avoiding its fangs, and throw it away from you. It slides on the ground slightly, dazed by the impact and returns its attention to you.", "Your dagger makes contact with the body of the snake and cuts of a small piece of skin. The snake seems to ignore it and just becomes more intent on attacking you", "You crush the end of the snakes tail with the boot as it slithers near."},
                new String[]{"The snake sees your dagger flying towards it and slithers out of the way with surprising speed.", "The snake slithers rapidly under the table just as your boot is about to make contact", "The snake seems to almost jump slightly as the dagger comes near, avoiding its body by fractions of a centimetre"},
                "The snake finally gives up its battle and gives into death as blood pools around its corpse.",
                new String[]{"The snake darts forward and sinks its fangs through the soft top leather of your boot. You cry out in pain and rip it away, praying that they are not venomous.", "The snake slithers up your leg and tightens, cutting of blood flow to your foot and crushing the soft tissue beneath. You rip it away quickly and shake your foot to regain feeling."},
                new String[]{"The snake goes to dart forward but you step to the side quickly and avoid its attack.", "The snake tries to slither up your leg but you kick it away quickly and get ready to attack.", "The snake tries to come near but you are prepared and get ready to dodge it and attack"},
                "The venom seems to take a hold of your body and you begin to freeze up, the world fades to black and you pass out."));
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {
    }

    @Override
    protected void setupCommands() {
        commands.put(CommandDefaults.EXIT.getRegex(), () -> {
            if (random.nextInt(5) == 0) {
                MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AttackPuzzle("l2r1", "Snake", getClass().getResourceAsStream("/combat/combat.snake.png"), 10,
                        new String[]{"You grab the snake by its body, carefully avoiding its fangs, and throw it away from you. It slides on the ground slightly, dazed by the impact and returns its attention to you.", "Your dagger makes contact with the body of the snake and cuts of a small piece of skin. The snake seems to ignore it and just becomes more intent on attacking you", "You crush the end of the snakes tail with the boot as it slithers near."},
                        new String[]{"The snake sees your dagger flying towards it and slithers out of the way with surprising speed.", "The snake slithers rapidly under the table just as your boot is about to make contact", "The snake seems to almost jump slightly as the dagger comes near, avoiding its body by fractions of a centimetre"},
                        "The snake finally gives up its battle and gives into death as blood pools around its corpse.",
                        new String[]{"The snake darts forward and sinks its fangs through the soft top leather of your boot. You cry out in pain and rip it away, praying that they are not venomous.", "The snake slithers up your leg and tightens, cutting of blood flow to your foot and crushing the soft tissue beneath. You rip it away quickly and shake your foot to regain feeling."},
                        new String[]{"The snake goes to dart forward but you step to the side quickly and avoid its attack.", "The snake tries to slither up your leg but you kick it away quickly and get ready to attack.", "The snake tries to come near but you are prepared and get ready to dodge it and attack"},
                        "The venom seems to take a hold of your body and you begin to freeze up, the world fades to black and you pass out."));
            } else {
                moveToRoom("l2r4");
            }
        });
    }
}
