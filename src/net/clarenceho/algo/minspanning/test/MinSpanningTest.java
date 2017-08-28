package net.clarenceho.algo.minspanning.test;

import net.clarenceho.algo.minspanning.Kruskal;
import net.clarenceho.algo.minspanning.MinSpanningProblem;
import net.clarenceho.util.Edge;
import net.clarenceho.util.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing the minimum spanning tree algorithms.
 */
public class MinSpanningTest {

    /**
     * Sample case from the book "Introduction To Algorithms", first edition, figure 24.4.
     */
    @Test
    public void testSample01() {
        // prepare nodes
        List<Node> nodes = new ArrayList<>();
        int size = 9;
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(Character.toString((char)(97+i))));
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

        MinSpanningProblem problem = new MinSpanningTestCase(nodes, adj);
        Kruskal solver = new Kruskal(problem);
        Collection<? extends Edge> result = solver.resolve();

        assertEquals(8, result.size());
        assertEquals(37, result.stream().mapToInt(Edge::getCost).sum());
    }
}
