package org.wcc.algorithm;

import lombok.Getter;
import org.jgrapht.Graphs;
import org.wcc.data.MyDirectedGraph;

import java.util.*;

public class Paths {

    public static class SubPath {

        @Getter
        private int rootVertex;

        @Getter
        private List<SubPath> subPaths;

        public SubPath(int rootVertex, List<SubPath> subPaths) {
            this.rootVertex = rootVertex;
            this.subPaths = subPaths;
        }

        public boolean includes(List<Integer> path) {

            if(path.size() == 1){
                if(subPaths != null) {
                    return false;
                } else {
                    return rootVertex == path.get(0);
                }
            } else {

                if(subPaths == null) {
                    return false;
                }

                if(rootVertex != path.get(0)) {
                    return false;
                } else {

                    for(SubPath subPath: subPaths) {
                        if(subPath.includes(path.subList(1, path.size()))) {
                            return true;
                        }
                    }
                    return false;
                }

            }
        }

//        /**
//         *
//         * @param paths
//         * @param path
//         * @return true if paths contains path
//         */
//        public static boolean contains(List<SubPath> paths, List<Integer> path) {
//
//        }

    }

    private MyDirectedGraph<Integer> directedGraph;

    private Map<Integer, SubPath> vertexPathsMap;

    public Paths(MyDirectedGraph<Integer> directedGraph) {
        this.directedGraph = directedGraph;
        this.vertexPathsMap = new HashMap<>();
    }

    public List<SubPath> paths() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        List<SubPath> subPaths = new ArrayList<>();

        // run an algorithm

        vertices.forEach(u -> {
            if(directedGraph.inDegreeOf(u) == 0) {
                subPaths.add(dfsPaths(u));
            }
        });

        return subPaths;
    }

    private SubPath dfsPaths(final Integer u) {

        SubPath uPaths = vertexPathsMap.get(u);
        if(uPaths != null) {
            return uPaths;
        }

        if (directedGraph.outDegreeOf(u) == 0) {
//            Path path = new Path();
//            path.addVertex(u);
//
//            uPaths = new ArrayList<>();
//            uPaths.add(path);
//
//            vertexPathsMap.put(u, uPaths);
//            return uPaths;
            SubPath subPath = new SubPath(u, null);
            vertexPathsMap.put(u, subPath);
            return subPath;
        }

//        uPaths = new ArrayList<>();
//        for (Integer v : Graphs.successorListOf(directedGraph, u)) {
//            for (Path vPath : dfsPaths(v)) {
////                //  prependU
//                Path uPath = new Path(vPath);
//                uPath.addVertex(0, u);
////                TODO this one is a problem
//                uPaths.add(uPath);
//            }
//        }
//        vertexPathsMap.put(u, uPaths);
        uPaths = new SubPath(u, new ArrayList<>());
        for (Integer v : Graphs.successorListOf(directedGraph, u)) {
            uPaths.getSubPaths().add(dfsPaths(v));
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
