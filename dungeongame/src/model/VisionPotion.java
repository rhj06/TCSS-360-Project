package dungeongame.src.model;

import java.awt.*;

/**
 * Represents a Vision Potion item in the dungeon adventure game.
 * When used, this potion reveals the surrounding area for the player.
 * It inherits common item properties from the AbstractItem class.
 *
 * @version 1.0
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public final class VisionPotion extends AbstractItem {

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

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = playerX + dx;
                int newY = playerY + dy;

                revealRoomContents(theMaze, newX, newY);
            }
        }
    }

    /**
     * Helper method to reveal contents of a specific room in the maze.
     *
     * @param theMaze The maze containing the room.
     * @param theX The x-coordinate of the room.
     * @param theY The y-coordinate of the room.
     */
    private void revealRoomContents(final Maze theMaze, final int theX, final int theY) {
        if (theX >= 0 && theX < theMaze.getSize() && theY >= 0 && theY < theMaze.getSize()) {
            Room room = theMaze.getRooms()[theX][theY];
            System.out.println("Room at (" + theX + ", " + theY + "):");

            if (room.getItem() != null) {
                System.out.println("  Contains item: " + room.getItem().getMyItemName());
            } else if (room.getMonster() != null) {
                System.out.println("  Contains a monster.");
            } else {
                System.out.println("  The room is empty.");
            }
        }
    }

    /**
     * Returns a string representation of the Vision Potion.
     *
     * @return The name of the potion.
     */
    @Override
    public String toString() {
        return getMyItemName();
    }
}

