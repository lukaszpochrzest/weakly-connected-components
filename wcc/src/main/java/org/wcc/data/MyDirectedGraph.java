package org.wcc.data;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class MyDirectedGraph<T> extends DefaultDirectedGraph<T, DefaultEdge> {

    public MyDirectedGraph() {
        super(DefaultEdge.class);
    }

}
