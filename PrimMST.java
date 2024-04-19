import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimMST {
    // Adjacency list to represent the graph
    private List<List<Edge>> adjacencyList;
    // Min-heap to pick the edge with the minimum weight
    private MinHeap heap;
    // Array to store the parent of each vertex in the MST
    private int[] parent;
    // Array to store the minimum weight to reach each vertex
    private int[] keys;

    // Constructor initializes the graph and supporting data structures
    public PrimMST(List<List<Edge>> adjacencyList, int vertices) {
        this.adjacencyList = adjacencyList;
        this.heap = new MinHeap(vertices);

        this.keys = new int[vertices + 1];
        this.parent = new int[vertices + 1];

        // Initialize keys values to infinity and parent to -1
        Arrays.fill(this.keys, Integer.MAX_VALUE);
        Arrays.fill(this.parent, -1);

        // Start from vertex 1 (assuming 1-based index)
        this.keys[1] = 0;
        // Initialize heap with all vertices, vertex 1 has the minimum key
        this.heap.heap_ini(this.keys, vertices);
    }

    // Method to execute Prim's algorithm and return the MST
    public List<Edge> execute() {
        List<Edge> mst = new ArrayList<>();

        // Loop until the heap is empty
        while (!heap.isEmpty()) {
            // Extract the vertex with the minimum key value
            int u = heap.min_id();
            heap.delete_min();

            // If u is connected to the MST, add the corresponding edge
            if (parent[u] != -1) {
                mst.add(new Edge(parent[u], u, keys[u]));
            }

            // Iterate through all adjacent vertices
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.to;
                int weight = (int)(edge.weight* 100);

                // If v is still in heap and found a cheaper connection to it
                if (heap.in_heap(v) && weight < keys[v]) {
                    keys[v] = weight;
                    heap.decrease_key(v, keys[v]);
                    parent[v] = u;
                }
            }
        }
        return mst;
    }
    
    public void printMST(List<Edge> mstEdges) {
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mstEdges) {
            System.out.printf("Edge from vertices %d to %d with weight %.2f\n", edge.from, edge.to, edge.weight/100);
        }
    }

}
