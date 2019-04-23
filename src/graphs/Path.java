package graphs;

import java.util.LinkedList;

/**
 * A class that represents a path through the graph.
 *
 * @param <E> The parameter type for the values along the path.
 */
public class Path<E> extends LinkedList<E> {
    /**
     * The total distance of the path.
     */
    private final int distance;

    /**
     * Creates a new path with the specified total distance.
     *
     * @param distance The total distance of this path; the sum of the weight
     *                 of the edges along the path.
     */
    Path(int distance) {
        this.distance = distance;
    }

    /**
     * Returns the total distance for the path. This is the sum of the weight
     * of the edges along the path.
     *
     * @return The total distance for the path.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns a string representation of the path including all of the
     * values in the path as well as
     *
     * @return
     */
    @Override
    public String toString() {
        return "Path{" + super.toString() + ", distance=" +
                    (distance == Integer.MAX_VALUE ?
                    "infinity" : Integer.toString(distance)) +
                "}";
    }
}
