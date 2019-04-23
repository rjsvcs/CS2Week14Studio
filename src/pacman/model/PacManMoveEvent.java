package pacman.model;

public class PacManMoveEvent extends MazeEvent {
    private final Location origin;
    private final Location destination;

    public PacManMoveEvent(Maze source, Location origin,
                           Location destination) {
        super(source);
        this.origin = origin;
        this.destination = destination;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }
}
