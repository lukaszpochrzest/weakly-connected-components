package org.wcc;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.wcc.algorithm.data.impl.WeaklyConnectedComponentsPrinterImpl;
import org.wcc.algorithm.paths.impl.PathsImpl;
import org.wcc.data.MyDirectedGraph;
import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.*;

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
        PathsImpl paths = new PathsImpl(directedGraph);

        // when
        List<TreeLikePath<Integer>> wccs = paths.computePaths();

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

    @Test
    public void should_Remove_Previous_And_Insert_When_UberPath() {
        Collection<Integer> collection1 = Arrays.asList(1);
        Collection<Integer> collection2 = Arrays.asList(2, 3);
        Collection<Integer> collection3 = Arrays.asList(4, 5, 6);
        Collection<Integer> collection4 = Arrays.asList(7, 8, 9, 10);

        Collection<Integer> collection5 = Arrays.asList(2, 3);
        Collection<Integer> collection6 = Arrays.asList(7, 8, 9, 10);


        Stack<Collection<Integer>> stack56 = new Stack<>();
        stack56.addAll(Arrays.asList(collection5, collection6));

        Stack<Collection<Integer>> stack14 = new Stack<>();
        stack14.addAll(Arrays.asList(collection1, collection2, collection3, collection4));


        List<Stack<Collection<Integer>>> stackList = new ArrayList<>(1);
        stackList.add(stack56);


        WeaklyConnectedComponentsPrinterImpl.tryToInsertAndReorganize(stack14, stackList);


        Assert.assertTrue(stackList.size() == 1);
        Assert.assertTrue(stackList.contains(stack14));
    }

    @Test
    public void should_Not_Insert_When_Sub_Path() {
        Collection<Integer> collection1 = Arrays.asList(1);
        Collection<Integer> collection2 = Arrays.asList(2, 3);
        Collection<Integer> collection3 = Arrays.asList(4, 5, 6);
        Collection<Integer> collection4 = Arrays.asList(7, 8, 9, 10);

        Collection<Integer> collection5 = Arrays.asList(2, 3);
        Collection<Integer> collection6 = Arrays.asList(7, 8, 9, 10);


        Stack<Collection<Integer>> stack14 = new Stack<>();
        stack14.addAll(Arrays.asList(collection1, collection2, collection3, collection4));

        Stack<Collection<Integer>> stack56 = new Stack<>();
        stack56.addAll(Arrays.asList(collection5, collection6));


        List<Stack<Collection<Integer>>> stackList = Arrays.asList(stack14);


        WeaklyConnectedComponentsPrinterImpl.tryToInsertAndReorganize(stack56, stackList);


        Assert.assertTrue(stackList.size() == 1);
        Assert.assertTrue(stackList.contains(stack14));
    }

    @Test
    public void should_Insert_When_Not_Sub_Path() {
        Collection<Integer> collection1 = Arrays.asList(1);
        Collection<Integer> collection2 = Arrays.asList(2, 3);
        Collection<Integer> collection3 = Arrays.asList(4, 5, 6);
        Collection<Integer> collection4 = Arrays.asList(7, 8, 9, 10);

        Collection<Integer> collection7 = Arrays.asList(2, 3);
        Collection<Integer> collection8 = Arrays.asList(7, 8, 9, 10, 11);


        Stack<Collection<Integer>> stack14 = new Stack<>();
        stack14.addAll(Arrays.asList(collection1, collection2, collection3, collection4));


        Stack<Collection<Integer>> stack78 = new Stack<>();
        stack78.addAll(Arrays.asList(collection7, collection8));


        List<Stack<Collection<Integer>>> stackList = new ArrayList<>(1);
        stackList.add(stack14);

        WeaklyConnectedComponentsPrinterImpl.tryToInsertAndReorganize(stack78, stackList);


        Assert.assertTrue(stackList.size() == 2);
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
