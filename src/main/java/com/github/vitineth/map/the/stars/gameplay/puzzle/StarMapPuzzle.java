package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.log.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class StarMapPuzzle extends Puzzle {

    private BufferedImage base;
    private BufferedImage[] cogs;
    private int rotationStep;
    private int[] steps = new int[4];

    public StarMapPuzzle(){
        this(6);
    }

    public StarMapPuzzle(int rotationSteps) {
        super(new Dimension(1920, 1080));
        this.rotationStep = 360 / rotationSteps;
        try {
            base = ImageIO.read(getClass().getResourceAsStream("/images/rotation/star-cog-base.png"));
            setSize(new Dimension(base.getWidth(), base.getHeight()));
            cogs = new BufferedImage[4];
            cogs[0] = ImageIO.read(getClass().getResourceAsStream("/images/rotation/star-cog-1.png"));
            cogs[1] = ImageIO.read(getClass().getResourceAsStream("/images/rotation/star-cog-2.png"));
            cogs[2] = ImageIO.read(getClass().getResourceAsStream("/images/rotation/star-cog-3.png"));
            cogs[3] = ImageIO.read(getClass().getResourceAsStream("/images/rotation/star-cog-4.png"));
            Random random = new Random();

            for (int i = 0; i < cogs.length; i++) {
                steps[i] = random.nextInt(6) + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getActiveFrame() {
        BufferedImage out = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = out.createGraphics();
        double nw = getSize().getHeight() / base.getHeight();
        int x = (int) (getSize().width - (base.getWidth() * nw)) / 2;
        int y = (int) (getSize().height - (base.getHeight() * nw)) / 2;
        g.drawImage(base, x, y, (int) (base.getWidth() * nw), (int) (base.getHeight() * nw), null);
        for (int i = 0; i < cogs.length; i++) {
            g.drawImage(rotate(cogs[i], steps[i]), x, y, (int) (base.getWidth() * nw), (int) (base.getHeight() * nw), null);
        }
        g.dispose();
        return out;
    }

    private BufferedImage rotate(BufferedImage in, int step) {
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
        Graphics2D g = out.createGraphics();
        AffineTransform oldtrans = new AffineTransform();
        AffineTransform trans = new AffineTransform();

        trans.setToIdentity();
        trans.rotate(Math.toRadians(step * rotationStep), in.getWidth() / 2, in.getHeight() / 2);
        g.setTransform(trans);
        g.drawImage(in, 0, 0, in.getWidth(), in.getHeight(), null);
        trans.setToIdentity();
        g.setTransform(oldtrans);
        g.dispose();
        return out;
    }

    @Override
    public boolean isComplete() {
        return false;
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
        double r = Math.sqrt(Math.pow(e.getX() - (getSize().getWidth() / 2), 2) + Math.pow(e.getY() - (getSize().getHeight() / 2), 2));
        int shift = -1;
        if (r < 153){
            shift = 3;
        }
        if (r > 153 && r < 274) {
            shift = 2;
        }
        if (r > 274 && r < 390) {
            shift = 1;
        }
        if (r > 390 && r < 500) {
            shift = 0;
        }
        if (shift >= 0) {
            steps[shift] += 1;
            if (steps[shift]>= 6) steps[shift] = 0;
        }
        panel.repaint();
        Log.d("StarMapPuzzle", Arrays.toString(steps));
        if (steps[0] == 0 && steps[1] == 0 && steps[2] == 0 && steps[3] == 0){
            panel.completePuzzle();
            panel.repaint();
        }
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