package org.wcc;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.wcc.algorithm.kosaraju.impl.KosarajuImpl;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.data.MyDirectedGraph;

public class KosarajuTest {

    @Test
    public void should_FindSCC_When_GivenGraph() {
        // given
        MyDirectedGraph<Integer> directedGraph = buildGraph();
        KosarajuImpl kosaraju = new KosarajuImpl(directedGraph);

        // when
        StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents =  kosaraju.computeStronglyConnectedComponents();

        // then
        for(Integer vertex : directedGraph.vertexSet()) {
            System.out.println("vertex:" + vertex + " -> scc:" + stronglyConnectedComponents.getSCC(vertex));
        }

        // check sccs: {1,2,3}, {4}, {5, 6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}
        assert_1_4_5_11_areInDifferentSCC(stronglyConnectedComponents);

        assert_1_2_3_areInSameSCC(stronglyConnectedComponents);
        assert_5_6_7_8_9_10_areInSameSCC(stronglyConnectedComponents);
        assert_11_12_13_14_15_areInSameSCC(stronglyConnectedComponents);
    }

    /**
     * https://www.greatandlittle.com/studios/public/blowup-images/Dart/.directed_graph_sccs_m.jpg
     * @return graph from link WITHOUT THIS SINGGLE VERTEX1->VERTEX1 EDGE !!
     */
    public static MyDirectedGraph<Integer> buildGraph() {
        MyDirectedGraph<Integer> directedGraph = new MyDirectedGraph<>();

        // fill with vertices (15)
        for(int i = 1; i <= 15; ++i) {
            directedGraph.addVertex(i);
        }

        // fill with edges (28)
        directedGraph.addEdge(1, 3);
        directedGraph.addEdge(2, 1);
        directedGraph.addEdge(3, 2);
        directedGraph.addEdge(3, 5);

        directedGraph.addEdge(4, 1);
        directedGraph.addEdge(4, 2);
        directedGraph.addEdge(4, 12);
        directedGraph.addEdge(4, 13);

        directedGraph.addEdge(5, 6);
        directedGraph.addEdge(5, 8);
        directedGraph.addEdge(6, 7);
        directedGraph.addEdge(6, 8);
        directedGraph.addEdge(6, 10);
        directedGraph.addEdge(7, 10);
        directedGraph.addEdge(8, 9);
        directedGraph.addEdge(8, 10);
        directedGraph.addEdge(9, 5);
        directedGraph.addEdge(9, 11);
        directedGraph.addEdge(10, 9);
        directedGraph.addEdge(10, 11);
        directedGraph.addEdge(10, 14);

        directedGraph.addEdge(11, 12);
        directedGraph.addEdge(11, 14);
        directedGraph.addEdge(12, 13);
        directedGraph.addEdge(13, 11);
        directedGraph.addEdge(13, 15);
        directedGraph.addEdge(14, 13);
        directedGraph.addEdge(15, 14);

        return directedGraph;
    }

    public void assert_1_4_5_11_areInDifferentSCC(StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents) {
        // check if 1, 4, 5, 11 are in different sccs

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(1),
                CoreMatchers.not(stronglyConnectedComponents.getSCC(4))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(1),
                CoreMatchers.not(stronglyConnectedComponents.getSCC(5))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(1),
                CoreMatchers.not(stronglyConnectedComponents.getSCC(11))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(4),
                CoreMatchers.not(stronglyConnectedComponents.getSCC(5))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(4),
                CoreMatchers.not(stronglyConnectedComponents.getSCC(11))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(5),
                CoreMatchers.not(stronglyConnectedComponents.getSCC(11))
        );
    }

    public void assert_1_2_3_areInSameSCC(StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents) {
        //check if 1, 2, 3 are in the same scc

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(1),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(2))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(2),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(3))
        );
    }

    public void assert_5_6_7_8_9_10_areInSameSCC(StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents) {
        // check if 5, 6, 7, 8, 9, 10 are in the same scc

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(5),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(6))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(7),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(8))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(5),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(7))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(7),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(9))
        );
    }

    public void assert_11_12_13_14_15_areInSameSCC(StronglyConnectedComponentsImpl<Integer> stronglyConnectedComponents) {
        // check if 11, 12, 13, 14, 15 are in the same scc

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(11),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(12))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(11),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(13))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(11),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(14))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(11),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(15))
        );

        Assert.assertThat(
                stronglyConnectedComponents.getSCC(11),
                CoreMatchers.equalTo(stronglyConnectedComponents.getSCC(15))
        );
    }

}
