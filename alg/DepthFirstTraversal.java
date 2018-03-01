package alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Graph;
import adt.Queue;
import adt.Stack;
import impl.ListQueue;
import impl.ListStack;

/**
 * DepthFirstTraversal
 * 
 * Class to provide an iterator giving access to the vertices
 * of a graph in depth first order from a given starting
 * vertex.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 18, 2015
 */
public class DepthFirstTraversal implements Traversal {

    /**
     * Timestamps for when each vertex was discovered.
     */
    protected int[] discoveryTimes;
    
    /**
     * The parents of each vertex from the most recent
     * traversal done by this object; -1 for each vertex
     * unreachable from the starting point of that traversal.
     */
    protected int[] parents;
    
    /**
     * Retrieve the discovery times of each vertex from the most recent
     * traversal done by this object.
     */
    public int[] discoveryTimes() { return discoveryTimes; }
        
    /**
     * Retrieve the parents of each vertex from the most recent
     * traversal done by this object.
     */
    public int[] parents() { return parents; }

    
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
        discoveryTimes = new int[g.numVertices()];
        //finishedTimes = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = discoveryTimes[i] = /*finishedTimes[i] =*/ -1;
        
        final Stack<Integer> worklist = new ListStack<Integer>();
        worklist.push(start);
        discoveryTimes[start] = 0;

        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    int time = 1;
                    
					// return false only when the stack (worklist) is empty.
                    public boolean hasNext() {
                    	return !worklist.isEmpty();
                    }
                    
                    public Integer next() {
                    	// empty stack check
						if (!hasNext())
							return null;

						// the current vertex for which we are analyzing the adjacent vertices
						int current = worklist.pop();

						// run through all the adjacent vertices to current
						for (Integer i : g.adjacents(current)) {

							// if the adjacent vertices have already been discovered
							// or is the starting vertex, skip it.
							if (parents[i] != -1 || i == start)
								continue;
							
							// otherwise update the distance and parent values for the newly
							// discovered vertex and put it in the queue.
							else {
								discoveryTimes[i] = time++;
								parents[i] = current;
								worklist.push(i);
							}
						}
						return current;
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
        discoveryTimes = new int[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            parents[i] = discoveryTimes[i] = -1;
        int time = 0;
        
        final Stack<Integer> worklist = new ListStack<Integer>();
		worklist.push(start);

		
		// keep moving through the graph until the worklist is empty which means there
		// are no new vertices to perform the operation on.
		while (!worklist.isEmpty()) {
			
			// the current vertex for which we are analyzing the adjacent vertices
			int current = worklist.pop();

			// run through all the adjacent vertices to current
			for (Integer i : g.adjacents(current)) {

				// if the adjacent vertices have already been discovered
				// or is the starting vertex, skip it.
				if (parents[i] != -1 || i == start)
					continue;

				// otherwise update the distance and parent values for the newly
				// discovered vertex and put it in the queue.
				else {
					discoveryTimes[i] = time++;
					parents[i] = current;
					worklist.push(i);
				}
			}
			
			op.perform(current); // perform the operation on the current vertex
		}
           
    }
}
