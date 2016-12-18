package org.wcc;

import java.util.List;

public class WCCUtils {

    public static void computeWCC(MyDirectedGraph<Integer> directedGraph) {

        // kosaraju
        Kosaraju kosaraju = new Kosaraju(directedGraph);
        Kosaraju.StronglyConnectedComponents<Integer> stronglyConnectedComponents = kosaraju.sccs();

        // transform
        Transform transform = new Transform(directedGraph, stronglyConnectedComponents);
        MyDirectedGraph sccGraph = transform.transform();

        // wccs (paths)
        List<WCC> wccs = wccs(sccGraph);

    }

    private static List<WCC> wccs(MyDirectedGraph<Integer> directedAcyclicGraph) {

        return null;
    }

}
