package org.wcc.data;

import java.util.LinkedList;
import java.util.List;

public class Path {

    private List<Integer> vertices;

    public Path(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public Path() {
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Path))
            return false;

        return vertices.equals(((Path)o).vertices);
    }

    @Override
    public String toString() {
        return vertices.toString();
    }
}
