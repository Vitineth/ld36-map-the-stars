package com.github.vitineth.map.the.stars.gameplay.room.l1;

import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.util.Callback;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class L1R1 extends Room {

    public L1R1(Level parent) {
        super("Bedroom", "You blink open your eyes, the fire in the corner casting the room in an almost ethereal glow. Your head pounds as you turn around in the bed and begin to sit up. You squint slightly as the pain reaches a cruchendo. You don't remember much of the night and your mind is hazy as to what you were doing. You know you must've been in this place, you haven't left for months. The room is small containing only a simple bed on which you lie, a small table next to you with a few shelves built in containing scattered papers and your diary, and a small fire in the corner that looks like its beginnign to die down. You look to your diary and you wonder whether it could shed light on last nights activities.", "L1R1", parent);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/rooms/l1r1.png")));
        } catch (IOException e) {
            Log.s("RoomL1R1", "Failed to load the level image! The interactive pane will be blank!");
        }
    }

    @Override
    protected void setupItems() {
        items.put("KEYS", new Item("Ring of Keys", 1, "The ring of keys contains 5 keys made of hand cast metal. This was another piece of technology borrowed from the Greeks, Fai had said something about 'tumbler locks' as a new development."));
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("FIRE", "The fire is hastily assembled with logs scattered haphazardly around the outside, no hope of catching fire. The wood in the centre is chared black with some regions slowly turning white and flaking off into ash. As far as you can see there is nothing unusual about the fire.");
        descriptions.put("(BEDSIDE )?TABLE", "The wooden table stands firm by the bed, supporting a range of disorganised papers, writing scribbled over the top in no ordered fashion. A ring of keys rests on the top shelf next to a small note. On one of the lower shelves, a leather bound book rests gently.");
        descriptions.put("(DIARY|BOOK|LEATHER BOUND BOOK)", "You pick up your diary and flip through to the last entry: 'We've made some good progress here, we are sure to be one of the first to discover the secrets of the stars. Fai says that the main body of the device is ready and that we just need to add some final parts to it. He also says hes made a breakthrough in making the lenses more precise but he's always saying that he's made a breakthrough. I may need to search his workshop later on to find what he's talking about or just ask him tomorrow'. You place the diary back where it was.");
        descriptions.put("(NOTE|PAPER)", "The note is in different handwriting than the rest of the papers scattered around the shelf and it reads 'I'm away for the week to talk to the Greeks, as far as I know they haven't made much more development in lens making but we've been making enough on our own. I'll see if they have anything new that we can use. I am unsure of when I shall return but keep working without me -Jeng Fai'");
    }

    @Override
    protected void setupCommands() {
        commands.put(CommandDefaults.INSPECT.getRegex() + "(RING OF )?KEY(S)?", () -> System.out.println(items.get("KEYS").getDescription()));
        commands.put(CommandDefaults.PICK_UP.getRegex() + "(RING OF )?KEY(S)?", () -> {
            if (items.containsKey("KEYS")) {
                getPlayer().addItem(items.get("KEYS"));
                items.remove("KEYS");
                System.out.println("You pick up the keys.");
            } else {
                System.err.println("There are no keys to pick up!");
            }
        });

        for (String key : descriptions.keySet()) {
            commands.put(CommandDefaults.INSPECT + key, () -> System.out.println(descriptions.get(key)));
        }
        commands.put(CommandDefaults.PICK_UP + "(DIARY|BOOK|LEATHER BOUND BOOK)", () -> System.out.println("You don't think you'll need your diary at this point and decide against picking it up."));
        commands.put(CommandDefaults.PICK_UP + "(NOTE|PAPER)", () -> System.out.println("You can think of no use for this note and decide against picking itup."));
        commands.put(CommandDefaults.EXIT.getRegex(), () -> moveToRoom("l1r2"));
    }

}
