package org.wcc.data;

import java.util.*;


public class StronglyConnectedComponents<T> {

    private Set<Integer> sccIds = new HashSet<>();

    private SCCVerticesMap sccVerticesMap = new SCCVerticesMap();

    private Map<T, Integer> vertexToSCCAssignment;

    public StronglyConnectedComponents() {
        vertexToSCCAssignment = new HashMap<>();
    }

    public StronglyConnectedComponents(int verticesCount) {
        vertexToSCCAssignment = new HashMap<>((int) Math.ceil(verticesCount / 0.75));
    }

    public Integer getSCCIdOf(T vertex) {
        return vertexToSCCAssignment.get(vertex);
    }

    public void assignSCCIdTo(T vertex, Integer sccId) {
        sccIds.add(sccId);
        vertexToSCCAssignment.put(vertex, sccId);
        sccVerticesMap.addVertice(sccId, vertex);
    }

    public Set<Integer> sccIds() {
        return sccIds;
    }

    public Collection<T> vertices(Integer sccID) {
        return sccVerticesMap.vertices(sccID);
    }


    private class SCCVerticesMap {

        private Map<Integer, Set<T>> sccVerticesMap;

        public SCCVerticesMap() {
            this.sccVerticesMap = new HashMap<>();
        }

        public void addVertice(Integer sccId, T vertice) {
            Set<T> verticesOfSCC = sccVerticesMap.get(sccId);
            if(verticesOfSCC == null) {
                verticesOfSCC = new HashSet<>();
                verticesOfSCC.add(vertice);
                sccVerticesMap.put(sccId, verticesOfSCC);
            } else {
                verticesOfSCC.add(vertice);
            }
        }

        public Collection<T> vertices(Integer sccID) {
            return sccVerticesMap.get(sccID);
        }

    }

}