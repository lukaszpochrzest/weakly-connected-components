package org.wcc.algorithm;

import org.jgrapht.Graphs;
import org.wcc.data.MyDirectedGraph;
import org.wcc.data.Path;

import java.util.*;

public class Paths {

    private MyDirectedGraph<Integer> directedGraph;

    private Stack<Integer> stack;

    private List<Path> paths;

    public Paths(MyDirectedGraph<Integer> directedGraph) {
        this.directedGraph = directedGraph;
    }

    public List<Path> paths() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        stack = new Stack<>();
        paths = new LinkedList<>();

        // run an algorithm

        vertices.forEach(u -> {
            if(directedGraph.inDegreeOf(u) == 0) {
                dfsPaths(u);
            }
        });

        return paths;
    }

    private void dfsPaths(final Integer u) {

        stack.push(u);

        if (directedGraph.outDegreeOf(u) != 0) {
            Graphs.successorListOf(directedGraph, u).forEach(v -> dfsPaths(v));
        } else {
            paths.add(new Path(new ArrayList<>(stack)));
        }

        stack.pop();
    }

}
