package com.github.vitineth.map.the.stars.gameplay.room;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.Player;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.util.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
public abstract class Room {

    protected HashMap<String, Item> items = new HashMap<>();
    protected HashMap<String, String> descriptions = new HashMap<>();
    protected HashMap<String, Callback> commands = new HashMap<>();
    private String roomName;
    private String description;
    private String roomID;
    private BufferedImage image;
    private Level parent;

    public Room(String roomName, String description, String roomID, Level parent) {
        this(roomName, description, roomID, null, parent);
    }

    public Room(String roomName, String description, String roomID, BufferedImage image, Level parent) {
        this.image = image;
        this.roomName = roomName;
        this.description = description;
        this.roomID = roomID;
        this.parent = parent;

        parent.addRoom(roomID, this);

        setupDefaults();
        setupItems();
        setupDescriptions();
        setupCommands();
    }

    public void enterRoom(){
        System.out.println(getDescription());
        MapTheStars.getMtsMainWindow().getInteractivePanel().setImage(getImage());
    }

    protected abstract void setupItems();

    protected abstract void setupDescriptions();

    protected abstract void setupCommands();

    public void handleCommand(String command) {
        boolean success = false;
        for (String regex : commands.keySet()) {
            if (command.matches("(?i)" + regex)) {
                commands.get(regex).callback();
                success = true;
            }
        }
        if (!success) {
            System.err.println("You take a moment and reconsider.");
        }
    }

    private void setupDefaults() {
        descriptions.put("ROOM", description);
        commands.put(CommandDefaults.INSPECT.getRegex() + "ROOM", () -> System.out.println(descriptions.get("ROOM")));
    }

    protected Player getPlayer() {
        return MapTheStars.getPlayer();
    }

    protected boolean moveToRoom(String id) {
        if (getPlayer().getLevel().getRooms().containsKey(id)) {
            getPlayer().setRoom(getPlayer().getLevel().getRooms().get(id));
            return true;
        } else {
            return false;
        }
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Level getParent() {
        return parent;
    }

    public void setParent(Level parent) {
        this.parent = parent;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
