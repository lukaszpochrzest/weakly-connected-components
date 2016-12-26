package org.wcc.algorithm;

import org.jgrapht.Graphs;
import org.wcc.data.MyDirectedGraph;
import org.wcc.data.StronglyConnectedComponents;

import java.util.HashSet;
import java.util.Set;

public class Transform {

    private MyDirectedGraph<Integer> directedGraph;

    private StronglyConnectedComponents<Integer> stronglyConnectedComponents;

    private Set<Integer> visited;

    private MyDirectedGraph<Integer> sccGraph;

    public Transform(MyDirectedGraph<Integer> directedGraph,
                     StronglyConnectedComponents<Integer> stronglyConnectedComponents) {
        this.directedGraph = directedGraph;
        this.stronglyConnectedComponents = stronglyConnectedComponents;
    }

    public MyDirectedGraph<Integer> transform() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        visited = new HashSet<>((int) Math.ceil(vertices.size() / 0.75));
        sccGraph = new MyDirectedGraph<>();
        stronglyConnectedComponents.sccIds().forEach(sccId -> sccGraph.addVertex(sccId));

        // run algorithm

        // DLA KAŻDEGO wierzchołka u grafu wywołaj procedurę DFS-EDGES(u, -1)

        vertices.forEach(u -> dfsEdges(u, -1));

        return sccGraph;
    }

    private void dfsEdges(final Integer u, final Integer sccId) {

        if(sccId > 0) {
            Integer sccIdOfU = stronglyConnectedComponents.getSCCIdOf(u);
            if(!sccId.equals(sccIdOfU)) {
                sccGraph.addEdge(sccId, sccIdOfU);
            }
        }

        if(!visited.contains(u)) {
            Integer sccIdOfU = stronglyConnectedComponents.getSCCIdOf(u);
            visited.add(u);
            Graphs.successorListOf(directedGraph, u).forEach(v -> dfsEdges(v, sccIdOfU));
        }
    }

}
