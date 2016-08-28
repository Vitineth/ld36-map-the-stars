package com.github.vitineth.map.the.stars.gameplay.room.l3;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.ChatPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.util.chat.ChatManager;
import com.github.vitineth.map.the.stars.util.chat.ChatNode;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class L3R1 extends Room{

    public L3R1(Level parent) {
        super("L3 Bedroom", "You wake up in your bedroom, neatly tucked into bed to the concerned face of Fei standing above you. His face morphs into relief as he sees you open your eyes, He sighs and begins to speak.", "l3r1", parent);
    }

    @Override
    public void enterRoom() {
        super.enterRoom();
        ChatNode n14 = new ChatNode(14, "I'll set off right away", "Good luck.", true, true);
        ChatNode n13 = new ChatNode(13, "Oh, yeah. Sure", "The emperor will be very pleased, you will need to bring it too him soon though.", true, false);
        ChatNode n12 = new ChatNode(12, "No? Did I", "Apparently so. The emperor will be very pleased, you will need to bring it too him soon though.", true, false);
        ChatNode n11 = new ChatNode(11, "Progress?", "You've managed to catalogue a large number. You wrote it down in your book remember", true, false);
        ChatNode n10 = new ChatNode(10, "I touched some of your mixture and everything went weird", "My mixture? I knew that it wasn't perfect but I thought it was better than that. I'm sorry about that. Even still, with all of that, I'm impressed with the progress you've made here", true, false);
        ChatNode n9 = new ChatNode(9, "I passed out", "How? What happened?", true, false);
        ChatNode n8 = new ChatNode(8, "I don't kow", "Could you try and describe what happened?", true, false);
        ChatNode n7 = new ChatNode(7, "I started hallucinating", "Hallucinating? Really? How did that happen?", true, false);
        ChatNode n6 = new ChatNode(6, "It can't have been that long", "It has, what happened to you?", true, false);
        ChatNode n5 = new ChatNode(5, "3 days!?", "What happened to you?", true, false);
        ChatNode n4 = new ChatNode(4, "How did you get here?", "I travelled back. I been away for three days.", true, false);
        ChatNode n3 = new ChatNode(3, "How long have you been away", "I was away for three days. Remember, I left you a note. I'm impressed with the progress you've made.", true, false);
        ChatNode n2 = new ChatNode(2, "Where have you been?", "I've been to see the greeks. I told you that, remember", true, false);
        ChatNode n1 = new ChatNode(1, "INITIAL", "\"<player name?>!\" he says worridly \"are you alright? What happened here?\"", true, false);

        n1.addOptions(n2, n3, n4);

        n2.addOptions(n2, n3, n4);
        n3.addOptions(n5, n6);
        n4.addOptions(n5, n6);

        n5.addOptions(n7, n8, n9, n10);
        n6.addOptions(n7, n8, n9, n10);

        n7.addOptions(n7, n8, n9, n10);
        n8.addOptions(n7, n8, n9, n10);
        n9.addOptions(n7, n8, n9, n10);

        n10.addOptions(n11);

        n11.addOptions(n12, n13);

        n12.addOptions(n14);
        n13.addOptions(n14);

        ChatManager manager = new ChatManager(() -> moveToRoom("l3r2"), n1);

        MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new ChatPuzzle(manager));
    }

    @Override
    protected void setupItems() {

    }

    @Override
    protected void setupDescriptions() {

    }

    @Override
    protected void setupCommands() {

    }
}
