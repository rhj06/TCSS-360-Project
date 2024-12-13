package dungeongame.src.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Represents the Victory Screen displayed when the player wins the game.
 * It shows a congratulatory message to the player.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class VictoryScreen extends AbstractScreen {

    /** Constant of 600 */
    private static final int SIX_HUNDRED = 600;

    /** Constant of 800 */
    private static final int EIGHT_HUNDRED = 800;

    /**
     * Creates the Victory Screen's scene with a simple congratulatory message.
     *
     * @param theStage the primary stage where the scene will be displayed
     * @return the constructed Scene for the Victory Screen
     */
    @Override
    public Scene createScene(final Stage theStage) {
        StackPane layout = new StackPane();
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black;");

        Label victoryLabel = new Label("You did it!");
        victoryLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");

        layout.getChildren().add(victoryLabel);

        return new Scene(layout, EIGHT_HUNDRED, SIX_HUNDRED);
    }
}
