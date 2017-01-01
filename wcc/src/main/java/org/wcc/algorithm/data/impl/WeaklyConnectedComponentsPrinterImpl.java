package org.wcc.algorithm.data.impl;


import org.wcc.algorithm.data.WeaklyConnectedComponentsPrinter;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class WeaklyConnectedComponentsPrinterImpl<S> implements WeaklyConnectedComponentsPrinter<S> {

    private Stack<Collection<S>> stack;

    private StronglyConnectedComponentsImpl<S> sccs;

    public WeaklyConnectedComponentsPrinterImpl(StronglyConnectedComponentsImpl<S> sccs) {
        this.sccs = sccs;
        this.stack = new Stack<>();
    }

    @Override
    public List<String> printToString(List<TreeLikePath<S>> treeLikePaths) {
        final List<String> result = new LinkedList<>();
        treeLikePaths.forEach(subPath -> result.addAll(printToString(subPath)));
        return result;
    }

    @Override
    public List<String> printToString(TreeLikePath<S> rootTreeLikePath) {
        List<String> result = new LinkedList<>();
        toString(rootTreeLikePath, result);
        return result;
    }

    private void toString(TreeLikePath<S> rootTreeLikePath, List<String> result) {

        stack.push(sccs.getVertices(rootTreeLikePath.getRootVertex()));

        if(rootTreeLikePath.getSubPaths() == null) {
            result.add(stack.toString());
        } else {
            rootTreeLikePath.getSubPaths().forEach(subPath -> toString(subPath, result));
        }

        stack.pop();

    }

}