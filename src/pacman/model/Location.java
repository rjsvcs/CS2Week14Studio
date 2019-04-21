package pacman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Location {
    private final int row;
    private final int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row &&
                col == location.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    public List<Location> getPath(Location other) {
        if(getRow() == other.getRow()) {
            return getHorizontalPath(this, other);
        } else if( getCol() == other.getCol()) {
            return getVerticalPath(this, other);
        } else {
            throw new IllegalStateException(
                    "Maze contains non-perpendicular paths: " +
                    this + ", " + other);
        }
    }

    private static List<Location> getHorizontalPath(Location start,
                                                   Location end) {
        List<Location> path = new ArrayList<>();
        Location origin = start.getCol() < end.getCol() ?
                start : end;
        Location destination = start.getCol() < end.getCol() ?
                end : start;
        for(int col=origin.getCol(); col<=destination.getCol(); col++) {
            path.add(new Location(origin.getRow(), col));
        }
        return path;
    }

    private static List<Location> getVerticalPath(Location start,
                                                    Location end) {
        List<Location> path = new ArrayList<>();
        Location origin = start.getRow() < end.getRow() ?
                start : end;
        Location destination = start.getRow() < end.getRow() ?
                end : start;
        for(int row=origin.getRow(); row<=destination.getRow(); row++) {
            path.add(new Location(row, origin.getCol()));
        }
        return path;
    }
}
