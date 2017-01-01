package org.wcc.complexity.utils;

import org.jgrapht.VertexFactory;

public class IntegerIncrementalGraphFactory implements VertexFactory<Integer> {

    private static int i = 0;

    public IntegerIncrementalGraphFactory() {
        reset();
    }

    @Override
    public Integer createVertex() {
        return i++;
    }

    public void reset() {
        i=0;
    }
}
