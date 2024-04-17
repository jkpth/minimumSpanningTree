// Edge.java
public class Edge {
    int from;
    int to;
    double weight;

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("to %d, weight %.2f", to, weight);
    }
}
