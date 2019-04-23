package pacman.model;

import java.util.Collection;

public class GhostEvent extends MazeEvent {
    private final PacMan pacMan;
    private final Collection<Ghost> ghosts;

    public GhostEvent(Maze source, PacMan pacMan, Collection<Ghost> ghosts) {
        super(source);
        this.pacMan = pacMan;
        this.ghosts = ghosts;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public Collection<Ghost> getGhosts() {
        return ghosts;
    }
}
