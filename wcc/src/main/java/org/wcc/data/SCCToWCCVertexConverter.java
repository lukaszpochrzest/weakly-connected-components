package org.wcc.data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SCCToWCCVertexConverter<V> implements TreeLikeConverter<TreeLikePath<V>, V, VertexSetTreeLike<V>, Collection<V>> {

    private StronglyConnectedComponents<V> sccs;

    public SCCToWCCVertexConverter(StronglyConnectedComponents<V> sccs) {
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
        return new VertexSetTreeLike<>(sccs.vertices(treeLike.getRootVertex()), branches);
    }
}
