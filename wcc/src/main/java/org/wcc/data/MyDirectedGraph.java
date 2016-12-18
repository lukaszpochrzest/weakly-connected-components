package org.wcc.data;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

//TODO neighour lists as graph representation
public class MyDirectedGraph<T> extends DefaultDirectedGraph<T, DefaultEdge> {

    public MyDirectedGraph() {
        super(DefaultEdge.class);
    }

}
