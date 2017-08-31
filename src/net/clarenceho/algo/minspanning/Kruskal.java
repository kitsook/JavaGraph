package net.clarenceho.algo.minspanning;

import net.clarenceho.util.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Kruskal's algorithm to solve minimum spanning problems
 */
public class Kruskal implements MinSpanningSolver {
    private GraphProblem problem;

    public Kruskal(GraphProblem problem) {
        this.problem = problem;
    }

    @Override
    public Collection<? extends Edge> resolve() {
        Set<Edge> result = new HashSet<>();
        DisjointSet<Node> disjointSet = new DisjointSet<>();

        for (Node n : getNodes()) {
            disjointSet.makeSet(n);
        }
        List<? extends Edge> sortedEdge = getSortedEdges();
        for (Edge e : sortedEdge) {
            Pair<? extends Node> nodePair = e.getNodePair();
            Node u = nodePair.left();
            Node v = nodePair.right();
            if (!disjointSet.findSet(u).equals(disjointSet.findSet(v))) {
                result.add(e);
                disjointSet.union(u, v);
            }
        }

        return result;
    }

    private Collection<? extends Node> getNodes() {
        return this.problem.getGraph().getNodes();
    }

    private List<? extends Edge> getSortedEdges() {
        return this.problem.getGraph().getSortedEdges(true);
    }

}
