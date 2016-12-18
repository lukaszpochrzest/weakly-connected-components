package org.wcc.ui.config;

import lombok.Getter;
import lombok.Setter;
import org.wcc.data.MyDirectedGraph;

import java.util.List;

public class GraphConfig {

    @Getter
    @Setter
    private List<Integer> vertices;

    @Getter
    @Setter
    private List<Edge> edges;

    public static class Edge {

        @Getter
        @Setter
        private Integer source;

        @Getter
        @Setter
        private Integer target;

        public Edge() {
        }

        public Edge(Integer source, Integer target) {
            this.source = source;
            this.target = target;
        }

    }

    public MyDirectedGraph<Integer> convert() {
        MyDirectedGraph<Integer> directedGraph = new MyDirectedGraph<>();
        vertices.forEach(v -> directedGraph.addVertex(v));
        edges.forEach(e -> directedGraph.addEdge(e.getSource(), e.getTarget()));
        return directedGraph;
    }


}
