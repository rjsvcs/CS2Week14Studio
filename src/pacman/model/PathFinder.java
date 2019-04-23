package pacman.model;

import java.util.List;

/**
 * Functional interface for a class that can build paths through a graph.
 */
public interface PathFinder {
    /**
     * Given the specified start and end locations, finds a path.
     *
     * @param start The starting location for the path.
     * @param end The ending location for the path.
     * @return The list of locations along the path.
     */
    List<Location> findPath(Location start, Location end);
}
