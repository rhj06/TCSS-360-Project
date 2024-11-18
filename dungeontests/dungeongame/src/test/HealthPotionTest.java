
package dungeongame.src.test;

import dungeongame.src.model.HealthPotion;
import dungeongame.src.model.Character;
import dungeongame.src.model.Thief; // Example concrete Character class
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HealthPotion class.
 */
public class HealthPotionTest {

    private HealthPotion healthPotion;
    private Character character;

    /**
     * Sets up the test environment before each test.
     * Initializes a HealthPotion instance and a Thief character with a maximum health of 70, current health of 50, and
     * other relevant attributes.
     */
    @BeforeEach
    public void setUp() {
        healthPotion = new HealthPotion();
        character = new Thief(70, 50, 10, 15, 10, "Test Thief");
    }

    /**
     * Tests that using the health potion does not exceed the character's maximum health.
     * Sets the character's health near the maximum and ensures that it caps correctly.
     */
    @Test
    public void testUseItemDoesNotExceedMaxHealth() {
        character.setHealth(75);
        healthPotion.useItem(character);
        assertEquals(70, character.getHealth(), "Health should cap at max health");
    }

    /**
     * Tests that using the health potion increases health correctly when the character's
     * current health is below the maximum by more than the potion's value.
     */
    @Test
    public void testUseItemIncreasesHealthBelowMax() {
        character.setHealth(40);
        healthPotion.useItem(character);
        assertEquals(60, character.getHealth(), "Health should increase by potion value");
    }

    /**
     * Tests that using the health potion when the character's health is at the maximum
     * does not increase health.
     */
    @Test
    public void testUseItemAtMaxHealthDoesNotIncreaseHealth() {
        character.setHealth(70);
        healthPotion.useItem(character);
        assertEquals(70, character.getHealth(), "Health should remain unchanged when already at max");
    }

    /**
     * Tests that using the health potion increases health correctly from a low starting value.
     */
    @Test
    public void testUseItemIncreasesLowHealthCorrectly() {
        character.setHealth(10);
        healthPotion.useItem(character);
        assertEquals(30, character.getHealth(), "Health should increase correctly from a low value");
    }

    /**
     * Tests that using the health potion when the character's health is near the maximum
     * caps the health at the maximum.
     */
    @Test
    public void testUseItemNearMaxHealth() {
        character.setHealth(69);
        healthPotion.useItem(character);
        assertEquals(70, character.getHealth(), "Health should cap at max health when near the maximum");
    }
}