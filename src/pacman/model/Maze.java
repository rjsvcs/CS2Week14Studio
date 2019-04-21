package pacman.model;

import graphs.Graph;

import java.util.List;
import java.util.Set;

public class Maze extends Graph<Location> {
    private Location pacman;
    private final Set<Location> pellets;
    private final Set<Location> ghosts;

    private final int rows;
    private final int cols;

    public Maze(int rows, int cols, Location pacman,
                Set<Location> pellets, Set<Location> ghosts) {
        this.rows = rows;
        this.cols = cols;
        this.pacman = pacman;
        this.pellets = pellets;
        this.ghosts = ghosts;
        System.out.println(pellets);
    }

    public Location getPacMan() {
        return pacman;
    }

    public boolean isGhostLocation(Location location) {
        return ghosts.contains(location);
    }

    public boolean isPowerPelletLocation(Location location) {
        if(location.getRow() == 5 && location.getCol() == 16) {
            System.out.println(pellets.contains(location));
        }

        return pellets.contains(location);
    }

    public boolean isPacManLocation(Location location) {
        return location.equals(pacman);
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
