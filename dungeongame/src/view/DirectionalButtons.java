package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.Directions;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeSupport;

/**
 * Represents directional buttons for navigating the maze.
 * Provides buttons for moving in all cardinal directions.
 */
public class DirectionalButtons {
    private final MazeTraverser myMazeTraverser;
    private final RoomDescription myRoomDescription;
    private final ButtonFactory myButtonFactory;

    /**
     * Constructs a DirectionalButtons instance.
     *
     * @param theRoomDescription the description of the room to be updated
     *                           based on player's movement
     */
    public DirectionalButtons(RoomDescription theRoomDescription) {
        myMazeTraverser = MazeTraverser.getInstance();
        myRoomDescription = theRoomDescription;
        myMazeTraverser.setRoomDescription(myRoomDescription);

        myButtonFactory = new ButtonFactory(80);
    }

    /**
     * Creates an HBox containing directional buttons for navigation.
     *
     * @return an HBox with directional buttons
     */
    public HBox createDirectionalButtons() {
        Button myMoveWestButton = myButtonFactory.createButton("Move West", () -> movePlayer("west"));
        Button myMoveEastButton = myButtonFactory.createButton("Move East", () -> movePlayer("east"));
        Button myMoveNorthButton = myButtonFactory.createButton("Move North", () -> movePlayer("north"));
        Button myMoveSouthButton = myButtonFactory.createButton("Move South", () -> movePlayer("south"));

        HBox myMovementButtons = new HBox(10, myMoveWestButton, myMoveEastButton, myMoveNorthButton, myMoveSouthButton);
        myMovementButtons.setStyle("-fx-alignment: center; -fx-padding: 40 0 0 -150;");
        return myMovementButtons;
    }

    /**
     * Moves the player in the specified direction and updates the room description.
     *
     * @param theDirection the direction to move the player (e.g., "west", "east", "north", "south")
     */
    private void movePlayer(String theDirection) {
        switch (theDirection) {
            case "west" -> myMazeTraverser.movePlayer(Directions.WEST);
            case "east" -> myMazeTraverser.movePlayer(Directions.EAST);
            case "north" -> myMazeTraverser.movePlayer(Directions.NORTH);
            case "south" -> myMazeTraverser.movePlayer(Directions.SOUTH);
        }
        myRoomDescription.updateDescription();
    }
}