package pacman.model;

import graphs.Graph;

import java.util.List;
import java.util.Set;

public class Maze extends Graph<Location, Integer> {
    private Location pacman;
    private final Set<Location> pellets;

    private int rows;
    private int cols;

    public Maze(Location pacman, Set<Location> pellets) {
        this.pacman = pacman;
        this.pellets = pellets;

        rows = -1;
        cols = -1;
    }

    public void addValue(Location location) {
        super.addValue(location);
        cols = location.getCol() <= cols ? cols : location.getCol() + 1;
        rows = location.getRow() <= rows ? rows : location.getRow() + 1;
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
