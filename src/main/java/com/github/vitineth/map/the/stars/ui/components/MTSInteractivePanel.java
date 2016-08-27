package com.github.vitineth.map.the.stars.ui.components;

import com.github.vitineth.map.the.stars.gameplay.puzzle.Puzzle;
import com.github.vitineth.map.the.stars.ui.theme.Theme;

import javax.swing.*;
import java.awt.*;
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
public class MTSInteractivePanel extends JPanel {

    private BufferedImage image;
    private Theme theme;
    private Puzzle puzzle;
    public int[] clickOffset = new int[]{0, 0};

    public MTSInteractivePanel(Theme theme) {
        this.theme = theme;
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
        if (puzzle != null){
            BufferedImage frame = puzzle.getActiveFrame();
            double hfr = (double) getHeight() / (double) frame.getHeight();
            BufferedImage scale = new BufferedImage((int) (frame.getWidth() * hfr), (int) (frame.getHeight() * hfr), frame.getType());
            Graphics2D sg = scale.createGraphics();
            sg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            sg.drawImage(frame, 0, 0, scale.getWidth(), scale.getHeight(), null);
            g2d.drawImage(scale, (getWidth() - scale.getWidth()) / 2, (getHeight() - scale.getHeight()) / 2, null);
            clickOffset = new int[]{(getWidth() - scale.getWidth()) / 2, (getHeight() - scale.getHeight()) / 2};
            puzzle.updateDrawSize(new Dimension(scale.getWidth(), scale.getHeight()));

            if (puzzle.isComplete()){
                completePuzzle();
            }
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }
    
    public void launchPuzzle(Puzzle puzzle){
        this.puzzle = puzzle;
        enableInputMethods(true);
        addKeyListener(puzzle);
        addMouseListener(puzzle);
        puzzle.puzzleLaunched(this);
        repaint();
    }

    public void completePuzzle(){
        if (puzzle == null)return;
        removeKeyListener(puzzle);
        removeMouseListener(puzzle);
        enableInputMethods(false);
        puzzle = null;
        repaint();
    }
}
