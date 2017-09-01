package net.clarenceho.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adjacency matrix representation of the graph.  Costs from one node to another are stored in the matrix.
 */
public class AdjMatrixGraph implements Graph {
    private List<? extends Node> nodes;
    private List<Edge> edges;
    private int[][] adj;
    private boolean directed;

    private int nodeCount;
    private Map<Node, Integer> pos;

    public static int NO_PATH = Integer.MIN_VALUE;
    public static int MIN_COST = Integer.MIN_VALUE+1;
    public static int MAX_COST = Integer.MAX_VALUE-1;

    /**
     * Adjacency matrix representation of the graph.
     *
     * @param nodes list of n nodes in the graph.
     * @param adj n by n matrix. For (i, j), the value represents the cost from node i to j.
     *            Cost can range from (Integer.MIN_VALUE+1) to (Integer.MAX_VALUE-1).
     *            Value of Integer.MIN_VALUE means no path from i to j.
     */
    public AdjMatrixGraph(List<? extends Node> nodes, int[][] adj, boolean directed) {
        this.nodes = nodes;
        this.adj = adj;
        this.directed = directed;

        this.pos = new HashMap<>();
        this.nodeCount = 0;
        for (Node n : nodes) {
            pos.put(n, nodeCount);
            nodeCount += 1;
        }

        edges = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            for (int j = this.directed? 0 : i; j < adj[0].length; j++) {
                if (adj[i][j] != NO_PATH) {
                    edges.add(new Edge(nodes.get(i), nodes.get(j), adj[i][j]));
                }
            }
        }
    }

    /**
     * For undirected graph, the adjacency-matrix is symmetric along the diagonal.
     * This helper function mirror the upper half of the matrix to the lower half along the diagonal.
     */
    public static void copyToLowerHalf(int[][] adj) {
        for (int i = 0; i < adj.length; i++) {
            for (int j = i+1; j < adj[0].length; j++) {
                adj[j][i] = adj[i][j];
            }
        }
    }

    /**
     * Retrieves all nodes.
     *
     * @return all nodes in the graph
     */
    @Override
    public Collection<? extends Node> getNodes() {
        return this.nodes;
    }

    @Override
    public Collection<? extends Edge> getEdges() {
        return this.edges;
    }

    /**
     * Retrieves neighbor nodes of given node.
     *
     * @param node retrieving neighbor of this node
     * @return list of neighbor nodes
     */
    @Override
    public Collection<? extends Node> getNeighbors(Node node) {
        List<Node> result = new ArrayList<>();
        int i = pos.get(node);
        for (int j = 0; j < this.nodeCount; j++) {
            if (this.adj[i][j] != NO_PATH) {
                result.add(this.nodes.get(j));
            }
        }

        return result;
    }

    /**
     * Returns cost of the edge from one node to another.
     *
     * @param from travelling from
     * @param to travelling to
     * @return the cost. Integer.MIN_VALUE if there is no edge between the two nodes.
     */
    @Override
    public int cost(Node from, Node to) {
        int i = pos.get(from);
        int j = pos.get(to);
        return adj[i][j];
    }
}
