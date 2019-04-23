package pacman;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import pacman.model.Maze.CellType;

/**
 * A single cell in the maze. May comprise several images stacked one atop the
 * other.
 */
public class MazeCell extends StackPane implements Images {

    /**
     * The {@link ImageView} that displays the background image.
     */
    private final ImageView background;

    /**
     * The {@link ImageView} that displays the cell decoration, e.g. pellets.
     */
    private final ImageView decoration;

    /**
     * The {@link ImageView} that displays Pac-Man (if he is present in the
     * cell).
     */
    private final ImageView pacMan;

    /**
     * The {@link ImageView} that displays a ghost (if it is present in the
     * cell).
     */
    private final ImageView ghost;

    /**
     * Creates a new maze cell of the specified type.
     *
     * @param type The {@link CellType} determines the images that are
     *             displayed on the cell.
     */
    MazeCell(CellType type) {
        background = new ImageView(WALL);
        decoration = new ImageView(EMPTY);
        pacMan = new ImageView(EMPTY);
        ghost = new ImageView(EMPTY);

        switch(type) {
            case GHOST:
                ghost.setImage(GHOST);
            case PATHWAY:
                background.setImage(PATHWAY);
                decoration.setImage(PELLETS);
                break;
            case PACMAN:
                background.setImage(PATHWAY);
                decoration.setImage(PELLETS);
                pacMan.setImage(PAC_MAN_RIGHT);
                break;
            case POWER_PELLET:
                background.setImage(PATHWAY);
                decoration.setImage(POWER_PELLET);
                break;
            case WALL:
            default:
                break;

        }

        getChildren().addAll(background, decoration, ghost, pacMan);
    }

    boolean isDecorated() {
        return decoration.getImage() != EMPTY;
    }

    void clearDecoration() {
        decoration.setImage(EMPTY);
    }

    void clearPacMan() {
        pacMan.setImage(EMPTY);
    }

    void setPacMan() {
        pacMan.setImage(PAC_MAN_RIGHT);
    }

    void clearGhost() {
        ghost.setImage(EMPTY);
    }

    void setGhost(boolean vulnerable) {
        ghost.setImage(vulnerable ? GHOST_BLUE : GHOST);
    }
}
