package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import adt.Graph;
import alg.DepthFirstTraversal;
import alg.DepthFirstTraversalRecursive;
import alg.PerformOnVertex;

public abstract class DepthFirstTraversalTest {

    protected Graph testGraph;
    
    protected abstract void reset(String filename);
    
    @Test
    public void smallExternal() {
        reset("small.graph");
        boolean[] hits = new boolean[testGraph.numVertices()];
        DepthFirstTraversal testTrav = new DepthFirstTraversal();
        int start = 1;
        for (Integer x : testTrav.external(testGraph, start))  
            hits[x] = true;

        boolean noneMissed = true;
        for (boolean hit : hits)  
            noneMissed &= hit;
        
        assertTrue(noneMissed);
        checkDiscovery(testTrav, start);
    }

    private void checkDiscovery(DepthFirstTraversal testTrav, int start) {
        int[] discoveryTimes = testTrav.discoveryTimes();
        //int[] finishedTimes = testTrav.finishedTimes();
        int[] parents = testTrav.parents();
        for (int i = 0; i < testGraph.numVertices(); i++) {
            if (i != start) {
                int j = parents[i];
                assertTrue(testGraph.adjacent(j, i));
                assertTrue(discoveryTimes[j] < discoveryTimes[i]);
                //assertTrue(discoveryTimes[i] < finishedTimes[i]);
                //assertTrue(finishedTimes[i] < finishedTimes[j]);
            }
        }
    }
    
    @Test
    public void smallInternal() {
        reset("small.graph");
        final boolean[] hits = new boolean[testGraph.numVertices()];
        DepthFirstTraversal testTrav = new DepthFirstTraversal();

        PerformOnVertex pov = new PerformOnVertex() {
            public void perform(int vertex) {
                hits[vertex] = true;
            }
        };
        
        int start = 1;
        testTrav.internal(testGraph, start, pov);
        boolean noneMissed = true;
        for (boolean hit : hits)
            noneMissed &= hit;
        assertTrue(noneMissed);
        
        checkDiscovery(testTrav, start);
    }
    
       private void checkParenthesis(DepthFirstTraversalRecursive testTrav, int start) {
            int[] discoveryTimes = testTrav.discoveryTimes();
            int[] finishedTimes = testTrav.finishedTimes();
            int[] parents = testTrav.parents();
            for (int i = 0; i < testGraph.numVertices(); i++) {
                if (i != start) {
                    int j = parents[i];
                    assertTrue(testGraph.adjacent(j, i));
                    assertTrue(discoveryTimes[j] < discoveryTimes[i]);
                    assertTrue(discoveryTimes[i] < finishedTimes[i]);
                    assertTrue(finishedTimes[i] < finishedTimes[j]);
                }
            }
        }

    
    @Test
    public void smallInternalR() {
        reset("small.graph");
        final boolean[] hits = new boolean[testGraph.numVertices()];
        DepthFirstTraversalRecursive testTrav = new DepthFirstTraversalRecursive();

        PerformOnVertex pov = new PerformOnVertex() {
            public void perform(int vertex) {
                hits[vertex] = true;
            }
        };

        int start = 1;
        testTrav.internal(testGraph, start, pov);
        boolean noneMissed = true;
        for (boolean hit : hits)
            noneMissed &= hit;
        assertTrue(noneMissed);

        checkParenthesis(testTrav, start);
    }
}
