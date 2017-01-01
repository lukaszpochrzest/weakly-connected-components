package org.wcc.algorithm.data.impl;

import org.wcc.algorithm.data.WeaklyConnectedComponents;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.List;

public class WeaklyConnectedComponentsImpl<T> implements WeaklyConnectedComponents<T> {

    private List<TreeLikePath<T>> paths;
    private StronglyConnectedComponentsImpl<T> stronglyConnectedComponents;

    public WeaklyConnectedComponentsImpl(List<TreeLikePath<T>> paths, StronglyConnectedComponentsImpl<T> stronglyConnectedComponents) {
        this.paths = paths;
        this.stronglyConnectedComponents = stronglyConnectedComponents;
    }


    @Override
    public List<TreeLikePath<T>> getPaths() {
        return paths;
    }

    @Override
    public void setPaths(List<TreeLikePath<T>> paths) {
        this.paths = paths;
    }

    @Override
    public StronglyConnectedComponentsImpl<T> getStronglyConnectedComponents() {
        return stronglyConnectedComponents;
    }

    @Override
    public void setStronglyConnectedComponents(StronglyConnectedComponentsImpl<T> stronglyConnectedComponents) {
        this.stronglyConnectedComponents = stronglyConnectedComponents;
    }

    @Override
    public List<String> printToString() {
        return new WeaklyConnectedComponentsPrinterImpl<>(stronglyConnectedComponents).printToString(paths);
    }

}