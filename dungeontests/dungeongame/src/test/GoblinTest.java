package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    @Test
    void getRandomItem() {
        Goblin goblin = new Goblin(50, 5, 10, 7, 3, .03, "Test");
        Item item = goblin.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}