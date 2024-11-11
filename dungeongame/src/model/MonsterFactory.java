package dungeongame.src.model;

import java.util.Random;

public final class MonsterFactory {

    private static final String[] NAMES = {
            "Alduin", "Baalor", "Cthara", "Draxis", "Eldar", "Todd", "Gorlak",
            "Hexar", "Kaleb", "Jareth", "Kaelis", "Lazrak", "Mordred", "Nyx",
            "Oberon", "David", "Professor Tom", "Ragnar", "Sargoth", "Thalor", "Ulric",
            "Vexor", "Wulfric", "Xargon", "Jeff", "Zalrok", "Zephyr", "Rynor",
            "Ryan", "Malakar"};

    public static AbstractMonster createMonster(final String theMonster, final int theMaxHealth, final int theMinAttack,
                                                final int theMaxAttack, final int theSpeed, final int theDefense,
                                                final double theItemDropRate) {
        if (theMonster.equalsIgnoreCase("goblin")) {
            return new Goblin(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("slime")) {
            return new Slime(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("skeleton")) {
            return new Skeleton(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("ogre")) {
            return new Ogre(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else {
            throw new IllegalArgumentException("Invalid monster name: " + theMonster);
        }

    }

    private static String getRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }

}
