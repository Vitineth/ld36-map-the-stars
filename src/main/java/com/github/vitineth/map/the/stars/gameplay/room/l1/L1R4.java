package com.github.vitineth.map.the.stars.gameplay.room.l1;

import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
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
public class L1R4 extends Room {

    public L1R4(Level parent) {
        super("Glass Forgery", "You enter into a large room that Fei uses to produce the glass lenses. The walls are crafted from the same stone and rammed earth materials that the rest of the facility is made from. It contains a few furnaces littered around the room with channels carved into the ground for the molten glass to collect in when not used. Various casts rest against the wall to the west. Your attention is more drawn to the large wooden table in the centre of the room. You see the flash of reflected light as you step in and guess that Fei has finally finished the lenses he's been promising. The room radiates heat and you can feel the residual warmth of the furnaces lingering in the room.", "l1r4", parent);
        tryImageLoad();
    }

    @Override
    protected void setupItems() {
        items.put("LENSES", new Item("Lens", 3, "The lenses are quite large, about the size fo your forearm."));
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("FURNACE", "you can feel the raw heat of the furnaces as you approach them. You can tell that they've been off for a few hours but even still they retain the heat. Cooled glass sticks against the output chute where Fei has emptied its contents into a mould.");
        descriptions.put("CHANNELS", "the channels are shallow grooves cut into the ground to collect any unnecessary glass that still remains in the furnaces once everything has been produced. Small fragments of glass litter them where Fei has chipped away the left over glass to use it again.");
        descriptions.put("MOULDS|CASTS", "the moulds rest heavily against the west wall crafted from hardened Wootz steel. The craftsmanship has to be admired with the near perfect curves to make the best lenses possible. There has been so much time and effort places into this endeavour, you will not lose, you remind yourself.");
        descriptions.put("LENS(ES)?", "The lenses are quite large, about the size fo your forearm and sit almost haphazardly on the table. The legs strain slightly under their weight but remarkable manages to keep them up. They look about the right size to fit into the device in the main room.");
        descriptions.put("TABLE", "the table contains the standard clutter of papers and three large lenses. The wood it chipped in places with splinters sticking out dangerously. The legs are bending slightly under the weight of its load but it stands firm.");
    }

    @Override
    protected void setupCommands() {
        for (String key : descriptions.keySet()) {
            commands.put(CommandDefaults.INSPECT + key, () -> System.out.println(descriptions.get(key)));
        }
        commands.put(CommandDefaults.PICK_UP+"(MO(U)?LD(S)?|CAST(S)?)", () -> System.out.println("You go to lift one of the moulds and remember that this is a two man job. Even if you could lift it, what would you need it for?"));
        commands.put(CommandDefaults.PICK_UP+"LENS(ES)?", () -> {
            if (items.containsKey("LENSES")) {
                System.out.println("with a mighty heave you manage to lift the lenses of the table. The legs bend back into their place almost thankful of being relieved of the weight. Your arms strain slightly but you steady yourself and place them into the bag next to the table. Gently placing it on your back you get used to the weight.");
                getPlayer().addItem(items.get("LENSES"));
                items.remove("LENSES");
            }else{
                System.out.println("You already have the lenses.");
            }
        });
        commands.put(CommandDefaults.USE + "furnace", () -> System.out.println("You can't see Fei's gloves or tools anywhere and touching the furnaces at this heat would surely be a painful endeavour. Even if you could find the tools, what would you need them for?"));
        commands.put(CommandDefaults.USE +"lens(es)?", () ->{
            if (items.containsKey("LENSES")){
                System.out.println("You look through the lens and see the words on the paper distorted and twisted to unreadable proportions. Maybe this would work better in something? ");
            }else{
                System.out.println("The lenses are already stowed in the bag, there's no use dragging them out once again.");
            }
        });
        commands.put(CommandDefaults.DOOR + "(north|front|forward|top) door", () -> moveToRoom("l1r6"));
        commands.put("(" + CommandDefaults.DOOR + "(south|exit|back|bottom) door)|" + CommandDefaults.EXIT, () -> moveToRoom("l1r3"));
    }
}
