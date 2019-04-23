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
    private boolean poweredUp;
    private boolean alive;


    PacMan(Location location) {
        this.location = location;
        direction = Direction.RIGHT_OPEN;
        poweredUp = false;
        alive = true;

    }

    public Location getLocation() {
        return location;
    }

    void setLocation(Location location) {
        int deltaCol = this.location.getCol() - location.getCol();

        switch(direction) {
            case RIGHT_OPEN:
            case LEFT_OPEN:
                direction = deltaCol <= 0 ?
                        Direction.RIGHT_CLOSED : Direction.LEFT_CLOSED;
                break;
            case RIGHT_CLOSED:
            case LEFT_CLOSED:
                direction = deltaCol <= 0 ?
                    Direction.RIGHT_OPEN : Direction.LEFT_OPEN;
                break;
        }

        this.location = location;
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
