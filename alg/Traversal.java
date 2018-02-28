package alg;

import adt.Graph;

/**
 * Traversal
 * 
 * Interface to define a class that, given a graph, will
 * provide an iterator giving access to the vertices of
 * the graph in a specific order, such as depth-first or
 * breadth-first.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 18, 2015
 */
public interface Traversal {

    /**
     * Provide access to the vertices of this graph in 
     * a specific ordering.
     * @param g The graph whose vertices to iterate over
     * @param start The vertex from which to start
     * @return An iterator providing access to the vertices
     * of this graph in a specific ordering.
     * @throws NoSuchElementException If start is not a valid
     * vertex.
     */
    Iterable<Integer> external(Graph g, int start);
    
    /**
     * Execute a given operation on each vertex of the graph
     * reachable able from start in a specific ordering
     * @param g The graph on whose vertices to operate
     * @param start The vertex from which to start
     * @param op The encapsulated operation to perform
     */
    void internal(Graph g, int start, PerformOnVertex op);
    
    /**
     * Return the parents of each vertex on the most recent
     * traversal.
     */
    int[] parents();
}
