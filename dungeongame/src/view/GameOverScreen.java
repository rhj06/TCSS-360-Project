package dungeongame.src.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

/**
 * Represents the "Game Over" screen displayed when the player dies.
 * This screen includes a "Game Over" label, a "Try Again?" button, and an "Exit Game" button.
 */
public class GameOverScreen extends AbstractScreen {

    /**
     * The path to the custom font used in the game.
     */
    private static final String MY_FONT_PATH = ".idea/resources/fonts/VIKING-N.TTF";

    @Override
    public Scene createScene(Stage theStage) {
        VBox myLayout = new VBox(20);
        myLayout.setAlignment(Pos.CENTER);
        myLayout.setStyle("-fx-background-color: black;");

        Font myVikingFont = null;
        try {
            myVikingFont = Font.loadFont(new File(MY_FONT_PATH).toURI().toString(), 36);
        } catch (Exception e) {
            System.err.println("Failed to load font: " + e.getMessage());
        }

        Label myGameOverLabel = new Label("Game Over");
        if (myVikingFont != null) {
            myGameOverLabel.setFont(myVikingFont);
        } else {
            myGameOverLabel.setStyle("-fx-font-size: 80px; -fx-text-fill: red;");
        }
        myGameOverLabel.setStyle("-fx-text-fill: red;");

        Button myTryAgainButton = new Button("Try Again?");
        myTryAgainButton.setStyle("-fx-font-size: 16px;");
        myTryAgainButton.setOnAction(event -> {
            theStage.setScene(new CharacterSelectScreen().createScene(theStage));
        });

        Button myExitGameButton = new Button("Exit Game");
        myExitGameButton.setStyle("-fx-font-size: 16px;");
        myExitGameButton.setOnAction(event -> {
            theStage.close();
        });

        myLayout.getChildren().addAll(myGameOverLabel, myTryAgainButton, myExitGameButton);

        return new Scene(myLayout, 800, 600);
    }
}