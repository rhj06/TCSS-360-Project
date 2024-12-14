package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing the {@link Slime} class.
 * <p>
 * This class includes tests for if the monster can return an Item
 * </p>
 */
class SlimeTest {

    /**
     * Get Random Item from monster
     */
    @Test
    void getRandomItem() {
        Slime slime = new Slime(50, 5, 10, 7, 3, .5, "Test");
        Item item = slime.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}