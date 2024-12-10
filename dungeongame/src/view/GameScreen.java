package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.*;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Represents the game screen displayed during gameplay.
 * This screen includes the maze layout, player health display, menu bar, directional controls, and inventory/map views.
 * It manages the integration of game UI elements to provide a seamless gameplay experience.
 *
 *
 */
public class GameScreen extends AbstractScreen {

    /**
     * The maze instance representing the game environment.
     */
    private final Maze myMaze;

    /**
     * The character controlled by the player.
     */
    private final AbstractDungeonCharacter myCharacter;

    /**
     * The inventory screen for displaying the player's items.
     */
    private final InventoryScreen myInventoryScreen;

    /**
     * Constructs a new GameScreen instance.
     * Initializes the maze, player character, inventory screen, and sets up the maze traverser to manage player
     * movements.
     *
     * @param theMaze      the maze instance representing the game environment
     * @param theCharacter the character controlled by the player
     */
    public GameScreen(Maze theMaze, AbstractDungeonCharacter theCharacter) {
        myMaze = theMaze;
        myCharacter = theCharacter;
        myInventoryScreen = new InventoryScreen(theCharacter);

        MazeTraverser theTraverser = MazeTraverser.getInstance();
        theTraverser.setPlayer((Player) myCharacter);
        theTraverser.setInventoryScreen(myInventoryScreen);
    }

    /**
     * Creates the game screen's scene.
     * This method assembles various UI components, including the maze layout, health display, directional buttons,
     * menu bar, and inventory/map views.
     *
     * @param theStage the primary stage where the scene will be displayed
     * @return the constructed Scene for the game screen
     */
    @Override
    public Scene createScene(Stage theStage) {
        BorderPane myMainLayout = new BorderPane();

        // Center: Maze layout
        StackPane mazeContainer = new StackPane();
        BorderPane mazeLayout = new MazeBorderPane(300, 300, myCharacter.getImageFileName());
        mazeContainer.getChildren().add(mazeLayout);
        mazeContainer.setAlignment(Pos.CENTER);
        mazeContainer.setStyle("-fx-padding: -70px;");
        mazeContainer.setMaxWidth(300);
        mazeContainer.setMaxHeight(300);
        mazeContainer.setTranslateY(-20);
        mazeContainer.setTranslateX(20);
        myMainLayout.setCenter(mazeContainer);

        // Right: Health display
        VBox healthDisplay = createHealthDisplay();
        myMainLayout.setRight(healthDisplay);

        // Top: Menu bar
        MenuBar myMenuBar = new MenuBar(theStage, myCharacter);
        myMainLayout.setTop(myMenuBar.createMenuBar());

        // Bottom: Map and inventory buttons + directional buttons
        MapAndInventory myMapAndInventory = new MapAndInventory(myMaze, myCharacter, myInventoryScreen);
        VBox mapAndInventoryButtons = myMapAndInventory.createMapAndInventoryButtons();

        DirectionalButtons myDirectionalButtons = new DirectionalButtons(new RoomDescription(myMaze));
        GridPane directionalButtons = myDirectionalButtons.createDirectionalButtons();

        // Create an HBox for the bottom pane
        HBox bottomPane = new HBox(220);
        bottomPane.setAlignment(Pos.CENTER_LEFT);
        bottomPane.getChildren().addAll(mapAndInventoryButtons, directionalButtons);
        myMainLayout.setBottom(bottomPane);

        // Set background styling
        //myMainLayout.setStyle("-fx-background-color: #2b2b2b;");
        return new Scene(myMainLayout, 800, 600);
    }

    /**
     * Creates a health display panel to show the character's current health.
     * The health display dynamically updates using bindings to reflect the character's current and maximum health.
     *
     * @return the VBox containing the health display
     */
    private VBox createHealthDisplay() {
        VBox healthDisplay = new VBox(-5);
        healthDisplay.setAlignment(Pos.CENTER);
        healthDisplay.setTranslateX(-80);
        healthDisplay.setTranslateY(-200);

        // Label for "Health" text
        Label healthLabel = new Label("Health:");
        healthLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: rgba(205,12,12,0.99);");

        // Label displaying current health value
        Label healthValue = new Label();
        healthValue.textProperty().bind(Bindings.concat(myCharacter.getCurHealthProperty(), "/",
                myCharacter.getMaxHealth()));
        healthValue.setStyle("-fx-font-size: 14px; -fx-text-fill: rgba(205,12,12,0.99);");

        healthDisplay.getChildren().addAll(healthLabel, healthValue);

        return healthDisplay;
    }
}