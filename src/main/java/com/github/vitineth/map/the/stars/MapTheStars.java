package com.github.vitineth.map.the.stars;

import com.github.vitineth.map.the.stars.gameplay.Player;
import com.github.vitineth.map.the.stars.gameplay.command.CommandController;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.L1;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.window.MTSMainWindow;

import javax.swing.*;
import java.io.IOException;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class MapTheStars {

    public static final String GAME_TITLE = "Map The Stars";
    public static final String VERSION = "1.0-SNAPSHOT";
    private static final String LOG_NAME = MapTheStars.class.getSimpleName();

    private static MTSMainWindow mtsMainWindow;
    private static Player player;
    private static CommandController commandController = new CommandController();

    public static void main(String[] args) throws IOException {
        Log.i(LOG_NAME, "Launching " + GAME_TITLE + "/" + VERSION + "...");
        mtsMainWindow = new MTSMainWindow();
        SwingUtilities.invokeLater(() -> mtsMainWindow.setVisible(true));
        player = new Player("Ryan", new L1());
        player.getRoom().enterRoom();
    }

    public static Player getPlayer() {
        return player;
    }

    public static CommandController getCommandController() {
        return commandController;
    }

    public static MTSMainWindow getMtsMainWindow() {
        return mtsMainWindow;
    }
}