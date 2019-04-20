package graphs;

import java.util.*;

/**
 * Represents a single vertex in a graph.
 *
 * @param <T> The type of value held by the vertex.
 */
public class Vertex<T> {
    /**
     * The value.
     */
    private T value;

    /**
     * The set of neighboring vertices, to which this vertex is connected by
     * an edge.
     */
    private final Set<Edge<T>> neighbors;

    /**
     * Creates a new vertex with the specified value.
     *
     * @param value The value stored in this vertex.
     */
    public Vertex(T value) {
        this.value = value;
        neighbors = new HashSet<>();
    }

    /**
     * Returns the value inside this vertex.
     *
     * @return The value inside this vertex.
     */
    public T getValue() {
        return value;
    }

    /**
     * Creates a weighted edge between the vertex and a neighbor.
     *
     * @param neighbor The neighboring vertex to which a weighted edge should
     *                 be created.
     * @param weight The weight of the new edge.
     */
    public void addNeighbor(Vertex<T> neighbor, int weight) {
        Edge<T> edge = new Edge<>(this, neighbor, weight);
        neighbors.add(edge);
    }

    /**
     * Returns the edges between this vertex and its neighbors.
     *
     * @return A {@link Set} of edges between this vertex and its neighbors.
     */
    public Set<Edge<T>> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }
}
