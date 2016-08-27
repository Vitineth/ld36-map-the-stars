package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;
import com.github.vitineth.map.the.stars.util.ColorMapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
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
public class AlignPuzzle extends Puzzle {
    private BufferedImage[] stars = new BufferedImage[4];
    private BufferedImage[] controls = new BufferedImage[8];
    private BufferedImage star_final = null;
    private BufferedImage puzzle_overlay;
    private int starIndex = 0;
    private int controlIndex = 0;

    @Override
    public void puzzleLaunched(MTSInteractivePanel panel) {
        super.puzzleLaunched(panel);

        try {
            stars[0] = ImageIO.read(getClass().getResourceAsStream("/images/stars/star-blur-1.png"));
            stars[1] = ImageIO.read(getClass().getResourceAsStream("/images/stars/star-blur-2.png"));
            stars[2] = ImageIO.read(getClass().getResourceAsStream("/images/stars/star-blur-3.png"));
            stars[3] = ImageIO.read(getClass().getResourceAsStream("/images/stars/star-blur-4.png"));

            controls[0] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos1.png"));
            controls[1] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos2.png"));
            controls[2] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos3.png"));
            controls[3] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos4.png"));
            controls[4] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos5.png"));
            controls[5] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos6.png"));
            controls[6] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos7.png"));
            controls[7] = ImageIO.read(getClass().getResourceAsStream("/cogs/control_pos8.png"));

            puzzle_overlay = ImageIO.read(getClass().getResourceAsStream("/images/puzzle_overlay.png"));
            star_final = ImageIO.read(getClass().getResourceAsStream("/images/stars/star-blur-5.png"));
            BufferedImageOp lookup = new LookupOp(new ColorMapper(Color.RED, new Color(54, 54, 54)), null);
            puzzle_overlay = lookup.filter(puzzle_overlay, null);
        } catch (IOException e) {
            Log.s("AlignPuzzle", "An image failed to load! This means the puzzle will not be able to function correctly!", e);
        }
    }

    @Override
    public BufferedImage getActiveFrame() {
        if (puzzle_overlay != null) {
            BufferedImage output = new BufferedImage(puzzle_overlay.getWidth(), puzzle_overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);
            BufferedImage control = scale(controls[controlIndex], 300, 335);
            BufferedImage star = getStars();
            Graphics g = output.createGraphics();
            g.drawImage(star, 472, 103, null);
            g.drawImage(puzzle_overlay, 0, 0, null);
            g.drawImage(control, 95, 197, null);
            return output;
        }
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    private BufferedImage scale(BufferedImage image, int w, int h) {
        BufferedImage scale = new BufferedImage(w, h, image.getType());
        scale.getGraphics().drawImage(image, 0, 0, w, h, null);
        return scale;
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

    public BufferedImage getStars() {
        BufferedImage out = new BufferedImage(515, 515, BufferedImage.TYPE_INT_ARGB);
        if (starIndex == 4) {
            out.getGraphics().drawImage(scale(star_final, 515, 515), 0, 0, null);
        } else if (controlIndex % 2 != 0) {
            Graphics2D g2d = out.createGraphics();
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect(0, 0, out.getWidth(), out.getHeight());
            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.drawImage(scale(stars[starIndex], out.getWidth(), out.getHeight()), 0, 0, null);
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
            g2d.drawImage(scale(stars[starIndex == stars.length - 1 ? 0 : starIndex + 1], 515, 515), 0, 0, null);
            g2d.dispose();
        } else {
            out.getGraphics().drawImage(scale(stars[starIndex], 515, 515), 0, 0, null);
        }
        return out;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 95 && e.getY() > 197 && e.getX() < 95 + controls[controlIndex].getWidth() && e.getY() < 197 + controls[controlIndex].getHeight() && starIndex != 3) {
            controlIndex++;
            if (controlIndex >= controls.length) controlIndex = 0;
            if (controlIndex % 2 == 0) {
                starIndex++;
                if (starIndex == 3) {
                    new Thread(() -> {
                        try {
                            Thread.sleep(500);
                            starIndex = 4;
                            panel.repaint();
                            Thread.sleep(500);
                            panel.completePuzzle();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }).start();
                }
            }
            if (starIndex >= stars.length) starIndex = 0;

        }
        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        panel.repaint();
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

}