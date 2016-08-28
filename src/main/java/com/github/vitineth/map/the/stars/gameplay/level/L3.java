package com.github.vitineth.map.the.stars.gameplay.level;

import com.github.vitineth.map.the.stars.gameplay.room.l3.L3R1;
import com.github.vitineth.map.the.stars.gameplay.room.l3.L3R2;
import com.github.vitineth.map.the.stars.gameplay.room.l3.L3R3;
import com.github.vitineth.map.the.stars.gameplay.room.l3.L3R4;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L3 extends Level {

    public L3() {
        super("Level 3", 2);

        setStarting(new L3R1(this));
        new L3R2(this);
        new L3R3(this);
        new L3R4(this);
    }
}
