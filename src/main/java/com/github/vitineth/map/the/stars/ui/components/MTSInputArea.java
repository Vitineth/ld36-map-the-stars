package com.github.vitineth.map.the.stars.ui.components;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.ui.theme.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This handles all text based user inputs into the game.
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class MTSInputArea extends JTextArea {

    private boolean locked = false;
    private Theme theme;

    public MTSInputArea(Theme theme) {
        this.theme = theme;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setText(">> ");
        setCaretPosition(3);
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (getText().length() <= 3){
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) e.consume();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (getText().length() > 3){
                        MapTheStars.getCommandController().onCommand(getText().substring(3));
                        setText(">> ");
                        e.consume();
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isAlphabetic(e.getKeyChar())){
                    if (!Character.isUpperCase(e.getKeyChar())){
                        e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isLocked()){
            Color t = theme.getBackground().brighter();
            g.setColor(new Color(t.getRed(), t.getGreen(), t.getBlue(), 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(new Color(146, 146, 146));
        g.fillRect(30, 0, getWidth() - 60, 1);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
        setEditable(!locked);
        repaint();
    }

    public JScrollPane wrapArea(){
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setBorder(null);
        return scrollPane;
    }
}
