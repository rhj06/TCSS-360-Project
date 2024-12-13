package dungeongame.src.model;

/**
 * Represents a slime monster in the game.
 *
 * @version 1.0
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 *
 */
public final class Slime extends AbstractMonster{

    /** Default name for a Slime monster. */
    private static final String DEFAULT_NAME = "Slime";

    /** Name of the specific Slime instance. */
    private final String myName;

    /**
     * Constructs a new Slime instance with the specified attributes.
     *
     * @param theMaxHealth    the maximum health of the Slime
     * @param theMinAttack    the minimum attack of the Slime
     * @param theMaxAttack    the maximum attack of the Slime
     * @param theSpeed        the speed of the Slime
     * @param theDefense      the defense value of the Slime
     * @param theItemDropRate the chance for the Slime to drop an item
     * @param theName         the name of the Slime
     */
    public Slime(final int theMaxHealth,
                 final int theMinAttack, final int theMaxAttack, final int theSpeed,
                 final int theDefense, final double theItemDropRate, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);
        myName = theName;
    }

    /**
     * Generates a random item dropped by the Slime.
     * @return the dropped item, or null if no item is dropped.
     */
    @Override
    public Item getRandomItem() {
        final double randomNumber;
        randomNumber = Math.random() * 10;
        Item item;
        item = null;
        if (randomNumber > 1.99) {
            if (randomNumber < 4.01) {
                item = new SpeedPotion();
            } else if(randomNumber < 6.01) {
                item = new VisionPotion();
            } else {
                item = new HealthPotion();
            }
        }
        return item;
    }

    /**
     * Provides a string representation of the Slime.
     * @return a string of the slime's name
     */
    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
