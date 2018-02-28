package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Graph;

/**
 * AdjMatrixGraph
 * 
 * Graph implementation using an adjacency matrix.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 17, 2015
 */
public class AdjMatrixGraph implements Graph {

 
    /**
     * Representation of the edges such that edges[i][j] 
     * iff there is an edge from i to j.
     */
    private boolean[][] edges;

    private int numEdges;
    
    /**
     * Plain constructor, called only by nested class
     * GraphBuilder.
     */
    private AdjMatrixGraph(int numVertices) {
        edges = new boolean[numVertices][numVertices];
    }

    
    /**
     * Builder for AdjMatrixGraphs. This allows
     * a graph to be made by adding edges, but enforces
     * a graph to be immutable once construction is
     * finished. If, after a call to getGraph(), either
     * connect() is called or getGraph() is called again,
     * then a NullPointerException will be thrown (not an 
     * ideal exception for this, but unfortunately Java
     * doesn't seem to have a standard exception to use for
     * an expired operation).
     */
    public static class AMGBuilder {
        private AdjMatrixGraph graph;
        public AMGBuilder(int numVertices) {
            graph = new AdjMatrixGraph(numVertices);
        }
        public void connect(int u, int v) {
            graph.edges[u][v] = true;
            graph.numEdges++;
        }
        public AdjMatrixGraph getGraph() {
            AdjMatrixGraph toReturn = graph;
            graph = null;
            return toReturn;
        }
    }

    
    /**
     * The number of vertices in the graph.
     */
    public int numVertices() {
        return edges.length;
    }


    /**
     * Iterate through the vertices adjacent to the given
     * vertex. Note that in a directed graph, these are
     * the vertices such that an edge exists from the
     * given one to them. 
     */
   public Iterable<Integer> adjacents(final int v) {
       int j = 0;
       while (j < edges.length && ! edges[v][j]) j++;
       final int finalJ = j;
       return new Iterable<Integer>() {
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                int i = finalJ;
                public boolean hasNext() {
                    return i < edges.length;
                }
                public Integer next() {
                    if (! hasNext())
                        throw new NoSuchElementException();
                    int toReturn = i;
                    do i++; while (i < edges.length && ! edges[v][i]);
                    return toReturn;
                }
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
       };
   }

   
   /**
    * Determine adjacency between two given vertices.
    * For undirected graphs, this relationship is
    * symmetric and so this method returns the same
    * result whatever order the parameters are given.
    * For directed graphs, this method indicates whether
    * or not there exists an edge from u to v.
    */
   public boolean adjacent(int u, int v) {
       return edges[u][v];
   }

   public int numEdges() {
       return numEdges;
   }

}
