package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        StackPane mazeContainer = new StackPane();
        BorderPane mazeLayout = new MazeBorderPane(300,300, myCharacter.getImageFileName());

        mazeContainer.getChildren().add(mazeLayout);
        mazeContainer.setAlignment(Pos.CENTER);
        mazeContainer.setStyle("-fx-padding: -70px;");
        mazeContainer.setMaxWidth(300);
        mazeContainer.setMaxHeight(300);

        MenuBar myMenuBar = new MenuBar(theStage);
        myMainLayout.setTop(myMenuBar.createMenuBar());
        myMainLayout.setCenter(mazeContainer);

        RoomDescription myRoomDescription = new RoomDescription(myMaze);

//        myMainLayout.setCenter(myRoomDescription.createDescriptionBox());

        MazeTraverser theTraverser = MazeTraverser.getInstance();
        theTraverser.setRoomDescription(myRoomDescription);

        MapAndInventory myMapAndInventory = new MapAndInventory(myMaze, myCharacter, myInventoryScreen);
        DirectionalButtons myDirectionalButtons = new DirectionalButtons(myRoomDescription);
        myMainLayout.setBottom(myMapAndInventory.createBottomPane(myDirectionalButtons));

        return new Scene(myMainLayout, 800, 600);
    }
}