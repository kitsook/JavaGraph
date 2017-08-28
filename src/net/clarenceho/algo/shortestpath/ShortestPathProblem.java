package net.clarenceho.algo.shortestpath;

import net.clarenceho.util.Node;

import java.util.Collection;

/**
 * Define a shortest path problem.
 */
public interface ShortestPathProblem {
    /**
     * Returns the starting node of the problem.
     *
     * @return the starting node
     */
    Node getStartNode();

    /**
     * Returns the ending node of the problem.
     *
     * @return the ending node
     */
    Node getEndNode();

    /**
     * Returns all the nodes of the problem.
     *
     * @return collection of nodes
     */
    Collection<? extends Node> getNodes();

    /**
     * Returns initial cost at the starting node.
     *
     * @return initial cost
     */
    int initCost();

    /**
     * Returns costs of the edge from one node to another.
     *
     * @param from
     * @param to
     * @return
     */
    int cost(Node from, Node to);

    /**
     * Returns neighbor nodes.
     *
     * @param n
     * @return
     */
    Collection<? extends Node> getNeighbor(Node n);
}
