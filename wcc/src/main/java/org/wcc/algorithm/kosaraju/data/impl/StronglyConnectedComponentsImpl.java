package org.wcc.algorithm.kosaraju.data.impl;

import java.util.*;


public class StronglyConnectedComponentsImpl<T> implements org.wcc.algorithm.kosaraju.data.StronglyConnectedComponents<T> {

    private Set<T> sccIds = new HashSet<>();

    private SCCVerticesMap sccVerticesMap = new SCCVerticesMap();

    private Map<T, T> vertexToSCCAssignment;

    public StronglyConnectedComponentsImpl() {
        vertexToSCCAssignment = new HashMap<>();
    }

    public StronglyConnectedComponentsImpl(int verticesCount) {
        vertexToSCCAssignment = new HashMap<>((int) Math.ceil(verticesCount / 0.75));
    }

    @Override
    public T getSCC(T vertex) {
        return vertexToSCCAssignment.get(vertex);
    }

    @Override
    public void assign(T vertex, T sccId) {
        sccIds.add(sccId);
        vertexToSCCAssignment.put(vertex, sccId);
        sccVerticesMap.addVertice(sccId, vertex);
    }

    @Override
    public Set<T> getSCCs() {
        return sccIds;
    }

    @Override
    public Collection<T> getVertices(T sccID) {
        return sccVerticesMap.vertices(sccID);
    }

    private class SCCVerticesMap {

        private Map<T, Set<T>> sccVerticesMap;

        public SCCVerticesMap() {
            this.sccVerticesMap = new HashMap<>();
        }

        public void addVertice(T sccId, T vertice) {
            Set<T> verticesOfSCC = sccVerticesMap.get(sccId);
            if(verticesOfSCC == null) {
                verticesOfSCC = new HashSet<>();
                verticesOfSCC.add(vertice);
                sccVerticesMap.put(sccId, verticesOfSCC);
            } else {
                verticesOfSCC.add(vertice);
            }
        }

        public Collection<T> vertices(T sccID) {
            return sccVerticesMap.get(sccID);
        }

    }

}