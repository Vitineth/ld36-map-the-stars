package com.github.vitineth.map.the.stars.gameplay.level;

import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.gameplay.room.l2.*;

import java.util.HashMap;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class L2 extends Level{

    public L2() {
        super("L2", 1);

        setStarting(new L2R1(this));
        new L2R2(this);
        new L2R3(this);
        new L2R4(this);
        new L2R5(this);
        new L2R6(this);
        new L2R7(this);
    }
}
