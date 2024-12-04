package dungeongame.src.view;

import dungeongame.src.model.Maze;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MapScreen extends AbstractScreen {

    private final Maze myMaze;

    public MapScreen(Maze theMaze) {
        myMaze = theMaze;
    }

    @Override
    public Scene createScene(Stage theStage) {
        VBox mapLayout = new VBox(10);
        mapLayout.setAlignment(Pos.CENTER);

        Label mapLabel = LabelHelper.createCenteredLabel("Map", "Arial", 20, "-fx-font-weight: bold;");
        String mapContent = captureOutput(myMaze::printMonsterMaze);

        Label mapContentLabel = LabelHelper.createLabel(mapContent, "Monospaced", 12, null);

        mapLayout.getChildren().addAll(mapLabel, mapContentLabel);

        return new Scene(new BorderPane(mapLayout), 400, 300);
    }

    private static String captureOutput(Runnable theAction) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;

        try {
            System.setOut(printStream);
            theAction.run();
            System.out.flush();
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }
}