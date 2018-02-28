package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.Set;
import adt.WeightedGraph;

/**
 * AdjListWeightedDirectedGraph
 * 
 * The graph implementation for use in MST and SSSP algorithms.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 23, 2015
 */
public class AdjListWeightedDirectedGraph implements WeightedGraph {


    /**
     * For each vertex u, the list of vertices v for
     * which there exists and edge from u to v.
     */
    private Set<WeightedEdge>[] adjSets;
    
    private int numEdges;

    /**
     * Plain constructor.
     */
    @SuppressWarnings("unchecked") 
    private AdjListWeightedDirectedGraph(int numVertices) {
        adjSets = (Set<WeightedEdge>[]) new Set[numVertices];
        for (int i = 0; i < numVertices; i++)
            adjSets[i] = new ListSet<WeightedEdge>();
    }

    /**
     * Builder for AdjListGraphs. This allows
     * a graph to be made by adding edges, but enforces
     * a graph to be immutable once construction is
     * finished. If, after a call to getGraph(), either
     * connect() is called or getGraph() is called again,
     * then a NullPointerException will be thrown (not an 
     * ideal exception for this, but unfortunately Java
     * doesn't seem to have a standard exception to use for
     * an expired operation).
     */
    public static class ALWDGBuilder {
        private AdjListWeightedDirectedGraph graph;
        public ALWDGBuilder(int numVertices) {
            graph = new AdjListWeightedDirectedGraph(numVertices);
        }
        public void connect(int u, int v, double weight) {
            WeightedEdge edge = new WeightedEdge(u, v, weight,true);
            graph.adjSets[u].add(edge);
            graph.adjSets[v].add(edge);
            graph.numEdges++;
        }
        public AdjListWeightedDirectedGraph getGraph() {
            AdjListWeightedDirectedGraph toReturn = graph;
            graph = null;
            return toReturn;
        }
    }
   
    
    /**
     * The number of vertices in the graph.
     */
   public int numVertices() {
        return adjSets.length;
    }
   
   /**
    * Iterate through the vertices adjacent to the given
    * vertex. Note that in a directed graph, these are
    * the vertices such that an edge exists from the
    * given one to them. 
    */
   public Iterable<Integer> adjacents(final int v) {
       return new Iterable<Integer>() {
         public Iterator<Integer> iterator() {  
             return new Iterator<Integer>() {
                 Iterator<WeightedEdge> innerIt = adjSets[v].iterator();
                public boolean hasNext() {
                    return innerIt.hasNext();
                }
                public Integer next() {
                    WeightedEdge edge = innerIt.next();
                    if (edge.first == v) return edge.second;
                    else return edge.first;
                }
                public void remove() {
                    innerIt.remove();
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
        boolean foundIt = false;
        for (Integer w : adjacents(u))
            foundIt |= w == v;
        return foundIt;
    }

    public int numEdges() {
        return numEdges;
    }

    public Iterable<WeightedEdge> edges() {
        class EdgeIt implements Iterator<WeightedEdge> {
            private WeightedEdge next;
            private int i;
            private Iterator<WeightedEdge> currentIt;

            public EdgeIt() {
                i = 0;
                currentIt = adjSets[i].iterator();
                advance();
            }
            
            private void advance() {
                next = null;
                while (next == null && i < adjSets.length) {
                    if (currentIt.hasNext()) {
                        next = currentIt.next();
                        if (next.second > next.first)
                            next = null;
                    }
                    else
                        currentIt = adjSets[i++].iterator();
                } 
            }
            public boolean hasNext() {
                return next != null;
            }

            public WeightedEdge next() {
                if (next == null) throw new NoSuchElementException();
                else {
                    WeightedEdge toReturn = next;
                    advance();
                    return toReturn;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        }
        
        return new Iterable<WeightedEdge>() {
            public Iterator<WeightedEdge> iterator() {
                return new EdgeIt();
            }
            
        };
    }

    public double weight(int u, int v) {
        double foundWeight = Double.POSITIVE_INFINITY;
        for (Iterator<WeightedEdge> it = adjSets[u].iterator();
                foundWeight == Double.POSITIVE_INFINITY && it.hasNext(); ) {
            WeightedEdge edge = it.next();
            if (v == edge.first || v == edge.second)
                foundWeight = edge.weight;
        }
        return foundWeight;
    }
}
