package org.wcc.algorithm.data;

import org.wcc.algorithm.paths.data.TreeLikePath;

import java.util.List;

public interface WeaklyConnectedComponentsPrinter<S> {

    List<String> printToString(List<TreeLikePath<S>> treeLikePaths);

}
