package com.github.vitineth.map.the.stars.gameplay.room.l3;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.ChatPuzzle;
import com.github.vitineth.map.the.stars.gameplay.puzzle.Puzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;
import com.github.vitineth.map.the.stars.util.ImageFrame;
import com.github.vitineth.map.the.stars.util.chat.ChatManager;
import com.github.vitineth.map.the.stars.util.chat.ChatNode;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L3R3 extends Room {

    private ChatManager manager;

    public L3R3(Level parent) {
        super("L3 Palace", "After even more travelling you finally reach the palace. As you walk it in feels familiar and suddenly the rooms seems to flash slightly, visions of the previous time you were in a building like this. Not just a building like this, this building. It's different now though. No cell attached to the side, the wolf replaced with sprawling tables at the sides of the room and a throne placed in the centre surrounded by the statues. The emperor sits in his throne and looks to you expectantly.", "l3r3", parent);

        ChatNode n0 = new ChatNode(-1, "Okay", "I am commanding you to go with the fleet. The need to know exactly where to go. I expect no retaliation or you will be taken by force.", true, true);

        ChatNode n1 = new ChatNode(0, "What are you saying?", "I am commanding you to go with the fleet. The need to know exactly where to go. I expect no retaliation or you will be taken by force.", true, false);
        ChatNode n2 = new ChatNode(1, "You want to me to go with the fleet?", "I am not wanting you to, I am commanding you to go with the fleet. The need to know exactly where to go. I expect no retaliation or you will be taken by force.", true, false);

        ChatNode n3 = new ChatNode(2, "I could explain it to them", "That is not enough. They will not understand it as well as you, even with hundreds of hours studying them. Who better to navigate than the man who wrote them?", true, false);
        ChatNode n4 = new ChatNode(3, "I wish them luck", "Luck is not enough in these times. We need guarantees. The only way we will know that they are going in the correct direction is if we had an expert on board.", true, false);
        ChatNode n5 = new ChatNode(4, "Isn't that their job.", "It is and they are very good at it if it somewhere they have been before. But on this voyage we are heading into unknown waters with only the stars to guide our way. We need an expert on those boats. Perhaps the man who wrote the maps?", true, false);
        ChatNode n6 = new ChatNode(5, "The maps should not be too hard to follow", "But there are no guarantees that they will get it right. We would be sure if we had the man responsible for writing the maps aboard the fleet.", true, false);

        ChatNode n7 = new ChatNode(6, "A new device in our facility. It amplifies the scale of the stars to allow us to draw", "Sounds like a breakthrough this will be very useful in navigation. If only we could understand it better.", true, false);
        ChatNode n8 = new ChatNode(7, "With the help of my partner Fei", "Well give him my thanks also, this shall be very useful in navigation. If only our navigators could understand it better.", true, false);

        ChatNode n9 = new ChatNode(8, "With a lot of effort", "I am not joking around here, how did you produce this?", true, false);
        ChatNode n10 = new ChatNode(9, "I'm honestly not sure", "Do not mess with me, how did you make this?", true, false);

        ChatNode n11 = new ChatNode(10, "A map of the stars", "You will respect my authority and call me by your highness or you shall be removed! Bring it to me. How did you produce this?", true, false);
        ChatNode n12 = new ChatNode(11, "A book your highness", "Bring it to me. How did you produce this?", true, false);
        ChatNode n13 = new ChatNode(12, "A star map your highness.", "Bring it to me. How did you produce this?", true, false);

        ChatNode n14 = new ChatNode(13, "INITIAL", "What have you brought me?", true, false);

        n14.addOptions(n13, n12, n11);

        n13.addOptions(n10, n9, n8, n7);
        n12.addOptions(n10, n9, n8, n7);
        n11.addOptions(n10, n9, n8, n7);

        n10.addOptions(n10, n9, n8, n7);
        n9.addOptions(n10, n9, n8, n7);

        n8.addOptions(n6, n5, n4, n3);
        n7.addOptions(n6, n5, n4, n3);

        n6.addOptions(n1, n2);
        n5.addOptions(n1, n2);
        n4.addOptions(n1, n2);
        n3.addOptions(n1, n2);

        n2.addOptions(n0);
        n1.addOptions(n0);

        manager = new ChatManager(() -> moveToRoom("l3r4"), n14);
        tryImageLoad();
    }

    @Override
    public void enterRoom() {
        super.enterRoom();

    }



    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {
        commands.put("(TALK( WITH| TO| AT)?|INTERACT( WITH| TO| AT)?|CONVERSE( WITH| TO| AT)?)|(" + CommandDefaults.USE + ")|(" + CommandDefaults.INSPECT + ")( KIND| EMPEROR| MAN| THRONE| PERSON)?", () -> {
            MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new ChatPuzzle(manager));
        });
    }
}

