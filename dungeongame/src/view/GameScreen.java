package dungeongame.src.view;

import dungeongame.src.model.Maze;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Represents the main game screen for the dungeon adventure game.
 * The game screen includes a menu bar, room description, directional movement buttons, and
 * map/inventory controls.
 *
 */
public class GameScreen {

    /** The maze object representing the dungeon layout and player state. */
    private final Maze myMaze;

    /** The primary stage of the application where the game screen will be displayed. */
    private final Stage myStage;

    /**
     * Constructs a new GameScreen with the specified stage and maze.
     *
     * @param theStage The primary stage of the application.
     * @param theMaze The maze representing the dungeon layout and game state.
     */
    public GameScreen(Stage theStage, Maze theMaze) {
        myStage = theStage;
        myMaze = theMaze;
    }

    /**
     * Creates and returns the main game scene.
     * The scene includes:
     * A menu bar for saving and quitting the game (top).
     * A room description box displaying information about the current room (center).
     * Directional movement buttons and map/inventory controls (bottom).
     *
     * @return The game scene ready to be displayed on the primary stage.
     */
    public Scene createGameScene() {
        BorderPane mainLayout = new BorderPane();

        // Add Menu Bar (Top)
        MenuBar menuBar = new MenuBar(myStage);
        mainLayout.setTop(menuBar.createMenuBar());

        // Add Room Description (Center)
        RoomDescription roomDescription = new RoomDescription(myMaze);
        mainLayout.setCenter(roomDescription.createDescriptionBox());

        // Add Bottom Pane with Directional Buttons and Map/Inventory
        MapAndInventory mapAndInventory = new MapAndInventory(myMaze);
        DirectionalButtons directionalButtons = new DirectionalButtons(roomDescription);
        mainLayout.setBottom(mapAndInventory.createBottomPane(directionalButtons));

        return new Scene(mainLayout, 800, 600);
    }
}