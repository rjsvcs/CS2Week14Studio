package pacman.model;

/**
 * The interface that should be implemented by an observer that will be
 * notified of specific maze events.
 *
 * @param <E> The type of maze event that the observer is interested in.
 */
public interface Observer<E extends MazeEvent> {
    /**
     * Called to handle the maze event when it occurs.
     *
     * @param event The maze event that has been raised.
     */
    void handle(E event);
}
