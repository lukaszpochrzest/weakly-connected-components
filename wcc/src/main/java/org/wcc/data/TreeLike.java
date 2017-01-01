package org.wcc.data;

import lombok.Getter;

import java.util.List;

public abstract class TreeLike<T extends TreeLike<T,V>, V> {

    //TODO move it out of here
    @Getter
    protected V rootVertex;

    @Getter
    protected List<T> subPaths;

    public TreeLike(V rootVertex, List<T> subPaths) {
        this.rootVertex = rootVertex;
        this.subPaths = subPaths;
    }

    //    public <TT extends TreeLike<TT, VV>, VV> TreeLike<TT, VV> convert(TreeLikeConverter<T, V, TT, VV> treeConverter) {
//        return treeConverter.convert(this);
//    }

    //TODO move methods functions to TreeLike

    public boolean includes(List<V> path) {

        if(path.size() == 1){
            if(subPaths != null) {
                return false;
            } else {
                return rootVertex.equals(path.get(0));
            }
        } else {

            if(subPaths == null) {
                return false;
            }

            if(!rootVertex.equals(path.get(0))) {
                return false;
            } else {

                for(T subPath: subPaths) {
                    if(subPath.includes(path.subList(1, path.size()))) {
                        return true;
                    }
                }
                return false;
            }

        }
    }

    public int size() {
        if(subPaths == null) {
            return 1;
        } else {
            int size = 0;
            for (T subPath : subPaths) {
                size += subPath.size();
            }
            return size;
        }
    }

}
