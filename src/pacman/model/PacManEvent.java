package pacman.model;

public class PacManEvent extends MazeEvent {
    private final Location origin;
    private final Location destination;
    private final PacMan pacMan;

    public PacManEvent(Maze source, PacMan pacMan, Location origin,
                       Location destination) {
        super(source);
        this.pacMan = pacMan;
        this.origin = origin;
        this.destination = destination;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public PacMan getPacMan() {
        return pacMan;
    }
}
