package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for testing the {@link Skeleton} class.
 * <p>
 * This class includes tests for if the monster can return an Item
 * </p>
 */
public class SkeletonTest {

    /**
     * Get Random Item from monster
     */
    @Test
    void getRandomItem() {
        Skeleton skeleton = new Skeleton(50, 5, 10, 7, 3, .03, "Test");
        Item item = skeleton.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}
