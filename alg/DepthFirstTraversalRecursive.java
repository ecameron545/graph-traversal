package alg;

import adt.Graph;

/**
 * DepthFirstTraversalRecursive
 * 
 * Class to provide an iterator giving access to the vertices of a graph in
 * depth first order from a given starting vertex, except that the internal
 * version is performed recursively
 * 
 * @author Thomas VanDrunen CSCI 345, Wheaton College June 18, 2015
 */
public class DepthFirstTraversalRecursive extends DepthFirstTraversal {

	/**
	 * Timestamps for when each vertex was finished.
	 */
	private int[] finishedTimes;

	/**
	 * Retrieve the finishing times of each vertex from the most recent traversal
	 * done by this object.
	 */
	public int[] finishedTimes() {
		return finishedTimes;
	}

	/**
	 * Counter to keep track of time for discoveries and finishings.
	 */
	private int time;
	public int j = 0;

	/**
	 * Execute a given operation on each vertex of the graph reachable able from
	 * start in a specific ordering
	 * 
	 * @param g
	 *            The graph on whose vertices to operate
	 * @param start
	 *            The vertex from which to start
	 * @param op
	 *            The encapsulated operation to perform
	 */
	@Override
	public void internal(Graph g, int start, PerformOnVertex op) {
		parents = new int[g.numVertices()];
		discoveryTimes = new int[g.numVertices()];
		finishedTimes = new int[g.numVertices()];
		for (int i = 0; i < g.numVertices(); i++)
			parents[i] = discoveryTimes[i] = finishedTimes[i] = -1;

		time = 0;
		internalR(g, start, op);
	}

	/**
	 * Perform depth first traversal from the given vertex in context of a larger
	 * depth first traversal already underway.
	 */
	private void internalR(Graph g, int v, PerformOnVertex op) {

		discoveryTimes[v] = time++;
		op.perform(v);

		System.out.println(v);

		for (Integer i : g.adjacents(v))
			if (discoveryTimes[i] == -1) {
				discoveryTimes[i] = time++;
				parents[i] = v;
				internalR(g, i, op);
			} 
		
		finishedTimes[v] = time++;
	}

}
