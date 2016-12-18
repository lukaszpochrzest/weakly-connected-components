package org.wcc.data;

import java.util.List;

public class WeaklyConnectedComponent extends VertexContainer {

    public WeaklyConnectedComponent(List<Integer> vertices) {
        super(vertices);
    }

    public WeaklyConnectedComponent() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WeaklyConnectedComponent))
            return false;

        return vertices.equals(((WeaklyConnectedComponent)o).vertices);
    }
}
