package pacman.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility class for loading a {@link Maze} from a file.
 */
public class MazeMaker {
    /**
     * A separator that indicates the ends of the maze vertices and the
     * beginning of the maze edges in the file.
     */
    private static final String PATHWAYS = "##PATHWAYS##";

    /**
     * Indicates that Pac-Man is located at the specified vertex.
     */
    private static final String PACMAN = "PM";

    /**
     * Indicates that a Power Pellet is located at the specified vertex.
     */
    private static final String POWER_PELLET = "PP";

    /**
     * Indicates that a Ghost is located at the specified vertex.
     */
    private static final String GHOST = "G";

    /**
     * Reads a new {@link Maze} using the data contained in the specified
     * {@link InputStream}.
     *
     * @param in The {@link InputStream} that contains the maze data.
     * @return The new {@link Maze}
     * @throws IOException If there is an error reading the maze data.
     */
    public static Maze readMaze(InputStream in) throws IOException {
        try(InputStreamReader inr = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(inr)) {

            Map<Integer, Location> locations = new HashMap<>();
            Location pacman = null;
            Set<Location> pellets = new HashSet<>();
            Set<Location> ghosts = new HashSet<>();

            String line = reader.readLine();
            String[] dimensions = line.split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            while((line = reader.readLine()) != null) {
                if(line.equals(PATHWAYS)) {
                    break;
                }

                String[] tokens = line.split(" ");
                int id = Integer.parseInt(tokens[0]);
                int row = Integer.parseInt(tokens[1]);
                int col = Integer.parseInt(tokens[2]);
                Location location = new Location(row, col);
                locations.put(id, location);

                if(tokens.length == 4) {
                    switch(tokens[3]) {
                        case PACMAN:
                            pacman = location;
                            break;
                        case POWER_PELLET:
                            pellets.add(location);
                            break;
                        case GHOST:
                            ghosts.add(location);
                            break;
                        default:
                            System.err.println("Unrecognized token: " +
                                    tokens[3]);
                    }
                }
            }

            Maze maze = new Maze(rows, cols, pacman, pellets, ghosts);
            for(Location location : locations.values()) {
                maze.addValue(location);
            }

            while((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int origin = Integer.parseInt(tokens[0]);
                int destination = Integer.parseInt(tokens[1]);
                int weight = 1;
                if(tokens.length == 3 && tokens[2].equals(GHOST)) {
                    weight = 1000;
                }

                if(locations.get(origin) == null) {
                    System.out.println(origin + " is null");
                }

                maze.connectUndirected(locations.get(origin),
                        locations.get(destination),
                        weight);
            }

            return maze;
        }
    }
}
