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
    private PacMan pacMan;

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

    private final Set<Observer<PacManEvent>> moveObservers;


    public Maze(int rows, int cols, Location pacManLocation,
                Set<Location> pellets, Set<Location> ghosts) {
        this.rows = rows;
        this.cols = cols;
        this.pacMan = new PacMan(pacManLocation);
        this.pellets = pellets;
        this.ghosts = ghosts;

        pathways = new HashSet<>();

        moveObservers = new HashSet<>();
    }

    public void registerPacManObserver(Observer<PacManEvent> observer) {
        moveObservers.add(observer);
    }

    @Override
    public void connectUndirected(Location fromValue, Location toValue, int weight) {
        weight = ghosts.contains(fromValue) || ghosts.contains(toValue) ?
                1000 : 1;
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

    public void movePacMan(PathFinder finder) {
        int shortest = Integer.MAX_VALUE;
        Path path = null;

        for(Location pellet : pellets) {
            List<Location> candidate =
                    finder.findPath(pacMan.getLocation(), pellet);
            if(candidate.size() > 0 && candidate.size() < shortest) {
                path = new Path(candidate);
            }
        }

        if(path != null) {
            pacMan.setLocation(path.getNext());
            PacManEvent event = new PacManEvent(this, pacMan, path.getStart(),
                    path.getNext());
            for(Observer<PacManEvent> observer : moveObservers) {
                observer.handle(event);
            }
        }
    }

    private Path getPath(Location start, Location end) {
        //List<Location> path = breadthFirstPath(start, end);
        //List<Location> path = depthFirstPath(start, end);
        List<Location> path = dijkstrasShortestPath(start, end);

        return new Path(path);
    }
}
