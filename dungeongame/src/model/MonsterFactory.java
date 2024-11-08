package dungeongame.src.model;

import java.util.Random;

public class MonsterFactory {

    private static final String[] NAMES = {
            "Alduin", "Baalor", "Cthara", "Draxis", "Eldar", "Todd", "Gorlak",
            "Hexar", "Kaleb", "Jareth", "Kaelis", "Lazrak", "Mordred", "Nyx",
            "Oberon", "David", "Quoron", "Ragnar", "Sargoth", "Thalor", "Ulric",
            "Vexor", "Wulfric", "Xargon", "Jeff", "Zalrok", "Zephyr", "Rynor",
            "Ryan", "Malakar"};

    public static AbstractMonster createMonster(final String theMonster, final int theMaxHealth, final int theMinAttack,
                                                final int theMaxAttack, final int theSpeed, final int theDefense,
                                                final double theItemDropRate) {
        if (theMonster.equalsIgnoreCase("Goblin")) {
            return new Goblin(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("Slime")) {
            return new Slime(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("Skeleton")) {
            return new Skeleton(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("Ogre")) {
            return new Ogre(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, getRandomName());
        } else {
            throw new IllegalArgumentException("Invalid monster name: " + theMonster);
        }

    }

    private static String getRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }

}
