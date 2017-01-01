package org.wcc.algorithm.kosaraju.impl;

import org.jgrapht.Graphs;
import org.wcc.algorithm.kosaraju.Kosaraju;
import org.wcc.data.MyDirectedGraph;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;

import java.util.*;

public class KosarajuImpl implements Kosaraju {

    private MyDirectedGraph<Integer> directedGraph;

    private Set<Integer> visited;

    private Stack<Integer> stack;

    private StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents;

    public KosarajuImpl(MyDirectedGraph<Integer> directedGraph) {
        this.directedGraph = directedGraph;
    }

    public StronglyConnectedComponentsImpl<Integer> computeStronglyConnectedComponents() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        visited = new HashSet<>((int) Math.ceil(vertices.size() / 0.75));
        stack = new Stack<>();
        // result vertex-strongly connected component assignment - scc[vertexId] = sccId
        stronglyConnectedComponents = new StronglyConnectedComponentsImpl<>(vertices.size());

        // run algorithm

        // DLA KAZDEGO wierzchołka u grafu G wykonaj procedure ODWIEDZ(u).
        vertices.forEach(v -> visit(v));
        // Dla kazdego elementu u na stosie S, wykonaj w kolejnosci wyjmowania ze stosu procedurę PRZYDZIEL(u,u).
        while(!stack.isEmpty()) {
            Integer u = stack.pop();
            assign(u, u);
        }

        return stronglyConnectedComponents;
    }

    private void visit(final Integer u) {

        if(u<0) {
            throw new RuntimeException();
        }

        // JEZELI u jest oznaczone jako nieodwiedzone:
        if(!visited.contains(u)) {
            // Zaznacz u jako odwiedzone.
            visited.add(u);

            // Dla kazdego wierzcholka v, który jest na drugim koncu krawedzi wychodzacej z u, wykonaj ODWIEDZ(v).
            Graphs.successorListOf(directedGraph, u).forEach(v -> visit(v));

            // Odloz u na stos S.
            stack.push(u);
        }
    }

    private void assign(final Integer u, final Integer root) {

        // JEŻELI u nie zostało oznaczone jako przydzielone do komponentu:
        if(stronglyConnectedComponents.getSCC(u) == null) {

            // Przydziel u do komponentu którego korzeniem jest root.
            stronglyConnectedComponents.assign(u, root);

            // Dla każdego wierzchołka v, który jest na drugim końcu krawędzi wchodzącej do u, wykonaj PRZYDZIEL(v,root).
            Graphs.predecessorListOf(directedGraph, u).forEach(v -> assign(v, root));
        }
    }

}
