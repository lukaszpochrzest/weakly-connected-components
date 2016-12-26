package org.wcc;

import org.hamcrest.CoreMatchers;
import org.jgrapht.Graphs;
import org.junit.Assert;
import org.junit.Test;
import org.wcc.data.StronglyConnectedComponents;
import org.wcc.algorithm.Transform;
import org.wcc.data.MyDirectedGraph;

import java.util.List;

public class TransformTest {

    @Test
    public void should_TransformToSCCGraph_When_GivenOriginalGraphAndSCCInfo() {
        // given
        MyDirectedGraph<Integer> directedGraph = KosarajuTest.buildGraph();

        StronglyConnectedComponents<Integer> sccs = new StronglyConnectedComponents<>();
        sccs.assignSCCIdTo(1, 1);
        sccs.assignSCCIdTo(2, 1);
        sccs.assignSCCIdTo(3, 1);

        sccs.assignSCCIdTo(4,2);

        sccs.assignSCCIdTo(5, 3);
        sccs.assignSCCIdTo(6, 3);
        sccs.assignSCCIdTo(7, 3);
        sccs.assignSCCIdTo(8, 3);
        sccs.assignSCCIdTo(9, 3);
        sccs.assignSCCIdTo(10, 3);

        sccs.assignSCCIdTo(11, 4);
        sccs.assignSCCIdTo(12, 4);
        sccs.assignSCCIdTo(13, 4);
        sccs.assignSCCIdTo(14, 4);
        sccs.assignSCCIdTo(15, 4);

        // when
        Transform transform = new Transform(directedGraph, sccs);
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
