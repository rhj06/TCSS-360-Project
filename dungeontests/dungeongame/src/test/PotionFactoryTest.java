package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PotionFactory class to verify that potions are created correctly and handle invalid potion
 * types as expected. Tests HealthPotion, SpeedPotion, and VisionPotion.
 */
class PotionFactoryTest {

    /**
     * Tests that a HealthPotion is created with the expected properties.
     * Verifies that the potion is not null, is an instance of HealthPotion, and has the correct name, description,
     * value, and quantity.
     */
    @Test
    void testCreateHealthPotion() {
        AbstractItem potion = PotionFactory.createPotion("health");
        assertNotNull(potion);
        assertInstanceOf(HealthPotion.class, potion);
        assertEquals("Health Potion", potion.getMyItemName());
        assertEquals("Restores health", potion.getMyItemDescription());
        assertEquals(50, potion.getMyItemValue());
        assertEquals(1, potion.getMyItemQuantity());
    }

    /**
     * Tests that a SpeedPotion is created with the expected properties.
     * Verifies that the potion is not null, is an instance of SpeedPotion, and has the correct name, description,
     * value, and quantity.
     */
    @Test
    void testCreateSpeedPotion() {
        AbstractItem potion = PotionFactory.createPotion("speed");
        assertNotNull(potion);
        assertInstanceOf(SpeedPotion.class, potion);
        assertEquals("Speed Potion", potion.getMyItemName());
        assertEquals("Increases attack speed", potion.getMyItemDescription());
        assertEquals(1, potion.getMyItemValue());
        assertEquals(1, potion.getMyItemQuantity());
    }

    /**
     * Tests that a VisionPotion is created with the expected properties.
     * Verifies that the potion is not null, is an instance of VisionPotion, and has the correct name, description,
     * value, and quantity.
     */
    @Test
    void testCreateVisionPotion() {
        AbstractItem potion = PotionFactory.createPotion("vision");
        assertNotNull(potion);
        assertInstanceOf(VisionPotion.class, potion);
        assertEquals("Vision Potion", potion.getMyItemName());
        assertEquals("Reveals surrounding area", potion.getMyItemDescription());
        assertEquals(5, potion.getMyItemValue());
        assertEquals(1, potion.getMyItemQuantity());
    }

    /**
     * Tests the behavior of PotionFactory.createPotion when an invalid potion type is specified.
     * Expects an IllegalArgumentException to be thrown.
     */
    @Test
    void testCreatePotionWithUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> PotionFactory.createPotion("unknown"));
    }
}