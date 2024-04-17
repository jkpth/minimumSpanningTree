public class MinHeap {

    private Node[] heap;      // This array holds the heap data.
    private int size;         // Current size of the heap.
    private int[] position;   // This will hold the position (index in heap array) of the vertices.
    private int maxSize;      // Maximum size of the heap
    private class Node {
        int vertex; // Vertex id
        int key;    // Key value representing the minimum edge cost to this vertex

        public Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }
    }

    public MinHeap(int maxsize) {
        this.maxSize = maxsize;
        this.size = 0;
        heap = new Node[maxsize + 1];
        position = new int[maxsize + 1]; // Position map to track where each vertex is in the heap
        heap[0] = new Node(-1, Integer.MIN_VALUE);
    }

    // Function to initialize the heap
    public void heap_ini(int[] keys, int n) {
        if (n > maxSize) {
            throw new IllegalArgumentException("Number of elements exceeds maximum heap size.");
        }

        for (int i = 1; i <= n; i++) {
            heap[i] = new Node(i, keys[i]);
            position[i] = i;
        }
        this.size = n;

        for (int i = size / 2; i >= 1; i--) {
            minHeapify(i);
        }
    }

    // Function to maintain the min-heap property
    private void minHeapify(int pos) {
        int smallest = pos;
        int left = 2 * pos;
        int right = 2 * pos + 1;

        if (left <= size && heap[left].key < heap[smallest].key) {
            smallest = left;
        }
        if (right <= size && heap[right].key < heap[smallest].key) {
            smallest = right;
        }

        if (smallest != pos) {
            swap(pos, smallest);
            minHeapify(smallest);
        }
    }

    // Swap function to swap two nodes in the heap
    private void swap(int fpos, int spos) {
        Node tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;

        position[heap[fpos].vertex] = fpos;
        position[heap[spos].vertex] = spos;
    }
    
    public boolean in_heap(int id) {
        return position[id] > 0 && position[id] <= size;
    }

    public int min_key() {
        return heap[1].key;
    }

    public int min_id() {
        return heap[1].vertex;
    }

    public int key(int id) {
        if (in_heap(id)) {
            return heap[position[id]].key;
        }
        return -1;
    }
    
    public void delete_min() {
        if (size == 0) return;
        swap(1, size);
        size--;
        minHeapify(1);
    }

    // decrease key and maintain minheap
    public void decrease_key(int id, int new_key) {
        if (!in_heap(id)) 
            return;

        int i = position[id];
        if (heap[i].key > new_key) {
            heap[i].key = new_key;
            while (i > 1 && heap[i].key < heap[i / 2].key) {
                swap(i, i / 2);
                i = i / 2;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
}

