package dungeongame.src.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {
    
    @Test
    void getRandomItem() {
        Skeleton skel = new Skeleton(50, 5, 10, 7, 3, .5, "Test");
        Item item = skel.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}