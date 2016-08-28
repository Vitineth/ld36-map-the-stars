package com.github.vitineth.map.the.stars.gameplay.puzzle;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.gameplay.items.Item;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;
import com.github.vitineth.map.the.stars.util.ColorMapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class AlchemyPuzzle extends Puzzle {

    private final Random random;
    private boolean hallucination = false;
    private BufferedImage table;
    private BufferedImage crack;
    private List<Circle> circles;
    private List<Mixture> mixture = new ArrayList<>();
    private List<Mixture> correct;

    public AlchemyPuzzle() {
        super(new Dimension(0, 0));
        try {
            table = ImageIO.read(getClass().getResourceAsStream("/puzzle/alchemy/table.png"));
            crack = ImageIO.read(getClass().getResourceAsStream("/puzzle/alchemy/crack.png"));
            setSize(new Dimension(table.getWidth(), table.getHeight()));
        } catch (IOException e) {
            Log.s("AlignPuzzle", "Failed to load the puzzle overlay! This won't display correctly!", e);
        }


        circles = new ArrayList<>();
        circles.add(new Circle(994, 691, 130, "Xaowao (Dark blue liquid)", Color.decode("#4e4d5e")));
        circles.add(new Circle(889, 803, 35, "Hian (Yellow liquid)", Color.decode("#625836")));
        circles.add(new Circle(989, 878, 70, "Zhao (Dark grey liquid)", Color.decode("#3f4447")));
        circles.add(new Circle(910, 947, 40, "Hiacia (Dark green liquid)", Color.decode("#3c4a42")));
        circles.add(new Circle(1164, 836, 120, "Huo (Grey liquid)", Color.decode("#555555")));
        circles.add(new Circle(1094, 954, 40, "Xaobuon (Dark red liquid)", Color.decode("#4a3639")));

        circles.add(new Circle(1714, 346, 90, "Hiancia (Green liquid)", Color.decode("#3d5b39")));
        circles.add(new Circle(1522, 400, 30, "Shaopia (Brown liquid)", Color.decode("#5f3f3e")));
        circles.add(new Circle(1446, 371, 30, "Xianmuo (Light yellow liquid)", Color.decode("#606143")));
        circles.add(new Circle(1537, 273, 65, "Qiawou (Blue liquid)", Color.decode("#433a5c")));
        circles.add(new Circle(1535, 183, 35, "Shian (Pink liquid)", Color.decode("#594058")));
        circles.add(new Circle(1779, 131, 100, "Shaocao (White liquid)", Color.decode("#575757")));


        List<Circle> copy = new CopyOnWriteArrayList<>(circles);
        Collections.shuffle(copy);
        random = new Random();
        correct = new ArrayList<>();
        for (int i = 0; i < random.nextInt(copy.size() - 3) + 3; i++) {
            correct.add(new Mixture(copy.get(i), random.nextInt(9) + 1));
        }
        circles.add(new Circle(1440, 822, 82, "RESET", Color.decode("#ff0000")));
    }

    @Override
    public void puzzleLaunched(MTSInteractivePanel panel) {
        super.puzzleLaunched(panel);
        MapTheStars.getMtsMainWindow().getInputArea().setLocked(true);
        System.out.println("Press the bowl next to the paper to reset the mixture.");
        System.out.println("Lens Enhancement Solution. Note: incorrect mixture causes nausea and some strange visual artifacts -Fei");
        System.out.println(correct.get(0).getAmount() + " drops of " + correct.get(0).getCircle().getCircleID());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    private Color averageColor() {
        if (mixture.size() == 0) return Color.BLACK;
        int r = 0;
        int g = 0;
        int b = 0;
        for (Mixture m : mixture) {
            r += m.getCircle().getCircleColor().getRed();
            g += m.getCircle().getCircleColor().getGreen();
            b += m.getCircle().getCircleColor().getBlue();
        }
        return new Color(r / mixture.size(), g / mixture.size(), b / mixture.size());
    }

    @Override
    public BufferedImage getActiveFrame() {
        BufferedImage out = new BufferedImage(table.getWidth(), table.getHeight(), table.getType());
        Graphics2D g = out.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(table, 0, 0, null);

        g.setColor(averageColor());
//        circles.forEach(circle -> {
//            g.setColor(Color.RED);
//            g.setFont(g.getFont().deriveFont(34f));
//            g.drawOval(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
//            g.drawString(circle.getCircleID(), circle.getX(), circle.getY());
//        });
        g.fillOval(1440 - (115 / 2), 820 - (115 / 2), 115, 115);
        if (hallucination) {
            g.drawImage(crack, 0, 0, out.getWidth(), out.getHeight(), null);
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), 100));
            g.fillRect(0, 0, out.getWidth(), out.getHeight());
            new Thread(() -> {
                try {
                    Thread.sleep(400);
                    panel.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        g.dispose();
        return out;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.repaint();
        circles.stream().filter(circle -> circle.contains(e.getX(), e.getY())).forEach(circle -> {
            if (circle.getCircleID().equals("RESET")) {
                mixture.clear();
            } else {
                if (mixture.size() >= 1 && mixture.get(mixture.size() - 1).getCircle().getCircleID().equals(circle.getCircleID())) {
                    Mixture m = mixture.get(mixture.size() - 1);
                    m.setAmount(m.getAmount() + 1);
                    mixture.set(mixture.size() - 1, m);
                } else {
                    mixture.add(new Mixture(circle, 1));
                }
            }
        });
        if (mixture.size() >= correct.size() && mixture.get(mixture.size() - 1).getAmount() >= correct.get(correct.size() - 1).getAmount()) {
            boolean s = true;
            if (mixture.size() == correct.size()) {
                for (int i = 0; i < mixture.size(); i++) {
                    if (!mixture.get(i).getCircle().getCircleID().equals(correct.get(i).getCircle().getCircleID()) || mixture.get(i).getAmount() != correct.get(i).getAmount()) {
                        s = false;
                    }
                }
            } else {
                s = false;
            }
            if (!s) {
                hallucination = true;
                panel.repaint();
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                        hallucination = false;
                        panel.repaint();
                        mixture.clear();
                        System.out.println(correct.get(0).getAmount() + " drops of " + correct.get(0).getCircle().getCircleID());
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }).start();
            } else {
                MapTheStars.getPlayer().addItem(new Item("Lens Enhancement Solution", 1, "A strange coloured mixture that you have been assured will make the lens more accurate."));
                MapTheStars.getMtsMainWindow().getInputArea().setLocked(false);
                panel.completePuzzle();
                return;
            }
        }
        if (mixture.size() > 0){
            System.out.println("   " + mixture.get(mixture.size() - 1 < 0 ? 0 : mixture.size() - 1).getAmount() + " drops");
            if (mixture.size() < correct.size() && correct.get(mixture.size() - 1).getAmount() == mixture.get(mixture.size() - 1).getAmount()) {
                System.out.println(correct.get(mixture.size()).getAmount() + " drops of " + correct.get(mixture.size()).getCircle().getCircleID());
            }
        }else if(mixture.size() == 0){
            System.out.println(correct.get(0).getAmount() + " drops of " + correct.get(0).getCircle().getCircleID());
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

class Circle {
    private int x;
    private int y;
    private int radius;
    private String circleID;
    private Color circleColor;

    public Circle(int x, int y, int radius, String circleID, Color circleColor) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.circleID = circleID;
        this.circleColor = circleColor;
    }

    public boolean contains(int x, int y) {
        double xl = Math.abs(x - getX());
        double yl = Math.abs(y - getY());
        double sqrt = Math.sqrt(Math.pow(xl, 2) + Math.pow(yl, 2));
        return sqrt <= radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getCircleID() {
        return circleID;
    }

    public void setCircleID(String circleID) {
        this.circleID = circleID;
    }

    public Color getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(Color circleColor) {
        this.circleColor = circleColor;
    }
}

class Mixture {

    private Circle circle;
    private int amount;

    public Mixture(Circle circle, int amount) {
        this.circle = circle;
        this.amount = amount;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}