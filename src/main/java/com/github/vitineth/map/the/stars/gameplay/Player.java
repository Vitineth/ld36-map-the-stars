package com.github.vitineth.map.the.stars.gameplay;

import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.room.Room;

import java.util.ArrayList;
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
public class Player {

    private String name;
    private Level level;
    private Room room;
    private List<Item> inventory = new ArrayList<>();

    public Player(String name, Level level, Room room) {
        this.name = name;
        this.level = level;
        this.room = room;
    }

    public Player(String name, Level level) {
        this.name = name;
        this.level = level;
        this.room = level.getStarting();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item){
        inventory.add(item);
        System.out.println(item.getName() + " picked up!");
    }

    public boolean removeItem(Item item){
        return inventory.remove(item);
    }

    public boolean containsItem(String name){
        for (Item item : inventory) if (item.getName().equalsIgnoreCase(name)) return true;
        return false;
    }
}
