package org.wcc.algorithm.paths.impl;

import org.jgrapht.Graphs;
import org.wcc.algorithm.paths.Paths;
import org.wcc.data.MyDirectedGraph;
import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.*;

public class PathsImpl implements Paths {

    private MyDirectedGraph<Integer> directedGraph;

    private Map<Integer, TreeLikePath<Integer>> vertexPathsMap;

    public PathsImpl(MyDirectedGraph<Integer> directedGraph) {
        this.directedGraph = directedGraph;
        this.vertexPathsMap = new HashMap<>();
    }

    @Override
    public List<TreeLikePath<Integer>> computePaths() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        List<TreeLikePath<Integer>> treeLikePaths = new ArrayList<>();

        // run an algorithm

        vertices.forEach(u -> {
            if(directedGraph.inDegreeOf(u) == 0) {
                treeLikePaths.add(dfsPaths(u));
            }
        });

        return treeLikePaths;
    }

    private TreeLikePath<Integer> dfsPaths(final Integer u) {

        TreeLikePath<Integer> uPaths = vertexPathsMap.get(u);
        if(uPaths != null) {
            return uPaths;
        }

        if (directedGraph.outDegreeOf(u) == 0) {
            TreeLikePath<Integer> treeLikePath = new TreeLikePath<>(u, null);
            vertexPathsMap.put(u, treeLikePath);
            return treeLikePath;
        }

        uPaths = new TreeLikePath<>(u, new ArrayList<>());
        for (Integer v : Graphs.successorListOf(directedGraph, u)) {
            uPaths.getSubPaths().add(dfsPaths(v));
        }
        vertexPathsMap.put(u, uPaths);
        return uPaths;

    }

}
