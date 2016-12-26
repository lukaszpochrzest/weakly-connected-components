package org.wcc.complexity.utils;

import org.wcc.complexity.execution.SingleExecutionTimeTest;
import org.wcc.complexity.utils.function.ComplexityFunctionForGraph;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class ComplexityTestResultsPrinter {

    private List<SingleExecutionTimeTest.SingleExecutionTimeTestResult> results;

    private ComplexityFunctionForGraph complexityFunctionForGraph;

    public ComplexityTestResultsPrinter(ComplexityFunctionForGraph complexityFunctionForGraph) {
        this.complexityFunctionForGraph = complexityFunctionForGraph;
        this.results = new LinkedList<>();
    }

    public void addTestResult(SingleExecutionTimeTest.SingleExecutionTimeTestResult singleExecutionTimeTestResult) {
        results.add(singleExecutionTimeTestResult);
    }

    // TODO think of a better implementation
    public void print() {

        DecimalFormat df = new DecimalFormat("#.00000");

        System.out.println("\t| |V|+|E| \t\t| time \t\t| time/(" + complexityFunctionForGraph.description() + ") \t|");
        results.forEach(
                result -> {
                    System.out.println("\t| " + (result.getVerticesCount() + result.getEdgeCount()) +  " \t\t| " + result.getExecutionTimeMillis() + " \t\t| " + df.format((double)result.getExecutionTimeMillis()/complexityFunctionForGraph.apply(result.getVerticesCount(), result.getEdgeCount())));
                }
        );
    }

}
