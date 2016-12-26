package org.wcc.algorithm;

import org.jgrapht.Graphs;
import org.wcc.data.MyDirectedGraph;
import org.wcc.data.Path;

import java.util.*;

public class Paths {

    private MyDirectedGraph<Integer> directedGraph;

    private Map<Integer, List<Path>> vertexPathsMap;

    public Paths(MyDirectedGraph<Integer> directedGraph) {
        this.directedGraph = directedGraph;
        this.vertexPathsMap = new HashMap<>();
    }

    public List<Path> paths() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        List<Path> paths = new ArrayList<>();

        // run an algorithm

        vertices.forEach(u -> {
            if(directedGraph.inDegreeOf(u) == 0) {
                paths.addAll(dfsPaths(u));
            }
        });

        return paths;
    }

    private List<Path> dfsPaths(final Integer u) {

        List<Path> uPaths = vertexPathsMap.get(u);
        if(uPaths != null) {
            return uPaths;
        }

        if (directedGraph.outDegreeOf(u) == 0) {
            Path path = new Path();
            path.addVertex(u);

            uPaths = new ArrayList<>();
            uPaths.add(path);

            vertexPathsMap.put(u, uPaths);
            return uPaths;
        }

        uPaths = new ArrayList<>();
        for (Integer v : Graphs.successorListOf(directedGraph, u)) {
            for (Path vPath : dfsPaths(v)) {
//                //  prependU
                Path uPath = new Path(vPath);
                uPath.addVertex(0, u);
//                TODO this one is a problem
                uPaths.add(uPath);
            }
        }
        vertexPathsMap.put(u, uPaths);

        return uPaths;



//        stack.push(u);
//
//        if (directedGraph.outDegreeOf(u) != 0) {
//            Graphs.successorListOf(directedGraph, u).forEach(v -> dfsPaths(v));
//        } else {
//            paths.add(new Path(new ArrayList<>(stack)));
//        }
//
//        stack.pop();
    }

}
