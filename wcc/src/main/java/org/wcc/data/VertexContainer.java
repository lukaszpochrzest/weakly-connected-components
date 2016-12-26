package org.wcc.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VertexContainer {

    protected List<Integer> vertices;

    public VertexContainer(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public VertexContainer() {
        vertices = new ArrayList<>();
    }

    public void addVertex(Integer vertex) {
        vertices.add(vertex);
    }

    public void addVertex(int index, Integer vertex) {
        vertices.add(index, vertex);
    }

    public void addVertices(Collection<Integer> vertices) {
        this.vertices.addAll(vertices);
    }

    public void removeVertex(Integer vertex) {
        vertices.remove(vertex);
    }

    public boolean includes(Integer vertex) {
        return vertices.indexOf(vertex) != -1;
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return vertices.toString();
    }


}
