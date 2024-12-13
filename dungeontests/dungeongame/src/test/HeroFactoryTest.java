package dungeongame.src.test;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.HeroFactory;
import dungeongame.src.model.Player;
import dungeongame.src.model.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroFactoryTest {

    private HeroFactory heroFactory;

    @BeforeEach
    void setUp() {
        heroFactory = new HeroFactory();
    }

    @Test
    void createThief() {
        AbstractDungeonCharacter thief = (AbstractDungeonCharacter) HeroFactory.createHero("thief",
                100, 30, 40, 12, 20, 8, "Ryan");
        assertNotNull(thief);
        assertEquals(100, thief.getHealth());
        assertTrue(12 <= thief.getSpeed() && 20 >= thief.getSpeed());
        //assertEquals(20, thief.getSpeed());
        System.out.println(thief);
    }

    @Test
    void createWarrior() {
        AbstractDungeonCharacter warrior = (AbstractDungeonCharacter) HeroFactory.createHero("warrior",
                140, 30, 50, 4, 12, 20, "David");
        assertNotNull(warrior);
        assertEquals(140, warrior.getHealth());
        assertTrue(4 <= warrior.getSpeed() && 12 >= warrior.getSpeed());
        //assertEquals(4, warrior.getSpeed());
        System.out.println(warrior);
    }

    @Test
    void createWizard() {
        AbstractDungeonCharacter wizard = (AbstractDungeonCharacter) HeroFactory.createHero("wizard",
                200, 15, 50, 8, 15, 15, "Kaleb");
        assertNotNull(wizard);
        assertEquals(200, wizard.getHealth());
        assertTrue(8 <= wizard.getSpeed() && 15 >= wizard.getSpeed());
        //assertEquals(8, wizard.getSpeed());
        System.out.println(wizard);
    }

    @Test
    void invalidName() {
        String invalidHeroName = "Dragon Slayer";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            HeroFactory.createHero(invalidHeroName, 999, 99,
                    999, 99, 99, 99,"Jeff");
        });

        assertEquals("Invalid monster name: " + invalidHeroName, e.getMessage());

    }
}