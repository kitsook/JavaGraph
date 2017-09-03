package net.clarenceho.algo.shortestpath;

import net.clarenceho.util.GraphProblem;
import net.clarenceho.util.Node;

import java.util.Collection;

/**
 * Define a shortest path problem.
 */
public interface ShortestPathProblem extends GraphProblem {
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
     * Returns initial cost at the starting node.
     *
     * @return initial cost
     */
    int initCost();

}
