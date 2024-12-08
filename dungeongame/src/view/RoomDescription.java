package dungeongame.src.view;

import dungeongame.src.model.Maze;
import dungeongame.src.model.PlayerInventory;
import dungeongame.src.model.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents a description box for the current room in the dungeon game.
 * Displays information about the room, such as items, monsters, or its emptiness.
 */
public class RoomDescription {

    private final Maze myMaze;
    private final PlayerInventory myInventory;
    private final Label roomDescriptionLabel;

    public RoomDescription(Maze theMaze) {
        myMaze = theMaze;
        myInventory = PlayerInventory.getInstance();
        roomDescriptionLabel = new Label(getRoomDescription());
        roomDescriptionLabel.setWrapText(true);
        roomDescriptionLabel.setStyle("-fx-font-size: 16px; -fx-text-alignment: center;");
    }

    public VBox createDescriptionBox() {
        VBox descriptionBox = new VBox(roomDescriptionLabel);
        descriptionBox.setStyle("-fx-alignment: center; -fx-padding: 400 10 10 10;");
        return descriptionBox;
    }

    public void updateDescription() {
        roomDescriptionLabel.setText(getRoomDescription());
    }

    public void updateDescription(String newDescription) {
        roomDescriptionLabel.setText(newDescription);
    }

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

    /**
     * Updates the description to show the player's progress in collecting pillars.
     */
    public void updatePillarStatus() {
        StringBuilder status = new StringBuilder("Pillars Collected:\n");
        myInventory.getInventory().entrySet().stream()
                .filter(entry -> entry.getKey() instanceof dungeongame.src.model.Pillar)
                .forEach(entry -> status.append(entry.getKey().getMyItemName()).append(": Collected\n"));

        // If no pillars are collected, show a default message
        if (status.length() <= "Pillars Collected:\n".length()) {
            status.append("No Pillars Collected.");
        }

        roomDescriptionLabel.setText(status.toString());
    }
}
