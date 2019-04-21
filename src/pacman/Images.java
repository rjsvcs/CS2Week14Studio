package pacman;

import javafx.scene.image.Image;

/**
 * Defines constants for all of the game's images.
 */
public interface Images {
    /**
     * The path to all of the image files.
     */
    String IMAGE_PATH = "file:media/images/";

    /**
     * Empty image.
     */
    Image EMPTY = new Image(IMAGE_PATH + "empty.png");

    /**
     * Basic wall image.
     */
    Image WALL = new Image(IMAGE_PATH + "wall.png");

    /**
     * Basic pathway image.
     */
    Image PATHWAY = new Image(IMAGE_PATH + "pathway.png");

    /**
     * Normal pellets.
     */
    Image PELLETS = new Image(IMAGE_PATH + "pellets.png");

    /**
     * The image for a power pellet; when consumed, Pac-Man can eat enemy
     * ghosts.
     */
    Image POWER_PELLET = new Image(IMAGE_PATH + "power_pellet.png");


    /**
     * Pac-Man, mouth closed.
     */
    Image PAC_MAN_CLOSED = new Image(IMAGE_PATH + "pac_closed.png");

    /**
     * Pac-Man, mouth open, facing right.
     */
    Image PAC_MAN_RIGHT = new Image(IMAGE_PATH + "pac_right.png");

    /**
     * Pac-Man, mouth open, facing left.
     */
    Image PAC_MAN_LEFT = new Image(IMAGE_PATH + "pac_right.png");

    /**
     * An enemy ghost.
     */
    Image GHOST = new Image(IMAGE_PATH + "ghost.png");

    /**
     * A blue ghost (can be eaten by Pac-Man).
     */
    Image GHOST_BLUE = new Image(IMAGE_PATH + "ghost_blue.png");
}
