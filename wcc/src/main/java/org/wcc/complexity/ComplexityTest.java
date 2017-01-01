package org.wcc.complexity;

import org.wcc.complexity.execution.SingleExecutionTimeTest;
import org.wcc.complexity.utils.ComplexityTestResultsPrinter;
import org.wcc.complexity.utils.EdgeNumberGenerator;
import org.wcc.complexity.utils.function.LinearComplexityFunctionForGraph;

import java.util.Arrays;
import java.util.List;

public class ComplexityTest {

    private final static double EDGES_VERTICES_FACTOR = 1.8d;

    public static void test() {

        final List<Integer> verticeCounts = Arrays.asList(
                1000, 2000, 4000, 8000, 12000, 16000, 24000, 48000, 64000, 128000, 256000, 512000

        );

        ComplexityTestResultsPrinter complexityTestResultsPrinter = new ComplexityTestResultsPrinter(new LinearComplexityFunctionForGraph());

        EdgeNumberGenerator edgeNumberGenerator = new EdgeNumberGenerator(EDGES_VERTICES_FACTOR);

        System.out.println("Tests shall be done for graphs with sum of vertices and egdes (|V|+|E|) listed below:");
        verticeCounts.forEach( vertexCount -> System.out.println("\t" +  (vertexCount + edgeNumberGenerator.generate(vertexCount))));
        System.out.println();

        try{
            verticeCounts.forEach(verticesCount -> {
                System.out.print("Test for " + (verticesCount + edgeNumberGenerator.generate(verticesCount)) + "... ");

                SingleExecutionTimeTest.SingleExecutionTimeTestResult result =
                        SingleExecutionTimeTest.singleExecution(verticesCount, EDGES_VERTICES_FACTOR);

                complexityTestResultsPrinter.addTestResult(result);

                System.out.println("DONE.");

            });
        } catch (StackOverflowError | OutOfMemoryError e) {
            e.printStackTrace();

            System.out.println(
                            "\n\n" +
                            "\t****************************************************************\n" +
                            "\t****************************************************************\n" +
                            "\t******************* MEMORY EXCEPTION HANDLED *******************\n" +
                            "\t*********** PLEASE INCREASE JVM STACK (e.g. -Xss128M) **********\n" +
                            "\t****************************************************************\n" +
                            "\t****************************************************************\n" +
                            "\n\n"
            );

        } finally {
            System.out.println("\n\n Final results:");
            complexityTestResultsPrinter.print();
        }

    }

    public static void main(String[] args) {
        ComplexityTest.test();
    }

}
