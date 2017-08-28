package net.clarenceho.algo.minspanning.test;

import net.clarenceho.algo.minspanning.MinSpanningProblem;
import net.clarenceho.util.AdjMatrixGraph;
import net.clarenceho.util.Edge;
import net.clarenceho.util.Graph;
import net.clarenceho.util.Node;

import java.util.Collection;
import java.util.List;

/**
 * Test case for minimum spanning tree problem.
 */
public class MinSpanningTestCase implements MinSpanningProblem {
    private List<? extends Node> nodes;
    private int[][] adj;
    private Graph graph;

    public MinSpanningTestCase(List<? extends Node> nodes, int[][] adj) {
        this.nodes = nodes;
        this.adj = adj;
        this.graph = new AdjMatrixGraph(this.nodes, this.adj, false);
    }

    @Override
    public Collection<? extends Node> getNodes() {
        return this.graph.getNodes();
    }

    @Override
    public List<? extends Edge> getSortedEdges() {
        return this.graph.getSortedEdges(true);
    }
}
