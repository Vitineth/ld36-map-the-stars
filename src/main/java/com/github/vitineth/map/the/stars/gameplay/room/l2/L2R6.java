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
public class L2R6 extends Room{

    public L2R6(Level parent) {
        super("Hallucination Metal Forge", "The door opens up into a massive room with large bloomeries scattered around the room. This is Fei's domain. Around the edge of the room lies large lumps of ore, dug up from the nearby mines or bought on some of his travels. The room is similarly as you left it but none of the furnaces seem to be burning.", "l1r6", parent);
        tryImageLoad();
    }

    @Override
    protected void setupItems() {
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("ORE(S)?", "the lumps of are a dumped randomly in the corner of the room, the ground seems strain under their weight as the rammed earth compresses slightly. ");
        descriptions.put("TOOL(S)?", "the tools are various crafting tools, rudimentary hammers and some instruments to help with the casting process.");
        descriptions.put("(BLOOMER(IES|Y))|FURNACE(S)?", "All of the bloomeries are cold to the touch, one even feels like touching pure ice. They were burning just a little bit earlier weren't they? Something isn't right about this place.");
    }

    @Override
    protected void setupCommands() {
        for (String key : descriptions.keySet()) {
            commands.put(CommandDefaults.INSPECT + key, () -> System.out.println(descriptions.get(key)));
        }
        commands.put(CommandDefaults.USE + "(BLOOMER(IES|Y))|FURNACE(S)?", () -> System.out.println("you have no need to use the bloomery and besides, you wouldn't trust yourself to do it without Fei present."));
        commands.put(CommandDefaults.USE + "TOOL(S)?", () -> System.out.println("you have no need to use the tools at this moment, you are sure that Fei can sort the bloomery when he returns and you have no need to smelt any materials."));
        commands.put(("(" + CommandDefaults.USE.getRegex() + "|" + CommandDefaults.PICK_UP.getRegex() + ")") + "ORE(S)?", () -> System.out.println("you could barely lift the ore without doing some serious injuries let alone do anything with it"));
        commands.put(CommandDefaults.PICK_UP + "TOOL(S)?", () -> System.out.println("you have no need for the tools right now so decide against picking them up."));
        commands.put(CommandDefaults.EXIT.getRegex(), () -> moveToRoom("l2r5"));
    }
}
