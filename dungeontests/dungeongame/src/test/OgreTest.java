package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OgreTest {

    @Test
    void getRandomItem() {
        Ogre ogre = new Ogre(50, 5, 10, 7, 3, .5, "Test");
        Item item = ogre.getRandomItem();
        boolean check = item instanceof HealthPotion || item instanceof VisionPotion || item instanceof SpeedPotion;
        assertTrue(check);
    }
}