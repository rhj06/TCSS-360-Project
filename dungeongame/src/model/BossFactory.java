package dungeongame.src.model;

import java.util.Random;

public class BossFactory {
    private static final String MINI_BOSS_TYPE = "Lich";
    private static final String FINAL_BOSS_TYPE = "Dragon";

    private static final String[] NAMES = {
            "Alduin", "Baalor", "Cthara", "Draxis", "Eldar", "Todd", "Gorlak",
            "Hexar", "Kaleb", "Jareth", "Kaelis", "Lazrak", "Mordred", "Nyx",
            "Oberon", "David", "Professor Tom", "Ragnar", "Sargoth", "Thalor", "Ulric",
            "Vexor", "Wulfric", "Xargon", "Jeff", "Zalrok", "Zephyr", "Rynor",
            "Ryan", "Malakar"};

    public AbstractMonster createBoss(String theString){
        AbstractMonster boss = null;
        Random rand = new Random();

        if(theString.equals("Mini Boss")){
            boss = new Boss(150, 15, 25, 7, 10, .5,
                    NAMES[rand.nextInt(NAMES.length)], MINI_BOSS_TYPE);
        } else if(theString.equals("Final Boss")){
            boss = new Boss(200, 20, 35, 10, 15, .00001,
                    NAMES[rand.nextInt(NAMES.length)], FINAL_BOSS_TYPE);
        }
        return boss;
    }
}
