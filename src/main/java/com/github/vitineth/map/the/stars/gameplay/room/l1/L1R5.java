package com.github.vitineth.map.the.stars.gameplay.room.l1;

import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
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
public class L1R5 extends Room {

    public L1R5(Level parent) {
        super("Metal Forge", "The door opens up into a massive room with large bloomeries scattered around the room. This is Fei's domain. Around the edge of the room lies large lumps of ore, dug up from the nearby mines or bought on some of his travels. On the table nearest the door lies a couple of tools and materials that look like they could be of use. Towards the back of the room one of the bloomeries is still burning.", "l1r5", parent);

    }

    @Override
    protected void setupItems() {
        items.put("FRAMES", new Item("Lens Frames", 3, "Frames that look like they should slot around a lens."));
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("ORE(S)?", "the lumps of are a dumped randomly in the corner of the room, the ground seems strain under their weight as the rammed earth compresses slightly. ");
        descriptions.put("TOOL(S)?", "the tools are various crafting tools, rudimentary hammers and some intruments to help with the casting process.");
        descriptions.put("(BLOOMER(IES|Y))|FURNACE(S)?", "two of the bloomeries are cool to the touch, the stone having cooled for days. The third threatens to burn your hand even as you reach towards it. Fei must have left it going while he left. That seems strange but you shrug it off to his strange nature.");
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
        commands.put(CommandDefaults.USE + "(BLOOMER(IES|Y))|FURNACE(S)?", new Callback() {
            @Override
            public void callback() {
                System.out.println("you have no need to use the bloomery and besides, you wouldn't trust yourself to do it without Fei present.");
            }
        });
        commands.put(CommandDefaults.USE + "TOOL(S)?", new Callback() {
            @Override
            public void callback() {
                System.out.println("you have no need to use the tools at this moment, you are sure that Fei can sort the bloomery when he returns and you have no need to smelt any materials.");
            }
        });
        commands.put(("(" + CommandDefaults.USE.getRegex() + "|" + CommandDefaults.PICK_UP.getRegex() + ")") + "ORE(S)?", new Callback() {
            @Override
            public void callback() {
                System.out.println("you could barely lift the ore without doing some serious injuries let alone do anything with it");
            }
        });
        commands.put(CommandDefaults.PICK_UP + "TOOL(S)?", new Callback() {
            @Override
            public void callback() {
                System.out.println("you have no need for the tools right now so decide against picking them up.");
            }
        });
        commands.put(CommandDefaults.PICK_UP + "FRAME(S)?", new Callback() {
            @Override
            public void callback() {
                if (items.containsKey("FRAMES")) {
                    System.out.println("you collect the frames, your arms straining under their weight.");
                    getPlayer().addItem(items.get("FRAMES"));
                    items.remove("FRAMES");
                } else {
                    System.out.println("You've already collected the frames!");
                }
            }
        });
        commands.put(CommandDefaults.EXIT.getRegex(), new Callback() {
            @Override
            public void callback() {
                moveToRoom("l1r3");
            }
        });
    }
}
