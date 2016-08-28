package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;
import com.github.vitineth.map.the.stars.util.Callback;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public abstract class Puzzle implements MouseListener, KeyListener, MouseMotionListener {

    protected MTSInteractivePanel panel;
    protected Dimension drawSize;
    private Dimension size;
    private Callback completeCallback;

    public Puzzle(Dimension size) {
        this.size = size;
    }

    public void setCompleteCallback(Callback completeCallback) {
        this.completeCallback = completeCallback;
    }

    public Callback getCompleteCallback() {
        return completeCallback;
    }

    public void puzzleLaunched(MTSInteractivePanel panel) {
        this.panel = panel;
        MapTheStars.getMtsMainWindow().getInputArea().setLocked(true);
    }

    public void updateDrawSize(Dimension drawSize) {
        this.drawSize = drawSize;
    }

    public abstract BufferedImage getActiveFrame();

    public abstract boolean isComplete();

    public Dimension getSize() {
        return size;
    }

    protected void setSize(Dimension size) {
        this.size = size;
    }
}
