package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.Directions;
import dungeongame.src.model.Maze;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Represents a set of directional buttons for player movement in the dungeon game.
 * Provides buttons for moving north, south, east, or west, and updates the room description
 * based on the result of the movement.
 *
 */
public class DirectionalButtons {

    /** The maze object containing the rooms and player state. */
    private final MazeTraverser myMazeTraverser;

    /** The room description object to update after movement. */
    private final RoomDescription roomDescription;

    /**
     * Constructs a DirectionalButtons instance for the specified maze and room description.
     *
     * @param theDescription The RoomDescription to update after movement.
     */
    public DirectionalButtons(RoomDescription theDescription) {
        myMazeTraverser = MazeTraverser.getInstance();
        roomDescription = theDescription;
        myMazeTraverser.setRoomDescription(roomDescription);
    }

    /**
     * Creates and returns an HBox containing directional movement buttons.
     * The buttons allow the player to move north, south, east, or west.
     *
     * @return An HBox containing directional movement buttons.
     */
    public HBox createDirectionalButtons() {
        // Button for moving west
        Button moveLeft = new Button("Move West");
        moveLeft.setOnAction(e -> movePlayer("west"));

        // Button for moving east
        Button moveRight = new Button("Move East");
        moveRight.setOnAction(e -> movePlayer("east"));

        // Button for moving north
        Button moveForward = new Button("Move North");
        moveForward.setOnAction(e -> movePlayer("north"));

        // Button for moving south
        Button moveBackward = new Button("Move South");
        moveBackward.setOnAction(e -> movePlayer("south"));

        // Arrange buttons in an HBox
        HBox movementButtons = new HBox(20, moveLeft, moveRight, moveForward, moveBackward);
        movementButtons.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return movementButtons;
    }

    /**
     * Moves the player in the specified direction and updates the room description.
     * If the move is successful, the room description updates to reflect the new room's state.
     * If the move is unsuccessful, a message is displayed indicating that movement is not possible.
     *
     * @param theDirection The direction to move ("west", "east", "north", "south").
     */
    private void movePlayer(String theDirection) {
        switch (theDirection) {
            case "west" -> myMazeTraverser.movePlayer(Directions.WEST);
            case "east" -> myMazeTraverser.movePlayer(Directions.EAST);
            case "north" -> myMazeTraverser.movePlayer(Directions.NORTH);
            case "south" -> myMazeTraverser.movePlayer(Directions.SOUTH);
        };

    }
}