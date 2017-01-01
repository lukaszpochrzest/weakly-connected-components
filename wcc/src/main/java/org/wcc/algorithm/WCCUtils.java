package org.wcc.algorithm;

import org.wcc.data.MyDirectedGraph;
import org.wcc.data.StronglyConnectedComponents;
import org.wcc.data.TreeLikePath;
import org.wcc.data.WeaklyConnectedComponents;

import java.util.List;

public class WCCUtils {

    public static WeaklyConnectedComponents<Integer> computeWCC(MyDirectedGraph<Integer> directedGraph) {

        // kosaraju
        Kosaraju kosaraju = new Kosaraju(directedGraph);
        StronglyConnectedComponents<Integer> stronglyConnectedComponents = kosaraju.sccs();
        kosaraju = null;

        // transform
        Transform transform = new Transform(directedGraph, stronglyConnectedComponents);
        MyDirectedGraph<Integer> sccGraph = transform.transform();
        transform = null;

        // wccs (paths)
        Paths paths = new Paths(sccGraph);
        List<TreeLikePath<Integer>> pathList = paths.paths();
        paths = null;

        return new WeaklyConnectedComponents<>(pathList, stronglyConnectedComponents);
    }

}
