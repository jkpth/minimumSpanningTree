import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimMST {
    private List<List<Edge>> adjacencyList;
    private MinHeap heap;
    private int[] parent;
    private int[] keys;

    public PrimMST(List<List<Edge>> adjacencyList, int vertices) {
        this.adjacencyList = adjacencyList;
        this.heap = new MinHeap(vertices);
        
        this.keys = new int[vertices + 1];
        this.parent = new int[vertices + 1];

        Arrays.fill(this.keys, Integer.MAX_VALUE);
        Arrays.fill(this.parent, -1);
        
        this.keys[1] = 0;
        this.heap.heap_ini(this.keys, vertices);
    }

    public List<Edge> execute() {
        List<Edge> mst = new ArrayList<>();
    
        while (!heap.isEmpty()) {
            int u = heap.min_id();
            heap.delete_min();
    
            if (parent[u] != -1) {
                mst.add(new Edge(parent[u], u, keys[u]));
            }
    
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.to;
                int weight = (int)(edge.weight* 100);
    
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
