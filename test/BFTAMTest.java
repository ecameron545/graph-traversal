package test;

import impl.UnWeightedGraphFactory;

public class BFTAMTest extends BreadthFirstTraversalTest {

    protected void reset(String filename) {
        testGraph = UnWeightedGraphFactory.directedAMGraphFromFile(filename);
    }

}
