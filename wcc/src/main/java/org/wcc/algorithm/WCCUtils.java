package org.wcc.algorithm;

import org.wcc.data.MyDirectedGraph;
import org.wcc.data.Path;
import org.wcc.data.StronglyConnectedComponents;
import org.wcc.data.WeaklyConnectedComponent;

import java.util.ArrayList;
import java.util.List;

public class WCCUtils {

    public static List<WeaklyConnectedComponent> computeWCC(MyDirectedGraph<Integer> directedGraph) {

        // kosaraju
        Kosaraju kosaraju = new Kosaraju(directedGraph);
        StronglyConnectedComponents<Integer> stronglyConnectedComponents = kosaraju.sccs();

        // transform
        Transform transform = new Transform(directedGraph, stronglyConnectedComponents);
        MyDirectedGraph<Integer> sccGraph = transform.transform();

        // wccs (paths)
        Paths paths = new Paths(sccGraph);
        List<Path> pathList = paths.paths();

        return sccGraphPathsToOriginalGraphWCCS(pathList, stronglyConnectedComponents);
    }

    private static List<WeaklyConnectedComponent> sccGraphPathsToOriginalGraphWCCS(List<Path> paths,
                                                                                   StronglyConnectedComponents<Integer> sccs) {
        List<WeaklyConnectedComponent> weaklyConnectedComponents = new ArrayList<>(paths.size());
        paths.forEach(path -> {
            WeaklyConnectedComponent wcc = new WeaklyConnectedComponent();
            path.getVertices().forEach(sccId -> wcc.addVertices(sccs.vertices(sccId)));
            weaklyConnectedComponents.add(wcc);
        });
        return weaklyConnectedComponents;
    }

}
