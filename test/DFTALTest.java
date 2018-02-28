package test;

import impl.UnWeightedGraphFactory;

public class DFTALTest extends DepthFirstTraversalTest {

    protected void reset(String filename) {
        testGraph = UnWeightedGraphFactory.directedALGraphFromFile(filename);
    }

}
