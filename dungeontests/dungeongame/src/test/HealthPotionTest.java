package dungeongame.src.test;

import dungeongame.src.model.HealthPotion;
import dungeongame.src.model.Character;
import dungeongame.src.model.Thief;  // Example concrete Character class
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthPotionTest {

    private HealthPotion healthPotion;
    private Character character;

    @BeforeEach
    public void setUp() {
        // Initialize a Thief with max health of 70 and current health of 50
        healthPotion = new HealthPotion();
        character = new Thief(70, 50, 10, 15, 10, "Test Thief");
    }

    @Test
    public void testUseItemDoesNotExceedMaxHealth() {
        // Set health close to max
        character.setHealth(75);

        // Use the potion
        healthPotion.useItem(character);

        // Ensure health does not exceed max health (70)
        assertEquals(70, character.getHealth(), "Health should cap at max health");
    }

    @Test
    public void testUseItemIncreasesHealthBelowMax() {
        // Set health well below max
        character.setHealth(40);

        // Use the potion
        healthPotion.useItem(character);

        // Health should increase by 20
        assertEquals(60, character.getHealth(), "Health should increase by potion value");
    }

    @Test
    public void testUseItemAtMaxHealthDoesNotIncreaseHealth() {
        // Set health to max
        character.setHealth(70);

        // Use the potion
        healthPotion.useItem(character);

        // Health should remain at max
        assertEquals(70, character.getHealth(), "Health should remain unchanged when already at max");
    }

    @Test
    public void testUseItemIncreasesLowHealthCorrectly() {
        // Set health to a low value
        character.setHealth(10);

        // Use the potion
        healthPotion.useItem(character);

        // Health should increase by 20, totaling 30
        assertEquals(30, character.getHealth(), "Health should increase correctly from a low value");
    }

    @Test
    public void testUseItemNearMaxHealth() {
        // Set health near max
        character.setHealth(69);

        // Use the potion
        healthPotion.useItem(character);

        // Health should cap at max
        assertEquals(70, character.getHealth(), "Health should cap at max health when near the maximum");
    }
}