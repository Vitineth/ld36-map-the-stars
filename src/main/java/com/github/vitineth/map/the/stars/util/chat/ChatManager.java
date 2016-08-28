package com.github.vitineth.map.the.stars.util.chat;

import com.github.vitineth.map.the.stars.log.Log;
import com.github.vitineth.map.the.stars.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Class Description
 * <p/>
 * File created by Ryan (vitineth).<br>
 * Created on 28/08/2016.
 *
 * @author Ryan (vitineth)
 * @since 28/08/2016
 */
public class ChatManager {

    private Callback terminatorCallback;
    private HashMap<Integer, ChatNode> nodeMap;
    private ChatNode active;

    public ChatManager(Callback terminatorCallback, ChatNode origin) {
        this.terminatorCallback = terminatorCallback;
        this.nodeMap = new HashMap<>();
        addAll(origin);
        nodeMap.keySet().forEach(key -> Log.d("ChatManager", "'" + key + "'='" + nodeMap.get(key) + "'"));

        this.active = origin;
    }

    private void addAll(ChatNode node) {
        if (nodeMap.containsKey(node.getNodeID())) return;
        nodeMap.put(node.getNodeID(), node);
        if (!node.hasChildren() || node.isTerminator() || !node.isEnabled()) return;
        node.getOptions().forEach(this::addAll);
    }

    public List<ChatNode> getOptions() {
        List<ChatNode> nodes = new ArrayList<>();
        active.getOptions().stream().filter(node -> node.isEnabled() && nodeMap.get(node.getNodeID()).isEnabled()).forEach(nodes::add);
        return nodes;
    }

    public void chatOptionSelected(ChatNode node) {
        node.setEnabled(false);
        nodeMap.get(node.getNodeID()).setEnabled(false);
        active = node;
        if (active.isTerminator()) {
            if (terminatorCallback != null) terminatorCallback.callback();
        }
    }

    public String getText() {
        return active.getResponseText();
    }

    public boolean continueChat() {
        return !active.isTerminator() && active.hasChildren();
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(active.getResponseText());
            if (active.isTerminator() || active.getOptions().size() == 0) break;
            int i = 0;
            List<Integer> valid = new ArrayList<>();
            List<ChatNode> nodes = new ArrayList<>();
            for (ChatNode n : active.getOptions()) {
                if (n.isEnabled() && nodeMap.get(n.getNodeID()).isEnabled()) {
                    valid.add(i);
                    nodes.add(n);
                    System.out.println("[" + i + "]: " + n.getOption());
                    i++;
                }
            }
            active = nodes.get(waitOnInput(scanner, valid));
            active.setEnabled(false);
            nodeMap.get(active.getNodeID()).setEnabled(false);
        }
    }

    private int waitOnInput(Scanner scanner, List<Integer> valid) {
        String line;
        while (!(line = scanner.nextLine()).matches("[0-9]+?")) {
            System.out.println("Invalid number.");
        }
        if (!valid.contains(Integer.parseInt(line))) {
            System.out.println("Invalid option.");
            return waitOnInput(scanner, valid);
        }
        return Integer.parseInt(line);
    }
}
