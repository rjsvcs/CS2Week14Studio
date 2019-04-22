package pacman.model;

import java.util.Collections;
import java.util.List;

public class Path {
    public enum Orientation {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        IDK
    }

    private final List<Location> path;
    private final Location start;
    private final Orientation orientation;

    public Path(List<Location> path) {
        this.path = Collections.unmodifiableList(path);
        start = path.get(0);
        Location next = path.size() > 0 ? path.get(1) : start;

        if(start.getRow() == next.getRow()) {
            orientation = start.getCol() < next.getCol() ?
                    Orientation.RIGHT : Orientation.LEFT;
        } else if(start.getCol() == next.getCol()) {
            orientation = start.getRow() < next.getRow() ?
                    Orientation.DOWN : Orientation.UP;
        } else {
            orientation = Orientation.IDK;
        }
    }

    public List<Location> getPath() {
        return path;
    }

    public Location getStart() {
        return start;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
