package com.github.vitineth.map.the.stars.gameplay.level;

import com.github.vitineth.map.the.stars.gameplay.room.l1.L1R1;
import com.github.vitineth.map.the.stars.gameplay.room.l1.L1R2;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class L1 extends Level {

    public L1() {
        super("Main", 0);

        setStarting(new L1R1(this));
        new L1R2(this);
    }
}
