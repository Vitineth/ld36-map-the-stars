package com.github.vitineth.map.the.stars.ui.components;

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
        if (image != null) {
            double hfr = (double) getHeight() / (double) image.getHeight();
            BufferedImage scale = new BufferedImage((int) (image.getWidth() * hfr), (int) (image.getHeight() * hfr), image.getType());
            scale.getGraphics().drawImage(image, 0, 0, scale.getWidth(), scale.getHeight(), null);
            g2d.drawImage(scale, (getWidth() - scale.getWidth()) / 2, (getHeight() - scale.getHeight()) / 2, null);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }
}
