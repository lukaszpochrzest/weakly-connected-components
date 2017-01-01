package org.wcc;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.wcc.algorithm.Paths;
import org.wcc.data.MyDirectedGraph;
import org.wcc.data.TreeLikePath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathsTest {

    /**
     * https://i.stack.imgur.com/zuLmn.png
     */
    public static MyDirectedGraph<Integer> buildDAG() {
        MyDirectedGraph<Integer> directedGraph = new MyDirectedGraph<>();

        // fill with vertices (16)
        for(int i = 0; i < 16; i++) {
            directedGraph.addVertex(i);
        }

        // fill with edges
        directedGraph.addEdge(0, 7);
        directedGraph.addEdge(0, 10);
        directedGraph.addEdge(0, 13);
        directedGraph.addEdge(0, 14);

        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(1, 9);
        directedGraph.addEdge(1, 13);

        directedGraph.addEdge(2, 10);
        directedGraph.addEdge(2, 12);
        directedGraph.addEdge(2, 13);
        directedGraph.addEdge(2, 14);

        directedGraph.addEdge(3, 6);
        directedGraph.addEdge(3, 8);
        directedGraph.addEdge(3, 9);
        directedGraph.addEdge(3, 11);

        directedGraph.addEdge(4, 7);

        directedGraph.addEdge(5, 6);
        directedGraph.addEdge(5, 7);
        directedGraph.addEdge(5, 9);
        directedGraph.addEdge(5, 10);

        directedGraph.addEdge(6, 15);

        directedGraph.addEdge(7, 14);

        directedGraph.addEdge(8, 15);

        directedGraph.addEdge(9, 11);
        directedGraph.addEdge(9, 14);

        directedGraph.addEdge(10, 14);

        return directedGraph;
    }

    @Test
    public void should_TransformToSCCGraph_When_GivenOriginalGraphAndSCCInfo() {
        // given
        MyDirectedGraph<Integer> directedGraph = buildDAG();
        Paths paths = new Paths(directedGraph);

        // when
        List<TreeLikePath<Integer>> wccs = paths.paths();

        // then
        wccs.forEach(System.out::println);

        List<List<Integer>> graphPaths = new ArrayList<>(22);
        graphPaths.add(Arrays.asList(0, 7, 14));
        graphPaths.add(Arrays.asList(0, 10, 14));
        graphPaths.add(Arrays.asList(0, 13));
        graphPaths.add(Arrays.asList(0, 14));

        graphPaths.add(Arrays.asList(1, 2, 10, 14));
        graphPaths.add(Arrays.asList(1, 2, 12));
        graphPaths.add(Arrays.asList(1, 2, 13));
        graphPaths.add(Arrays.asList(1, 2, 14));
        graphPaths.add(Arrays.asList(1, 9, 11));
        graphPaths.add(Arrays.asList(1, 9, 14));
        graphPaths.add(Arrays.asList(1, 13));

        graphPaths.add(Arrays.asList(3, 6, 15));
        graphPaths.add(Arrays.asList(3, 8, 15));
        graphPaths.add(Arrays.asList(3, 9, 11));
        graphPaths.add(Arrays.asList(3, 9, 14));
        graphPaths.add(Arrays.asList(3, 11));

        graphPaths.add(Arrays.asList(4, 7, 14));

        graphPaths.add(Arrays.asList(5, 6, 15));
        graphPaths.add(Arrays.asList(5, 7, 14));
        graphPaths.add(Arrays.asList(5, 9, 11));
        graphPaths.add(Arrays.asList(5, 9, 14));
        graphPaths.add(Arrays.asList(5, 10, 14));

        Assert.assertThat(countPaths(wccs), CoreMatchers.equalTo(graphPaths.size()));

        assertContainsAll(wccs, graphPaths);
    }

    private int countPaths(List<TreeLikePath<Integer>> paths) {
        int size = 0;
        for (TreeLikePath path : paths) {
            size += path.size();
        }
        return size;
    }

    private void assertContainsAll(List<TreeLikePath<Integer>> shouldContain, List<List<Integer>> shouldBeContained) {
        shouldBeContained.forEach(
                path -> Assert.assertTrue(TreeLikePath.contains(shouldContain, path))
        );
    }

}
