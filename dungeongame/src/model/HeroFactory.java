package dungeongame.src.model;

public final class HeroFactory {

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
