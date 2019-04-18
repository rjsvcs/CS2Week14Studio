package pacman;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class PacMan extends Application {
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

            String[] dim = reader.readLine().trim().split(" ");
            int rows = Integer.parseInt(dim[0]);
            int cols = Integer.parseInt(dim[1]);


        } catch(IOException ioe) {
            System.err.println("Error loading maze: " + ioe.getMessage());
            System.exit(1);
        }
    }
}
