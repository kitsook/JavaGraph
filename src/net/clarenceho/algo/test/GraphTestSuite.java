package net.clarenceho.algo.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        net.clarenceho.algo.minspanning.test.MinSpanningTest.class,
        net.clarenceho.algo.shortestpath.test.MinCostSqMatrixTest.class
})

/**
 * Test suite for graph algorithms
 */
public class GraphTestSuite {
}
