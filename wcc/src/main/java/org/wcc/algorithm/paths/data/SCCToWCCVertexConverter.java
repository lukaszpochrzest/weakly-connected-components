package org.wcc.algorithm.paths.data;


import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SCCToWCCVertexConverter<V> implements TreeLikeConverter<TreeLikePath<V>, V, VertexSetTreeLike<V>, Collection<V>> {

    private StronglyConnectedComponentsImpl<V> sccs;

    public SCCToWCCVertexConverter(StronglyConnectedComponentsImpl<V> sccs) {
        this.sccs = sccs;
    }

    @Override
    public VertexSetTreeLike<V> convert(TreeLikePath<V> treeLike) {
        List<VertexSetTreeLike<V>> branches = null;
        if(treeLike.getSubPaths() != null) {
            branches = new ArrayList<>(treeLike.getSubPaths().size());
            for (TreeLikePath<V> vTreeLikePath : treeLike.getSubPaths()) {
                branches.add(convert(vTreeLikePath));
            }
        }
        return new VertexSetTreeLike<>(sccs.getVertices(treeLike.getRootVertex()), branches);
    }
}
