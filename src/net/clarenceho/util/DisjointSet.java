package net.clarenceho.util;

import java.util.*;

/**
 * Implementation of disjoint-set.
 */
public class DisjointSet<E> {
    private Map<E, Set<E>> item2Set;

    public DisjointSet() {
        item2Set = new HashMap<>();
    }

    /**
     * Creates a new set that contain the item.  The item shouldn't be in any other set.
     *
     * @param item item to be added
     * @throws IllegalArgumentException if the item already exists in other set
     */
    public void makeSet(E item) throws IllegalArgumentException {
        if (item2Set.containsKey(item)) {
            throw new IllegalArgumentException("Item already exists in distinct set");
        }
        Set<E> newSet = new HashSet<>();
        newSet.add(item);
        item2Set.put(item, newSet);
    }

    /**
     * Finds the set that contain the item.
     *
     * @param item item to be searched
     * @return the set that contains the item.  null is not found.
     */
    public Set<E> findSet(E item) {
        return item2Set.get(item);
    }

    /**
     * Combines the two sets that contain given items.
     *
     * @param item1 item one whose the set containing it will be combined
     * @param item2 item two whose the set containing it will be combined
     * @throws IllegalArgumentException if the provided item can't be found or the two items already in the same set
     */
    public void union(E item1, E item2) throws IllegalArgumentException {
        Set<E> set1 = item2Set.get(item1);
        Set<E> set2 = item2Set.get(item2);
        if (set1 == null || set2 == null) {
            throw new IllegalArgumentException("One or more item can't be found: " + item1 + ", " + item2);
        }
        if (set1.equals(set2)) {
            throw new IllegalArgumentException("The two items are in the same set already");
        }

        Set<E> newSet = new HashSet<>();
        newSet.addAll(set1);
        newSet.addAll(set2);

        for (E e : newSet) {
            Set<E> previous = item2Set.put(e, newSet);
            if (previous != null) {
                previous.clear();
            }
        }
    }
}
