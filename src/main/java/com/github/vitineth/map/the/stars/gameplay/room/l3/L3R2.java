package com.github.vitineth.map.the.stars.gameplay.room.l3;

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
public class L3R2 extends Room {

    public L3R2(Level parent) {
        super("L3 Cart", "After a long treck you are half way there. You decide to stop for the night and begin the laborious task of setting up your tent. Finally, late into the night once the sun has set, you finally have your tent up and a fire going. You sit down, 'your' book next to you that you somehow wrote during your black out session. That couldn't have been you. How could you not remember 3 days. The last thing you remember was... was... passing out. But anything before that point doesn't seem to want to come forth in your mind. Maybe your book could shed some light.", "l3r2", parent);
        tryImageLoad();
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("(BOOK|CATALOGUE)", "You pick up the book which is reasonably heavy and begin to flip through the pages. Everything seems accurate and detailed but none of it seems to make any sense. You have never seen this before in your life. You couldn't have written this. It just doesn't make sense. You head pounds slightly as you look at it, sudden de ja vu of the same feeling you had days ago washing over you. You decide it's best to put the book down and head to the emperor in the morning.");
    }

    @Override
    protected void setupCommands() {
        for (String key : descriptions.keySet()) {
            commands.put(CommandDefaults.INSPECT + key, () -> {
                System.out.println(descriptions.get(key));
                new Thread(() ->{
                    try {
                        Thread.sleep(500);
                        moveToRoom("l3r3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            });
        }
    }
}
