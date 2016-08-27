package com.github.vitineth.map.the.stars.util;

import java.awt.*;
import java.awt.image.LookupTable;
import java.util.Arrays;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public
class ColorMapper
        extends LookupTable {

    private final int[] from;
    private final int[] to;

    public ColorMapper(Color from,
                       Color to) {
        super(0, 4);

        this.from = new int[]{
                from.getRed(),
                from.getGreen(),
                from.getBlue(),
                from.getAlpha(),
        };
        this.to = new int[]{
                to.getRed(),
                to.getGreen(),
                to.getBlue(),
                to.getAlpha(),
        };
    }

    @Override
    public int[] lookupPixel(int[] src,
                             int[] dest) {
        if (dest == null) {
            dest = new int[src.length];
        }

        int[] newColor = (Arrays.equals(src, from) ? to : src);
        System.arraycopy(newColor, 0, dest, 0, newColor.length);

        return dest;
    }
}