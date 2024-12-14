package dungeongame.src.test;

import dungeongame.src.model.Boss;
import dungeongame.src.model.HealthPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing the {@link Boss} class.
 * <p>
 * This class includes tests for the Boss's attributes, random item generation,
 * string representation, and its health-related methods.
 * </p>
 */
class BossTest {

    /**
     * The Boss instance used for testing.
     */
    private Boss myTestBoss;

    /**
     * Initializes the Boss instance before each test.
     */
    @BeforeEach
    void setUp() {
        myTestBoss = new Boss(200, 15, 30, 10, 5, 0.5,
                "Sauron", "Dark Lord");
    }

    /**
     * Tests the Boss's attributes to ensure they are correctly initialized.
     */
    @Test
    void testConstructorAndAttributes() {
        assertEquals(200, myTestBoss.getMaxHealth(), "Max health should be 200");
        assertEquals(200, myTestBoss.getHealth(), "Initial health should be max health");
        assertEquals(10, myTestBoss.getSpeed(), "Speed should be 10");
        assertEquals(5, myTestBoss.getDefense(), "Defense should be 5");
        assertEquals(0.5, myTestBoss.getItemDropRate(), "Item drop rate should be 0.5");
    }

    /**
     * Tests the Boss's random attack range.
     */
    @Test
    public void testBossAttack() {
        assertNotNull(myTestBoss);
        for (int i = 0; i < 100; i++) {
            int attack = myTestBoss.getAttack();
            assertTrue(attack >= 15 && attack <= 30);
        }
        System.out.println("Boss attacked you for " + myTestBoss.getAttack() + " damage.");
    }

    /**
     * Verifies that the Boss drops the correct random item.
     */
    @Test
    void testGetRandomItem() {
        assertInstanceOf(HealthPotion.class, myTestBoss.getRandomItem(), "Boss should always drop a HealthPotion");
    }

    /**
     * Tests the string representation of the Boss.
     */
    @Test
    void testToString() {
        assertEquals("Sauron the Dark Lord", myTestBoss.toString(),
                "toString should correctly format name and type");
    }

    /**
     * Tests if the Boss is correctly identified as dead when health is zero.
     */
    @Test
    void testIsDead() {
        myTestBoss.setHealth(0);
        assertTrue(myTestBoss.isDead(), "Boss should be dead when health is 0");
    }
}