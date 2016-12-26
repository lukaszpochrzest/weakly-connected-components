package org.wcc.complexity.utils;

public class EdgeNumberGenerator {

    private final double factor;

    public EdgeNumberGenerator(double factor) {
        if(factor < 0.0d) {
            throw new IllegalArgumentException();
        }
        this.factor = factor;
    }

    public int generate(int verticesNumber) {
        return (int)(factor * verticesNumber);
    }

}
