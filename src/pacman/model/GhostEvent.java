package pacman.model;

import java.util.Collection;

public class GhostEvent extends MazeEvent {
    private final Ghost ghost;


    public GhostEvent(Maze source, Ghost ghost, PacMan pacMan) {
        super(source);
        this.ghost = ghost;
    }


}
