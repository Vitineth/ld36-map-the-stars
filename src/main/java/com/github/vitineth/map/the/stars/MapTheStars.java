package com.github.vitineth.map.the.stars;

import com.github.vitineth.map.the.stars.gameplay.Player;
import com.github.vitineth.map.the.stars.gameplay.command.CommandController;
import com.github.vitineth.map.the.stars.gameplay.level.L1;
import com.github.vitineth.map.the.stars.gameplay.level.L2;
import com.github.vitineth.map.the.stars.gameplay.level.L3;
import com.github.vitineth.map.the.stars.gameplay.level.Level;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.window.MTSMainWindow;
import com.github.vitineth.map.the.stars.util.ImageFrame;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static HashMap<String, Level> levels = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Log.i(LOG_NAME, "Launching " + GAME_TITLE + "/" + VERSION + "...");
        mtsMainWindow = new MTSMainWindow(1);
        SwingUtilities.invokeLater(() -> mtsMainWindow.setVisible(true));

        levels.put("L1", new L1());
        levels.put("L2", new L2());
        levels.put("L3", new L3());

        player = new Player("Ryan", levels.get("L1"));
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

    public static HashMap<String, Level> getLevels() {
        return levels;
    }

    public static ImageFrame[] readGIF(ImageReader reader) throws IOException {
        ArrayList<ImageFrame> frames = new ArrayList<ImageFrame>(2);

        int width = -1;
        int height = -1;

        IIOMetadata metadata = reader.getStreamMetadata();
        if (metadata != null) {
            IIOMetadataNode globalRoot = (IIOMetadataNode) metadata.getAsTree(metadata.getNativeMetadataFormatName());

            NodeList globalScreenDescriptor = globalRoot.getElementsByTagName("LogicalScreenDescriptor");

            if (globalScreenDescriptor != null && globalScreenDescriptor.getLength() > 0) {
                IIOMetadataNode screenDescriptor = (IIOMetadataNode) globalScreenDescriptor.item(0);

                if (screenDescriptor != null) {
                    width = Integer.parseInt(screenDescriptor.getAttribute("logicalScreenWidth"));
                    height = Integer.parseInt(screenDescriptor.getAttribute("logicalScreenHeight"));
                }
            }
        }

        BufferedImage master = null;
        Graphics2D masterGraphics = null;

        for (int frameIndex = 0;; frameIndex++) {
            BufferedImage image;
            try {
                image = reader.read(frameIndex);
            } catch (IndexOutOfBoundsException io) {
                break;
            }

            if (width == -1 || height == -1) {
                width = image.getWidth();
                height = image.getHeight();
            }

            IIOMetadataNode root = (IIOMetadataNode) reader.getImageMetadata(frameIndex).getAsTree("javax_imageio_gif_image_1.0");
            IIOMetadataNode gce = (IIOMetadataNode) root.getElementsByTagName("GraphicControlExtension").item(0);
            int delay = Integer.valueOf(gce.getAttribute("delayTime"));
            String disposal = gce.getAttribute("disposalMethod");

            int x = 0;
            int y = 0;

            if (master == null) {
                master = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                masterGraphics = master.createGraphics();
                masterGraphics.setBackground(new Color(0, 0, 0, 0));
            } else {
                NodeList children = root.getChildNodes();
                for (int nodeIndex = 0; nodeIndex < children.getLength(); nodeIndex++) {
                    Node nodeItem = children.item(nodeIndex);
                    if (nodeItem.getNodeName().equals("ImageDescriptor")) {
                        NamedNodeMap map = nodeItem.getAttributes();
                        x = Integer.valueOf(map.getNamedItem("imageLeftPosition").getNodeValue());
                        y = Integer.valueOf(map.getNamedItem("imageTopPosition").getNodeValue());
                    }
                }
            }
            masterGraphics.drawImage(image, x, y, null);

            BufferedImage copy = new BufferedImage(master.getColorModel(), master.copyData(null), master.isAlphaPremultiplied(), null);
            frames.add(new ImageFrame(copy, delay, disposal));

            if (disposal.equals("restoreToPrevious")) {
                BufferedImage from = null;
                for (int i = frameIndex - 1; i >= 0; i--) {
                    if (!frames.get(i).getDisposal().equals("restoreToPrevious") || frameIndex == 0) {
                        from = frames.get(i).getImage();
                        break;
                    }
                }

                master = new BufferedImage(from.getColorModel(), from.copyData(null), from.isAlphaPremultiplied(), null);
                masterGraphics = master.createGraphics();
                masterGraphics.setBackground(new Color(0, 0, 0, 0));
            } else if (disposal.equals("restoreToBackgroundColor")) {
                masterGraphics.clearRect(x, y, image.getWidth(), image.getHeight());
            }
        }
        reader.dispose();

        return frames.toArray(new ImageFrame[frames.size()]);
    }

}

