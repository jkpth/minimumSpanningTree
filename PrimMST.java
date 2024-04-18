import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimMST {
    private List<List<Edge>> adjacencyList;
    private MinHeap heap;
    private int[] parent;
    private double[] keys;
    private int[] heapKeys;

    public PrimMST(List<List<Edge>> adjacencyList, int vertices) {
        this.adjacencyList = adjacencyList;
        this.heap = new MinHeap(vertices);
        
        this.keys = new double[vertices + 1];
        this.parent = new int[vertices + 1];
        this.heapKeys = new int[vertices + 1];

        Arrays.fill(this.keys, Double.MAX_VALUE);
        Arrays.fill(this.parent, -1);
        Arrays.fill(this.heapKeys, Integer.MAX_VALUE);
        
        this.keys[1] = 0;
        this.heapKeys[1] = 0;
        this.heap.heap_ini(this.heapKeys, vertices);
    }

    public void execute() {
        List<Edge> mst = new ArrayList<>();
    
        while (!heap.isEmpty()) {
            int u = heap.min_id();
            heap.delete_min();
    
            if (parent[u] != -1) {
                mst.add(new Edge(parent[u], u, keys[u]));
            }
    
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.to;
                double weight = edge.weight;
    
                if (heap.in_heap(v) && weight < keys[v]) {
                    keys[v] = weight;
                    int scaledWeight = (int)(weight * 100);
                    heap.decrease_key(v, scaledWeight);
                    parent[v] = u;
                    heapKeys[v] = scaledWeight;
                }
            }
        }
        printMST(mst);
    }
    
    public static void printMST(List<Edge> mstEdges) {
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mstEdges) {
            System.out.printf("Edge from vertices %d to %d with weight %.2f\n", edge.from, edge.to, edge.weight);
        }
    }

}
