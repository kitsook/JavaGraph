package net.clarenceho.util;

/**
 * Representation of a node in graph
 */
public class Node {
    private String id;

    public Node(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Node " + id;
    }

}
