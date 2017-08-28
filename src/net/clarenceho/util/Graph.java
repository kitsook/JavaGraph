package net.clarenceho.util;

import java.util.*;

/**
 * Representation of graph.
 */
public interface Graph {

    /**
     * Retrieves all nodes.
     *
     * @return all nodes in the graph
     */
    Collection<? extends Node> getNodes();

    /**
     * Retrieves all edges.
     *
     * @return all edges in the graph
     */
    Collection<? extends Edge> getEdges();

    /**
     * Retrieves all edges in sorted order
     *
     * @param ascending whether sort in ascending order
     * @return all edges in sorted order
     */
    default List<? extends Edge> getSortedEdges(boolean ascending) {
        List<Edge> result = new ArrayList<>();
        result.addAll(getEdges());
        result.sort((e1, e2) ->
                ascending? e1.getCost() - e2.getCost() : e2.getCost() - e1.getCost()
        );

        return result;
    }

    /**
     * Retrieves neighbor nodes of given node.
     *
     * @param node
     * @return list of neighbor nodes
     */
    Collection<? extends Node> getNeighbor(Node node);

    /**
     * Returns cost of the edge from one node to another.
     *
     * @param from travelling from
     * @param to travelling to
     * @return the cost. -1 if there is no edge between the two nodes.
     */
    int cost(Node from, Node to);

}
