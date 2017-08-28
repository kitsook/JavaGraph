package net.clarenceho.algo.shortestpath.test;

import net.clarenceho.algo.shortestpath.Dijkstra;
import net.clarenceho.algo.shortestpath.ShortestPathProblem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MinCostSqMatrixTest {

    @Test
    public void testGivenExamples() {
        List<TestCase> cases = new ArrayList<>();
        cases.add(new TestCase(2, new int[][] {
                { 42, 93 },
                {  7, 14 }
            }, 63));
        cases.add(new TestCase(5, new int[][] {
                {  31, 100,  65,  12,  18 },
                {  10,  13,  47, 157,   6 },
                { 100, 113, 174,  11,  33 },
                {  88, 124,  41,  20, 140 },
                {  99,  32, 111,  41,  20 }
            }, 327));

        for (TestCase testCase : cases) {
            runTestCase(testCase);
        }
    }

    private void runTestCase(TestCase testCase) {
        ShortestPathProblem problem = new MinCostSqMatrix(testCase.values);
        Dijkstra solver = new Dijkstra(problem);
        assertEquals(testCase.expected, solver.resolve());
    }

    private class TestCase {
        int size;
        int[][] values;
        int expected;

        TestCase(int size, int[][] values, int expected) {
            this.size = size;
            this.values = values;
            this.expected = expected;
        }
    }

}