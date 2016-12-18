package org.wcc;

import org.jgrapht.Graphs;

import java.util.*;

public class Kosaraju {

    private MyDirectedGraph<Integer> directedGraph;

    private Set<Integer> visited;

    private Stack<Integer> stack;

    private StronglyConnectedComponents<Integer> stronglyConnectedComponents;

    public Kosaraju(MyDirectedGraph<Integer> directedGraph) {
        this.directedGraph = directedGraph;
    }

    public StronglyConnectedComponents<Integer> sccs() {

        // initialize

        Set<Integer> vertices = directedGraph.vertexSet();
        visited = new HashSet<>((int) Math.ceil(vertices.size() / 0.75));
        stack = new Stack<>();
        // result vertex-strongly connected component assignment - scc[vertexId] = sccId
        stronglyConnectedComponents = new StronglyConnectedComponents<>(vertices.size());

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
        if(stronglyConnectedComponents.getSCCIdOf(u) == null) {

            // Przydziel u do komponentu którego korzeniem jest root.
            stronglyConnectedComponents.assignSCCIdTo(u, root);

            // Dla każdego wierzchołka v, który jest na drugim końcu krawędzi wchodzącej do u, wykonaj PRZYDZIEL(v,root).
            Graphs.predecessorListOf(directedGraph, u).forEach(v -> assign(v, root));
        }
    }


    public static class StronglyConnectedComponents<T> {

        private Set<Integer> sccIds = new HashSet<>();

        private Map<T, Integer> vertexToSCCAssignment;

        public StronglyConnectedComponents() {
            vertexToSCCAssignment = new HashMap<>();
        }

        public StronglyConnectedComponents(int verticesCount) {
            vertexToSCCAssignment = new HashMap<>((int) Math.ceil(verticesCount / 0.75));
        }

        public Integer getSCCIdOf(T vertex) {
            return vertexToSCCAssignment.get(vertex);
        }

        public void assignSCCIdTo(T vertex, Integer sccId) {
            sccIds.add(sccId);
            vertexToSCCAssignment.put(vertex, sccId);
        }

        public Set<Integer> sccIds() {
            return sccIds;
        }

    }


}
