package dungeongame.src.view;

import dungeongame.src.model.Maze;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a view for the map and inventory in the dungeon game.
 * Provides buttons for displaying the map or inventory screen.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class MapAndInventory {

    /** Constant of 5 */
    private static final int FIVE = 5;

    /** Constant of 150 */
    private static final int ONE_HUNDRED_FIFTY = 150;

    /** The inventory screen for managing items. */
    private final InventoryScreen myInventoryScreen;

    /** A factory for creating buttons with consistent styling. */
    private final ButtonFactory myButtonFactory;

    /**
     * Constructs a MapAndInventory instance.
     *
     * @param theInventoryScreen the inventory screen for item management
     */
    public MapAndInventory(final Maze ignoredTheMaze, final InventoryScreen theInventoryScreen) {
        myInventoryScreen = theInventoryScreen;
        myButtonFactory = new ButtonFactory(ONE_HUNDRED_FIFTY);
    }

    /**
     * Creates a VBox containing buttons for the map and inventory.
     * These buttons are aligned to the bottom-left of the screen.
     *
     * @return the VBox with map and inventory buttons
     */
    public VBox createMapAndInventoryButtons() {
        VBox myLeftButtons = new VBox(0);
        myLeftButtons.setPadding(new Insets(FIVE));
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