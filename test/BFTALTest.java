package test;

import impl.UnWeightedGraphFactory;

public class BFTALTest extends BreadthFirstTraversalTest {

    protected void reset(String filename) {
        testGraph = UnWeightedGraphFactory.directedALGraphFromFile(filename);
    }

}
