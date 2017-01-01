package org.wcc.data;

public interface TreeLikeConverter <T1 extends TreeLike<T1, V1>, V1, T2 extends TreeLike<T2, V2>, V2> {

    T2 convert (T1 treeLike);

}
