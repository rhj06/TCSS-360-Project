package dungeongame.src.test;

import dungeongame.src.model.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Warrior} class.
 * This class validates the functionality of the Warrior's attributes, methods, and special attack behavior.
 * The tests ensure that the Warrior class behaves as expected under various scenarios.
 *
 */
public class WarriorTest {

    /** Instance of Warrior used for testing. */
    private Warrior myWarrior;

    /**
     * Sets up a Warrior instance before each test.
     */
    @BeforeEach
    public void setup() {
        myWarrior = new Warrior(140, 30, 50, 4, 12, 20,
                "Geralt");
    }

    /**
     * Tests the Warrior constructor and basic attribute initialization.
     */
    @Test
    public void testWarrior() {
        assertNotNull(myWarrior);
        assertEquals("Geralt the Warrior", myWarrior.toString());
        assertEquals(140, myWarrior.getMaxHealth());
        assertEquals(20, myWarrior.getDefense());
    }

    /**
     * Tests that the Warrior's attack value is within the expected range.
     * Runs multiple iterations to account for randomness in attack calculation.
     */
    @Test
    public void testWarriorAttack() {
        assertNotNull(myWarrior);
        for (int i = 0; i < 100; i++) {
            int attack = myWarrior.getAttack();
            assertTrue(attack >= 30 && attack <= 50);
        }
        System.out.println("You attacked for " + myWarrior.getAttack() + " damage.");
    }

    /**
     * Tests that the Warrior's speed value is within the expected range.
     * Runs multiple iterations to account for randomness in speed calculation.
     */
    @Test
    public void testWarriorSpeed() {
        assertNotNull(myWarrior);
        for (int i = 0; i < 100; i++) {
            int speed = myWarrior.getSpeed();
            assertTrue(speed >= 4 && speed <= 12);
        }
        System.out.println("You gained " + myWarrior.getSpeed() + " speed.");
    }

    /**
     * Tests the effects of the Warrior's special attack on attack values.
     * Validates that bonuses are correctly applied and resets to default after one (or however many) turn.
     */
    @Test
    public void testUseSpecialAttack() {
        int baseAttack = 30;
        System.out.println("initial attack for: " + baseAttack);
        myWarrior.useSpecialAttack();
       int boostedAttack = myWarrior.getAttack();
        System.out.println("boosted attacked for: " + boostedAttack);
        assertTrue(boostedAttack > baseAttack + 50);
    }

    /**
     * Tests the effects of the Warrior's special attack on defense values.
     * Validates that bonuses are correctly applied and resets to default after one (or however many) turn.
     */
    @Test
    public void testUseSpecialAttackDefenseBoost() {
        assertEquals(20, myWarrior.getDefense(), "Defense should start at 20.");
        myWarrior.useSpecialAttack();
        assertEquals(70, myWarrior.getDefense(), "Defense should increase by 50 for one turn.");
        assertEquals(20, myWarrior.getDefense(), "Defense should reset to 20 after the bonus is used.");
    }

    /**
     * Tests the string representation of the Warrior class.
     * Validates the format of the name and title.
     */
    @Test
    public void testToString() {
        assertEquals("Geralt the Warrior", myWarrior.toString());
    }

    /**
     * Tests default behavior of the Warrior class with a new instance.
     * Validates that the default Warrior is correctly initialized.
     */
    @Test
    public void testDefaultValues() {
        Warrior defaultWarrior = new Warrior(100, 10, 20, 5, 15,
                30, "Default");
        assertEquals("Default the Warrior", defaultWarrior.toString());
    }

    /**
     * Tests if warrior is dead if health is zero.
     */
    @Test
    void testIsWarriorDead() {
        myWarrior.setHealth(0);
        assertTrue(myWarrior.isDead());
    }

    /**
     * Tests if warrior is not dead if warrior still has health.
     */
    @Test
    void testIsWarriorDeadWithHealth() {
        myWarrior.setHealth(30);
        assertFalse(myWarrior.isDead());
    }
}