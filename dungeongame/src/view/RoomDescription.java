package dungeongame.src.view;

import dungeongame.src.model.Maze;
import dungeongame.src.model.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents a description box for the current room in the dungeon game.
 * Displays information about the room, such as items, monsters, or its emptiness.
 *
 */
public class RoomDescription {

    /** The maze that contains the rooms and player state. */
    private final Maze myMaze;

    /** The label used to display the room's description. */
    private final Label roomDescriptionLabel;

    /**
     * Constructs a RoomDescription instance for the specified maze.
     * Initializes the label with the current room's description.
     *
     * @param theMaze The maze to extract room descriptions from.
     */
    public RoomDescription(Maze theMaze) {
        myMaze = theMaze;
        roomDescriptionLabel = new Label(getRoomDescription());
        roomDescriptionLabel.setWrapText(true);
        roomDescriptionLabel.setStyle("-fx-font-size: 16px; -fx-text-alignment: center;");
    }

    /**
     * Creates and returns a VBox containing the room description label.
     * The VBox is styled to align and position the description appropriately.
     *
     * @return A VBox containing the room description label.
     */
    public VBox createDescriptionBox() {
        VBox descriptionBox = new VBox(roomDescriptionLabel);
        descriptionBox.setStyle("-fx-alignment: center; -fx-padding: 400 10 10 10;");
        return descriptionBox;
    }

    /**
     * Updates the room description label to reflect the current room's state.
     * This method fetches the latest description from the maze.
     */
    public void updateDescription() {
        roomDescriptionLabel.setText(getRoomDescription());
    }

    /**
     * Updates the room description label with a custom description.
     *
     * @param newDescription The new description to set in the label.
     */
    public void updateDescription(String newDescription) {
        roomDescriptionLabel.setText(newDescription);
    }

    /**
     * Retrieves the description of the current room based on its contents.
     * Descriptions include whether the room contains an item, a monster, or is empty.
     *
     * @return A string describing the current room.
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
