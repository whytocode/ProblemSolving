package Graphs;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class AdjecencyMatrix {

    final int vertices;
    private static AdjecencyMatrix INSTANCE;
    private int[][] adjMatrix;

    private AdjecencyMatrix(int N) {
        vertices = N;
        adjMatrix = new int[vertices][vertices];
        init();
    }

    private void init() {
        for(int i = 0; i<vertices;i++) {
            for(int j = 0; j<vertices; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public static AdjecencyMatrix of(int N) {
        if(INSTANCE == null) {
            INSTANCE = new AdjecencyMatrix(N);
        }
        return INSTANCE;
    }

    public void addEdge(int vertexOne,int vertexTwo) {
        if(adjMatrix[vertexOne][vertexTwo] == 0) {
            adjMatrix[vertexOne][vertexTwo] = 1;
            adjMatrix[vertexTwo][vertexOne] = 1;
            log("Edge Added");
        } else {
            log("Edge Exists!");
        }
    }

    public void printMatrix() {
        log("Graph: ");
        for(int i = 0; i<vertices;i++) {
            logS("[ ");
            for(int j=0 ;j<vertices;j++) {
                logS(" "+ adjMatrix[i][j]+" ");
            }
            logS("]\n");
        }

    }
    private void log(String log) {
        System.out.println(log);
    }

    private void logS(String log) {
        System.out.print(log);
    }

    public void BFS(int startVertex) {
        boolean[] visited = new boolean[vertices];
        log("Visited: "+startVertex);
        for(int i = 0 ; i< vertices ; i++) visited[i] = false;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startVertex);
        visited[startVertex] = true;

        while(!queue.isEmpty()) {
            startVertex = queue.remove();
            for(int i = 0 ; i<vertices ; i++) {
                if( adjMatrix[startVertex][i] == 1 && !visited[i]) {
                    // visit the node.
                    log("Visited: "+i);
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public void DFSWithStack(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        for(int i=0 ;i<vertices;i++) visited[i] = false;

        stack.push(startVertex);

        while(!stack.isEmpty()) {
            int temp = stack.pop();
            if(!visited[temp]) {
                logS("\nVisited: "+temp);
                visited[temp] = true;
            }
            for(int i = 0; i<vertices;i++) {
                // Get all the peers and see if something is not visited,then add it to stack
                if(adjMatrix[temp][i] == 1 && !visited[i]) {
                    stack.push(i);
                }
            }
        }
    }
}
