package org.wcc.complexity.execution;

import lombok.Getter;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.wcc.algorithm.WCCUtils;
import org.wcc.complexity.utils.EdgeNumberGenerator;
import org.wcc.complexity.utils.IntegerIncrementalGraphFactory;
import org.wcc.data.MyDirectedGraph;
import org.wcc.data.WeaklyConnectedComponent;

import java.util.List;

public class SingleExecutionTimeTest {

    public static class SingleExecutionTimeTestResult {

        @Getter
        private int verticesCount;

        @Getter
        private int edgeCount;

        @Getter
        private long executionTimeMillis;

        public SingleExecutionTimeTestResult(int verticesCount, int edgeCount, long executionTimeMillis) {
            this.verticesCount = verticesCount;
            this.edgeCount = edgeCount;
            this.executionTimeMillis = executionTimeMillis;
        }
    }

    public static SingleExecutionTimeTestResult singleExecution(int verticesCount, double edgePercentage) {
        MyDirectedGraph<Integer> graph = new MyDirectedGraph<>();

        EdgeNumberGenerator edgeNumberGenerator = new EdgeNumberGenerator(edgePercentage);
        int edgeNumber = edgeNumberGenerator.generate(verticesCount);

        GnmRandomGraphGenerator<Integer, DefaultEdge> gnmRandomGraphGeneratorGenerator =
                new GnmRandomGraphGenerator<>(verticesCount, edgeNumber);
        gnmRandomGraphGeneratorGenerator.generateGraph(
                graph,
                new IntegerIncrementalGraphFactory(),
                null
        );


        long startTime = System.currentTimeMillis();
        List<WeaklyConnectedComponent> result = WCCUtils.computeWCC(graph);
//        System.out.println(result.size());
        long estimatedTime1 = System.currentTimeMillis() - startTime;

        return new SingleExecutionTimeTestResult(
                verticesCount,
                edgeNumber,
                estimatedTime1
        );

    }


}
