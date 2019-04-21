package pacman;

import graphs.Edge;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pacman.model.Location;
import pacman.model.Maze;
import pacman.model.MazeMaker;

import java.io.*;
import java.util.List;

public class PacManGUI extends Application {
    private String mazeFilename;
    private Maze maze;
    private MazeCell[][] mazeCells;
    private GridPane mazePane;

    @Override
    public void init() throws Exception {
        super.init();

        List<String> params = getParameters().getRaw();
        if(params.size() != 1) {
            System.err.println("Usage: java pacman.PacManGUI <filename>");
            System.exit(1);
        }

        mazeFilename = params.get(0);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadMaze();

        stage.setTitle("Pac-Man!");
        stage.setScene(new Scene(mazePane));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    private void loadMaze() throws IOException{
        maze = MazeMaker.readMaze(new FileInputStream(mazeFilename));

        int rows = maze.getRows();
        int cols = maze.getCols();

        mazeCells = new MazeCell[rows][cols];
        mazePane = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Location location = new Location(row, col);
                MazeCell cell = new MazeCell();
                mazeCells[row][col] = cell;
                mazePane.add(cell, col, row);

                if(maze.isPacManLocation(location)) {
                    mazeCells[row][col].setPacMan();
                } else if(maze.isGhostLocation(location)) {
                    mazeCells[row][col].setGhost();
                } else if(maze.isPowerPelletLocation(location)) {
                    System.out.println("power pellet");
                    mazeCells[row][col].setPowerPellet();
                }
            }
        }

        for (Edge<Location> edge : maze.getEdges()) {
            Location origin = edge.getFromValue();
            Location destination = edge.getToValue();
            List<Location> pathway = origin.getPath(destination);

            for (Location path : pathway) {
                mazeCells[path.getRow()][path.getCol()].setPathway();
            }
        }
    }
}
