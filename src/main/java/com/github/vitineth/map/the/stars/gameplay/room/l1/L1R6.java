package com.github.vitineth.map.the.stars.gameplay.room.l1;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.AlchemyPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.log.Log;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class L1R6 extends Room {

    private boolean puzzleComplete = false;

    public L1R6(Level parent) {
        super("Fei's Workshop", "You enter into Fei's workshop. It's always a cluttered place to begin with but today seems to be even worse. There are pots and tools strewn around the room, broken pieces of pottery scattered around on the floor. Unlike the rest of the facility there aren't many pieces of paper in this room. Many of them seem to be attached to the various pots and bottles around the room. Fei told you that he'd made a break through in making the device more accurate. It must be in here somewhere. There is one large table in the middle of the room with a stool stood next to it. The area around the table is clear, presumably from where Fei rushes around the table to get his work done. ", "l1r6", parent);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/rooms/l1r6.png")));
        } catch (IOException e) {
            Log.s("RoomL1R6", "Failed to load the level image! The interactive pane will be blank!", e);
        }
    }

    @Override
    protected void setupItems() {
        items.put("MIXTURE", new Item("Lens Enhancement Solution", 1, "A murky black viscous liquid that seems to cling to the bottle it's in."));
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("TABLE", "the table contains all the work that Fei has done. To one side there is a collection of bottles, some half spilt and some places within a small stand to keep them upright. To the other side of the table is a collection of pots containing various coloured liquids. In the center of the table is the main collection of paper with various sections circled.");
        descriptions.put("BOTTLES", "the bottles seem to almost contain a rainbow when places next to one another. Various mixtures in ranges of different colours with unknown properties sit idly inside. You feel a sense of dread looking at them, you have no idea what any of these mixtures could do if handled wrong. This is Fei's domain.");
        descriptions.put("POTS", "the pots seem to contain much darker liquids than those in the bottles. Even still, they fill you with trepidation at what they could do if you use them wrong.");
    }

    @Override
    protected void setupCommands() {
        commands.put(CommandDefaults.PICK_UP + "BOTTLE(S)?", () -> System.out.println("you don't trust yourself to pick up the correct bottle without knowing what Fei has said or written."));
        commands.put(CommandDefaults.PICK_UP + "POT(S)?", () -> System.out.println("you don't trust yourself to pick up the correct pot without knowing what Fei ahs said or written."));
        commands.put(CommandDefaults.INSPECT + "(NOTE(S)?|PAPER(S)?|WRITING(S)?)", () -> {
            if (puzzleComplete) {
                System.out.println("You've already looked over the papers and completed Fei's instructions.");
            } else {
                System.out.println("the notes are filled with various equations and formulas, presumably how to make various mixtures and recipes. Towards the bottom of the second page is a recipe titled 'Lens enhancement solution' ");
                MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AlchemyPuzzle());
            }
        });
        commands.put(CommandDefaults.EXIT.getRegex(), () -> moveToRoom("l1r4"));
    }
}
