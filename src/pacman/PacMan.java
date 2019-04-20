package pacman;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class PacMan extends Application {
    private static final char WALL = 'w';
    private static final char BACKGROUND = 'b';
    private static final char PATHWAY = '=';
    private static final char INTERSECTION = 'o';


    private GridPane maze;

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

    }


    private void loadMaze(String filename) {
        try(InputStream fin = new FileInputStream(filename);
            InputStreamReader inputReader = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(inputReader)) {



            int row = 0;
            String line;
            while((line = reader.readLine()) != null) {
                int col = 0;
                for(char c : line.toCharArray()) {
                    switch (c) {

                    }
                }
            }

        } catch(IOException ioe) {
            System.err.println("Error loading maze: " + ioe.getMessage());
            System.exit(1);
        }
    }
}
