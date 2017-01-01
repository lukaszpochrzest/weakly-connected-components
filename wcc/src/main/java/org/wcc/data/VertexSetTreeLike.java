package org.wcc.data;

import java.util.Collection;
import java.util.List;

public class VertexSetTreeLike<S> extends TreeLike<VertexSetTreeLike<S>, Collection<S>> {

    public VertexSetTreeLike(Collection<S> rootVertex, List<VertexSetTreeLike<S>> subPaths) {
        super(rootVertex, subPaths);
    }
}
