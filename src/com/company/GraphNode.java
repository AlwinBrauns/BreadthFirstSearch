package com.company;

import java.util.LinkedList;

public class GraphNode {
    private int id;
    private LinkedList<GraphNode> connected;

    GraphNode(int id)
    {
        this.id = id;
        this.connected = new LinkedList<>();
    }

    GraphNode(int id, LinkedList<GraphNode> connected)
    {
        this.id = id;
        this.connected = connected;
    }

    public int getId() {
        return id;
    }

    public LinkedList<GraphNode> getConnected() {
        return connected;
    }

    public void setConnected(GraphNode[] connected) {
        for (GraphNode graphNode : connected) {
            addConnected(graphNode);
        }
    }

    public boolean addConnected(GraphNode graphNode){
        return connected.add(graphNode);
    }

    public boolean removeConnected(GraphNode graphNode){
        return connected.remove(graphNode);
    }
}
