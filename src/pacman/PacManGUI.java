package pacman;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pacman.model.Location;
import pacman.model.Maze;
import pacman.model.MazeMaker;
import pacman.model.PacManEvent;

import java.io.*;
import java.util.List;

public class PacManGUI extends Application implements Images {
    /**
     * Message displayed at the start of a game.
     */
    private static final String READY_PLAYER_1 = "READY PLAYER 1";

    /**
     * Message displayed when the game is over.
     */
    private static final String GAME_OVER = "GAME OVER";

    private String mazeFilename;
    private Maze maze;
    private MazeCell[][] mazeCells;
    private GridPane mazePane;
    private Label feedback;
    private FadeTransition fade;

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
        feedback = new Label();
        feedback.setBackground(
                new Background(new BackgroundFill(Color.TRANSPARENT,
                        CornerRadii.EMPTY, Insets.EMPTY)));
        feedback.setFont(Font.loadFont("file:media/fonts/8-bit-wonder.ttf", 48));
        feedback.setAlignment(Pos.CENTER);
        feedback.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        feedback.setTextFill(Color.WHITE);

        fade = new FadeTransition(Duration.millis(750), feedback);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setCycleCount(3);
    }

    @Override
    public void start(Stage stage) {
        try {
            loadMaze();

            BorderPane main = new BorderPane();

            StackPane console = new StackPane();

            console.getChildren().addAll(mazePane, feedback);
            main.setCenter(console);

            Button moveBFS = makeButton("Use BFS!");
            moveBFS.setOnAction(e -> {
                maze.movePacMan(maze::breadthFirstPath);
            });

            Button moveDijkstra = makeButton("Use Dijkstra!");
            moveDijkstra.setOnAction(
                    e -> maze.movePacMan(maze::dijkstrasShortestPath));

            Button reset = makeButton("Try Again");
            reset.setOnAction(e -> loadMaze());

            GridPane bottom = new GridPane();
            bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            bottom.add(moveBFS, 0, 0);
            bottom.add(moveDijkstra, 1, 0);
            bottom.add(reset, 2, 0);
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPercentWidth(33.333);
            bottom.getColumnConstraints().addAll(constraints, constraints,
                    constraints);

            main.setBottom(bottom);

            stage.setTitle("Pac-Man!");
            stage.setScene(new Scene(main));
            stage.show();

            SoundBoard.play(SoundBoard.START);
        } catch(Throwable thrown) {
            thrown.printStackTrace();
            System.exit(1);
        }
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

    private void loadMaze() {
        try {
            maze = MazeMaker.readMaze(new FileInputStream(mazeFilename));
            maze.addPacManObserver(this::pacManChanged);

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
            feedback.setText(READY_PLAYER_1);
            fade.play();
        } catch(IOException ioe) {

        }
    }

    private void pacManChanged(PacManEvent event) {
        Location origin = event.getOrigin();
        Location dest = event.getDestination();
        mazeCells[origin.getRow()][origin.getCol()].clearPacMan();
        mazeCells[dest.getRow()][dest.getCol()].setPacManView(event.getPacMan());

        List<Location> pathways = origin.getPath(dest);
        boolean pellets = false;
        for(Location pathway : pathways) {
            MazeCell cell = mazeCells[pathway.getRow()][pathway.getCol()];
            if(cell.isDecorated()) {
                cell.clearDecoration();
                pellets = true;
            }
        }

        if(!event.getPacMan().isAlive()) {
            SoundBoard.play(SoundBoard.END);
            feedback.setText(GAME_OVER);
            feedback.setOpacity(1);
        }
        if(pellets) {
            SoundBoard.play(SoundBoard.CHOMP);
        }
    }
}
