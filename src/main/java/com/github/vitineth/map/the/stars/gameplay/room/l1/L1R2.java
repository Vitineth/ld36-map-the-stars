package com.github.vitineth.map.the.stars.gameplay.room.l1;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.command.CommandDefaults;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.gameplay.puzzle.AlignPuzzle;
import com.github.vitineth.map.the.stars.gameplay.room.Room;
import com.github.vitineth.map.the.stars.log.Log;

import javax.imageio.ImageIO;
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
public class L1R2 extends Room {

    private boolean deviceInspected = false;
    private boolean controlInserted = false;
    private boolean[] lensInserted = new boolean[]{false, false, false};
    private boolean[] frameInserted = new boolean[]{false, false, false};
    private boolean chemicalInserted = false;
    private boolean deviceAligned = false;

    public L1R2(Level parent) {
        super("Main Room", "You enter into a large room crafted of stone and rammed earth. The walls are rather bleak but have been covered in places with paper bearing scribblings of equations and designs. The sides of the rooms are bordered with tables which are covered in various bits of metal, glass, wood and gadgets. Some looks highly complex with various pieces locking into one another. The center of the room houses the most important instrument. A large cylinder shaped device is attached to a stand, one side pointing towards the skies through the only open portion of the building. There is one door to the side north of the room, inlaid slightly into the wall.", "l1r2", parent);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/rooms/l1r2.png")));
        } catch (IOException e) {
            Log.s("RoomL1R2", "Failed to load the level image! The interactive pane will be blank!", e);
        }
    }

    @Override
    protected void setupItems() {
        items.put("GADGET", new Item("Unknown Gadget", 1, "The gadget seems to made of a mix of metal and wood crafted together to form what looks almost like a control. There is a small cog to the side that moves some of the internal pieces as it is turned. It is of a unique hexagonal shape that doesn't seem to match anything on the table."));
    }

    @Override
    protected void setupDescriptions() {
        descriptions.put("DEVICE", "The device in the center of the room is impressive in scale, reaching up towards the roof. It is one big cylindrical shape with slots carved into the side. It is made of a mix of wood and metal expertly mixed together. It rests heavily in a frame which is buried deep into the ground which allows it to move and swivel with the help of some controls attached to the side, each slotting into a hexagonal hole along the side. There seems to be one hole in the side in the same shape of the controls along the bottom of the device. Along the bottom at around shoulder height, there is a small section sticking out with a piece of glass sat just inside. It looks like you would look through it.");
        descriptions.put("TABLE(S)?", "The tables are nothing special, made out of various types of wood. On the top lies a collection of raw materials and an intricate gadget.");
        descriptions.put("PAPER|PARCHMENT", "Just like the bedroom, there is paper scattered around the room with almost unreadable scribblings and equations emblazoned across it. There doesn't seem to be anything work reading on them so you place them back down.");
        descriptions.put("ROOF|SKY", " The roof splits open down the center revealing the night sky shining above you, the device in the center of the room points directly through the gap.");
    }

    @Override
    protected void setupCommands() {
        commands.put(CommandDefaults.INSPECT + "GADGET", () -> System.out.println(items.get("GADGET").getDescription()));
        commands.put(CommandDefaults.PICK_UP + "(DEVICE|GADGET)", () -> {
            if (items.containsKey("GADGET")) {
                System.out.print("You pick up the gadget from the table ");
                if (deviceInspected)
                    System.out.println("and recognise the shape to match some of those already inserted in the device in the centre of the room.");
                else System.out.println("but you do not recognise where it could fit.");

                getPlayer().addItem(items.get("GADGET"));
                items.remove("GADGET");
            } else {
                System.out.println("There is no gadget to pick up in the room");
            }
        });
        commands.put("(USE (GADGET|DEVICE) (ON|WITH)|INSERT (GADGET|DEVICE) INTO|PUSH (GADGET|DEVICE) INTO|COMBINE (GADGET|DEVICE) WITH) (DEVICE|TELESCOPE|MAIN DEVICE|DEVICE IN( THE)? CENTRE( OF THE ROOM)?)", () -> {
            if (deviceInspected) {
                if (!controlInserted) {
                    System.out.println("You push the gadget into the slot on the side of the device and it slides in gently, locking into place with a soft clicking sound. ");
                    getPlayer().removeItem("Unknown Gadget");
                    controlInserted = true;
                } else {
                    System.out.println("You've already put the gadget into the device.");
                }
            } else {
                System.out.println("You don't know how the gadget would fit into the device.");
            }
        });
        commands.put("(" + CommandDefaults.USE + "DEVICE)|(LOOK THROUGH DEVICE)", () -> {
            if (!frameInserted[0] || !frameInserted[1] || !frameInserted[2] || !lensInserted[0] || !lensInserted[1] || !lensInserted[2]) {
                System.out.println("The image through the lens is still blurry. Some of the slots don't seem to have any frames or lenses in. Perhaps that could have something to do with it.");
            } else {
                System.out.println("The image seems to be clearer now but it's still not perfect.");
                if (controlInserted) {
                    if (!deviceAligned) {
                        if (chemicalInserted) {
                            System.out.println("You remember the control you inserted on the side of the device and begin trying to adjust the lenses.");
                            MapTheStars.getMtsMainWindow().getInteractivePanel().launchPuzzle(new AlignPuzzle());
                            deviceAligned = true;
                        }else{
                            System.out.println("You look through the lens but realise that Fei's mixture is missing. If only you could find it.");
                        }
                    } else {
                        System.out.println("You've already lined up the device and seen the stars.");
                    }
                } else {
                    System.out.println("If only there was some way you could adjust the lenses to make the image clearer.");
                }
            }
        });
        commands.put("(" + CommandDefaults.USE + "(LIQUID|SOLUTION|LENS SOLUTION|LENSES SOLUTION|LENS ENHANCEMENT SOLUTION|LENS LIQUID|CHEMICAL|FEIS CHEMICAL) ON DEVICE)|(POUR LIQUID IN DEVICE)", () -> {
            if (getPlayer().containsItem("Lens Enhancement Solution")) {
                if (!frameInserted[0] || !frameInserted[1] || !frameInserted[2] || !lensInserted[0] || !lensInserted[1] || !lensInserted[2]) {
                    System.out.println("Shouldn't you insert all the lenses and frames before you add the liquid?");
                } else {
                    System.out.println("You pull out the lens enhancement liquid and begin to pour it into the labelled slot on the side of the device. ");
                    chemicalInserted = true;
                }
            } else {
                System.out.println("You don't have any liquids to apply on the machine.");
            }
        });
        commands.put("(" + CommandDefaults.USE + "(ON|TO)|USE (FRAME(S)?) ON DEVICE|INSERT FRAME(S)? INTO DEVICE|COMBINE FRAME(S)? AND DEVICE|COMBINE FRAME(S)? WITH DEVICE|PUT FRAME(S)? IN DEVICE)", () -> {
            if (getPlayer().containsItem("Lens Frames")) {
                if (frameInserted[0] && frameInserted[1] && frameInserted[2]) {
                    System.out.println("All the frames are inserted!");
                } else {
                    if (!frameInserted[0]) {
                        System.out.println("One frame inserted, two to go!");
                        frameInserted[0] = true;
                    } else if (!frameInserted[1]) {
                        System.out.println("Two frames inserted, one to go!");
                        frameInserted[1] = true;
                    } else {
                        System.out.println("All three frames inserted!");
                        frameInserted[2] = true;
                    }
                    for (Item i : getPlayer().getInventory()) {
                        if (i.getName().equals("Lens Frames")) {
                            i.setAmount(i.getAmount() - 1);
                            if (i.getAmount() == 0) {
                                getPlayer().removeItem(i);
                            }
                            break;
                        }
                    }
                }
            }else{
                System.out.println("You have no frames.");
            }
        });
        commands.put("(" + CommandDefaults.USE + "(ON|TO)|USE (LENS(ES)?) ON DEVICE|INSERT LENS(ES)? INTO DEVICE|COMBINE LENS(ES)? AND DEVICE|COMBINE LENS(ES)? WITH DEVICE|PUT LENS(ES)? IN DEVICE)", () -> {
            if (getPlayer().containsItem("Lens")) {
                if (lensInserted[0] && lensInserted[1] && lensInserted[2]) {
                    System.out.println("All the lenses are inserted!");
                } else {
                    if (!lensInserted[0]) {
                        System.out.println("One lens inserted, two to go!");
                        lensInserted[0] = true;
                    } else if (!lensInserted[1]) {
                        System.out.println("Two lens inserted, one to go!");
                        lensInserted[1] = true;
                    } else  {
                        System.out.println("All three lens inserted!");
                        lensInserted[2] = true;
                    }
                    for (Item i : getPlayer().getInventory()) {
                        if (i.getName().equals("Lens")) {
                            i.setAmount(i.getAmount() - 1);
                            if (i.getAmount() == 0){
                                getPlayer().removeItem(i);
                            }
                            break;
                        }
                    }
                }
            }else{
                System.out.println("You have no lenses.");
            }
        });
        commands.put(CommandDefaults.DOOR + "((NORTH|FORWARD|FRONT) DOOR)", () -> moveToRoom("l1r3"));
        commands.put(CommandDefaults.DOOR + "((SOUTH|BACKWARD|BACK) DOOR)", () -> moveToRoom("l1r1"));

        for (String key : descriptions.keySet()) {
            if (key.equals("DEVICE")) {
                commands.put(CommandDefaults.INSPECT + key, () -> {
                    System.out.println(descriptions.get(key));
                    deviceInspected = true;
                });
            } else {
                commands.put(CommandDefaults.INSPECT + key, () -> System.out.println(descriptions.get(key)));
            }
        }
    }
}
