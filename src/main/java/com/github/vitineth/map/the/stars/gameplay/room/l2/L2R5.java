package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
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
public class L2R5 extends Room {

    public L2R5(Level parent) {
        super("Hallucination Passage", "The passage way is empty as usual but an unnatural breeze rolls through with the torches flickering in the breeze, two of which blow out.", "l2r5", parent);
        tryImageLoad();
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {

        commands.put(CommandDefaults.DOOR + "(FRONT|NORTH|FORWARD) DOOR", () -> moveToRoom("l2r4"));
        commands.put(CommandDefaults.DOOR + "(RIGHT|EAST|SIDE) DOOR", () -> moveToRoom("l2r6"));
        commands.put(CommandDefaults.EXIT.getRegex(), () -> moveToRoom("l2r7"));
    }
}
