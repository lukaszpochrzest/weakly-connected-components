package org.wcc;

import java.util.LinkedList;
import java.util.List;

public class WCC {

    private List<Integer> vertices;

    public WCC() {
        vertices = new LinkedList<>();
    }

    public void addVertex(Integer vertex) {
        vertices.add(vertex);
    }

    public void removeVertex(Integer vertex) {
        vertices.remove(vertex);
    }

    public boolean includes(Integer vertex) {
        return vertices.indexOf(vertex) != -1;
    }



}
