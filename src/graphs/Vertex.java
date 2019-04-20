package graphs;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single vertex in a graph.
 *
 * @param <T> The type of value held by the vertex.
 */
public class Vertex<T,W> {
    /**
     * The value.
     */
    private T value;

    /**
     * The set of neighboring vertices, to which this vertex is connected by
     * an edge.
     */
    private final Set<Edge<T,W>> neighbors;

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

    public void addNeighbor(Vertex<T,W> neighbor, W weight) {
        Edge<T,W> edge = new Edge<>(this, neighbor, weight);
        neighbors.add(edge);
    }

    @SuppressWarnings("unchecked")
    public Set<Edge<T,W>> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }
}
