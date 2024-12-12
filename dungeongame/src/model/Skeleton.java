package dungeongame.src.model;

/**
 * Represents a Skeleton monster in the game.
 *
 * @version 1.0
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 */
public final class Skeleton extends AbstractMonster{

    /** Default name for a Skeleton monster. */
    private static final String DEFAULT_NAME = "Skeleton";

    /** Name of the specific Skeleton instance. */
    private final String myName;

    /**
     * Constructs a new Skeleton instance with the specified attributes.
     *
     * @param theMaxHealth    the maximum health of the Skeleton
     * @param theMinAttack    the minimum attack of the Skeleton
     * @param theMaxAttack    the maximum attack of the Skeleton
     * @param theSpeed        the speed of the Skeleton
     * @param theDefense      the defense value of the Skeleton
     * @param theItemDropRate the chance for the Skeleton to drop an item
     * @param theName         the name of the Skeleton
     */
    public Skeleton(final int theMaxHealth,
                    final int theMinAttack, final int theMaxAttack, final int theSpeed,
                    final int theDefense, final double theItemDropRate, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);
        myName = theName;
    }

    /**
     * generates a random item dropped by the skeleton.
     * @return the dropped item, or null if no item is dropped.
     */
    public Item getRandomItem() {
        final double randomNumber = Math.random() * 10;
        Item item = null;
        if (randomNumber > 1.99) {
            if (randomNumber < 4.01) {
                item = new HealthPotion();
            } else if(randomNumber < 6.01) {
                item = new SpeedPotion();
            } else {
                item = new VisionPotion();
            }
        }
        return item;
    }


    /**
     * Provides a string representation of the Skeleton.
     * @return a string of the skeletons name.
     */
    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}

