package alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Graph;
import adt.Queue;
import impl.ListQueue;

/**
 * BreadthFirstTraversal
 * 
 * Class to provide an iterator giving access to the vertices
 * of a graph in breadth first order from a given starting
 * vertex.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 18, 2015
 */
public class BreadthFirstTraversal implements Traversal {

    /**
     * The parents of each vertex from the most recent
     * traversal done by this object; -1 for each vertex
     * unreachable from the starting point of that traversal.
     */
    private int[] parents;
    
    /**
     * The distances from the starting point for each vertex 
     * from the most recent traversal done by this object; 
     * -1 for each vertex unreachable from the starting point 
     * of that traversal.
     */
    private int[] distances;
    
    
    /**
     * Retrieve the parents of each vertex from the most recent
     * traversal done by this object.
     */
    public int[] parents () { return parents; }


    /**
     * Retrieve the distances from the starting point for each vertex 
     * from the most recent traversal done by this object.
     */
    public int[] distances() { return distances; }
    
    /**
     * Provide access to the vertices of this graph in 
     * a specific ordering.
     * @param g The graph whose vertices to iterate over
     * @param start The vertex at which to start
     * @return An iterator providing access to the vertices
     * of this graph in a specific ordering.
     * @throws NoSuchElementException If start is not a valid
     * vertex.
     */
    public Iterable<Integer> external(final Graph g, int start) {
        parents = new int[g.numVertices()];
        distances = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            distances[i] = -1;
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = -1;
        distances[start] = 0;
        final Queue<Integer> worklist = new ListQueue<Integer>();
        worklist.enqueue(start);
        return new Iterable<Integer>() {

            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {

                    public boolean hasNext() {
                        // Add code
                    	throw new UnsupportedOperationException();
                    }

                    public Integer next() {
                        // Add code
                    	throw new UnsupportedOperationException();
                    }

                };
            }
        };
    }

    /**
     * Execute a given operation on each vertex of the graph
     * reachable able from start in a specific ordering
     * @param g The graph on whose vertices to operate
     * @param start The vertex from which to start
     * @param op The encapsulated operation to perform
     */
    public void internal(Graph g, int start, PerformOnVertex op) {
        parents = new int[g.numVertices()];
        distances = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = distances[i] = -1;
        distances[start] = 0;
        
        // Add code
    	throw new UnsupportedOperationException();

    }

}
