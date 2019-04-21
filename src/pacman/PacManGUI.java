package pacman;

import graphs.Edge;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pacman.model.Location;
import pacman.model.Maze;
import pacman.model.MazeMaker;

import java.io.*;
import java.util.List;

public class PacManGUI extends Application implements Images {
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

        mazePane = new GridPane();
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

        mazePane.getChildren().clear();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                MazeCell cell;
                switch(maze.getCellType(new Location(row, col))) {
                    case PACMAN:
                        cell = new MazeCell(PATHWAY, EMPTY, PAC_MAN_RIGHT);
                        break;
                    case POWER_PELLET:
                        cell = new MazeCell(PATHWAY, POWER_PELLET, EMPTY);
                        break;
                    case GHOST:
                        cell = new MazeCell(PATHWAY, PELLETS, GHOST);
                        break;
                    case PATHWAY:
                        cell = new MazeCell(PATHWAY, PELLETS, EMPTY);
                        break;
                    case WALL:
                    default:
                        cell = new MazeCell(WALL, EMPTY, EMPTY);
                        break;
                }
                mazeCells[row][col] = cell;
                mazePane.add(cell, col, row);
            }
        }
    }
}
