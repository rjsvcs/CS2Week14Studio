package graphs;

import java.util.Objects;

/**
 * A weighted edge on a graph.
 *
 * @param <T> The type parameter for the values stored by the vertices.
 * @param <W> The type parameter for the edge weights.
 */
public class Edge<T,W> {
    /**
     * The origin vertex.
     */
    private final Vertex<T,W> from;

    /**
     * The destination vertex.
     */
    private final Vertex<T,W> to;

    /**
     * The weight of the edge.
     */
    private W weight;

    /**
     * Creates a new edge between two vertices with the specified weight.
     *
     * @param from The origin vertex.
     * @param to The destination vertex.
     * @param weight The weight of the edge.
     */
    public Edge(Vertex<T,W> from, Vertex<T,W> to, W weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Changes the weight of this edge.
     *
     * @param weight The new weight of the edge.
     */
    public void setWeight(W weight) {
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return The current weight of the edge.
     */
    public W getWeight() {
        return weight;
    }

    /**
     * Returns the destination vertex.
     *
     * @return The destination vertex.
     */
    public Vertex<T,W> getTo() {
        return to;
    }

    /**
     * Returns the origin vertex.
     *
     * @return The origin vertex.
     */
    public Vertex<T,W> getFrom() {
        return from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return Objects.equals(from, edge.from) &&
                Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Edge{" +
                from.getValue() + " -" +
                weight + "-> " +
                to.getValue() +
                "}";
    }
}
