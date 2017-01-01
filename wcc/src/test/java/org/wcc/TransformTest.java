package org.wcc;

import org.hamcrest.CoreMatchers;
import org.jgrapht.Graphs;
import org.junit.Assert;
import org.junit.Test;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.algorithm.transform.impl.TransformImpl;
import org.wcc.data.MyDirectedGraph;

import java.util.List;

public class TransformTest {

    @Test
    public void should_TransformToSCCGraph_When_GivenOriginalGraphAndSCCInfo() {
        // given
        MyDirectedGraph<Integer> directedGraph = KosarajuTest.buildGraph();

        StronglyConnectedComponentsImpl<Integer> sccs = new StronglyConnectedComponentsImpl<>();
        sccs.assign(1, 1);
        sccs.assign(2, 1);
        sccs.assign(3, 1);

        sccs.assign(4,2);

        sccs.assign(5, 3);
        sccs.assign(6, 3);
        sccs.assign(7, 3);
        sccs.assign(8, 3);
        sccs.assign(9, 3);
        sccs.assign(10, 3);

        sccs.assign(11, 4);
        sccs.assign(12, 4);
        sccs.assign(13, 4);
        sccs.assign(14, 4);
        sccs.assign(15, 4);

        // when
        TransformImpl transform = new TransformImpl(directedGraph, sccs);
        MyDirectedGraph<Integer> sccGraph = transform.transform();

        // then
        sccGraph.vertexSet().forEach(
                v -> System.out.println(v + " -> " + Graphs.successorListOf(sccGraph, v))
        );

        assertVertexNeighbourhood(sccGraph, 1, 3);
        assertVertexNeighbourhood(sccGraph, 2, 1, 4);
        assertVertexNeighbourhood(sccGraph, 3, 4);
        assertVertexNeighbourhood(sccGraph, 4);

    }

    private void assertVertexNeighbourhood(MyDirectedGraph<Integer> sccGraph, int vertex, int... neighbours) {
        List<Integer> successors = Graphs.successorListOf(sccGraph, vertex);
        Assert.assertThat(successors.size(), CoreMatchers.equalTo(neighbours.length));
        for(int neighbour : neighbours) {
            Assert.assertTrue(successors.contains(neighbour));
        }
    }

}
