package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Represents the game screen displayed during gameplay.
 * This screen includes a menu bar, room description,
 * directional controls, and map/inventory views.
 */
public class GameScreen extends AbstractScreen {
    private final Maze myMaze;
    private final AbstractDungeonCharacter myCharacter;
    private final InventoryScreen myInventoryScreen;

    /**
     * Constructs a new GameScreen instance.
     *
     * @param theMaze      the maze instance representing the game environment
     * @param theCharacter the character controlled by the player
     */
    public GameScreen(Maze theMaze, AbstractDungeonCharacter theCharacter) {
        myMaze = theMaze;
        myCharacter = theCharacter;
        myInventoryScreen = new InventoryScreen(theCharacter);
        MazeTraverser theTraverser = MazeTraverser.getInstance();
        theTraverser.setPlayer(((Player) myCharacter));
        theTraverser.setInventoryScreen(myInventoryScreen);
    }

    /**
     * Creates the game screen's scene, which includes layout elements like
     * the menu bar, room description, directional controls, and inventory/map view.
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
        myMainLayout.setCenter(mazeContainer);

        // Top: Menu bar
        MenuBar myMenuBar = new MenuBar(theStage);
        myMainLayout.setTop(myMenuBar.createMenuBar());

        // Bottom: Map and inventory buttons + directional buttons in an HBox
        MapAndInventory myMapAndInventory = new MapAndInventory(myMaze, myCharacter, myInventoryScreen);
        VBox mapAndInventoryButtons = myMapAndInventory.createMapAndInventoryButtons();

        DirectionalButtons myDirectionalButtons = new DirectionalButtons(new RoomDescription(myMaze));
        GridPane directionalButtons = myDirectionalButtons.createDirectionalButtons();

        // Create an HBox for the bottom pane
        HBox bottomPane = new HBox(220);
        bottomPane.setAlignment(Pos.CENTER_LEFT);
        bottomPane.getChildren().addAll(mapAndInventoryButtons, directionalButtons);
        myMainLayout.setBottom(bottomPane);
        myMainLayout.setStyle("-fx-background-color: #2b2b2b;");

        return new Scene(myMainLayout, 800, 600);
    }
}