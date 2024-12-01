package dungeongame.src.model;

import java.awt.*;

/**
 * Represents a Vision Potion item in the dungeon adventure game.
 * When used, this potion reveals the surrounding area for the player.
 * It inherits common item properties from the AbstractItem class.
 *
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public class VisionPotion extends AbstractItem {

    /**
     * Constructs a Vision Potion with a predefined name, description, and value.
     * The Vision Potion has a single use and reveals the surrounding area when used.
     */
    public VisionPotion() {

        super("Vision Potion", "Reveals surrounding area", 5, 1);
    }

    /**
     * Uses the Vision Potion to reveal the contents of rooms surrounding the player.
     * This method fetches neighboring rooms of the player's current location in the maze.
     *
     * @param theMaze The maze in which the player is located.
     */
    public void useItem(Maze theMaze) {
        Point playerCoords = theMaze.getPlayerCords();
        int playerX = playerCoords.x;
        int playerY = playerCoords.y;

        // Reveal the player's current room and surrounding rooms
        revealRoomContents(theMaze, playerX, playerY); // Current room
        revealRoomContents(theMaze, playerX - 1, playerY); // North
        revealRoomContents(theMaze, playerX + 1, playerY); // South
        revealRoomContents(theMaze, playerX, playerY - 1); // West
        revealRoomContents(theMaze, playerX, playerY + 1); // East
    }

    /**
     * Helper method to reveal contents of a specific room in the maze.
     *
     * @param theMaze The maze containing the room.
     * @param theX The x-coordinate of the room.
     * @param theY The y-coordinate of the room.
     */
    private void revealRoomContents(Maze theMaze, int theX, int theY) {
        // Check if the coordinates are within bounds
        if (theX >= 0 && theX < theMaze.getSize() && theY >= 0 && theY < theMaze.getSize()) {
            Room room = theMaze.getRooms()[theX][theY];
            System.out.println("Room at (" + theX + ", " + theY + "):");

            // Check room contents
            if (room.getItem() != null) {
                System.out.println("  Contains item: " + room.getItem().getMyItemName());
            } else if (room.getMonster() != null) { // Assuming getContents > 0 indicates a monster
                System.out.println("  Contains a monster.");
            } else {
                System.out.println("  The room is empty.");
            }
        }
    }
}

