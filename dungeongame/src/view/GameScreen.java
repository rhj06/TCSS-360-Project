package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * Represents the game screen displayed during gameplay.
 * This screen includes the maze layout, player health display, menu bar, directional controls, and inventory/map views.
 * It manages the integration of game UI elements to provide a seamless gameplay experience.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
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
    public GameScreen(Maze theMaze, AbstractDungeonCharacter theCharacter, Stage theStage) {
        myMaze = theMaze;
        myCharacter = theCharacter;
        myInventoryScreen = new InventoryScreen(theCharacter);

        MazeTraverser theTraverser = MazeTraverser.getInstance();
        theTraverser.setPlayer((Player) myCharacter);
        theTraverser.setInventoryScreen(myInventoryScreen);
        theTraverser.addPropertyChangeListener(evt -> {
            if ("final_boss_killed".equals(evt.getPropertyName())) {
                Platform.runLater(() -> transitionToVictoryScreen(theStage));
            } else if ("player_dead".equals(evt.getPropertyName())) {
                Platform.runLater(() -> transitionToGameOverScreen(theStage));
            }
        });

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
        VBox menuBarContainer = myMenuBar.createMenuBar();
        menuBarContainer.prefWidthProperty().bind(myMainLayout.widthProperty());
        myMainLayout.setTop(menuBarContainer);
        myMainLayout.setTop(menuBarContainer);

        // Bottom: Map and inventory buttons + directional buttons
        MapAndInventory myMapAndInventory = new MapAndInventory(myMaze, myInventoryScreen);
        VBox mapAndInventoryButtons = myMapAndInventory.createMapAndInventoryButtons();

        DirectionalButtons myDirectionalButtons = new DirectionalButtons(new RoomDescription(myMaze));
        GridPane directionalButtons = myDirectionalButtons.createDirectionalButtons();

        // Create an HBox for the bottom pane
        HBox bottomPane = new HBox(220);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().addAll(mapAndInventoryButtons, directionalButtons);
        bottomPane.setTranslateX(-100);
        myMainLayout.setBottom(bottomPane);
        myMainLayout.setStyle("-fx-background-color: black;");

        return new Scene(myMainLayout, 800, 600);
    }

    /**
     * Transitions the game to the victory screen.
     * This method is triggered when the "final_boss_killed" event is received. It replaces the current scene with the
     * VictoryScreen scene.
     *
     * @param theStage the primary stage where the victory screen will be displayed
     */
    private void transitionToVictoryScreen(Stage theStage) {
        System.out.println("Transitioning to VictoryScreen.");
        VictoryScreen victoryScreen = new VictoryScreen();
        Scene victoryScene = victoryScreen.createScene(theStage);
        theStage.setScene(victoryScene);
    }

    /**
     * Transitions the game to the game over screen.
     * This method is triggered when the "player_dead" event is received. It replaces the current scene with the
     * GameOverScreen scene.
     *
     * @param theStage the primary stage where the game over screen will be displayed
     */
    private void transitionToGameOverScreen(Stage theStage) {
        GameOverScreen gameOverScreen = new GameOverScreen();
        Scene gameOverScene = gameOverScreen.createScene(theStage);
        theStage.setScene(gameOverScene);
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

        Label healthLabel = new Label("Health:");
        healthLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: rgba(205,12,12,0.99);");

        Label healthValue = new Label();
        healthValue.textProperty().bind(Bindings.concat(myCharacter.getCurHealthProperty(), "/",
                myCharacter.getMaxHealth()));
        healthValue.setStyle("-fx-font-size: 14px; -fx-text-fill: rgba(205,12,12,0.99);");

        healthDisplay.getChildren().addAll(healthLabel, healthValue);

        return healthDisplay;
    }
}