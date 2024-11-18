package dungeongame.src.test;

import dungeongame.src.model.SpeedPotion;
import dungeongame.src.model.Character;
import dungeongame.src.model.Thief;  // Example concrete Character class
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpeedPotionTest {

    /** The SpeedPotion instance under test. */
    private SpeedPotion speedPotion;

    /** The Character instance to be used in the tests. */
    private Character character;

    /**
     * Sets up the test environment before each test case.
     * Creates a SpeedPotion instance and initializes a Thief character with default attributes.
     * The Thief starts with a speed of 15.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        speedPotion = new SpeedPotion();
        character = new Thief(70, 50, 10, 15, 10, "Test Thief"); // Speed = 15
    }

    /**
     * Tests that using the SpeedPotion increases the character's speed by the potion's value.
     */
    @Test
    public void testUseItemIncreasesSpeed() {
        speedPotion.useItem(character);

        assertEquals(20, character.getSpeed());
    }

    /**
     * Tests that using the SpeedPotion multiple times applies cumulative speed increases.
     */
    @Test
    public void testUseItemMultipleTimes() {
        speedPotion.useItem(character);
        speedPotion.useItem(character);

        assertEquals(25, character.getSpeed());
    }

    /**
     * Tests that using the SpeedPotion correctly increases speed for a character
     * with a negative starting speed.
     */
    @Test
    public void testUseItemOnNegativeSpeed() {
        character.setSpeed(-10);
        speedPotion.useItem(character);

        assertEquals(-5, character.getSpeed());
    }

    /**
     * Tests that using the SpeedPotion correctly increases speed for a character
     * with a starting speed of zero.
     */
    @Test
    public void testUseItemOnZeroSpeed() {
        character.setSpeed(0);
        speedPotion.useItem(character);

        assertEquals(5, character.getSpeed());
    }
}
