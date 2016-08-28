package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.StarMapPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.util.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L2R4 extends Room {

    private BufferedImage fire;

    public L2R4(Level parent) {
        super("Hallucination glasswork", "you stumble out, checking behind you for any more serpents but the room seems clear all of a sudden. the glassworking room seems normal to begin with but something seems off about the furnaces. The room seems to be how you left it, there are no lenses or bag next to the table but you can tell that something isn't quite right. Is it your mind playing tricks on you? ", "l2r4", parent);
        tryImageLoad();
        try {
            fire = ImageIO.read(getClass().getResourceAsStream("/rooms/l2r4-f.png"));
        } catch (IOException e) {
            Log.s("RoomL2R4", "Failed to load the fire image. This will not update when the furnace is inspected.", e);
        }
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("FURNACE", "the first three furnaces seem normal but as you turn the fourth it moves slightly. You blink rapidly, surely your mind is just tricking you. Leaning in closer the furnace suddenly rears up, the orange glow seems to multiply and the heat becomes fierce. Almost without warning, the top explodes into flames. Without thinking you run towards the door only to be met with a closed and bolted door and a strange device on the floor");
        descriptions.put("TABLE", "the table is just as you left it but as you look at it, something seems to shift out of the corner of your eye. It must be nothing... right?");
    }

    @Override
    protected void setupCommands() {
        for (final String key : descriptions.keySet()) {
            if (key.equals("FURNACE")) {
                commands.put(CommandDefaults.INSPECT + key, new Callback() {
                    @Override
                    public void callback() {
                        System.out.println(descriptions.get(key));
                        if (fire != null) {
                            setImage(fire);
                            MapTheStars.getMtsMainWindow().getInteractivePanel().setImage(getImage());
                            MapTheStars.getMtsMainWindow().getInteractivePanel().repaint();
                        }
                    }
                });
            } else {
                commands.put(CommandDefaults.INSPECT + key, new Callback() {
                    @Override
                    public void callback() {
                        System.out.println(descriptions.get(key));
                    }
                });
            }
        }
        commands.put(CommandDefaults.INSPECT + "DEVICE", new Callback() {
            @Override
            public void callback() {
                System.out.println("it seems to show the stars that you saw before, but how can that be? The sound of flames behind you clears you mind and you try to figure out what it means");
                StarMapPuzzle puzzle = new StarMapPuzzle();
                puzzle.setCompleteCallback(new Callback() {
                    @Override
                    public void callback() {
                        moveToRoom("l2r5");
                    }
                });
                MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(puzzle);
            }
        });

    }
}
