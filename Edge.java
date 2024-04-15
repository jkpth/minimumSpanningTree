// Edge.java
public class Edge {
    int to;
    double weight;

    public Edge(int to, double weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("to %d, weight %.2f", to, weight);
    }
}
