package org.wcc.complexity;

import org.wcc.complexity.execution.SingleExecutionTimeTest;
import org.wcc.complexity.utils.ComplexityTestResultsPrinter;
import org.wcc.complexity.utils.function.LinearComplexityFunctionForGraph;

import java.util.Arrays;
import java.util.List;

public class ComplexityTest {

    private final static double EDGES_VERTICES_FACTOR = 0.8d;//1.1d;

    public static void test() {

        final List<Integer> verticeCounts = Arrays.asList(
                50000, 100000, 200000, 400000, 600000, 800000, 1000000, 1100000
//                1000, 5000, 10000, 20000, 40000

        );

        ComplexityTestResultsPrinter complexityTestResultsPrinter = new ComplexityTestResultsPrinter(new LinearComplexityFunctionForGraph());

        verticeCounts.forEach(verticesCount -> {
            System.out.print("Test for " + verticesCount + "... ");

            SingleExecutionTimeTest.SingleExecutionTimeTestResult result =
                    SingleExecutionTimeTest.singleExecution(verticesCount, EDGES_VERTICES_FACTOR);

            complexityTestResultsPrinter.addTestResult(result);

            System.out.println("DONE.");

            complexityTestResultsPrinter.print();

        });

        complexityTestResultsPrinter.print();
    }

    public static void main(String[] args) {
        ComplexityTest.test();
    }

}
