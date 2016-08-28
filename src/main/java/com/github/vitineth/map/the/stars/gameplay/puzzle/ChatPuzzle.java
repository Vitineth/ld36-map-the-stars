package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;
import com.github.vitineth.map.the.stars.util.chat.ChatManager;
import com.github.vitineth.map.the.stars.util.chat.ChatNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class ChatPuzzle extends Puzzle {

    private Font font;
    private ChatManager manager;
    private boolean complete = false;
    private java.util.List<Rectangle> opts = new ArrayList<>();

    public ChatPuzzle(ChatManager manager) {
        super(new Dimension(0, 0));
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/AMERIKA_.ttf"));

        } catch (FontFormatException | IOException e) {
            Log.s("MapTheStars/ChatPuzzle", "Failed to load custom font or image.", e);
        }

        this.manager = manager;
    }

    @Override
    public void puzzleLaunched(MTSInteractivePanel panel) {
        setSize(panel.getSize());
        font = font.deriveFont(getBestSize("Main title text string input words aaaa bbbb cccc dddd eeee ffff gggg", new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics(), (int) getSize().getWidth()));
        super.puzzleLaunched(panel);
    }

    @Override
    public BufferedImage getActiveFrame() {
        BufferedImage image = new BufferedImage((int) getSize().getWidth(), (int) getSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setFont(font);
        int y = 100;
        for (String s : wrap(manager.getText(), g, 80)) {
            g.drawString(s.trim(), 40, y);
            y += g.getFontMetrics().getHeight();
        }
        opts.clear();
        g.setFont(g.getFont().deriveFont((float) g.getFont().getSize() - 10));
        int originX = (int) (getSize().getWidth() / 8);
        int originY = (int) (getSize().getHeight() / 2) + (y / 3);
        int h = g.getFontMetrics().getHeight();
        int start = originY - (h * (manager.getOptions().size() / 2));
        for (ChatNode node : manager.getOptions()) {
            g.drawOval(originX + 50, start, 4, 4);
            g.drawLine(originX, originY, originX + 50, start + 1);
            g.drawString(node.getOption(), originX + 60, start + (h / 3));

            opts.add(new Rectangle(originX + 60, start - (h / 3), g.getFontMetrics().charsWidth(node.getOption().toCharArray(), 0, node.getOption().length()), h));
            start += h;
        }

        g.fillOval(originX - 15, originY - 15, 30, 30);
        g.dispose();
        Log.d("ChatPuzzle/TextWrapping", manager.getText() + "  =>  " + Arrays.toString(wrap(manager.getText(), g, 80)));
        return image;
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
            if (g.getFontMetrics().charsWidth(active.toCharArray(), 0, active.length()) >= getSize().getWidth() - offset) {
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

    private float getBestSize(String text, Graphics g, int max) {
        int size = 1;
        while (g.getFontMetrics().charsWidth(text.toCharArray(), 0, text.length()) < max) {
            size += 1;
            g.setFont(g.getFont().deriveFont((float) size));
        }
        return size;
    }

    @Override
    public boolean isComplete() {
        return complete || !manager.continueChat();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < opts.size(); i++) {
            if (opts.get(i).contains(e.getPoint())) {
                manager.chatOptionSelected(manager.getOptions().get(i));
            }
        }
        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}