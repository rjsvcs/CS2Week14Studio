package graphs;

import java.util.Collection;
import java.util.HashSet;

/**
 * A priority queue.
 *
 * @param <E> The type of elements stored in the queue. Must implement
 *           {@link Comparable}.
 */
public class PriorityQueue<E extends Comparable<? super E>> {
    /**
     * The {@link Collection} used to store the elements in the queue.
     */
    private final Collection<E> elements;

    /**
     * Creates a new, empty priority queue.
     */
    public PriorityQueue() {
        this.elements = new HashSet<>();
    }

    /**
     * Adds an element to the priority queue.
     *
     * @param element The element to add to the priority queue.
     */
    public void enqueue(E element) {
        elements.add(element);
    }

    /**
     * Returns true if the priority queue is currently empty.
     *
     * @return True if the priority queue is currently empty.
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Removes and returns the highest priority item in the queue. Determines
     * priority with a simple linear search.
     *
     * @return The highest priority item in the queue. The item is also
     * removed from the queue.
     */
    public E dequeue() {
        if(isEmpty()) {
            return null;
        } else {
            E highestPriority = null;
            for(E candidate : elements) {
                if(highestPriority == null ||
                        candidate.compareTo(highestPriority) < 0) {
                    highestPriority = candidate;
                }
            }
            elements.remove(highestPriority);
            return highestPriority;
        }
    }
}
