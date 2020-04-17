package pacman.model;

import graphs.Graph;

import java.util.Collection;
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
    private PacMan pacMan;

    /**
     * The locations of all of the power pellets in the maze.
     */
    private final Set<Location> pellets;

    /**
     * The locations of all of the ghosts in the maze.
     */
    private final Set<Ghost> ghosts;

    /**
     * The current locations of all ghosts.
     */
    private final Set<Location> ghostLocations;

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

    /**
     * The set of registered Pac-Man observers.
     */
    private final Set<Observer<PacManEvent>> pacManObservers;

    /**
     * The set of registered ghost observers.
     */
    private final Set<Observer<GhostEvent>> ghostObservers;

    public Maze(int rows, int cols, Location pacManLocation,
                Set<Location> pellets, Set<Location> ghostLocations) {
        this.rows = rows;
        this.cols = cols;
        this.pacMan = new PacMan(pacManLocation);
        this.pellets = pellets;
        this.ghosts = new HashSet<>();
        this.ghostLocations = ghostLocations;
        for(Location location : ghostLocations) {
            ghosts.add(new Ghost(location));
        }

        pathways = new HashSet<>();

        pacManObservers = new HashSet<>();
        ghostObservers = new HashSet<>();
    }

    /**
     * Adds an observer to be notified about important events happening
     * to Pac-Man.
     *
     * @param observer The observer to be notified of important events
     *                 happening to Pac-Man.
     */
    public void addPacManObserver(Observer<PacManEvent> observer) {
        pacManObservers.add(observer);
    }

    public void addGhostObserver(Observer<GhostEvent> observer) {
        ghostObservers.add(observer);
    }

    @Override
    public void connectUndirected(Location fromValue, Location toValue, int weight) {
        weight = ghostLocations.contains(fromValue) ||
                ghostLocations.contains(toValue) ?  1000 : 1;
        try {
            super.connectUndirected(fromValue, toValue, weight);
            pathways.addAll(fromValue.getPath(toValue));
        } catch(NullPointerException npe) {
            npe.printStackTrace();
            System.exit(1);
        }
    }

    public CellType getCellType(int row, int col) {
        return getCellType(new Location(row, col));
    }

    public CellType getCellType(Location location) {
        if(location.equals(pacMan.getLocation())) {
            return CellType.PACMAN;
        } else if(ghostLocations.contains(location)) {
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

    public void movePacMan(PathFinder finder) {
        if(!pacMan.isAlive()) {
            return;
        }

        Path path = findPath(pacMan.getLocation(), pellets, finder);

        if(path != null) {
            pacMan.setLocation(path.getNext());

            if(pellets.contains(pacMan.getLocation())) {
                pellets.remove(pacMan.getLocation());
                pacMan.setPoweredUp();

                for(Ghost ghost : ghosts) {
                    ghost.setVulnerable();
                }

            } else if(ghostLocations.contains(pacMan.getLocation())) {
                pacMan.setDead();
            }

            PacManEvent event = new PacManEvent(this, pacMan, path.getStart(),
                    path.getNext());
            for(Observer<PacManEvent> observer : pacManObservers) {
                observer.handle(event);
            }
        }
    }

    private Path findPath(Location start,
                                    Collection<Location> locations,
                                    PathFinder finder) {
        int shortest = Integer.MAX_VALUE;
        Path path = null;

        for(Location target : locations) {
            List<Location> candidate = finder.findPath(start, target);
            if(candidate.size() > 0 && candidate.size() < shortest) {
                path = new Path(candidate);
                shortest = candidate.size();
            }
        }
        return path;
    }
}
