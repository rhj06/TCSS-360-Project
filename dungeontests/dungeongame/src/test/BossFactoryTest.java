package dungeongame.src.test;

import dungeongame.src.model.AbstractMonster;
import dungeongame.src.model.Boss;
import dungeongame.src.model.BossFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BossFactory} class.
 * This class ensures that the BossFactory correctly creates Mini Bosses, Final Bosses,
 * and handles invalid inputs. It also verifies the attributes and behaviors of the created bosses.
 */
class BossFactoryTest {

    /**
     * The factory used to create bosses during testing.
     */
    private BossFactory myBossFactory;

    /**
     * Initializes the {@link BossFactory} instance before each test.
     */
    @BeforeEach
    void setUp() {
        myBossFactory = new BossFactory();
    }

    /**
     * Tests the creation of a Mini Boss and verifies its attributes.
     */
    @Test
    void testCreateMiniBoss() {
        AbstractMonster miniBoss = myBossFactory.createBoss("Mini Boss");

        assertNotNull(miniBoss, "Mini Boss should not be null.");
        assertTrue(miniBoss.toString().contains("Lich"), "Mini Boss type should contain 'Lich'.");
        assertNotEquals("", miniBoss.toString(), "Mini Boss name should not be empty.");
        assertEquals(150, miniBoss.getMaxHealth(), "Mini Boss max health should be 150.");
        assertEquals(10, miniBoss.getDefense(), "Mini Boss defense should be 10.");
        assertEquals(.5, miniBoss.getItemDropRate(), "Mini Boss item drop rate should be 0.5.");

        System.out.println(miniBoss);
    }

    /**
     * Tests that the Mini Boss's attack is always within the range of 15 to 25.
     */
    @Test
    void testMiniBossAttackRange() {
        AbstractMonster miniBoss = myBossFactory.createBoss("Mini Boss");

        assertNotNull(miniBoss);

        for (int i = 0; i < 100; i++) {
            int attack = miniBoss.getAttack();
            assertTrue(attack >= 15 && attack <= 25);
        }
        System.out.println("Attacked for: " + miniBoss.getAttack() + " damage.");
    }

    /**
     * Tests the creation of a Final Boss and verifies its attributes.
     */
    @Test
    void testCreateFinalBoss() {
        AbstractMonster finalBoss = myBossFactory.createBoss("Final Boss");

        assertNotNull(finalBoss);
        assertTrue(finalBoss.toString().contains("Dragon"));
        assertNotEquals("", finalBoss.toString());
        assertEquals(200, finalBoss.getMaxHealth());
        assertEquals(15, finalBoss.getDefense());
        assertEquals(.00001, finalBoss.getItemDropRate());

        System.out.println(finalBoss);
    }

    /**
     * Tests that the Final Boss's attack is always within the range of 20 to 35.
     */
    @Test
    void testFinalBossAttackRange() {
        AbstractMonster finalBoss = myBossFactory.createBoss("Final Boss");

        assertNotNull(finalBoss);

        for (int i = 0; i < 100; i++) {
            int attack = finalBoss.getAttack();
            assertTrue(attack >= 20 && attack <= 35);
        }
        System.out.println("Attacked for: " + finalBoss.getAttack() + " damage.");
    }

    /**
     * Tests that an invalid boss type returns null.
     */
    @Test
    void testCreateBossInvalidType() {
        AbstractMonster invalidBoss = myBossFactory.createBoss("Unknown Type");

        assertNull(invalidBoss);
    }

    /**
     * Tests that the BossFactory assigns random names to created bosses,
     * ensuring two bosses have different names.
     */
    @Test
    void testRandomNameAssignment() {
        AbstractMonster boss1 = myBossFactory.createBoss("Mini Boss");
        AbstractMonster boss2 = myBossFactory.createBoss("Mini Boss");

        assertNotNull(boss1);
        assertNotNull(boss2);
        assertNotEquals(boss1.toString(), boss2.toString());
        System.out.println(boss1 + " and " + boss2);
    }

    /**
     * Tests that the {@link Boss#getRandomItem()} method returns a valid item
     * ('none', 'health', 'speed', or 'vision').
     */
    @Test
    void testBossRandomItemDrop() {
        AbstractMonster boss = myBossFactory.createBoss("Final Boss");
        assertNotNull(boss);

        String randomItem = ((Boss) boss).getRandomItem();
        assertTrue(randomItem.equals("none") || randomItem.equals("health")
                || randomItem.equals("speed") || randomItem.equals("vision"));

        System.out.println(randomItem);
    }
}
