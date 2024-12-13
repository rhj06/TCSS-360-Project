package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.PlayerInventory;
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
 *
 *  @version 1.0
 *  @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 */
public final class GameOverScreen extends AbstractScreen {

    /** Constant of 20 */
    private static final int TWENTY = 20;

    /** Constant of 600 */
    private static final int SIX_HUNDRED = 600;

    /** Constant of 800 */
    private static final int EIGHT_HUNDRED = 800;

    /** The path to the custom font used in the game. */
    private static final String MY_FONT_PATH = ".idea/resources/fonts/VIKING-N.TTF";

    @Override
    public Scene createScene(final Stage theStage) {
        VBox myLayout = new VBox(TWENTY);
        myLayout.setAlignment(Pos.CENTER);
        myLayout.setStyle("-fx-background-color: black;");

        Font myVikingFont = null;
        try {
            myVikingFont = Font.loadFont(new File(MY_FONT_PATH).toURI().toString(), 50);
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
        myTryAgainButton.setOnAction(_ -> resetGame(theStage)); // Call resetGame here.

        Button myExitGameButton = new Button("Exit Game");
        myExitGameButton.setStyle("-fx-font-size: 16px;");
        myExitGameButton.setOnAction(_ -> theStage.close());

        myLayout.getChildren().addAll(myGameOverLabel, myTryAgainButton, myExitGameButton);

        return new Scene(myLayout, EIGHT_HUNDRED, SIX_HUNDRED);
    }

    /**
     * Resets the game state and transitions back to the CharacterSelectScreen.
     *
     * @param theStage the primary stage where the character select screen will be displayed
     */
    private void resetGame(final Stage theStage) {
        // Reset Player Inventory
        PlayerInventory inventory = PlayerInventory.getInstance();
        inventory.clear();

        // Reset MazeTraverser
        MazeTraverser traverser = MazeTraverser.getInstance();
        traverser.setPlayer(null);
        traverser.setRoomDescription(null);
        traverser.setInventoryScreen(null);

        // Transition to CharacterSelectScreen
        CharacterSelectScreen characterSelectScreen = new CharacterSelectScreen();
        theStage.setScene(characterSelectScreen.createScene(theStage));
    }
}