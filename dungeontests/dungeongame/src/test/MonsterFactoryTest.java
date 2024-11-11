package dungeongame.src.test;

import dungeongame.src.model.AbstractMonster;
import dungeongame.src.model.MonsterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MonsterFactoryTest {
    private MonsterFactory monsterFactory;
    @BeforeEach
    void setUp() {
        monsterFactory = new MonsterFactory();
    }

    @Test
    void createGoblin() {
        final AbstractMonster goblin = MonsterFactory.createMonster("goblin", 80, 14,
                20,20,13,10.0);
        assertNotNull(goblin);
        assertEquals(80, goblin.getHealth());
        assertEquals(20, goblin.getSpeed());
        System.out.println(goblin);
    }

    @Test
    void createSlime() {
        final AbstractMonster slime = MonsterFactory.createMonster("slime", 40, 8,
                18,10,5,10.0);
        assertNotNull(slime);
        assertEquals(40, slime.getHealth());
        assertEquals(10, slime.getSpeed());
        System.out.println(slime);
    }

    @Test
    void createSkeleton() {
        final AbstractMonster skeleton = MonsterFactory.createMonster("Skeleton", 60, 20,
                25,30,1,10.0);
        assertNotNull(skeleton);
        assertEquals(60, skeleton.getHealth());
        assertEquals(30, skeleton.getSpeed());
        System.out.println(skeleton);
    }

    @Test
    void createOgre() {
        final AbstractMonster ogre = MonsterFactory.createMonster("Ogre", 100, 20,
                40,5,30,10.0);
        assertNotNull(ogre);
        assertEquals(100, ogre.getHealth());
        assertEquals(5, ogre.getSpeed());
        System.out.println(ogre);
    }

    @Test
    void invalidName() {
        String invalidMonsterName = "Zombie";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            monsterFactory.createMonster(invalidMonsterName, 100, 20,
                    30, 2, 20, 10.0);
        });

        assertEquals("Invalid monster name: " + invalidMonsterName, e.getMessage());

    }
}