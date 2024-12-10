package dungeongame.src.model;

/**
 * Hero Factory for creating Thief, Warrior, and Wizard objects
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class HeroFactory {

    /**
     * Create a Hero Object
     * @param theHero String of Hero
     * @param theMaxHealth the max health
     * @param theMinAttack the min attack
     * @param theMaxAttack the max attack
     * @param theMinSpeed the min speed
     * @param theMaxSpeed the max speed
     * @param theDefense the defence
     * @param theName the name
     * @return AbstractDungeonCharacter
     */
    public static AbstractDungeonCharacter createHero(final String theHero, final int theMaxHealth,
                                                      final int theMinAttack, final int theMaxAttack,
                                                      final int theMinSpeed, final int theMaxSpeed, final int theDefense, final String theName) {
        if (theHero.equalsIgnoreCase("thief")) {
            return new Thief(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
        } else if (theHero.equalsIgnoreCase("warrior")) {
            return new Warrior(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
        } else if (theHero.equalsIgnoreCase("wizard")) {
            return new Wizard(theMaxHealth, theMinAttack, theMaxAttack, theMinSpeed, theMaxSpeed, theDefense, theName);
        } else {
            throw new IllegalArgumentException("Invalid monster name: " + theHero);
        }
    }
}
