package dungeongame.src.model;

public class MonsterFactory {

    public static AbstractMonster createMonster(final String theMonster, final int theMaxHealth, final int theMinAttack,
                                                final int theMaxAttack, final int theSpeed, final int theDefense,
                                                final String theName, final double theItemDropRate) {
        if (theMonster.equalsIgnoreCase("Goblin")) {
            return new Goblin(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName, theItemDropRate);
        } else if (theMonster.equalsIgnoreCase("Slime")) {
            return new Slime(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName, theItemDropRate);
        } else if (theMonster.equalsIgnoreCase("Skeleton")) {
            return new Skeleton(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName, theItemDropRate);
        } else if (theMonster.equalsIgnoreCase("Ogre")) {
            return new Ogre(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName, theItemDropRate);
        } else {
            throw new IllegalArgumentException("Invalid monster name: " + theMonster);
        }

    }

}
