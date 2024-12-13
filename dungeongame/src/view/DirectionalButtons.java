package dungeongame.src.view;

import dungeongame.src.controller.MazeTraverser;
import dungeongame.src.model.Directions;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Represents directional buttons for navigating the maze.
 * Provides buttons for moving in all cardinal directions.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class DirectionalButtons {

    /** Constant of -90 */
    private static final int NEGATIVE_NINETY = -90;

    /** Constant of -52 */
    private static final int NEGATIVE_FIFTY_TWO = -52;

    /** Constant of -5 */
    private static final int NEGATIVE_FIVE = -5;

    /** Constant of 2 */
    private static final int TWO = 2;

    /** Constant of 3 */
    private static final int THREE = 3;

    /** Constant of 80 */
    private static final int EIGHTY = 80;

    /** The MazeTraverser instance for handling player movements. */
    private final MazeTraverser myMazeTraverser;

    /** The RoomDescription instance for updating the current room's description. */
    private final RoomDescription myRoomDescription;

    /** The ButtonFactory instance for creating directional buttons. */
    private final ButtonFactory myButtonFactory;

    /**
     * Constructs a DirectionalButtons instance.
     *
     * @param theRoomDescription the description of the room to be updated based on player's movement.
     *
     */
    public DirectionalButtons(final RoomDescription theRoomDescription) {
        myMazeTraverser = MazeTraverser.getInstance();
        myRoomDescription = theRoomDescription;
        myMazeTraverser.setRoomDescription(myRoomDescription);
        myButtonFactory = new ButtonFactory(EIGHTY);
    }

    /**
     * Creates a GridPane containing directional buttons for navigation.
     *
     * @return a GridPane with directional buttons
     */
    public GridPane createDirectionalButtons() {
        String[] directions = {"North", "West", "East", "South"};
        int[][] gridPositions = {
                {1, 0}, // North
                {0, 1}, // West
                {TWO, 1}, // East
                {1, TWO}  // South
        };

        GridPane directionalButtonsGrid = new GridPane();
        directionalButtonsGrid.setHgap(NEGATIVE_FIFTY_TWO);
        directionalButtonsGrid.setVgap(THREE);
        directionalButtonsGrid.setAlignment(Pos.CENTER);
        directionalButtonsGrid.setTranslateX(NEGATIVE_NINETY);
        directionalButtonsGrid.setTranslateY(NEGATIVE_FIVE);

        for (int i = 0; i < directions.length; i++) {
            String direction = directions[i];
            int[] position = gridPositions[i];
            Button button = myButtonFactory.createButton("Move " + direction, () -> movePlayer(direction.toLowerCase()));
            GridPane.setConstraints(button, position[0], position[1], 1, 1, HPos.CENTER, VPos.CENTER);
            directionalButtonsGrid.getChildren().add(button);
        }
        return directionalButtonsGrid;
    }

    /**
     * Moves the player in the specified direction and updates the room description.
     *
     * @param theDirection the direction to move the player (e.g., "west", "east", "north", "south")
     */
    private void movePlayer(final String theDirection) {
        switch (theDirection) {
            case "west" -> myMazeTraverser.movePlayer(Directions.WEST);
            case "east" -> myMazeTraverser.movePlayer(Directions.EAST);
            case "north" -> myMazeTraverser.movePlayer(Directions.NORTH);
            case "south" -> myMazeTraverser.movePlayer(Directions.SOUTH);
        }
        myRoomDescription.updateDescription();
    }
}