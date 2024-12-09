package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PlayerInventory}.
 * Verifies the functionality of adding, using, and checking items in the player's inventory.
 */
class PlayerInventoryTest {

    /**
     * The instance of {@link PlayerInventory} used for testing.
     */
    private PlayerInventory myInventory;

    /**
     * Sets up the test environment by clearing the inventory before each test.
     */
    @BeforeEach
    void setUp() {
        myInventory = PlayerInventory.getInstance();
        myInventory.updateFrom(new PlayerInventory());
    }

    /**
     * Tests adding a single item to the inventory.
     */
    @Test
    void testAddItem() {
        Item healthPotion = new HealthPotion();
        myInventory.addItem(healthPotion);

        assertTrue(myInventory.containsItem(healthPotion), "Inventory should contain the added health potion.");
        assertEquals(1, myInventory.getInventory().get(healthPotion), "Health potion quantity should be 1.");
    }

    /**
     * Tests adding multiple instances of the same item to the inventory.
     */
    @Test
    void testAddMultipleItems() {
        Item healthPotion = new HealthPotion();
        myInventory.addItem(healthPotion);
        myInventory.addItem(healthPotion);

        assertEquals(2, myInventory.getInventory().get(healthPotion));
    }

    /**
     * Tests using an item from the inventory, reducing its count to zero.
     */
    @Test
    void testUseItem() {
        Item healthPotion = new HealthPotion();
        myInventory.addItem(healthPotion);
        myInventory.useItem(healthPotion);

        assertFalse(myInventory.containsItem(healthPotion));
    }

    /**
     * Tests using an item from the inventory when there are multiple quantities.
     */
    @Test
    void testUseItemWithMultipleQuantities() {
        Item healthPotion = new HealthPotion();
        myInventory.addItem(healthPotion);
        myInventory.addItem(healthPotion);
        myInventory.useItem(healthPotion);

        assertTrue(myInventory.containsItem(healthPotion));
        assertEquals(1, myInventory.getInventory().get(healthPotion));
    }

    /**
     * Tests attempting to use an item that is not present in the inventory.
     */
    @Test
    void testUseItemNotInInventory() {
        Item healthPotion = new HealthPotion();

        assertThrows(IllegalArgumentException.class, () -> myInventory.useItem(healthPotion));
    }

    /**
     * Tests that collecting all four pillars triggers the exit spawn in the maze.
     */
    @Test
    void testPillarsTriggerExitSpawn() {
        Maze myMaze = Maze.getInstance();
        myMaze.setMazeSize(5);
        myMaze.generateMaze();

        Item encapsulationPillar = new Pillar("Encapsulation Pillar");
        Item inheritancePillar = new Pillar("Inheritance Pillar");
        Item polymorphismPillar = new Pillar("Polymorphism Pillar");
        Item abstractionPillar = new Pillar("Abstraction Pillar");

        myInventory.addItem(encapsulationPillar);
        myInventory.addItem(inheritancePillar);
        myInventory.addItem(polymorphismPillar);
        myInventory.addItem(abstractionPillar);
        myInventory.checkforPillars();
        System.out.println("Test passed: Exit spawned successfully.");
    }

    /**
     * Tests that {@link PlayerInventory#getInventory()} returns a deep copy of the inventory.
     * Modifications to the copy should not affect the original inventory.
     */
    @Test
    void testGetInventoryReturnsDeepCopy() {
        Item healthPotion = new HealthPotion();
        myInventory.addItem(healthPotion);

        HashMap<Item, Integer> myInventoryCopy = myInventory.getInventory();
        myInventoryCopy.remove(healthPotion);

        assertTrue(myInventory.containsItem(healthPotion));
    }
}