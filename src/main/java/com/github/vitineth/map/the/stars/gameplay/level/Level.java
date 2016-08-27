package com.github.vitineth.map.the.stars.gameplay.level;

import com.github.vitineth.map.the.stars.gameplay.room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class Level {

    private String levelName;
    private int levelID;
    private HashMap<String, Room> rooms;
    private Room starting;

    public Level(String levelName, int levelID, HashMap<String, Room> rooms) {
        this.levelName = levelName;
        this.levelID = levelID;
        this.rooms = rooms;
    }

    public Level(String levelName, int levelID) {
        this.levelName = levelName;
        this.levelID = levelID;
        this.rooms = new HashMap<>();
    }

    public void addRoom(String id, Room room){
        rooms.put(id, room);
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    public Room getStarting() {
        return starting;
    }

    public void setStarting(Room starting) {
        this.starting = starting;
    }
}
