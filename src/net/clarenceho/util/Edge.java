package net.clarenceho.util;

/**
 * Representation of an edge in graph
 */
public class Edge {
    private Pair<? extends Node> nodePair;
    private int cost;

    public Edge(Node u, Node v, int cost) {
        this.nodePair = new Pair<>(u, v);
        this.cost = cost;
    }

    public Pair<? extends Node> getNodePair() {
        return this.nodePair;
    }

    public int getCost() { return this.cost; }

    public String toString() {
        return "Edge between " + this.nodePair.left().toString() + " and " + this.nodePair.right().toString();
    }
}
