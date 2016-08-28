package com.github.vitineth.map.the.stars.ui.components;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.puzzle.Puzzle;
import com.github.vitineth.map.the.stars.ui.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * The panel to house all graphics code and puzzles that the user must interact with.
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class MTSInteractivePanel extends JPanel implements MouseListener {

    private BufferedImage image;
    private Theme theme;
    private Puzzle puzzle;

    private int[] clickOffset = new int[]{0, 0};
    private int[] drawSize = new int[]{0, 0};

    public MTSInteractivePanel(Theme theme) {
        this.theme = theme;
        enableInputMethods(true);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(theme.getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (image != null && puzzle == null) {
            double hfr = (double) getHeight() / (double) image.getHeight();
            BufferedImage scale = new BufferedImage((int) (image.getWidth() * hfr), (int) (image.getHeight() * hfr), image.getType());
            scale.getGraphics().drawImage(image, 0, 0, scale.getWidth(), scale.getHeight(), null);
            g2d.drawImage(scale, (getWidth() - scale.getWidth()) / 2, (getHeight() - scale.getHeight()) / 2, null);
        }
        if (puzzle != null) {
            BufferedImage frame = puzzle.getActiveFrame();
            double hfr = (double) getHeight() / (double) frame.getHeight();
            BufferedImage scale = new BufferedImage((int) (frame.getWidth() * hfr), (int) (frame.getHeight() * hfr), frame.getType());
            Graphics2D sg = scale.createGraphics();
            sg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            sg.drawImage(frame, 0, 0, scale.getWidth(), scale.getHeight(), null);
            g2d.drawImage(scale, (getWidth() - scale.getWidth()) / 2, (getHeight() - scale.getHeight()) / 2, null);

            clickOffset = new int[]{(getWidth() - scale.getWidth()) / 2, (getHeight() - scale.getHeight()) / 2};
            drawSize = new int[]{scale.getWidth(), scale.getHeight()};

            puzzle.updateDrawSize(new Dimension(scale.getWidth(), scale.getHeight()));

            if (puzzle.isComplete()) {
                completePuzzle();
            }
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    public void launchPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        enableInputMethods(true);
        addKeyListener(puzzle);
        puzzle.puzzleLaunched(this);
        repaint();
    }

    public void completePuzzle() {
        if (puzzle == null) return;
        removeKeyListener(puzzle);
        enableInputMethods(false);
        if (puzzle.getCompleteCallback() != null) puzzle.getCompleteCallback().callback();
        MapTheStars.getMtsMainWindow().getInputArea().setLocked(false);
        puzzle = null;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (puzzle != null) mapInput(e, "click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (puzzle != null) mapInput(e, "press");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (puzzle != null) mapInput(e, "release");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (puzzle != null) mapInput(e, "enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (puzzle != null) mapInput(e, "exit");
    }

    private void mapInput(MouseEvent e, String type) {
        int drawX = clickOffset[0];
        int drawY = clickOffset[1];

        int drawW = drawSize[0];
        int drawH = drawSize[1];

        if (e.getX() > drawX && e.getY() > drawY && e.getX() < drawX + drawW && e.getY() < drawY + drawH) {
            //Here we know that the X and Y is at least greater than the click offset so we can just remove that making
            //0 on the image, 0.
            int clickX = e.getX() - drawX;
            int clickY = e.getY() - drawY;

            //Now we need to scale this to the size of the puzzle. Each puzzle will define what size it wants to draw at
            //and we can just cater to that.
            int newX = map(clickX, 0, drawW, 0, (int) puzzle.getSize().getWidth());
            int newY = map(clickY, 0, drawH, 0, (int) puzzle.getSize().getHeight());

            MouseEvent event = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), newX, newY, e.getClickCount(), e.isPopupTrigger(), e.getButton());
            if (type.equals("click")) puzzle.mouseClicked(event);
            if (type.equals("press")) puzzle.mousePressed(event);
            if (type.equals("release")) puzzle.mouseReleased(event);
            if (type.equals("enter")) puzzle.mouseEntered(event);
            if (type.equals("exit")) puzzle.mouseExited(event);
        }
    }

    private int map(int n, int in_min, int in_max, int out_min, int out_max) {
        return (n - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
