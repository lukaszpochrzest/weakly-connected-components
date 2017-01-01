package org.wcc.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class WeaklyConnectedComponents<T> {

    private List<TreeLikePath<T>> paths;
    private StronglyConnectedComponents<T> stronglyConnectedComponents;

    public WeaklyConnectedComponents(List<TreeLikePath<T>> paths, StronglyConnectedComponents<T> stronglyConnectedComponents) {
        this.paths = paths;
        this.stronglyConnectedComponents = stronglyConnectedComponents;
    }


    //TODO refactor
    public List<String> printToString() {
        return new WeaklyConnectedComponentsPrinter<>(stronglyConnectedComponents).printToString(paths);
    }


    public static class WeaklyConnectedComponentsPrinter<S> {

        private Stack<S> stack;

        private StronglyConnectedComponents<S> sccs;

        public WeaklyConnectedComponentsPrinter(StronglyConnectedComponents<S> sccs) {
            this.sccs = sccs;
            this.stack = new Stack<>();
        }

        public List<String> printToString(List<TreeLikePath<S>> treeLikePaths) {
            final List<String> result = new LinkedList<>();
            treeLikePaths.forEach(subPath -> result.addAll(printToString(subPath)));
            return result;
        }

        public List<String> printToString(TreeLikePath<S> rootTreeLikePath) {
            List<String> result = new LinkedList<>();
            toString(rootTreeLikePath, result);
            return result;
        }

        private void toString(TreeLikePath<S> rootTreeLikePath, List<String> result) {

            stack.push(rootTreeLikePath.getRootVertex());

            if(rootTreeLikePath.getSubPaths() == null) {
                result.add(stack.toString());
            } else {
                rootTreeLikePath.getSubPaths().forEach(subPath -> toString(subPath, result));
            }

            stack.pop();

        }

    }

}