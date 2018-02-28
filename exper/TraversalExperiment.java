package exper;

import impl.UnWeightedGraphFactory;


import adt.Graph;
import alg.BreadthFirstTraversal;
import alg.DepthFirstTraversal;
import alg.DepthFirstTraversalRecursive;
import alg.PerformOnVertex;

public class TraversalExperiment {

    private static PerformOnVertex nop = new PerformOnVertex() {
        public void perform(int vertex) {}
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
        Graph amg = UnWeightedGraphFactory.directedAMGraphFromFile("sew-subgraph");
        Graph alg = UnWeightedGraphFactory.directedALGraphFromFile("sew-subgraph");
        BreadthFirstTraversal bft = new BreadthFirstTraversal();
        DepthFirstTraversal dft = new DepthFirstTraversal();
        DepthFirstTraversalRecursive dftr = new DepthFirstTraversalRecursive();

        long fore, aft;
        
        System.out.println("Adjacency matrix:");

        fore = System.nanoTime();
        for (Integer v : bft.external(amg, 0))
            nop.perform(v);
        aft = System.nanoTime();
        System.out.println("BF external: \t" + (aft-fore));
        
        fore = System.nanoTime();
        bft.internal(amg, 0, nop);
        aft = System.nanoTime();
        System.out.println("BF internal: \t" + (aft-fore));

        fore = System.nanoTime();
        for (Integer v : dft.external(amg, 0))
            nop.perform(v);
        aft = System.nanoTime();
        System.out.println("DF external: \t" + (aft-fore));
        
        fore = System.nanoTime();
        dft.internal(amg, 0, nop);
        aft = System.nanoTime();
        System.out.println("DF internal: \t" + (aft-fore));

        fore = System.nanoTime();
        dftr.internal(amg, 0, nop);
        aft = System.nanoTime();
        System.out.println("DF internal R: \t" + (aft-fore));

        System.out.println("Adjacency list:");

        fore = System.nanoTime();
        for (Integer v : bft.external(alg, 0))
            nop.perform(v);
        aft = System.nanoTime();
        System.out.println("BF external: \t" + (aft-fore));
        
        fore = System.nanoTime();
        bft.internal(alg, 0, nop);
        aft = System.nanoTime();
        System.out.println("BF internal: \t" + (aft-fore));

        fore = System.nanoTime();
        for (Integer v : dft.external(alg, 0))
            nop.perform(v);
        aft = System.nanoTime();
        System.out.println("DF external: \t" + (aft-fore));
        
        fore = System.nanoTime();
        dft.internal(alg, 0, nop);
        aft = System.nanoTime();
        System.out.println("DF internal: \t" + (aft-fore));

        fore = System.nanoTime();
        dftr.internal(alg, 0, nop);
        aft = System.nanoTime();
        System.out.println("DF internal R: \t" + (aft-fore));

        }        
    }
}
