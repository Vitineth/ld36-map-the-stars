package com.github.vitineth.map.the.stars.gameplay.command;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public enum CommandDefaults {

    INSPECT("(inspect|examine|check( over)?|scrutini(z|s)e|investigate|survey|study|(go |look )over|(take a )?look at|pore over|view|scan|observe|explore) "),
    PICK_UP("(seize|grasp|snatch|(seize|grab|take|catch|lay) hold of|take a grip of|pick up|grab|lift|take|retrieve) "),
    EXIT("(exit|leave|use exit|go out)");

    private String regex;

    CommandDefaults(String regex){
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public String toString() {
        return getRegex();
    }
}
