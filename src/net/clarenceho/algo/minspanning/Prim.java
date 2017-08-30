package net.clarenceho.algo.minspanning;

import net.clarenceho.util.Edge;
import net.clarenceho.util.GraphProblem;
import net.clarenceho.util.Node;

import java.util.*;

/**
 */
public class Prim implements MinSpanningSolver  {
    private GraphProblem problem;

    public Prim(GraphProblem problem) {
        this.problem = problem;
    }

    @Override
    public Collection<? extends Edge> resolve() {
        Set<Edge> result = new HashSet<>();
        Map<Node, Integer> keys = new HashMap<>();
        List<Node> nodes = new ArrayList<>();
        Map<Node, Node> parentOf = new HashMap<>();

        // TODO: need to check if starting node not connected?
        boolean firstNode = true;
        for (Node n : getNodes()) {
            if (firstNode) {
                keys.put(n, 0);
                nodes.add(n);
                firstNode = false;
            } else {
                keys.put(n, Integer.MAX_VALUE);
                nodes.add(n);
            }
        }
        sortNodeList(nodes, keys);

        while(!nodes.isEmpty()) {
            Node n = nodes.get(0);
            nodes.remove(0);
            for (Node neighbor : getNeighbors(n)) {
                int cost = cost(n, neighbor);
                // TODO: improve performance. lookup from nodes list is slow
                if (nodes.contains(neighbor) && cost < keys.get(neighbor)) {
                    keys.put(neighbor, cost);
                    parentOf.put(neighbor, n);
                }
            }
            sortNodeList(nodes, keys);
        }

        for (Node u : parentOf.keySet()) {
            Node v = parentOf.get(u);
            result.add(new Edge(u, v, cost(u, v)));
        }
        return result;
    }

    private void sortNodeList(List<Node> nodes, Map<Node, Integer> keys) {
        nodes.sort((n1, n2) -> keys.get(n1) - keys.get(n2));
    }

    private Collection<? extends Node> getNodes() {
        return this.problem.getGraph().getNodes();
    }

    private Collection<? extends Node> getNeighbors(Node node) {
        return this.problem.getGraph().getNeighbors(node);
    }

    private int cost(Node from, Node to) {
        return this.problem.getGraph().cost(from, to);
    }

}
