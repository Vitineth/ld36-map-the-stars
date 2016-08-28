package com.github.vitineth.map.the.stars.ui.components;

import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.ui.theme.Theme;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The output pane to be used for all text based output when using the {@link com.github.vitineth.map.the.stars.ui.window.MTSMainWindow}.
 * This has two {@link PrintStream} instances included which can be used to overwrite the standard outputs using the
 * {@link #overwriteStd()} method which allows the rest of the program to use {@link System#out} and {@link System#err}
 * for general output for the game.
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 27/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 27/08/2016
 */
public class MTSOutputPane extends JTextPane {

    private boolean locked = false;

    private Style inputStyle;
    private Style outputStyle;
    private Style errorStyle;
    private Theme theme;

    private PrintStream out = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
            try {
                getStyledDocument().insertString(getStyledDocument().getLength(), new String(new byte[]{(byte) b}), outputStyle);
            } catch (BadLocationException e) {
                Log.s("MTSOutputPane/Out/Write", "Bad Location Exception when inserting output text!", e);
            }
        }
    });

    private PrintStream err = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
            try {
                getStyledDocument().insertString(getStyledDocument().getLength(), new String(new byte[]{(byte) b}), errorStyle);
            } catch (BadLocationException e) {
                Log.s("MTSOutputPane/Err/Write", "Bad Location Exception when inserting error text!", e);
            }
        }
    });

    public MTSOutputPane(Theme theme) {
        this.theme = theme;

        inputStyle = getStyledDocument().addStyle("inputStyle", null);
        outputStyle = getStyledDocument().addStyle("outputStyle", null);
        errorStyle = getStyledDocument().addStyle("errorStyle", null);

        StyleConstants.setForeground(inputStyle, new Color(0, 127, 0));
        StyleConstants.setForeground(outputStyle, theme.getForeground());
        StyleConstants.setForeground(errorStyle, new Color(255, 107, 104));

        setEditable(false);
        overwriteStd();
        ((DefaultCaret) getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isLocked()) {
            Color t = theme.getBackground().brighter();
            g.setColor(new Color(t.getRed(), t.getGreen(), t.getBlue(), 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(new Color(146, 146, 146));
        g.fillRect(30, 0, getWidth() - 60, 1);
    }

    /**
     * Returns whether the output pane is locked. This is to be used when the user needs to do something with the
     * interactive pane.
     *
     * @return boolean if the pane is locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets whether the pane is locked. This should be used when the user needs to provide input into the interactive
     * panel to make it clear. This paints a translucent rectangle over the top of the pane to show that it is currently
     * deactivated.
     *
     * @param locked boolean if pane should be locked.
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
        repaint();
    }

    /**
     * Returns a version of the pane wrapped in a borderless scroll pane.
     *
     * @return JScrollPane a borderless scroll pane.
     */
    public JScrollPane wrapPane() {
        JScrollPane pane = new JScrollPane(this);
        pane.setBorder(null);
        return pane;
    }

    /**
     * Overwrites the system out and err printstreams with the outputs of this panel.
     */
    public void overwriteStd() {
        System.setOut(out);
        System.setErr(err);
    }

    public void addInput(String command) {
        try {
            getStyledDocument().insertString(getStyledDocument().getLength(), ">> " + command, inputStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}
