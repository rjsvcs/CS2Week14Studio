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

    public List<Location> getPathway(Location destination) {
        if(destination.getRow() == getRow()) {
            return getCol() < destination.getCol()?
                    getHorizontalPathway(this, destination) :
                    getHorizontalPathway(destination, this);
        } else if(destination.getCol() == getCol()) {
            return getRow() < destination.getRow() ?
                    getVerticalPathway(this, destination) :
                    getVerticalPathway(destination, this);
        } else {
            return null;
        }
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

    private List<Location> getHorizontalPathway(Location start, Location end) {
        List<Location> pathway = new ArrayList<>();
        for(int col=start.getCol(); col<=end.getCol(); col++) {
            pathway.add(new Location(start.getRow(), col));
        }
        return pathway;
    }

    private List<Location> getVerticalPathway(Location start, Location end) {
        List<Location> pathway = new ArrayList<>();
        for(int row=start.getRow(); row<=end.getRow(); row++) {
            pathway.add(new Location(row, start.getCol()));
        }
        return pathway;
    }
}
