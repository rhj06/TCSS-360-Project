package dungeongame.src.view;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a combined view for the map and inventory in the dungeon game.
 * Provides functionality to display a map or inventory screen and directional controls.
 */
public class MapAndInventory {

    /** The maze representing the dungeon layout. */
    private final Maze myMaze;

    /** The inventory screen for managing items. */
    private final InventoryScreen myInventoryScreen;

    /** A factory for creating buttons with consistent styling. */
    private final ButtonFactory myButtonFactory;

    /**
     * Constructs a MapAndInventory instance.
     *
     * @param theMaze the maze to display
     * @param theCharacter the character associated with the inventory
     * @param theInventoryScreen the inventory screen for item management
     */
    public MapAndInventory(Maze theMaze, AbstractDungeonCharacter theCharacter, InventoryScreen theInventoryScreen) {
        myMaze = theMaze;
        myInventoryScreen = theInventoryScreen;
        myButtonFactory = new ButtonFactory(150);
    }

    /**
     * Creates the bottom pane containing map, inventory, and directional buttons.
     *
     * @param theDirectionalButtons the directional buttons for navigation
     * @return the created bottom pane
     */
    public BorderPane createBottomPane(DirectionalButtons theDirectionalButtons) {
        BorderPane myBottomPane = new BorderPane();
        VBox myLeftButtons = createLeftButtons();
        myBottomPane.setLeft(myLeftButtons);
        myBottomPane.setCenter(theDirectionalButtons.createDirectionalButtons());

        return myBottomPane;
    }

    /**
     * Creates a VBox containing buttons for the map and inventory.
     *
     * @return the VBox with map and inventory buttons
     */
    private VBox createLeftButtons() {
        VBox myLeftButtons = new VBox(10);
        myLeftButtons.setPadding(new Insets(10));
        myLeftButtons.setStyle("-fx-alignment: bottom-left;");

        myLeftButtons.getChildren().addAll(
                myButtonFactory.createButton("Map", this::displayMap),
                myButtonFactory.createButton("Inventory", myInventoryScreen::display)
        );
        return myLeftButtons;
    }

    /**
     * Displays the map screen in a new stage.
     */
    private void displayMap() {
        Stage myMapStage = new Stage();
        myMapStage.setScene(new MapScene(new BorderPane())); //new MapScreen(myMaze).createScene(myMapStage)
        myMapStage.show();
    }
}