package pacman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * A single cell in the maze. May comprise several images stacked one atop the
 * other.
 */
public class MazeCell extends StackPane implements Images {

    /**
     * The background image.
     */
    private final ImageView background;

    /**
     * The middle ground image.
     */
    private final ImageView middleGround;

    /**
     * The foreground image.
     */
    private final ImageView foreground;

    /**
     * Creates a new maze cell with the specified images.
     *
     * @param backgroundImage The background image.
     * @param middleGroundImage The middle ground image.
     * @param foregroundImage The foreground image.
     */
    MazeCell(Image backgroundImage, Image middleGroundImage,
             Image foregroundImage) {
        background = new ImageView(backgroundImage);
        middleGround = new ImageView(middleGroundImage);
        foreground = new ImageView(foregroundImage);

        getChildren().addAll(background, middleGround, foreground);
    }

    /**
     * Changes the foreground image.
     *
     * @param image The image to which the foreground should change.
     */
    public void setForeground(Image image) {
        foreground.setImage(image);
    }
}
