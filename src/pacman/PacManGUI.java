package pacman;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pacman.model.Location;
import pacman.model.Maze;
import pacman.model.Maze.MovementType;
import pacman.model.MazeMaker;
import pacman.model.PacManMoveEvent;

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

        BorderPane main = new BorderPane();
        main.setCenter(mazePane);

        Button moveBFS = makeButton("Use BFS!");
        moveBFS.setOnAction(e -> maze.movePacMan(MovementType.BFS));

        Button moveDijkstra = makeButton("Use Dijkstra!");
        moveDijkstra.setOnAction(e -> maze.movePacMan(MovementType.DIJKSTRA));

        GridPane bottom = new GridPane();
        bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        bottom.add(moveBFS, 0, 0);
        bottom.add(moveDijkstra, 1, 0);
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(50);
        bottom.getColumnConstraints().addAll(constraints, constraints);

        main.setBottom(bottom);

        stage.setTitle("Pac-Man!");
        stage.setScene(new Scene(main));
        stage.show();
    }

    private Button makeButton(String label) {
        Button button = new Button(label);
        button.setFont(new Font("Courier New", 14));
        button.setAlignment(Pos.CENTER);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setPadding(new Insets(10));
        return button;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    private void loadMaze() throws IOException{
        maze = MazeMaker.readMaze(new FileInputStream(mazeFilename));
        maze.registerPacManMoveObserver(this::pacManMoved);

        int rows = maze.getRows();
        int cols = maze.getCols();

        mazeCells = new MazeCell[rows][cols];

        mazePane.getChildren().clear();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                MazeCell cell = new MazeCell(maze.getCellType(row, col));
                mazeCells[row][col] = cell;
                mazePane.add(cell, col, row);
            }
        }
    }

    private void pacManMoved(PacManMoveEvent event) {
        Location origin = event.getOrigin();
        Location dest = event.getDestination();
        mazeCells[origin.getRow()][origin.getCol()].clearPacMan();
        mazeCells[dest.getRow()][dest.getCol()].setPacMan();

        List<Location> pathways = origin.getPath(dest);
        for(Location pathway : pathways) {
            mazeCells[pathway.getRow()][pathway.getCol()].clearDecoration();
        }
    }
}
