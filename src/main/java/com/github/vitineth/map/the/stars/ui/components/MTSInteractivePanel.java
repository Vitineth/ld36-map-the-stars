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

        if (image == null) {
            g.setColor(theme.getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            double wofh = image.getHeight() / getHeight();
            BufferedImage scale = new BufferedImage((int) (image.getWidth() * wofh), (int) (image.getHeight() * wofh), image.getType());
            scale.getGraphics().drawImage(image, 0, 0, scale.getWidth(), scale.getHeight(), null);
        }
    }
}
