package org.wcc;

import java.util.List;

public class WCCUtils {

    public static void computeWCC(MyDirectedGraph<Integer> directedGraph) {

        // kosaraju
        Kosaraju kosaraju = new Kosaraju(directedGraph);
        Kosaraju.StronglyConnectedComponents<Integer> stronglyConnectedComponents = kosaraju.sccs();

        // transform
        Transform transform = new Transform(directedGraph, stronglyConnectedComponents);
        MyDirectedGraph<Integer> sccGraph = transform.transform();

        // wccs (paths)
        Paths paths = new Paths(sccGraph);
        List<Path> wccs = paths.paths();

    }

}
