package dungeongame.src.view;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a view for the map and inventory in the dungeon game.
 * Provides buttons for displaying the map or inventory screen.
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
     * Creates a VBox containing buttons for the map and inventory.
     * These buttons are aligned to the bottom-left of the screen.
     *
     * @return the VBox with map and inventory buttons
     */
    public VBox createMapAndInventoryButtons() {
        VBox myLeftButtons = new VBox(0);
        myLeftButtons.setPadding(new Insets(5));
        myLeftButtons.setAlignment(Pos.BOTTOM_LEFT);
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
        myMapStage.setScene(new MapScene(new BorderPane()));
        myMapStage.show();
    }
}