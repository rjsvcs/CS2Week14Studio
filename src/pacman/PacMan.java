package pacman;

import graphs.Edge;
import graphs.Vertex;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pacman.model.Location;
import pacman.model.Maze;
import pacman.model.MazeMaker;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PacMan extends Application {
    private static final char WALL = 'w';
    private static final char BACKGROUND = 'b';
    private static final char PATHWAY = '=';
    private static final char INTERSECTION = 'o';


    private Maze maze;
    private MazeCell[][] mazeCells;
    private GridPane mazePane;

    @Override
    public void init() throws Exception {
        super.init();

        List<String> params = getParameters().getRaw();
        if(params.size() != 1) {
            System.err.println("Usage: java pacman.PacMan <filename>");
            System.exit(1);
        }

        loadMaze(params.get(0));
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            int rows = maze.getRows();
            int cols = maze.getCols();

            mazeCells = new MazeCell[rows][cols];
            mazePane = new GridPane();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    MazeCell cell = new MazeCell();
                    mazeCells[row][col] = cell;
                    mazePane.add(cell, col, row);
                }
            }

            for (Edge<Location, Integer> edge : maze.getEdges()) {
                Location origin = edge.getFromValue();
                Location destination = edge.getToValue();
                List<Location> pathway = origin.getPathway(destination);
                if (pathway != null) {
                    for (Location path : pathway) {
                        mazeCells[path.getRow()][path.getCol()].setPathway();
                    }
                } else {
                    throw new IllegalStateException(
                            "Maze contains a non-perpendicular pathway: " + edge);
                }
            }

            stage.setTitle("Pac-Man!");
            stage.setScene(new Scene(mazePane));
            stage.show();
        } catch(Throwable thrown) {
          thrown.printStackTrace();
            }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    private void loadMaze(String filename) throws IOException{
        maze = MazeMaker.readMaze(new FileInputStream(filename));
    }
}
