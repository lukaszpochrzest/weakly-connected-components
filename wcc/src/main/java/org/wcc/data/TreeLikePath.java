package org.wcc.data;

import java.util.List;

public class TreeLikePath<S> extends TreeLike<TreeLikePath<S>, S> {

    public TreeLikePath(S rootVertex, List<TreeLikePath<S>> treeLikePaths) {
        super(rootVertex, treeLikePaths);
    }


    /**
     *
     * @param paths
     * @param path
     * @return true if paths contains path
     */
    public static <S> boolean contains(List<TreeLikePath<S>> paths, List<S> path) {
        for (TreeLikePath<S> treeLikePath : paths) {
            if(treeLikePath.includes(path)){
                return true;
            }
        }
        return false;
    }




}