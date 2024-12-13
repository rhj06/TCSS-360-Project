package dungeongame.src.test;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.HealthPotion;
import dungeongame.src.model.Thief;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the HealthPotion class.
 */
public class HealthPotionTest {

    private HealthPotion healthPotion;
    private AbstractDungeonCharacter character;

    @BeforeAll
    public static void initToolkit() {
        new Thread(() -> Platform.startup(() -> {})).start();
    }

    @BeforeEach
    public void setUp() {
        healthPotion = new HealthPotion();
        character = new Thief(70, 50, 10, 15, 10, 10, "Test Thief");
    }

    @Test
    public void testUseItemDoesNotExceedMaxHealth() {
        character.setHealth(75); // Exceed max health
        healthPotion.useItem(character);
        assertEquals(character.getMaxHealth(), character.getHealth(),
                "Health should cap at max health");
    }

    @Test
    void testUseItemRestoresHealth() {
        character.setHealth(50); // Set initial health below max
        healthPotion.useItem(character);

        // Verify health increased correctly
        assertEquals(70, character.getCurHealthProperty());
    }

    @Test
    public void testUseItemAtMaxHealthDoesNotIncreaseHealth() {
        character.setHealth(character.getMaxHealth());
        healthPotion.useItem(character);
        assertEquals(character.getMaxHealth(), character.getHealth(),
                "Health should remain unchanged when already at max");
    }


    @Test
    public void testUseItemNearMaxHealth() {
        character.setHealth(69);
        healthPotion.useItem(character);
        assertEquals(character.getMaxHealth(), character.getHealth(),
                "Health should cap at max health when near the maximum");
    }
}