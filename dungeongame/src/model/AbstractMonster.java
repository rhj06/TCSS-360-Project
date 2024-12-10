package dungeongame.src.model;

/**
 * Abstract monster class for the foundation of all monsters created
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public abstract class AbstractMonster extends AbstractDungeonCharacter {

    /** Monsters speed */
    private final int mySpeed;
    /** Monsters item drop rate */
    private final double myItemDropRate;

    /**
     * Abstract Monster constructor
     * @param theMaxHealth the max health
     * @param theMinAttack the min attack
     * @param theMaxAttack the max attack
     * @param theSpeed the speed
     * @param theDefense the defence
     * @param theItemDropRate the item drop rate
     * @param theName the name
     */
    public AbstractMonster(final int theMaxHealth,
                           final int theMinAttack, final int theMaxAttack, final int theSpeed,
                           final int theDefense, final double theItemDropRate, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theSpeed, theDefense, theName);
        myItemDropRate = theItemDropRate;
        mySpeed = theSpeed;

    }

    /**
     * Monsters only have 1 speed variable
     * @return monster speed
     */
    @Override
    public int getSpeed(){
        return mySpeed;
    }

    /**
     * monsters have a chance to drop random item
     * @return Item
     */
    public Item getRandomItem() {
        return null;
    }

    /**
     * Monster drop rate for item
     * @return double value
     */
    public double getItemDropRate() {
        return myItemDropRate;
    }

    /**
     * can monster to heal?
     * @return true or false
     */
    public boolean canHeal(){
        return Math.random() * 10 < 2.01;
    }
}
