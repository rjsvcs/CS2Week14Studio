package graphs;

/**
 * A path tuple that holds the current, known shortest distance to a vertex
 * and its predecessor.
 */
public class PathTuple<T> implements Comparable<PathTuple> {
    /**
     * The shortest known distance to the vertex associated with this path
     * tuple.
     */
    private int distanceFromStart;

    /**
     * The vertex.
     */
    private final Vertex<T> vertex;

    /**
     * The predecessor vertex.
     */
    private Vertex<T> predecessor;


    /**
     * Creates a new path tuple for the specified vertex. The default distance
     * is set to {@link Integer#MAX_VALUE} and the default predecessor is set
     * to null.
     *
     * @param vertex The vertex associated with this path tuple.
     */
    PathTuple(Vertex<T> vertex) {
        this(Integer.MAX_VALUE, vertex, null);
    }

    /**
     * Creates a new path tuple with the specified values.
     *
     * @param distanceFromStart The current, known distance from the start
     *                          vertex.
     * @param vertex The vertex associated with the new path tuple.
     * @param predecessor The predecessor on the path from the start vertex.
     */
    PathTuple(int distanceFromStart, Vertex<T> vertex, Vertex<T> predecessor) {
        this.distanceFromStart = distanceFromStart;
        this.vertex = vertex;
        this.predecessor = predecessor;
    }


    /**
     * Updates the distance from start and predecessor vertex iff the
     * distance through V is less than the current known distance.
     *
     * @param v The vertex V.
     * @param distanceThroughV The distance from the starting vertex to the
     *                         path tuple's vertex through the vertex V.
     */
    void update(Vertex<T> v, int distanceThroughV) {
        if(distanceThroughV < this.distanceFromStart) {
            this.distanceFromStart = distanceThroughV;
            this.predecessor = v;
        }
    }

    /**
     * Returns the current known shortest distance from the starting vertex.
     *
     * @return The current known shortest distance from the starting vertex.
     */
    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    /**
     * This path tuple's vertex.
     *
     * @return The vertex to which this path tuple belongs.
     */
    public Vertex<T> getVertex() {
        return vertex;
    }

    /**
     * The predecessor vertex on the current known shortest path.
     *
     * @return The predecessor vertex on the current known shortest path.
     */
    public Vertex<T> getPredecessor() {
        return predecessor;
    }

    /**
     * Compares this path tuple to another path tuple based on the relative
     * distance from start.
     *
     * @param tuple The path tuple to which this path tuple is being compared.
     *
     * @return A negative value if the vertex associated with this path tuple
     * is closer to the start than the vertex associated with the other path
     * tuple, 0 if they are the same distance, and a positive number if the
     * other vertex is closer.
     */
    @Override
    public int compareTo(PathTuple tuple) {
        return distanceFromStart - tuple.distanceFromStart;
    }
}
