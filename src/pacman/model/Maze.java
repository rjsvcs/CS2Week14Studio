package pacman.model;

import graphs.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Pac-Man maze.
 */
public class Maze extends Graph<Location> {
    /**
     * Defines the different types that a cell may be.
     */
    public enum CellType {
        WALL,
        PATHWAY,
        POWER_PELLET,
        GHOST,
        PACMAN
    }

    /**
     * The location of Pac-Man in the maze.
     */
    private Location pacMan;

    /**
     * The locations of all of the power pellets in the maze.
     */
    private final Set<Location> pellets;

    /**
     * The locations of all of the ghosts in the maze.
     */
    private final Set<Location> ghosts;

    /**
     * The locations of all of the pathways in the maze.
     */
    private final Set<Location> pathways;

    /**
     * The number of rows in the maze.
     */
    private final int rows;

    /**
     * The number of columns in the maze.
     */
    private final int cols;

    public Maze(int rows, int cols, Location pacman,
                Set<Location> pellets, Set<Location> ghosts) {
        this.rows = rows;
        this.cols = cols;
        this.pacMan = pacman;
        this.pellets = pellets;
        this.ghosts = ghosts;

        pathways = new HashSet<>();
    }

    @Override
    public void connectUndirected(Location fromValue, Location toValue, int weight) {
        super.connectUndirected(fromValue, toValue, weight);
        pathways.addAll(fromValue.getPath(toValue));
    }

    public CellType getCellType(Location location) {
        if(location.equals(pacMan)) {
            return CellType.PACMAN;
        } else if(ghosts.contains(location)) {
            return CellType.GHOST;
        } else if(pellets.contains(location)) {
            return CellType.POWER_PELLET;
        } else if(pathways.contains(location)) {
            return CellType.PATHWAY;
        } else {
            return CellType.WALL;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void movePacMan() {

    }

    private Path getPath(Location start, Location end) {
        List<Location> path = breadthFirstPath(start, end);

        return new Path(path);
    }
}
