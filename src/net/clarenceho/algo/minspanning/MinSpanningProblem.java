package net.clarenceho.algo.minspanning;

import net.clarenceho.util.Edge;
import net.clarenceho.util.Node;

import java.util.Collection;
import java.util.List;

/**
 * Created by clarence on 8/27/2017.
 */
public interface MinSpanningProblem {
    Collection<? extends Node> getNodes();
    List<? extends Edge> getSortedEdges();
}
