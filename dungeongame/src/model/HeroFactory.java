package dungeongame.src.model;

public final class HeroFactory {

    public static AbstractDungeonCharacter createHero(final String theHero, final int theMaxHealth,
                                                      final int theMinAttack, final int theMaxAttack,
                                                      final int theSpeed, final int theDefense, final String theName) {
        if (theHero.equalsIgnoreCase("thief")) {
            return new Thief(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName);
        } else if (theHero.equalsIgnoreCase("warrior")) {
            return new Warrior(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName);
        } else if (theHero.equalsIgnoreCase("wizard")) {
            return new Wizard(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName);
        } else {
            throw new IllegalArgumentException("Invalid monster name: " + theHero);
        }
    }
}
