package com.github.vitineth.map.the.stars.util.chat;

import java.util.ArrayList;
import java.util.Collections;
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
public class ChatNode {

    private int nodeID;
    private String option;
    private String responseText;
    private List<ChatNode> options;
    private boolean enabled;
    private boolean terminator;

    public ChatNode(int nodeID, String option, String responseText, List<ChatNode> options, boolean enabled, boolean terminator) {
        this.nodeID = nodeID;
        this.option = option;
        this.responseText = responseText;
        this.options = options;
        this.enabled = enabled;
        this.terminator = terminator;
    }

    public ChatNode(int nodeID, String option, String responseText, boolean enabled, boolean terminator, ChatNode... nodes) {
        this.nodeID = nodeID;
        this.option = option;
        this.responseText = responseText;
        this.enabled = enabled;
        this.terminator = terminator;
        options = new ArrayList<>();
        Collections.addAll(options, nodes);
    }

    public void addOptions(ChatNode... node) {
        Collections.addAll(options, node);
    }

    public boolean hasChildren() {
        return options.size() > 0;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public List<ChatNode> getOptions() {
        return options;
    }

    public void setOptions(List<ChatNode> options) {
        this.options = options;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTerminator() {
        return terminator;
    }

    public void setTerminator(boolean terminator) {
        this.terminator = terminator;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "[" + getNodeID() + "] " + getResponseText() + " (with " + getOptions().size() + " options. " + (isEnabled() ? "Is" : "Is not") + " enabled. " + (isTerminator() ? "Is" : "Is not") + " terminator.)";
    }
}
