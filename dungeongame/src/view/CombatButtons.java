package dungeongame.src.view;

import dungeongame.src.controller.Arena;
import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.Directions;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CombatButtons {

    /** The maze object containing the rooms and player state. */
    private final Arena myArena;

    /** The room description object to update after movement. */
    //private final RoomDescription roomDescription;

    /**
     * Constructs a DirectionalButtons instance for the specified maze and room description.
     *
     *
     */
    public CombatButtons(Arena theArena) {
        myArena = theArena;
        //roomDescription = theDescription;
        //myMazeTraverser.setRoomDescription(roomDescription);
    }

    /**
     * Creates and returns an HBox containing directional movement buttons.
     * The buttons allow the player to move north, south, east, or west.
     *
     * @return An HBox containing directional movement buttons.
     */
    public HBox createDirectionalButtons() {
        // Button for moving west
        Button moveLeft = new Button("Basic Attack");
        moveLeft.setOnAction(e -> myArena.setPlayerMove(0));

        // Button for moving east
        Button moveRight = new Button("Use Health Potion");
        moveRight.setOnAction(e -> myArena.setPlayerMove(1));

        // Button for moving north
        Button moveForward = new Button("Use Special Ability");
        moveForward.setOnAction(e -> myArena.setPlayerMove(2));

        // Button for moving south
        Button moveBackward = new Button("Debug Attack");
        moveBackward.setStyle("-fx-background-color: gold;");
        moveBackward.setOnAction(e -> myArena.setPlayerMove(3));

        // Arrange buttons in an HBox
        HBox movementButtons = new HBox(20, moveLeft, moveRight, moveForward, moveBackward);
        movementButtons.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return movementButtons;
    }

//    /**
//     * Moves the player in the specified direction and updates the room description.
//     * If the move is successful, the room description updates to reflect the new room's state.
//     * If the move is unsuccessful, a message is displayed indicating that movement is not possible.
//     *
//     * @param theDirection The direction to move ("west", "east", "north", "south").
//     */
//    private void playerAction(String theDirection) {
//        switch (theDirection) {
//            case "west" -> myMazeTraverser.movePlayer(Directions.WEST);
//            case "east" -> myMazeTraverser.movePlayer(Directions.EAST);
//            case "north" -> myMazeTraverser.movePlayer(Directions.NORTH);
//            case "south" -> myMazeTraverser.movePlayer(Directions.SOUTH);
//        };
//
//    }
}
