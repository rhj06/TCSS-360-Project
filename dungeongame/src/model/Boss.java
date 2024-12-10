package dungeongame.src.model;

/**
 * Boss class extends Abstract Monster
 * Creates a boss monster
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class Boss extends AbstractMonster {

    /** the name of boss */
    private final String myName;
    /** the type of boss */
    private final String myType;

    /**
     * Boss class constructor
     * @param theMaxHealth the max health
     * @param theMinAttack the min attack
     * @param theMaxAttack the max attack
     * @param theSpeed the speed
     * @param theDefense the defense
     * @param theItemDropRate the item drop rate
     * @param theName the name
     * @param theType the type of boss
     */
    public Boss(final int theMaxHealth,
                final int theMinAttack, final int theMaxAttack, final int theSpeed,
                final int theDefense, final double theItemDropRate, final String theName, final String theType) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);

        myName = theName;
        myType = theType;
    }

    /**
     * Get type of boss
     * @return myType
     */
    public String getType() {
        return myType;
    }

    /**
     * Get random item
     * @return Health Potion
     */
    @Override
    public Item getRandomItem() {
        return new HealthPotion();
    }

    /**
     * toString of Boss
     * @return Name of Boss + " the " + Type of Boss
     */
    @Override
    public String toString() {
        return myName + " the " + myType;
    }
}
