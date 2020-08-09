package Graphs;

import java.util.*;

public class AdjacencyList {

    private final int vertices;
    private static AdjacencyList INSTANCE;
    private List<Integer>[] adjList;

    private AdjacencyList(int N) {
        vertices = N;
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        adjList = new LinkedList[vertices];
        for(int i = 0 ; i<vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public static AdjacencyList of(int N) {
        if(INSTANCE == null) {
            INSTANCE = new AdjacencyList(N);
        }
        return INSTANCE;
    }

    public void addEdge(int vertexOne,int vertexTwo) {
        List<Integer> vertex = adjList[vertexOne];
        if(!vertex.contains(vertexTwo)) {
            vertex.add(vertexTwo);
            adjList[vertexTwo].add(vertexOne);
        } else {
            logS("\nEdge exists!");
        }
    }

    public void BFS(int entryVertex) {
        boolean[] visited = new boolean[vertices];
        for(int i = 0; i<vertices;i++) visited[i] = false;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(entryVertex);
        visited[entryVertex] = true;
        logS("\nVisiting: " + entryVertex);

        while(!queue.isEmpty()) {
            int temp = queue.remove();
            for (int elem : adjList[temp]) {
                if (!visited[elem]) {
                    logS("\nVisiting: " + elem);
                    visited[elem] = true;
                    queue.add(elem);
                }
            }
        }
    }

    private void log(String log) {
        System.out.println(log);
    }

    private void logS(String log) {
        System.out.print(log);
    }
}
