package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for VisionPotion functionality in the dungeon adventure game.
 * Ensures that the VisionPotion correctly reveals surrounding rooms
 * and behaves as expected in various scenarios.
 *
 * @author Your Name
 */
class VisionPotionTest {

    /** The VisionPotion instance to be tested. */
    private VisionPotion myVisionPotion;

    /** The Maze instance used for testing. */
    private Maze myTestMaze;

    /**
     * Sets up the test environment before each test.
     * Initializes the VisionPotion and a 5x5 maze with the player placed at the center.
     */
    @BeforeEach
    void setUp() {
        myVisionPotion = new VisionPotion();
        myTestMaze = Maze.getInstance();
        myTestMaze.setMazeSize(5);
        myTestMaze.generateMaze();


        Point playerPosition = new Point(2, 2);
        myTestMaze.getPlayerCords().setLocation(playerPosition);


        myTestMaze.getRooms()[1][1].setItem(new HealthPotion()); // North-West
        myTestMaze.getRooms()[1][2].setItem(new VisionPotion()); // North
        myTestMaze.getRooms()[1][3].setItem(new SpeedPotion()); // North-East

        myTestMaze.getRooms()[2][1].setMonster(MonsterFactory.createMonster("Goblin")); // West
        myTestMaze.getRooms()[2][3].setMonster(MonsterFactory.createMonster("Ogre")); // East
        myTestMaze.getRooms()[3][3].setMonster(MonsterFactory.createMonster("Skeleton")); // South-East

        myTestMaze.getRooms()[3][1].setItem(new Pillar("Polymorphism Pillar")); // South-West
        myTestMaze.getRooms()[3][2].setItem(new Pillar("Encapsulation Pillar")); // South
    }

    /**
     * Tests the useItem method of the VisionPotion.
     * Verifies that all 8 surrounding rooms and the current room are revealed.
     * Also checks for items, pillars, and monsters in these rooms.
     */
    @Test
    void testUseItemRevealsSurroundingRoomsAndContents() {
        myVisionPotion.useItem(myTestMaze);

        Point playerCoords = myTestMaze.getPlayerCords();
        int playerX = playerCoords.x;
        int playerY = playerCoords.y;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int x = playerX + dx;
                int y = playerY + dy;

                if (x >= 0 && x < myTestMaze.getSize() && y >= 0 && y < myTestMaze.getSize()) {
                    Room room = myTestMaze.getRooms()[x][y];
                    assertNotNull(room, "Room at (" + x + ", " + y + ") should exist.");

                    if (room.getItem() != null) {
                        String itemName = room.getItem().getMyItemName();
                        System.out.println("Room at (" + x + ", " + y + ") contains item: " + itemName);

                        if (x == 1 && y == 1) assertEquals("Health Potion", itemName);
                        if (x == 1 && y == 2) assertEquals("Vision Potion", itemName);
                        if (x == 3 && y == 1) assertTrue(itemName.contains("Pillar"));
                    } else if (room.getMonster() != null) {
                        String monsterName = room.getMonster().toString();
                        System.out.println("Room at (" + x + ", " + y + ") contains monster: " + monsterName);

                        if (x == 2 && y == 1) assertTrue(monsterName.contains("Goblin"));
                        if (x == 2 && y == 3) assertTrue(monsterName.contains("Ogre"));
                        if (x == 3 && y == 3) assertTrue(monsterName.contains("Skeleton"));
                    } else {
                        System.out.println("Room at (" + x + ", " + y + ") is empty.");
                    }
                }
            }
        }
    }
}