package pacman.model;

import graphs.Graph;

import java.util.HashSet;
import java.util.Set;

public class Maze extends Graph<Location> {
    public enum CellType {
        WALL,
        PATHWAY,
        POWER_PELLET,
        GHOST,
        PACMAN
    }

    private Location pacMan;
    private final Set<Location> pellets;
    private final Set<Location> ghosts;
    private final Set<Location> pathways;

    private final int rows;
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
}
