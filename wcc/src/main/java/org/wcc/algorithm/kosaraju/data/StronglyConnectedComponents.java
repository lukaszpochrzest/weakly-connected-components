package org.wcc.algorithm.kosaraju.data;

import java.util.Collection;
import java.util.Set;

public interface StronglyConnectedComponents<T> {

    T getSCC(T vertex);

    void assign(T vertex, T sccId);

    Set<T> getSCCs();

    Collection<T> getVertices(T sccID);

}
