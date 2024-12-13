package dungeongame.src.test;

import dungeongame.src.model.AbstractMonster;
import dungeongame.src.model.MonsterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MonsterFactoryTest {


    @Test
    void createGoblin() {
        final AbstractMonster goblin = MonsterFactory.createMonster("Goblin");
        assertNotNull(goblin);
        assertEquals(50, goblin.getHealth());
        assertEquals(7, goblin.getSpeed());
    }

    @Test
    void createSlime() {
        final AbstractMonster slime = MonsterFactory.createMonster("Slime");
        assertNotNull(slime);
        assertEquals(40, slime.getHealth());
        assertEquals(4, slime.getSpeed());
    }

    @Test
    void createSkeleton() {
        final AbstractMonster skeleton = MonsterFactory.createMonster("Skeleton");
        assertNotNull(skeleton);
        assertEquals(70, skeleton.getHealth());
        assertEquals(6, skeleton.getSpeed());
    }

    @Test
    void createOgre() {
        final AbstractMonster ogre = MonsterFactory.createMonster("Ogre");
        assertNotNull(ogre);
        assertEquals(120, ogre.getHealth());
        assertEquals(5, ogre.getSpeed());
    }

    @Test
    void invalidName() {
        String invalidMonsterName = "Zombie";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            MonsterFactory.createMonster(invalidMonsterName);
        });

        assertEquals("Invalid monster name: " + invalidMonsterName, e.getMessage());

    }
}//