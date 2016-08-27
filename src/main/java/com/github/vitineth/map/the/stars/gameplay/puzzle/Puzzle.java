package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
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
public abstract class Puzzle implements MouseListener,KeyListener{

    protected MTSInteractivePanel panel;
    protected Dimension drawSize;
    private Dimension size;

    public Puzzle(Dimension size) {
        this.size = size;
    }

    public void puzzleLaunched(MTSInteractivePanel panel){
        this.panel = panel;
    }

    public void updateDrawSize(Dimension drawSize){
        this.drawSize = drawSize;
    }

    public abstract BufferedImage getActiveFrame();

    public abstract boolean isComplete();

    protected void setSize(Dimension size) {
        this.size = size;
    }

    public Dimension getSize() {
        return size;
    }
}
