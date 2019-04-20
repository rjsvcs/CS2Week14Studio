package pacman.model;

import graphs.Graph;

import java.util.List;
import java.util.Set;

public class Maze extends Graph<Location> {
    private Location pacman;
    private final Set<Location> pellets;

    private final int rows;
    private final int cols;

    public Maze(int rows, int cols, Location pacman,
                Set<Location> pellets) {
        this.rows = rows;
        this.cols = cols;
        this.pacman = pacman;
        this.pellets = pellets;
    }

    public void addValue(Location location) {
        super.addValue(location);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    void movePacMan(Location destination) {

    }

    public List<Location> getPath() {
        return null;
    }
}
