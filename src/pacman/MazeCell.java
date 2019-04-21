package pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MazeCell extends StackPane {
    private static final String IMAGE_PATH = "file:media/images/";
    private static final Image WALL = new Image(IMAGE_PATH + "wall.png");
    private static final Image PATHWAY = new Image(IMAGE_PATH + "pathway.png");
    private static final Image PELLETS = new Image(IMAGE_PATH + "pellets.png");
    private static final Image EMPTY = new Image(IMAGE_PATH + "empty.png");
    private static final Image PAC_MAN = new Image(IMAGE_PATH + "pac_right.png");

    private final ImageView background;
    private final ImageView midground;
    private final ImageView foreground;

    MazeCell() {
        background = new ImageView(WALL);
        midground = new ImageView(EMPTY);
        foreground = new ImageView(EMPTY);

        getChildren().addAll(background, midground, foreground);
    }

    public void setWall() {
        background.setImage(WALL);
    }

    public void setPathway() {
        background.setImage(PATHWAY);
        midground.setImage(PELLETS);
    }

    public void setPacMan(boolean pacMan) {
        if(pacMan) {
            foreground.setImage(PAC_MAN);
        } else {
            foreground.setImage(EMPTY);
        }
    }
}
