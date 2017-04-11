package com.allpago.testtask.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by svolkovskyi on 11.04.17.
 */
public class Graph {
    private final Map<String, Vertex> adjacencyList = new HashMap<>();


    public Graph(List<String[]> lines) {
        for (String[] parts : lines) {
            Vertex vertex = new Vertex(parts);
            this.adjacencyList.put(vertex.name(), vertex);
            for (String destination : vertex.destinations()) {
                adjacencyList.put(destination, new Vertex(destination));
            }
        }
    }

    public void traceEstimates() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            for(Map.Entry<String, Vertex> entry : adjacencyList.entrySet()){
                Vertex current = entry.getValue();
                for(String destination : current.destinations()){
                    Vertex v = adjacencyList.get(destination);
                    int pathWeight = current.hard(destination);
                    v.relax(current.name(), current.hardEstimate(), pathWeight);
                }
            }
        }
    }

    public double hardEstimate(String name){
        return adjacencyList.get(name).hardEstimate();
    }

    public void print(){
        System.out.println("\nAdjacency list: ");
        for (Vertex v : adjacencyList.values()) {
            System.out.println(v);
        }
    }
}
