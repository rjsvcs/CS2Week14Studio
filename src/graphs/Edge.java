package graphs;

public class Edge<T,W> {
    private final Vertex<T,W> from;
    private final Vertex<T,W> to;
    private W weight;

    public Edge(Vertex<T,W> from, Vertex<T,W> to, W weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public void setWeight(W weight) {
        this.weight = weight;
    }

    public W getWeight() {
        return weight;
    }

    public Vertex<T,W> getTo() {
        return (Vertex<T,W>)to;
    }

    public Vertex<T,W> getFrom() {
        return (Vertex<T,W>)from;
    }
}
