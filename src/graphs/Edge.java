package graphs;

import java.util.Objects;

/**
 * A weighted edge on a graph.
 *
 * @param <T> The type parameter for the values stored by the vertices.
 */
public class Edge<T> {
    /**
     * The origin vertex.
     */
    private final Vertex<T> from;

    /**
     * The destination vertex.
     */
    private final Vertex<T> to;

    /**
     * The weight of the edge.
     */
    private int weight;

    /**
     * Creates a new edge between two vertices with the specified weight.
     *
     * @param from The origin vertex.
     * @param to The destination vertex.
     * @param weight The weight of the edge.
     */
    public Edge(Vertex<T> from, Vertex<T> to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Changes the weight of this edge.
     *
     * @param weight The new weight of the edge.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return The current weight of the edge.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns the origin vertex.
     *
     * @return The origin vertex.
     */
    public Vertex<T> getFrom() {
        return from;
    }

    /**
     * Convenience method to return the value from the origin vertex. Mostly
     * to silence Demeter's voice in my head.
     *
     * @return The value contained by the origin vertex.
     */
    public T getFromValue() {
        return from.getValue();
    }

    /**
     * Returns the destination vertex.
     *
     * @return The destination vertex.
     */
    public Vertex<T> getTo() {
        return to;
    }

    /**
     * Convenience method to return the value from the destination vertex.
     * Mostly to silence Demeter's voice in my head.
     *
     * @return The value contained by the destination vertex.
     */
    public T getToValue() {
        return to.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
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
