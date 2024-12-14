package dungeongame.src.test;

import dungeongame.src.model.AbstractMonster;
import dungeongame.src.model.MonsterFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing the {@link MonsterFactory} class.
 * <p>
 * This class includes tests for MonsterFactory
 * </p>
 */
class MonsterFactoryTest {

    /**
     * create a goblin
     */
    @Test
    void createGoblin() {
        final AbstractMonster goblin = MonsterFactory.createMonster("Goblin");
        assertNotNull(goblin);
        assertEquals(50, goblin.getHealth());
        assertEquals(7, goblin.getSpeed());
    }

    /**
     * create a slime
     */
    @Test
    void createSlime() {
        final AbstractMonster slime = MonsterFactory.createMonster("Slime");
        assertNotNull(slime);
        assertEquals(40, slime.getHealth());
        assertEquals(4, slime.getSpeed());
    }

    /**
     * create a skeleton
     */
    @Test
    void createSkeleton() {
        final AbstractMonster skeleton = MonsterFactory.createMonster("Skeleton");
        assertNotNull(skeleton);
        assertEquals(70, skeleton.getHealth());
        assertEquals(6, skeleton.getSpeed());
    }

    /**
     * create an ogre
     */
    @Test
    void createOgre() {
        final AbstractMonster ogre = MonsterFactory.createMonster("Ogre");
        assertNotNull(ogre);
        assertEquals(120, ogre.getHealth());
        assertEquals(5, ogre.getSpeed());
    }

    /**
     * test for invalid monster input
     */
    @Test
    void invalidName() {
        String invalidMonsterName = "Zombie";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            MonsterFactory.createMonster(invalidMonsterName);
        });

        assertEquals("Invalid monster name: " + invalidMonsterName, e.getMessage());

    }
}//