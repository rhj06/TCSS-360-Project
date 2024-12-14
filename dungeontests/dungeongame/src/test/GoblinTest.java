package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing the {@link Goblin} class.
 * <p>
 * This class includes tests for if the monster can return an Item
 * </p>
 */
class GoblinTest {

    /**
     * Get Random Item from monster
     */
    @Test
    void getRandomItem() {
        Goblin goblin = new Goblin(50, 5, 10, 7, 3, .03, "Test");
        Item item = goblin.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}