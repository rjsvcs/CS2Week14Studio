package pacman;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MazeCell extends StackPane implements Images {

    private final ImageView background;
    private final ImageView midddleGround;
    private final ImageView foreground;

    MazeCell() {
        background = new ImageView(WALL);
        midddleGround = new ImageView(EMPTY);
        foreground = new ImageView(EMPTY);

        getChildren().addAll(background, midddleGround, foreground);
    }

    public void setGhost() {
        foreground.setImage(GHOST);
    }

    public void setPacMan() {
        midddleGround.setImage(EMPTY);
        foreground.setImage(PAC_MAN_RIGHT);
    }

    public void setPowerPellet() {
        midddleGround.setImage(POWER_PELLET);
    }

    public void setPathway() {
        background.setImage(PATHWAY);
        midddleGround.setImage(PELLETS);
    }
}
