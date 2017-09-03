package net.clarenceho.algo.shortestpath.test;

import net.clarenceho.algo.shortestpath.ShortestPathProblem;
import net.clarenceho.util.AdjMatrixGraph;
import net.clarenceho.util.Graph;
import net.clarenceho.util.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Idea based on http://practice.geeksforgeeks.org/problems/minimum-cost-path/0
 *
 * Given a square grid of size n, each cell of which contains integer cost which
 * represents a cost to traverse through that cell, we need to find a path from
 * top left cell to bottom right cell by which total cost incurred is minimum.
 *
 * Example 1. Input:
 * { {  31, 100,  65,  12,  18 },
 *   {  10,  13,  47, 157,   6 },
 *   { 100, 113, 174,  11,  33 },
 *   {  88, 124,  41,  20, 140 },
 *   {  99,  32, 111,  41,  20 } }
 * Expected output is 327.
 *
 * Example 2. Input:
 * { { 42, 93 },
 *   {  7, 14 } }
 * Expected output is 63.
 */
public class MinCostSqMatrix implements ShortestPathProblem {
    private int problemSize;
    private List<MatrixNode> nodes;
    private Graph graph;
    private int initCost;

    MinCostSqMatrix(int[][] costs) {
        this.problemSize = costs.length;
        this.nodes = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs[0].length; j++) {
                nodes.add(new MatrixNode(i, j));
            }
        }

        int[][] adj = generateAdj(costs);
        this.initCost = costs[0][0];
        this.graph = new AdjMatrixGraph(this.nodes, adj, false);
    }

    /**
     * Converts the cost matrix to a graph adjacency matrix. There are n * n element in the cost matrix.  So the
     * graph adjacency matrix has dimension (n * n) x (n * n).
     *
     * @param costs
     * @return graph adjacency matrix
     */
    private int[][] generateAdj(int[][] costs) {
        int[][] result = new int[costs.length * costs.length][costs.length * costs.length];
        for (int i = 0; i < costs.length * costs.length; i++) {
            for (int j = 0; j < costs.length * costs.length; j++) {
                result[i][j] = AdjMatrixGraph.NO_PATH;
            }
        }

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs[0].length; j++) {
                int nodeId = i * costs.length + j;
                // the element above
                if (i - 1 >= 0) {
                    int nodeIdAbove = (i - 1) * costs.length + j;
                    result[nodeId][nodeIdAbove] = costs[i - 1][j];
                    result[nodeIdAbove][nodeId] = costs[i][j];
                }
                // the element below
                if (i + 1 < problemSize) {
                    int nodeIdBelow = (i + 1) * costs.length + j;
                    result[nodeId][nodeIdBelow] = costs[i + 1][j];
                    result[nodeIdBelow][nodeId] = costs[i][j];
                }
                // the element on the left
                if (j - 1 >= 0) {
                    int nodeIdLeft = nodeId - 1;
                    result[nodeId][nodeIdLeft] = costs[i][j - 1];
                    result[nodeIdLeft][nodeId] = costs[i][j];
                }
                // the element on the right
                if (j + 1 < problemSize) {
                    int nodeIdRight = nodeId + 1;
                    result[nodeId][nodeIdRight] = costs[i][j + 1];
                    result[nodeIdRight][nodeId] = costs[i][j];
                }
            }
        }

        return result;
    }

    @Override
    public Node getStartNode() {
        return nodes.get(0);
    }

    @Override
    public Node getEndNode() {
        return nodes.get(nodes.size() - 1);
    }

    @Override
    public int initCost() {
        return this.initCost;
    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

    /**
     * Each grid element is a node.
     */
    private class MatrixNode extends net.clarenceho.util.Node {
        int i;
        int j;

        MatrixNode(int i, int j) {
            super(i + "," + j);
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MatrixNode that = (MatrixNode) o;

            return i == that.i && j == that.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
