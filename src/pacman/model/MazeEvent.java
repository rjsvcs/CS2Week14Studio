package pacman.model;

public class MazeEvent {
    private final Maze source;

    public MazeEvent(Maze source) {
        this.source = source;
    }

    public Maze getSource() {
        return source;
    }
}
