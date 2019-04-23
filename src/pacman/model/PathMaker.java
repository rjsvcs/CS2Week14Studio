package pacman.model;

import java.util.List;

public interface PathMaker {
    List<Location> makePath(Location start, Location end);
}
