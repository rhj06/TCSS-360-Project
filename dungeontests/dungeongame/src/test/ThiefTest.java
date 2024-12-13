package dungeongame.src.test;

import dungeongame.src.model.Ogre;
import dungeongame.src.model.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Thief} class.
 * Ensures correct behavior of methods and properties of the Thief character.
 */
public class ThiefTest {

    /** The speed bonus applied during the special attack. */
    private static final int SPEED_BONUS = 50;

    /** The Thief instance used for testing. */
    private Thief myThief;

    /**
     * The Ogre instance used as a target for the theif's special attack.
     */
    Ogre myOgre;

    /**
     * Sets up a new Thief instance before each test.
     */
    @BeforeEach
    public void setup() {
        myThief = new Thief(95, 15, 35, 8, 20, 20,
                "Robin Hood");
        myOgre = new Ogre(50, 5, 10, 3, 8, 2,
                "Gary");
    }

    /**
     * Tests the constructor of the Thief class.
     * Ensures fields are initialized correctly.
     */
    @Test
    public void testConstructor() {
        assertNotNull(myThief);
        assertEquals("Robin Hood the Thief", myThief.toString());
        assertEquals(95, myThief.getMaxHealth());
        assertEquals(20, myThief.getDefense());
    }

    /**
     * Tests the attack range of the Thief.
     * Ensures that attacks are within the specified range.
     */
    @Test
    public void testAttackRange() {
        for (int i = 0; i < 100; i++) {
            int theAttack = myThief.getAttack();
            assertTrue(theAttack >= 15 && theAttack <= 35);
        }
        System.out.println("Attack is " + myThief.getAttack());
    }

    /**
     * Tests the speed range of the Thief.
     * Ensures that speeds are within the specified range.
     */
    @Test
    public void testSpeedRange() {
        for (int i = 0; i < 100; i++) {
            int speed = myThief.getSpeed();
            assertTrue(speed >= 8 && speed <= 20);
        }
        System.out.println("Speed is " + myThief.getSpeed());
    }

    /**
     * Tests the {@code useSpecialAttack} method.
     * Ensures that speed is increased by the speed bonus.
     */
    @Test
    void testUseSpecialAttackIncreasesSpeed() {
        int minSpeed = 8;
        //System.out.println("Initial speed is: " + initialSpeed);
        myThief.useTargetedSpecialAttack(myOgre);
        int theBuffedSpeed = myThief.getSpeed();
        System.out.println("Buffed speed is: " + theBuffedSpeed);

        assertTrue(theBuffedSpeed >= minSpeed + SPEED_BONUS);
    }

    /**
     * Tests the chance to attack twice after using the special attack.
     * Ensures the probability of attacking twice is approximately 80%.
     */
    @Test
    void testUseSpecialAttackChanceToAttackTwice() {
        int myAttackTwiceCount = 0;
        int trials = 1000;
        for (int i = 0; i < trials; i++) {
            myThief.useSpecialAttack();
            if (myThief.canAttack(10)) {
                myAttackTwiceCount++;
            }
        }
        assertTrue(myAttackTwiceCount >= 0.75 * trials);
        System.out.println("able to attack twice with useSpecial(): " + myAttackTwiceCount * .1 + "% of the time");
    }

    /**
     * Tests the {@code ableToAttackTwice} method directly.
     * Validates the probability of attacking twice.
     */
    @Test
    void testAbleToAttackTwice() {
        int myAttackTwiceCount = 0;
        int trials = 1000;
        for (int i = 0; i < trials; i++) {
            if (myThief.canAttack(10)) {
                myAttackTwiceCount++;
            }
        }
        assertTrue(myAttackTwiceCount >= 0.75 * trials);
        System.out.println("able to attack twice: " + myAttackTwiceCount * .1 + "% of the time");
    }

    /**
     * Tests if thief is dead if health is zero.
     */
    @Test
    void testIsThiefDead() {
        myThief.setHealth(0);
        assertTrue(myThief.isDead());
    }

    /**
     * Tests if thief is not dead if warrior still has health.
     */
    @Test
    void testIsThiefDeadWithHealth() {
        myThief.setHealth(30);
        assertFalse(myThief.isDead());
    }
}