package org.wcc.complexity.utils;

import org.jgrapht.VertexFactory;

public class IntegerIncrementalGraphFactory implements VertexFactory<Integer> {

    private static int i = 0;

    @Override
    public Integer createVertex() {
        return i++;
    }

    public void reset() {
        i=0;
    }
}
