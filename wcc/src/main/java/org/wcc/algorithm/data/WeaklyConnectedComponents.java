package org.wcc.algorithm.data;

import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.List;

public interface WeaklyConnectedComponents<T> {

    List<TreeLikePath<T>> getPaths();
    void setPaths(List<TreeLikePath<T>> paths);

    StronglyConnectedComponentsImpl<T> getStronglyConnectedComponents();
    void setStronglyConnectedComponents(StronglyConnectedComponentsImpl<T> stronglyConnectedComponents);

    List<String> printToString();

}
