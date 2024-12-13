package dungeongame.src.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlimeTest {

    @Test
    void getRandomItem() {
        Slime slime = new Slime(50, 5, 10, 7, 3, .5, "Test");
        Item item = slime.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}