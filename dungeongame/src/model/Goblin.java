package dungeongame.src.model;

/**
 * Goblin Monster Class
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class Goblin extends AbstractMonster{

    /** Name of goblin */
    private final String myName;

    /**
     * Goblin constructor
     * @param theMaxHealth the max health
     * @param theMinAttack the min attack
     * @param theMaxAttack the max attack
     * @param theSpeed the speed
     * @param theDefense the defence
     * @param theItemDropRate the item drop rate
     * @param theName the name of Goblin
     */
    public Goblin(final int theMaxHealth,
                  final int theMinAttack, final int theMaxAttack, final int theSpeed,
                  final int theDefense, final double theItemDropRate, final String theName) {

        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);
        myName = theName;
    }

    /**
     * Get Random Item
     * 40% chance for a VisionPotion
     * 30% chance for a SpeedPotion
     * 30% chance for a HealthPotion
     * @return Item
     */
    @Override
    public Item getRandomItem() {
        final double randomNumber = Math.random() * 10;
        Item item;
        if (randomNumber < 4.01) {
            item = new VisionPotion();
        } else if(randomNumber < 7.01) {
            item = new SpeedPotion();
        } else {
            item = new HealthPotion();
        }
        return item;
    }

    /**
     * toString of Goblin
     * @return Name + " the " + Goblin
     */
    @Override
    public String toString() {
        return myName + " the Goblin";
    }
}
