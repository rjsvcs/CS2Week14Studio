package pacman.model;

/**
 * Represents Pac-Man in the maze.
 */
public class PacMan {
    /**
     * Pac-Man's facing direction.
     */
    public enum Direction {
        RIGHT_OPEN,
        LEFT_OPEN,
        RIGHT_CLOSED,
        LEFT_CLOSED
    }

    private Location location;
    private Direction direction;
    private boolean closed;
    private boolean poweredUp;
    private boolean alive;


    PacMan(Location location) {
        this.location = location;
        direction = Direction.RIGHT_OPEN;
        closed = false;
        poweredUp = false;
        alive = true;

    }

    public Location getLocation() {
        return location;
    }

    void setLocation(Location newLocation) {
        closed = !closed;
        location = newLocation;
    }

    public boolean isAlive() {
        return alive;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isPoweredUp() {
        return poweredUp;
    }

    void setPoweredUp() {
        poweredUp = true;
    }

    void setDead() {
        alive = false;
    }
}
