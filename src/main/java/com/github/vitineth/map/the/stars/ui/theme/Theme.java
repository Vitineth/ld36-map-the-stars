package com.github.vitineth.map.the.stars.ui.theme;

import java.awt.*;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class Theme {

    private Color background;
    private Color foreground;
    private Font font;

    public Theme(Color background, Color foreground, Font font) {
        this.background = background;
        this.foreground = foreground;
        this.font = font;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
