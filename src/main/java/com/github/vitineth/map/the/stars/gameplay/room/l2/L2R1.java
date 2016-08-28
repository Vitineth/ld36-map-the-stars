package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.log.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
public class L2R1 extends Room {

    private boolean bound = true;
    private boolean inCell = true;

    private BufferedImage bonds;
    private BufferedImage cell;
    private BufferedImage def;

    public L2R1(Level parent) {
        super("Hallucination Cell", "The world materialises around you yet there's a sort of ethereal shimmer. Your head pounds as the rooms swims into sight. You find yourself in a stone cell arms tied firmly behind your back. Rough metal bars stand between you and a door you see to the east yet one of the bars doesn't look quite right. Nothing  sits inside the cell but next to the door you see a small dagger.", "l2r1", parent);
        tryImageLoad();
        def = getImage();

        try {
            bonds = ImageIO.read(getClass().getResourceAsStream("/rooms/l2r1-b.png"));
            cell = ImageIO.read(getClass().getResourceAsStream("/rooms/l2r1-c.png"));
        } catch (IOException e) {
            Log.s("L2R1", "Failed to load bonds and cell images. These will not update.", e);
        }
    }

    @Override
    protected void setupItems() {
        items.put("DAGGER", new Item("Dagger", 1, "A small but sharp dagger."));
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("BAR(S)?", "One of the bars is twisted slightly revealing a sharp piece of metal sticking out. The bar also seems loose but doesn't seem to want to move much. If only you had your two hands.");
        descriptions.put("BOND(S)?", "Your bonds are tied tightly behind your back with some rope. You try to pull them free but the bonds don't seem to pull apart at all.");
        descriptions.put("WALLS|CELL", "the cell is made from dark stone mixed with the occasional patch of rammed earth but even still, it is too hard to tunnel through. ");

    }

    @Override
    protected void setupCommands() {
        for (String key : descriptions.keySet()) {
            commands.put(CommandDefaults.INSPECT + key, () -> System.out.println(descriptions.get(key)));
        }
        commands.put("(BREAK BARS|PUSH( ON)? BARS|MOVE BARS|BEND BARS|USE BARS|INTERACT WITH BARS|HIT BARS)", () -> {
            if (bound) {
                System.out.println("The bar also seems loose but doesn't seem to want to move much. If only you had your two hands.");
            } else {
                System.out.println("you push against the bar with your two free hands and after a second of resistance it gives way sending you sprawling across the floor. The pounding feeling in your head returns.");
                inCell = false;

                if (cell != null){
                    setImage(cell);
                    MapTheStars.getMtsMainWindow().getInteractivePanel().setImage(cell);
                    MapTheStars.getMtsMainWindow().getInteractivePanel().repaint();
                }
            }
        });
        commands.put("(SLICE BONDS WITH BAR|FREE HANDS WITH BAR|USE BAR ON BONDS|USE BONDS ON BAR|CUT BONDS WITH BAR|USE BAR TO CUT BONDS|USE BAR TO FREE HANDS|USE BAR TO RELEASE HANDS)", () -> {
            if (bound) {
                System.out.println("the bonds take a moment to break but finally the piece of metal breaks through and your arms are released. The black patch of liquid on your hand seems to shimmer and move, as if angered by your freedom.");
                bound = false;

                if (bonds != null){
                    setImage(bonds);
                    MapTheStars.getMtsMainWindow().getInteractivePanel().setImage(bonds);
                    MapTheStars.getMtsMainWindow().getInteractivePanel().repaint();
                }
            } else {
                System.out.println("Your hands are already free!");
            }
        });
        commands.put(CommandDefaults.EXIT.getRegex(), () -> {
            if (!inCell && !bound) moveToRoom("l2r2");
            else System.out.println("You can exit the room, you are still locked in the cell!");
        });
    }

    @Override
    public void reset() {
        super.reset();
        bound = true;
        inCell = true;
        setImage(def);
    }
}
