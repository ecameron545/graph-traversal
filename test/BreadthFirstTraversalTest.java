package test;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.Graph;
import alg.BreadthFirstTraversal;
import alg.PerformOnVertex;

public abstract class BreadthFirstTraversalTest {

    protected Graph testGraph;
    
    protected abstract void reset(String filename);
    
    @Test
    public void smallExternal() {
        reset("small.graph");
        boolean[] hits = new boolean[testGraph.numVertices()];
        BreadthFirstTraversal testTrav = new BreadthFirstTraversal();
        for (Integer x : testTrav.external(testGraph, 1))  
            hits[x] = true;
        boolean noneMissed = true;
        for (boolean hit : hits) 
            noneMissed &= hit;
        
        assertTrue(noneMissed);
        
        int[] distances = testTrav.distances();
        int[] parents = testTrav.parents();
        assertEquals(1, distances[0]);
        assertEquals(1, parents[0]);
        assertEquals(0, distances[1]);
        assertEquals(-1, parents[1]);
        assertEquals(1, distances[2]);
        assertEquals(1, parents[2]);
        assertEquals(1, distances[3]);
        assertEquals(1, parents[3]);
        assertEquals(2, distances[4]);
        assertEquals(0, parents[4]);
        assertEquals(2, distances[5]);
        assertEquals(3, parents[5]);
    }
    
    @Test
    public void smallInternal() {
        reset("small.graph");
        final boolean[] hits = new boolean[testGraph.numVertices()];
        BreadthFirstTraversal testTrav = new BreadthFirstTraversal();

        PerformOnVertex pov = new PerformOnVertex() {
            public void perform(int vertex) {
                hits[vertex] = true;
            }
        };
        
        testTrav.internal(testGraph, 1, pov);
        boolean noneMissed = true;
        for (boolean hit : hits)
            noneMissed &= hit;
        assertTrue(noneMissed);
        
        int[] distances = testTrav.distances();
        int[] parents = testTrav.parents();
        assertEquals(distances[0], 1);
        assertEquals(parents[0], 1);
        assertEquals(distances[1], 0);
        assertEquals(parents[1], -1);
        assertEquals(distances[2], 1);
        assertEquals(parents[2], 1);
        assertEquals(distances[3], 1);
        assertEquals(parents[3], 1);
        assertEquals(distances[4], 2);
        assertEquals(parents[4], 0);
        assertEquals(distances[5], 2);
        assertEquals(parents[5], 3);
        
    }
}
