package dungeongame.src.view;

import dungeongame.src.model.Maze;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Represents a screen that displays the map of the dungeon.
 * The map is generated using the Maze class printMonsterMaze() method and displayed in a new window.
 *
 */
public class MapScreen {

    /** The maze object whose map will be displayed. */
    private final Maze myMaze;

    /**
     * Constructs a new MapScreen when the "Map" button is selected.
     *
     * @param theMaze The maze whose map will be displayed.
     */
    public MapScreen(Maze theMaze) {
        myMaze = theMaze;
    }

    /**
     * Displays the map in a new window.
     * The map is rendered as text output captured from the Maze class printMonsterMaze() method.
     */
    public void display() {
        Stage mapStage = new Stage();
        VBox mapLayout = new VBox(10);
        mapLayout.setAlignment(Pos.CENTER);

        // Title for the map
        Label mapLabel = new Label("Map");
        mapLabel.setFont(new Font("Arial", 20));
        mapLabel.setTextAlignment(TextAlignment.CENTER);

        // Capture the output of the maze's printMonsterMaze() method
        String mapContent = captureOutput(myMaze::printMonsterMaze);

        // Display the captured map content in a label
        Label mapContentLabel = new Label(mapContent);
        mapContentLabel.setFont(Font.font("Monospaced", 12)); // Use monospace font for alignment
        mapContentLabel.setWrapText(false); // Prevent line wrapping for proper formatting

        mapLayout.getChildren().addAll(mapLabel, mapContentLabel);

        Scene mapScene = new Scene(mapLayout, 400, 300);
        mapStage.setScene(mapScene);
        mapStage.show();
    }

    /**
     * Captures the console output of the provided action.
     * This method temporarily redirects System.out to capture
     * all printed output during the execution of the specified action.
     *
     * @param theAction The action whose console output will be captured.
     * @return The captured console output as a string.
     */
    private static String captureOutput(Runnable theAction) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;

        try {
            // Redirect System.out to capture output
            System.setOut(printStream);
            theAction.run();
            System.out.flush();
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }
}