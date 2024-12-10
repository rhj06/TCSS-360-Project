package dungeongame.src.model;

import java.util.Random;

/**
 * Boss Factory for creating Boss Objects
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class BossFactory {

    /** Mini boss type */
    private static final String MINI_BOSS_TYPE = "Lich";
    /** Final boss type */
    private static final String FINAL_BOSS_TYPE = "Dragon";
    /** Array of possible boss names */
    private static final String[] NAMES = {
            "Alduin", "Baalor", "Cthara", "Draxis", "Eldar", "Todd", "Gorlak",
            "Hexar", "Kaleb", "Jareth", "Kaelis", "Lazrak", "Mordred", "Nyx",
            "Oberon", "David", "Professor Tom", "Ragnar", "Sargoth", "Thalor", "Ulric",
            "Vexor", "Wulfric", "Xargon", "Jeff", "Zalrok", "Zephyr", "Rynor",
            "Ryan", "Malakar"};

    /***
     * Creates a boss based on the string parameter
     * @param theType Type of Boss
     * @return Boss Object
     */
    public Boss createBoss(final String theType){
        Boss boss = null;
        Random rand = new Random();

        if(theType.equals("mini_boss")){
            boss = new Boss(150, 15, 25, 7, 10, .99,
                    NAMES[rand.nextInt(NAMES.length)], MINI_BOSS_TYPE);
        } else if(theType.equals("final_boss")){
            boss = new Boss(200, 20, 35, 10, 15, .00001,
                    NAMES[rand.nextInt(NAMES.length)], FINAL_BOSS_TYPE);
        }
        return boss;
    }
}
