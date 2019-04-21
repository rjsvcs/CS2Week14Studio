package pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MazeCell extends StackPane implements Images {

    private final ImageView background;
    private final ImageView middleGround;
    private final ImageView foreground;

    MazeCell(Image backgroundImage, Image middleGroundImage,
             Image foregroundImage) {
        background = new ImageView(backgroundImage);
        middleGround = new ImageView(middleGroundImage);
        foreground = new ImageView(foregroundImage);

        getChildren().addAll(background, middleGround, foreground);
    }
}
