package dungeongame.src.view;

import dungeongame.src.model.Maze;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MapAndInventory {

    private final Maze myMaze;

    public MapAndInventory(Maze theMaze) {
        myMaze = theMaze;
    }

    public BorderPane createBottomPane(DirectionalButtons theDirectionalButtons) {
        BorderPane bottomPane = new BorderPane();

        // Map and Inventory Buttons (Left)
        VBox leftButtons = new VBox(10);
        leftButtons.setStyle("-fx-alignment: bottom-left; -fx-padding: 10;");

        // Add buttons to the VBox
        leftButtons.getChildren().addAll(
                createButton("Map", e -> new MapScreen(myMaze).display()),
                createButton("Inventory", e -> new InventoryScreen().display())
        );

        bottomPane.setLeft(leftButtons);

        // Directional Buttons (Center)
        bottomPane.setCenter(theDirectionalButtons.createDirectionalButtons());

        return bottomPane;
    }

    /**
     * Creates a button with standardized size, font, and an action handler.
     *
     * @param text     The text to display on the button.
     * @param handler  The action handler for the button.
     * @return The configured Button instance.
     */
    private Button createButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button button = new Button(text);
        button.setFont(new Font("Arial", 12));
        button.setPrefSize(80, 30);
        button.setOnAction(handler);
        return button;
    }
}