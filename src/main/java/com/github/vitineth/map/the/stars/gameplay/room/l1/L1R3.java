package com.github.vitineth.map.the.stars.gameplay.room.l1;

import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
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
public class L1R3 extends Room {

    public L1R3(Level parent) {
        super("Passage way", "The door opens into a small passage way with two doors along its edge. One to the north and one to east. ", "l1r3", parent);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/rooms/l1r3.png")));
        } catch (IOException e) {
            Log.s("RoomL1R3", "Failed to load the level image! The interactive pane will be blank!", e);
        }
    }

    @Override
    protected void setupItems() {
        //No items.
    }

    @Override
    protected void setupDescriptions() {
        //No descriptions.
    }

    @Override
    protected void setupCommands() {
        commands.put(CommandDefaults.DOOR + "(FRONT|NORTH|FORWARD) DOOR", () -> moveToRoom("l1r4"));
        commands.put(CommandDefaults.DOOR + "(RIGHT|EAST|SIDE) DOOR", () -> moveToRoom("l1r5"));
        commands.put(CommandDefaults.EXIT.getRegex(), () -> moveToRoom("l1r2"));
    }
}
