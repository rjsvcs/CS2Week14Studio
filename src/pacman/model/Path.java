package pacman.model;

import java.util.Collections;
import java.util.List;

/**
 * A path from one point to another through the maze.
 */
public class Path {
    /**
     * The possible of the first step in the path.
     */
    public enum Orientation {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        IDK
    }

    /**
     * All of the locations in the path.
     */
    private final List<Location> path;

    /**
     * The first location in the path.
     */
    private final Location start;

    /**
     * The next step in the path (after the first).
     */
    private final Location next;

    /**
     * The orientation of the path.
     */
    private final Orientation orientation;

    /**
     * Creates a new path with the specified locations.
     *
     * @param path The list of locations along the path.
     */
    Path(List<Location> path) {
        this.path = Collections.unmodifiableList(path);
        start = path.get(0);
        next = path.size() > 1 ? path.get(1) : start;

        // determine the orientation based on the start and next
        if(start.getRow() == next.getRow()) {
            orientation = start.getCol() < next.getCol() ?
                    Orientation.RIGHT : Orientation.LEFT;
        } else if(start.getCol() == next.getCol()) {
            orientation = start.getRow() < next.getRow() ?
                    Orientation.DOWN : Orientation.UP;
        } else {
            orientation = Orientation.IDK;
        }
    }

    /**
     * Returns the starting location for the path.
     *
     * @return The starting location for the path.
     */
    public Location getStart() {
        return start;
    }

    /**
     * Returns the next location (after the start) in the path.
     *
     * @return The next location (after the start) in the path.
     */
    public Location getNext() {
        return next;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns the length of the path. This is the number of values, not the
     * number of edges.
     *
     * @return The number of values in the path.
     */
    public int length() {
        return path.size();
    }
}
