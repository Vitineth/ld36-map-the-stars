package com.github.vitineth.map.the.stars.gameplay.command;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.util.CommandCallback;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class CommandController {

    private boolean overwritten;
    private CommandCallback overwriter;

    public void onCommand(String command) {
        MapTheStars.getMtsMainWindow().getOutputPane().addInput(command + "\n");
        if (overwritten && overwriter != null) {
            overwriter.onCommand(command);
        } else {
            MapTheStars.getPlayer().getRoom().handleCommand(command);
        }
    }

    public boolean isOverwritten() {
        return overwritten;
    }

    public void setOverwritten(boolean overwritten) {
        this.overwritten = overwritten;
    }

    public CommandCallback getOverwriter() {
        return overwriter;
    }

    public void setOverwriter(CommandCallback overwriter) {
        this.overwriter = overwriter;
    }
}
