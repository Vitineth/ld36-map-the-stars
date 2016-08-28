package com.github.vitineth.map.the.stars.gameplay.room.l3;

import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L3R4 extends Room {

    public L3R4(Level parent) {
        super("L4 Boat", "It's been weeks now. You've been on the seas too long. You're getting lonely on board the ship, Fei is long behind you and you miss his friendship. You think you're getting close to land now and you've been sent off on your own. The boats rocks slightly as the water laps at the side, the darkness enveloping you like a blanket. Your map book sits on the bar in front of you but you remember it by heart.\n'To find the land: look for the two large stars, slightly diagonal from one another. Steer in the direction of the line.'", "l3r4", parent);
        BufferedImage img = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        int yoff = 200;
        g.setFont(g.getFont().deriveFont(getBestSize("Thanks for playing", g, 1920 - 60)));
        g.drawString("Thanks for playing", 30, 30 + yoff);
        g.setFont(g.getFont().deriveFont(getBestSize("aaaabbbbaaaabbbbaaaabbbbaaaabbb", g, 1920-60)));
        int y = 30 + g.getFontMetrics().getHeight() + yoff;
        for (String s : wrap("This is my first LD entry for LD36: 'Ancient Technology'. I had hoped to get some more done but this is all I have achieved for now.", g, 60)) {
            g.drawString(s, 30, y);
            y += g.getFontMetrics().getHeight();
        }
        setImage(img);

    }

    private float getBestSize(String text, Graphics g, int max) {
        int size = 1;
        g.setFont(g.getFont().deriveFont((float)size));
        while (g.getFontMetrics().charsWidth(text.toCharArray(), 0, text.length()) < max) {
            size += 1;
            g.setFont(g.getFont().deriveFont((float) size));
        }
        return size;
    }

    private String[] wrap(String text, Graphics g, int offset) {
        java.util.List<String> lines = new ArrayList<>();
        int index = 0;
        int lastSpace = -1;
        int lastCut = 0;
        String active = "";
        while (index < text.length()) {
            if (text.charAt(index) == ' ') lastSpace = index;
            active += text.substring(index, index + 1);
            if (g.getFontMetrics().charsWidth(active.toCharArray(), 0, active.length()) >= 1920 - offset) {
                if (lastSpace != -1) {
                    lines.add(text.substring(lastCut, lastSpace));
                    index = lastSpace;
                    lastCut = index;
                    lastSpace = -1;
                    active = "";
                } else {
                    lines.add(text.substring(lastCut, index));
                    lastCut = index;
                    active = "";
                }
            }
            index++;
        }
        if (lastCut != text.length()) {
            lines.add(text.substring(lastCut));
        }

        return lines.toArray(new String[lines.size()]);
    }

    @Override
    public void enterRoom() {
        super.enterRoom();
//        launchSkyPuzzle();
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {

    }
}