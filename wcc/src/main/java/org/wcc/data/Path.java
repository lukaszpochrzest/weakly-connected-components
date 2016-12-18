package org.wcc.data;

import java.util.List;

public class Path extends VertexContainer {

    public Path(List<Integer> vertices) {
        super(vertices);
    }

    public Path() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Path))
            return false;

        return vertices.equals(((Path)o).vertices);
    }
}
