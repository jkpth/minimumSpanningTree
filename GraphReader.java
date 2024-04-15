import java.io.*;
import java.util.*;

public class GraphReader {

    public static void main(String[] args) {
        // Path to the graph file
        String filename = "graph.txt";

        try {
            List<List<Edge>> adjacencyList = readGraph(filename);
            printGraph(adjacencyList);
        } catch (IOException e) {
            System.err.println("Error reading the graph file: " + e.getMessage());
        }
    }

    // Reads the graph from a file and returns an adjacency list representation
    public static List<List<Edge>> readGraph(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Read the first line to get the number of vertices
        int vertices = Integer.parseInt(br.readLine().trim());
        List<List<Edge>> adjacencyList = new ArrayList<>(vertices + 1);

        // Initialize adjacency list
        for (int i = 0; i <= vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        String line;
        while ((line = br.readLine()) != null) {
            // Check for empty or malformed lines
            if (line.trim().isEmpty()) continue;

            String[] parts = line.trim().split("\\s+");
            if (parts.length < 3) {
                continue; // Skip any lines that do not contain enough data
            }
            int i = Integer.parseInt(parts[0]);
            int j = Integer.parseInt(parts[1]);
            double w = Double.parseDouble(parts[2]);

            // Add edges to both vertices for undirected graph
            adjacencyList.get(i).add(new Edge(j, w));
            adjacencyList.get(j).add(new Edge(i, w));
        }

        br.close();
        return adjacencyList;
    }

    // Prints the graph's adjacency list
    public static void printGraph(List<List<Edge>> adjacencyList) {
        System.out.println("Graph in adjacency list format:");
        for (int i = 1; i < adjacencyList.size(); i++) {
            List<Edge> edges = adjacencyList.get(i);
            System.out.print("Vertex " + i + ":");
            for (Edge edge : edges) {
                System.out.print(" " + edge.toString() + ";");
            }
            System.out.println();
        }
    }
}
