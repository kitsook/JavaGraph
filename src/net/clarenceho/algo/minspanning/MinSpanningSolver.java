package net.clarenceho.algo.minspanning;

import net.clarenceho.util.Edge;

import java.util.Collection;

/**
 * Created by clarence on 8/28/2017.
 */
public interface MinSpanningSolver {
    Collection<? extends Edge> resolve();
}
