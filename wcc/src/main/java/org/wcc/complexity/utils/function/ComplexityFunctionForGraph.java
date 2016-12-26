package org.wcc.complexity.utils.function;

public interface ComplexityFunctionForGraph {

    int apply(int numberOfVertices, int numberOfEdges);

    String description();

}
