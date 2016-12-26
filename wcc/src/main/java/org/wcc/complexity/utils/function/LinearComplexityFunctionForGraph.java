package org.wcc.complexity.utils.function;

public class LinearComplexityFunctionForGraph implements ComplexityFunctionForGraph {

    @Override
    public int apply(int numberOfVertices, int numberOfEdges) {
        return numberOfVertices + numberOfEdges;
    }

    @Override
    public String description() {
        return "|V|+|E|";
    }
}
