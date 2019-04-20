package pacman.model;

public class PacManMovedEvent extends MazeEvent {
    private final Location origin;
    private final Location destination;

    public PacManMovedEvent(Maze source, Location origin,
                            Location destination) {
        super(source);
        this.origin = origin;
        this.destination = destination;
    }

    public Location getOrigin() {
        return null;
    }
}
