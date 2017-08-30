package net.clarenceho.algo.minspanning.test;

import net.clarenceho.util.AdjMatrixGraph;
import net.clarenceho.util.Graph;
import net.clarenceho.util.GraphProblem;
import net.clarenceho.util.Node;

import java.util.List;

/**
 * Test case for minimum spanning tree problem.
 */
public class MinSpanningTestCase implements GraphProblem {
    private Graph graph;

    public MinSpanningTestCase(List<? extends Node> nodes, int[][] adj) {
        this.graph = new AdjMatrixGraph(nodes, adj, false);
    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }
}
