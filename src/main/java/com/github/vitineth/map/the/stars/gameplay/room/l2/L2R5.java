package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.util.Callback;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L2R5 extends Room {

    public L2R5(Level parent) {
        super("Hallucination Main Room", "like the rest of the rooms in the facility, something seems off about the main room. It doesn't strike you at first but for some reason all the papers scattered around the room seem to be facing the same direcion. You rub your eyes, sure that you are simply imagining this, that it's just a bad dream but as you blink a few times the room stays the same. The device in the middle of the room also seems to be orientated differently, pointing to a random point of the sky. Who could have done this? Is Fei back? Is he just playing a prank? That must be it...", "l2r5", parent);
        tryImageLoad();
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("PAPER(S)?", "All of the paper seems to be fixed in one direction and as you take a closer look, all the pages bare the same drawing the constellation you saw to begin with.");
        descriptions.put("DEVICE", "The device is positioned in the centre of the room still, but it has been angled at a random point in the sky. But the eye piece is scratched with some chips out of it. How could this have happened?");
    }

    @Override
    protected void setupCommands() {
        for (final String key : descriptions.keySet()) {
            commands.put(CommandDefaults.INSPECT + key, new Callback() {
                @Override
                public void callback() {
                    System.out.println(descriptions.get(key));
                }
            });
        }
        commands.put("(" + CommandDefaults.EXIT + ")|(" + CommandDefaults.DOOR + "((SOUTH|BACKWARD|BACK) DOOR))", new Callback() {
            @Override
            public void callback() {
                moveToRoom("l2r7");
            }
        });
    }
}
