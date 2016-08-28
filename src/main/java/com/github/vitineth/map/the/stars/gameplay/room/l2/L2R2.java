package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.AttackPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;

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
        MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AttackPuzzle("l2r1", getClass().getResourceAsStream("/combat/combat.wolf.png"), 20,
                new String[]{"You swipe at the wolf and catch it slightly. It whimpers in pain but anger fills its eyes", "Your dagger makes contact with the wolf, tearing roughly through its flesh. Blood trickles out mixing with its fur turning it a deep shade of red making the wolf look almost demonic.", "You go to slice at the wolf but it tries to move, instead your hand makes contact with the wolf, its head whips sideways from the impact."},
                new String[]{"You go to attack the wolf but it dodges!", "The wolf sees you getting ready to attack and sprints sideways with incredible speed. Caught in the momentum, you trip forward and miss the attack.", "The wolf jumps at the same time you go to attack and misses the blade by mere centimetres."},
                "You manage to kill the wolf, its corpse falls to the ground heavily, blood pooling around its head, mixing with its fur tainting it red.",
                new String[]{"The wolf lunges and wraps its teeth around your leg. You cry out in pain and kick it away", "The wolf pounces suddenly, its teeth making easy work of the flesh on your leg. Pain explodes and you wrestle it away. Blood weeps from your wound and begins to stain your garments.", "The wolf jumps but you anticipate it and dodge its teeth, instead the full weight of it lands directly in the centre of your chest knocking you backwards slightly."},
                new String[]{"The wolf lunges at you but you realise in time and sidestep.", "The wold reveals its teeth and you see that it is getting ready to attack. You wait a second and dive to the side, narrowly missing the wolfs attack.", "You swipe at the wolf as it runs close to you but do not make contact, even still you drive it off course, saving yourself an injury this time around."},
                "The wolf manages to land a finishing blow and you can feel the loss of blood take over. The world fades to black..."));
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {
        commands.put("(" + CommandDefaults.EXIT + ")|(" + CommandDefaults.DOOR + "(NORTH|TOP|FRONT|FORWARD) DOOR)", () -> moveToRoom("l2r3"));
    }
}
