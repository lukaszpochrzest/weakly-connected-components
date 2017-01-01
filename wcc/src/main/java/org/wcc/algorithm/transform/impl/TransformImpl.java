package org.wcc.algorithm.transform.impl;

import org.jgrapht.Graphs;
import org.wcc.algorithm.transform.Transform;
import org.wcc.data.MyDirectedGraph;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;

import java.util.HashSet;
import java.util.Set;

public class TransformImpl implements Transform {

    private MyDirectedGraph<Integer> directedGraph;

    private StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents;

    private Set<Integer> visited;

    private MyDirectedGraph<Integer> sccGraph;

    public TransformImpl(MyDirectedGraph<Integer> directedGraph,
                         StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents) {
        this.directedGraph = directedGraph;
        this.stronglyConnectedComponents = stronglyConnectedComponents;
    }

    public MyDirectedGraph<Integer> transform() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        visited = new HashSet<>((int) Math.ceil(vertices.size() / 0.75));
        sccGraph = new MyDirectedGraph<>();
        stronglyConnectedComponents.getSCCs().forEach(sccId -> sccGraph.addVertex(sccId));

        // run algorithm

        // DLA KAŻDEGO wierzchołka u grafu wywołaj procedurę DFS-EDGES(u, -1)

        vertices.forEach(u -> dfsEdges(u, -1));

        return sccGraph;
    }

    private void dfsEdges(final Integer u, final Integer sccId) {

        if(sccId > 0) {
            Integer sccIdOfU = stronglyConnectedComponents.getSCC(u);
            if(!sccId.equals(sccIdOfU)) {
                sccGraph.addEdge(sccId, sccIdOfU);
            }
        }

        if(!visited.contains(u)) {
            Integer sccIdOfU = stronglyConnectedComponents.getSCC(u);
            visited.add(u);
            Graphs.successorListOf(directedGraph, u).forEach(v -> dfsEdges(v, sccIdOfU));
        }
    }

}
