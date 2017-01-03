package org.wcc.algorithm;

import org.wcc.algorithm.kosaraju.impl.KosarajuImpl;
import org.wcc.algorithm.paths.impl.PathsImpl;
import org.wcc.algorithm.transform.impl.TransformImpl;
import org.wcc.data.MyDirectedGraph;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.algorithm.paths.data.TreeLikePath;
import org.wcc.algorithm.data.impl.WeaklyConnectedComponentsImpl;

import java.util.List;

public class WCCUtils {

    public static WeaklyConnectedComponentsImpl<Integer> computeWCC(MyDirectedGraph<Integer> directedGraph) {

        // kosaraju
        KosarajuImpl kosaraju = new KosarajuImpl(directedGraph);
        StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents = kosaraju.computeStronglyConnectedComponents();

        // transform
        TransformImpl transform = new TransformImpl(directedGraph, stronglyConnectedComponents);
        MyDirectedGraph<Integer> sccGraph = transform.transform();

        // wccs (paths)
        PathsImpl paths = new PathsImpl(sccGraph);
        List<TreeLikePath<Integer>> pathList = paths.computePaths();

        return new WeaklyConnectedComponentsImpl<>(pathList, stronglyConnectedComponents);
    }

}
