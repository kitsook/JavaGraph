package net.clarenceho.algo.minspanning.test;

import net.clarenceho.algo.minspanning.Kruskal;
import net.clarenceho.algo.minspanning.MinSpanningSolver;
import net.clarenceho.algo.minspanning.Prim;
import net.clarenceho.util.AdjMatrixGraph;
import net.clarenceho.util.Edge;
import net.clarenceho.util.GraphProblem;
import net.clarenceho.util.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

/**
 * Testing the minimum spanning tree algorithms.
 */
public class MinSpanningTest {

    /**
     * Sample case from the book "Introduction To Algorithms", first edition, figure 24.4.
     */
    @Test
    public void testKruskalSample01() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        int size = 9;
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(Character.toString((char) (97 + i))));
        }
        // prepare edges
        int adj[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adj[i][j] = -1;
            }
        }
        adj[0][1] = 4;
        adj[0][7] = 8;
        adj[1][2] = 8;
        adj[1][7] = 11;
        adj[2][3] = 7;
        adj[2][5] = 4;
        adj[2][8] = 2;
        adj[3][4] = 9;
        adj[3][5] = 14;
        adj[4][5] = 10;
        adj[5][6] = 2;
        adj[6][7] = 1;
        adj[6][8] = 6;
        adj[7][8] = 7;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Kruskal(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(8, result.size());
        assertEquals(37, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void testPrimSample01() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        int size = 9;
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(Character.toString((char) (97 + i))));
        }
        // prepare edges
        int adj[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adj[i][j] = -1;
            }
        }
        adj[0][1] = 4;
        adj[0][7] = 8;
        adj[1][2] = 8;
        adj[1][7] = 11;
        adj[2][3] = 7;
        adj[2][5] = 4;
        adj[2][8] = 2;
        adj[3][4] = 9;
        adj[3][5] = 14;
        adj[4][5] = 10;
        adj[5][6] = 2;
        adj[6][7] = 1;
        adj[6][8] = 6;
        adj[7][8] = 7;
        AdjMatrixGraph.copyToLowerHalf(adj); // mirror it across the diagonal

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Prim(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(8, result.size());
        assertEquals(37, result.stream().mapToInt(Edge::getCost).sum());
    }

    /**
     * A simple graph in square shape.
     */
    @Test
    public void testKruskalSample02() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));
        nodes.add(new Node("b"));
        nodes.add(new Node("c"));
        nodes.add(new Node("d"));

        // prepare edges
        int adj[][] = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                adj[i][j] = -1;
            }
        }
        adj[0][1] = 5;
        adj[0][3] = 4;
        adj[1][2] = 3;
        adj[2][3] = 2;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Kruskal(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(3, result.size());
        assertEquals(9, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void testPrimSample02() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));
        nodes.add(new Node("b"));
        nodes.add(new Node("c"));
        nodes.add(new Node("d"));

        // prepare edges
        int adj[][] = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                adj[i][j] = -1;
            }
        }
        adj[0][1] = 5;
        adj[0][3] = 4;
        adj[1][2] = 3;
        adj[2][3] = 2;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Prim(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(3, result.size());
        assertEquals(9, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void testKruskalSingleNode() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));

        // prepare edges
        int adj[][] = new int[1][1];
        adj[0][0] = -1;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Kruskal(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(0, result.size());
        assertEquals(0, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void testPrimSingleNode() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));

        // prepare edges
        int adj[][] = new int[1][1];
        adj[0][0] = -1;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Prim(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(0, result.size());
        assertEquals(0, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void testKruskalSingleEdge() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));
        nodes.add(new Node("b"));

        // prepare edges
        int adj[][] = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                adj[i][j] = -1;
            }
        }
        adj[0][1] = 3;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Kruskal(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(1, result.size());
        assertEquals(3, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void testPrimSingleEdge() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a"));
        nodes.add(new Node("b"));

        // prepare edges
        int adj[][] = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                adj[i][j] = -1;
            }
        }
        adj[0][1] = 3;
        AdjMatrixGraph.copyToLowerHalf(adj);

        GraphProblem problem = new MinSpanningTestCase(nodes, adj);
        MinSpanningSolver solver = new Prim(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(1, result.size());
        assertEquals(3, result.stream().mapToInt(Edge::getCost).sum());
    }

    @Test
    public void compareSolvers01() {
        int count = 1000;
        int maxSize = 20;
        int maxCost = 20;

        for (int c = 0; c < count; c++) {
            int size = ThreadLocalRandom.current().nextInt(1, maxSize + 1);

            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                nodes.add(new Node(Character.toString((char) (97 + i))));
            }

            int adj[][] = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = i; j < size; j++) {
                    if (i == j) {
                        adj[i][j] = -1;
                    } else {
                        adj[i][j] = ThreadLocalRandom.current().nextInt(0, maxCost + 1);
                    }
                }
            }
            AdjMatrixGraph.copyToLowerHalf(adj);

            GraphProblem problem = new MinSpanningTestCase(nodes, adj);
            MinSpanningSolver solver1 = new Kruskal(problem);
            Collection<? extends Edge> result1 = solver1.resolve();
            MinSpanningSolver solver2 = new Prim(problem);
            Collection<? extends Edge> result2 = solver2.resolve();

            try {
                assertEquals(result1.size(), result2.size());
                assertEquals(result1.stream().mapToInt(Edge::getCost).sum(),
                        result2.stream().mapToInt(Edge::getCost).sum());
            } catch (AssertionError e) {
                System.err.println("adj matrix:");
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        System.err.print(adj[i][j] + " ");
                    }
                    System.err.println("");
                }
                System.err.println("Solver1 result:");
                for (Edge edge : result1) {
                    System.err.println(edge);
                }
                System.err.println("Solver2 result:");
                for (Edge edge : result2) {
                    System.err.println(edge);
                }
                throw e;
            }
        }

    }

}