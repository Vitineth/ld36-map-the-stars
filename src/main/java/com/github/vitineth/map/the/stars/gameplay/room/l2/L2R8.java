package com.github.vitineth.map.the.stars.gameplay.room.l2;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;

import java.awt.*;
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
public class L2R8 extends Room {

    public L2R8(Level parent) {
        super("Hallucination Bedroom", "it never occurred to you that you were heading for your bedroom. It was always a place of comfort, but can anything really be of comfort in this world? What is going on? Is any of this real? Is this hell? Heaven? Questions swirl and the pounding in your head reaches a crescendo. The world slowly fades to black as you collapse on the floor of your room.", "l2r8", parent);
        tryImageLoad();
    }

    @Override
    public void enterRoom() {
        BufferedImage n = getImage();
        Graphics2D g = n.createGraphics();
        Font f;

        try {
            f= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/AMERIKA_.ttf")).deriveFont((float) g.getFont().getSize());
        } catch (FontFormatException |IOException e) {
            f = g.getFont();
        }

        g.setFont(f);
        g.setFont(f.deriveFont(getBestSize("Thanks for playing", g, n.getWidth() - 70)));
        g.drawString("Thanks for playing", 35, (n.getHeight() / 2 - 30));
        super.enterRoom();
        MapTheStars.getMtsMainWindow().getInputArea().setLocked(true);
        MapTheStars.getMtsMainWindow().getOutputPane().setLocked(true);
        System.out.println("Game over! Thanks for playing!");
    }

    private float getBestSize(String text, Graphics g, int max) {
        int size = 1;
        while (g.getFontMetrics().charsWidth(text.toCharArray(), 0, text.length()) < max) {
            size+=7;
            g.setFont(g.getFont().deriveFont((float) size));
        }
        return size;
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
