package dungeongame.src.model;

public abstract class AbstractMonster extends AbstractDungeonCharacter {

    /***/
    private final double myItemDropRate;
    /***/
    private static final String DEFAULT_NAME = "Dungeon Character";

    public AbstractMonster(final int theMaxHealth,
                           final int theMinAttack, final int theMaxAttack, final int theSpeed,
                           final int theDefense, final double theItemDropRate, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theName);
        myItemDropRate = theItemDropRate;

    }

    public double getItemDropRate() {
        return myItemDropRate;
    }

    public boolean canHeal(){
        return Math.random() * 10 < 2.01;
    }
}
