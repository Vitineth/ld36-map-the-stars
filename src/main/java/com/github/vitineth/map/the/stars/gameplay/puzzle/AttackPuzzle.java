package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.util.Callback;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class AttackPuzzle extends Puzzle {

    private int localhp = 20;
    private int attackhp = 20;
    private Font custom = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
    private BufferedImage attacker;
    private Random random;
    private HashMap<Rectangle, Callback> bounds = new HashMap<>();
    private String reviveRoom = "l1r1";

    public AttackPuzzle(String reviveRoom, InputStream attackStream, int attackhp) {
        super(new Dimension(1920, 1080));
        this.reviveRoom = reviveRoom;
        this.random = new Random();
        this.attackhp = attackhp;

        try {
            custom = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/AMERIKA_.ttf"));
            attacker = ImageIO.read(attackStream);
        } catch (FontFormatException | IOException e) {
            Log.s("MapTheStars/AttackPuzzle", "Failed to load custom font or image.", e);
        }
    }

    @Override
    public BufferedImage getActiveFrame() {
        bounds.clear();
        BufferedImage out = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = out.createGraphics();
        g.drawImage(attacker, 0, 0, 1920, 1080, null);
        {
            g.setColor(Color.WHITE);
            g.setFont(custom.deriveFont(132f));
            g.drawString("WOLF", 78, 162);

            g.setFont(custom.deriveFont(52f));
            g.drawString("HP: " + attackhp + "/20", 91, 284);

            drawHPBar(attackhp, 20, 93, 310, 760, 50, g);

            g.drawString("ATTACK", 92, 446);
            bounds.put(new Rectangle(92, 446 - 70, g.getFontMetrics().charsWidth("ATTACK".toCharArray(), 0, 6), 70), new Callback() {
                @Override
                public void callback() {
                    if (random.nextInt(3) != 0) {
                        //Success
                        System.out.println("You swipe at the dog and catch it slightly. It wimpers in pain but anger fills its eyes");
                        attackhp -= random.nextInt(5) + 1;
                    } else {
                        System.out.println("You go to attack the dog but it dodges!");
                    }
                    simulateDogAttack();
                    update();
                }
            });
            g.drawString("RUN", 92, 516);
            bounds.put(new Rectangle(92, 516 - 70, g.getFontMetrics().charsWidth("RUN".toCharArray(), 0, 3), 70), new Callback() {
                @Override
                public void callback() {
                    System.out.println("You try to run away but the wolf quickly catches up and attacks");
                    simulateDogAttack();
                    update();
                }
            });
            g.drawString("HIDE", 92, 586);
            bounds.put(new Rectangle(92, 586 - 70, g.getFontMetrics().charsWidth("HIDE".toCharArray(), 0, 4), 70), new Callback() {
                @Override
                public void callback() {
                    System.out.println("You try to run and duck behind a column but the wolf can sniff you out.");
                    simulateDogAttack();
                    update();
                }
            });

            g.drawString("YOUR HP: " + localhp + "/20", 90, 825);
            drawHPBar(localhp, 20, 93, 877, 758, 47, g);
        }
        g.dispose();
        return out;
    }

    private void update(){
        if (attackhp <= 0){
            System.out.println("You manage to kill the wolf, its corpse falls to the ground heavily, blood pooling around its head, mixing with its fur tainting it red.");
            panel.completePuzzle();
        }
        if (localhp <= 0){
            System.out.println("The wolf manages to land a finishing blow and you can feel the loss of blood take over. The world fades to black...");
            panel.completePuzzle();
            MapTheStars.getPlayer().setRoom(MapTheStars.getPlayer().getLevel().getRooms().get(reviveRoom));
            System.out.println("The world fades into view and you gasp suddenly. Apparently this world will not let you die, wherever you are.");
            MapTheStars.getPlayer().getRoom().reset();
            MapTheStars.getPlayer().getRoom().enterRoom();
        }
        panel.repaint();
    }

    private void simulateDogAttack() {
        if (random.nextInt(3) == 0) {
            System.out.println("The dog lunges at you but you realise in time and sidestep.");
        } else {
            System.out.println("The log lunges and wraps its teeth around your leg. You cry out in pain and kick it away");
            localhp -= random.nextInt(4) + 1;
        }
    }

    private void drawHPBar(int hp, int max, int x, int y, int w, int h, Graphics2D g) {
        Color c = g.getColor();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.decode("#b20000"));
        int hph = (int) (w * ((double) hp / (double) max));
        g.fillRect(x, y, hph, h);

        g.setStroke(new BasicStroke(2f));
        g.setColor(Color.decode("#680000"));
        g.drawRect(x, y, w, h);
        g.setColor(c);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Rectangle bound : bounds.keySet()){
            if (bound.contains(e.getPoint())){
                bounds.get(bound).callback();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}