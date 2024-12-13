package dungeongame.src.view;

import dungeongame.src.model.Maze;
import dungeongame.src.model.Room;
import javafx.scene.control.Label;

/**
 * Represents a description box for the current room in the dungeon game.
 * Displays information about the room, such as items, monsters, or its emptiness.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class RoomDescription {

    /** The maze instance representing the game environment. */
    private final Maze myMaze;

    /** The label used to display the room's description. */
    private final Label myRoomDescriptionLabel;

    /**
     * Constructs a {@code RoomDescription} instance.
     * Initializes the label with the description of the current room and applies default styles.
     *
     * @param theMaze the maze instance representing the game environment
     */
    public RoomDescription(final Maze theMaze) {
        myMaze = theMaze;
        myRoomDescriptionLabel = new Label(getRoomDescription());
        myRoomDescriptionLabel.setWrapText(true);
        myRoomDescriptionLabel.setStyle("-fx-font-size: 16px; -fx-text-alignment: center;");
    }

    /**
     * Updates the description of the current room.
     * Dynamically fetches the current room's details from the maze and updates the label text.
     */
    public void updateDescription() {
        myRoomDescriptionLabel.setText(getRoomDescription());
    }

    /**
     * Updates the room description with a custom message.
     *
     * @param theNewDescription the custom description to display in the label
     */
    public void updateDescription(final String theNewDescription) {
        myRoomDescriptionLabel.setText(theNewDescription);
    }

    /**
     * Retrieves the description of the current room.
     * The description is based on the room's contents, such as items or monsters, or indicates if the room is empty.
     *
     * @return the description of the current room
     */
    private String getRoomDescription() {
        Room currentRoom = myMaze.getRooms()[myMaze.getPlayerCords().y][myMaze.getPlayerCords().x];
        if (currentRoom.getItem() != null) {

            return "You found an item: " + currentRoom.getItem().getMyItemName();
        } else if (currentRoom.getMonster() != null) {

            return "There is a monster here!";
        } else {

            return "The room is empty.";
        }
    }
}
