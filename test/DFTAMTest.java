package test;

import impl.UnWeightedGraphFactory;

public class DFTAMTest extends DepthFirstTraversalTest {

    protected void reset(String filename) {
        testGraph = UnWeightedGraphFactory.directedAMGraphFromFile(filename);
    }

}
