package com.github.vitineth.map.the.stars.ui.window;

import com.github.vitineth.map.the.stars.MapTheStars;
import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.components.MTSInputArea;
import com.github.vitineth.map.the.stars.ui.components.MTSInteractivePanel;
import com.github.vitineth.map.the.stars.ui.components.MTSOutputPane;
import com.github.vitineth.map.the.stars.ui.theme.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The main input window for the Map The Stars game.
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class MTSMainWindow extends JFrame {

    private final String LOG_NAME = getClass().getSimpleName();

    private Theme theme = new Theme(new Color(54, 54, 54), new Color(168, 167, 168), new Font(Font.MONOSPACED, Font.PLAIN, 12));
    private MTSInputArea inputArea;
    private MTSOutputPane outputPane;
    private MTSInteractivePanel interactivePanel;

    public MTSMainWindow() {
        this(0);
    }

    public MTSMainWindow(int monitor) {
        setTitle(MapTheStars.GAME_TITLE + "/" + MapTheStars.GAME_TITLE);
        setSize(getDesiredSize(monitor));
        setLocation(getBestLocation(monitor));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        init();
    }

    /**
     * Initialises the window by setting up the component layout.
     */
    private void init() {
        int spacerWidth = getWidth() / 10;
        JPanel spacer = new JPanel();
        spacer.setBackground(theme.getBackground());
        GridBagConstraints spacerGBC = new GridBagConstraints();
        spacerGBC.gridx = 0;
        spacerGBC.gridy = 0;
        spacerGBC.ipadx = spacerWidth;
        spacerGBC.weighty = 1d;
        spacerGBC.fill = GridBagConstraints.VERTICAL;
        add(spacer, spacerGBC);

        JPanel spacer1 = new JPanel();
        spacer1.setBackground(theme.getBackground());
        GridBagConstraints spacer1GBC = new GridBagConstraints();
        spacer1GBC.gridx = 2;
        spacer1GBC.gridy = 0;
        spacer1GBC.ipadx = spacerWidth;
        spacer1GBC.weighty = 1d;
        spacer1GBC.fill = GridBagConstraints.VERTICAL;
        add(spacer1, spacer1GBC);

        JPanel body = new JPanel(new GridBagLayout());
        GridBagConstraints bodyGBC = new GridBagConstraints();
        bodyGBC.gridx = 1;
        bodyGBC.gridy = 0;
        bodyGBC.weightx = 1d;
        bodyGBC.weighty = 1d;
        bodyGBC.fill = GridBagConstraints.BOTH;
        add(body, bodyGBC);

        interactivePanel = new MTSInteractivePanel(theme);
        GridBagConstraints graphicGBC = new GridBagConstraints();
        graphicGBC.gridx = 0;
        graphicGBC.gridy = 0;
        graphicGBC.weightx = 1d;
        graphicGBC.weighty = 0.7d;
        graphicGBC.fill = GridBagConstraints.BOTH;
        body.add(interactivePanel, graphicGBC);

        outputPane = new MTSOutputPane(theme);
        outputPane.setBackground(theme.getBackground());
        outputPane.setForeground(theme.getForeground());
        outputPane.setFont(theme.getFont());
        GridBagConstraints outputGBC = new GridBagConstraints();
        outputGBC.gridx = 0;
        outputGBC.gridy = 1;
        outputGBC.weightx = 1d;
        outputGBC.weighty = 0.25d;
        outputGBC.fill = GridBagConstraints.BOTH;
        body.add(outputPane.wrapPane(), outputGBC);

        inputArea = new MTSInputArea(theme);
        inputArea.setBackground(theme.getBackground());
        inputArea.setForeground(theme.getForeground());
        inputArea.setFont(theme.getFont());
        inputArea.setBorder(new EmptyBorder(3, 3, 3, 3));
        GridBagConstraints inputGBC = new GridBagConstraints();
        inputGBC.gridx = 0;
        inputGBC.gridy = 2;
        inputGBC.weightx = 1d;
        inputGBC.weighty = 0.05d;
        inputGBC.fill = GridBagConstraints.BOTH;
        body.add(inputArea.wrapArea(), inputGBC);
    }

    /**
     * This will return 2/3rds of the given monitor size or the default monitor if the given monitor is not valid.
     *
     * @param monitor the desired monitor.
     * @return Dimension the size of the window.
     */
    private Dimension getDesiredSize(int monitor) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd;
        if (monitor >= ge.getScreenDevices().length) {
            Log.w(LOG_NAME, "The specified monitor index '" + monitor + "' is greater than the number of monitors '" + ge.getScreenDevices().length + "'. Using default monitor.");
            gd = ge.getDefaultScreenDevice();
        } else {
            gd = ge.getScreenDevices()[monitor];
        }

        DisplayMode dm = gd.getDisplayMode();
        double fraction = 2d / 3d;

        int width = (int) (dm.getWidth() * fraction);
        int height = (int) (dm.getHeight() * fraction);

        return new Dimension(width, height);
    }

    /**
     * This will return where on the monitor to place the window. If the monitor is >0 then it will add the widths of
     * all other windows onto the x value to offset it into the next window. If the monitor index is not valid it will
     * resort to the default window.
     * @param monitor the desired monitor
     * @return Point the location to place the window.
     */
    private Point getBestLocation(int monitor) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (monitor >= ge.getScreenDevices().length) {
            Log.e(LOG_NAME, "The specified monitor index '" + monitor + "' is greater than the number of monitors '" + ge.getScreenDevices().length + "'. Defaulting to 0.");
            monitor = 0;
            for (int i = 0; i < ge.getScreenDevices().length; i++)
                if (ge.getScreenDevices()[i] == ge.getDefaultScreenDevice()) monitor = i;
        }

        int wo = 0;
        for (int i = 0; i < monitor - 1; i++) {
            wo += ge.getScreenDevices()[i].getDisplayMode().getWidth();
        }

        Dimension d = getDesiredSize(monitor);
        double x = wo + ((ge.getScreenDevices()[monitor].getDisplayMode().getWidth() - d.getWidth()) / 2);
        double y = (ge.getScreenDevices()[monitor].getDisplayMode().getHeight() - d.getHeight()) / 2;
        return new Point((int) x, (int) y);
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public MTSInputArea getInputArea() {
        return inputArea;
    }

    public void setInputArea(MTSInputArea inputArea) {
        this.inputArea = inputArea;
    }

    public MTSOutputPane getOutputPane() {
        return outputPane;
    }

    public void setOutputPane(MTSOutputPane outputPane) {
        this.outputPane = outputPane;
    }

    public MTSInteractivePanel getInteractivePanel() {
        return interactivePanel;
    }

    public void setInteractivePanel(MTSInteractivePanel interactivePanel) {
        this.interactivePanel = interactivePanel;
    }
}
