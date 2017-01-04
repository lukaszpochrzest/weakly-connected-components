package org.wcc.algorithm.data.impl;


import org.wcc.algorithm.data.WeaklyConnectedComponentsPrinter;
import org.wcc.algorithm.kosaraju.data.impl.StronglyConnectedComponentsImpl;
import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.*;
import java.util.stream.Collectors;

public class WeaklyConnectedComponentsPrinterImpl<S> implements WeaklyConnectedComponentsPrinter<S> {

    private Stack<Collection<S>> stack;

    private StronglyConnectedComponentsImpl<S> sccs;

    public WeaklyConnectedComponentsPrinterImpl(StronglyConnectedComponentsImpl<S> sccs) {
        this.sccs = sccs;
        this.stack = new Stack<>();
    }

    @Override
    public List<String> printToString(List<TreeLikePath<S>> treeLikePaths) {
        final List<Stack<Collection<S>>> result = new LinkedList<>();
        treeLikePaths.forEach(subPath -> result.addAll(gather(subPath)));

        List<String> stringsResult = result.stream()
                .map(s -> s.toString())
                .collect(Collectors.toList());
        return stringsResult;
    }

    private List<Stack<Collection<S>>> gather(TreeLikePath<S> rootTreeLikePath) {
        List<Stack<Collection<S>>> result = new LinkedList<>();
        gather(rootTreeLikePath, result);
        return result;
    }

    private void gather(TreeLikePath<S> rootTreeLikePath, List<Stack<Collection<S>>> result) {

        stack.push(sccs.getVertices(rootTreeLikePath.getRootVertex()));

        if(rootTreeLikePath.getSubPaths() == null) {
//            if(!isSubStackInOneOf(stack, result)) {
//                result.add((Stack<Collection<S>>)stack.clone());
//            }
            tryToInsertAndReorganize((Stack<Collection<S>>)stack.clone(), result);
        } else {
            rootTreeLikePath.getSubPaths().forEach(subPath -> gather(subPath, result));
        }

        stack.pop();

    }

//    public static <S> boolean isSubStackInOneOf(Stack<Collection<S>> subStack, List<Stack<Collection<S>>> result) {
//        for (Stack<Collection<S>> stack : result) {
//            if(stack.containsAll(subStack)) {
//                return true;
//            }
//        }
//        return false;
//    }

    // a bit of a hack here
    public static <S> void tryToInsertAndReorganize(Stack<Collection<S>> subStack, List<Stack<Collection<S>>> result) {

        // part one
        result.removeIf(subStack::containsAll);

        // part two
        boolean insert = true;
        for (Stack<Collection<S>> stack : result) {
            if(stack.containsAll(subStack)) {
                insert = false;
                break;
            }
        }
        if(insert) {
            result.add(subStack);
        }
    }


}