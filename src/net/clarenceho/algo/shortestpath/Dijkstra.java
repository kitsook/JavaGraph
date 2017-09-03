package net.clarenceho.algo.shortestpath;

import net.clarenceho.util.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Dijkstra's algorithm for finding shortest path.
 * Reference: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class Dijkstra {
    private ShortestPathProblem problem;

    public Dijkstra(ShortestPathProblem problem) {
        this.problem = problem;
    }


    /**
     * Solve the shortest path problem
     * 
     * @return cost of the shortest path
     * @throws IllegalArgumentException if encountered path with negative cost
     */
    public int resolve() throws IllegalArgumentException {
        Map<Node, Integer> tentativeDist = new HashMap<>();
        Node currentNode = problem.getStartNode();
        Set<Node> visited = new HashSet<>();

        // Assign to every node a tentative distance value.
        for (Node n : problem.getGraph().getNodes()) {
            if (n.equals(problem.getStartNode())) {
                tentativeDist.put(n, problem.initCost()); // initial cost of starting node
            } else {
                tentativeDist.put(n, Integer.MAX_VALUE);
            }
        }

        while (!visited.contains(problem.getEndNode())) {
            for (Node neighbor : problem.getGraph().getNeighbors(currentNode)) {
                int costToNeighbor = problem.getGraph().cost(currentNode, neighbor);
                if (costToNeighbor < 0) {
                    throw new IllegalArgumentException("Negative cost from " + currentNode + " to " + neighbor);
                }
                int newDist = tentativeDist.get(currentNode) + costToNeighbor;
                if (newDist < tentativeDist.get(neighbor)) {
                    tentativeDist.put(neighbor, newDist);
                }
            }
            visited.add(currentNode);

            Node smallestUnvisited = smallestUnvisited(tentativeDist, visited);
            if (smallestUnvisited == null ||
                    currentNode.equals(problem.getEndNode()) ||
                    tentativeDist.get(smallestUnvisited) == Integer.MAX_VALUE) {
                break;
            }
            currentNode = smallestUnvisited;
        }

        return tentativeDist.get(problem.getEndNode());
    }

    private Node smallestUnvisited(Map<Node, Integer> tentativeDist, Set<Node> visited) {
        Node result = null;
        for (Node n : tentativeDist.keySet()) {
            if (!visited.contains(n) &&
                    (result == null || tentativeDist.get(n) < tentativeDist.get(result))) {
                result = n;
            }
        }
        return result;
    }
}
