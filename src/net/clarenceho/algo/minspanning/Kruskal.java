package net.clarenceho.algo.minspanning;

import net.clarenceho.util.DisjointSet;
import net.clarenceho.util.Edge;
import net.clarenceho.util.Node;
import net.clarenceho.util.Pair;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Kruskal's algorithm to solve minimum spanning problems
 */
public class Kruskal {
    private MinSpanningProblem problem;

    public Kruskal(MinSpanningProblem problem) {
        this.problem = problem;
    }

    public Collection<? extends Edge> resolve() {
        Set<Edge> result = new HashSet<>();
        DisjointSet<Node> disjointSet = new DisjointSet<>();

        for (Node n : problem.getNodes()) {
            disjointSet.makeSet(n);
        }
        List<? extends Edge> sortedEdge = problem.getSortedEdges();

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
}
