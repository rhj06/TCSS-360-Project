package dungeongame.src.model;

/**
 * Represents an Ogre monster in the game.
 *
 * @version 1.0
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 *
 */
public final class Ogre extends AbstractMonster{

    /** Default name for an Ogre monster. */
    private static final String DEFAULT_NAME = "Ogre";

    /** Name of the specific Ogre monster. */
    private final String myName;

    /**
     * Constructs a new Ogre instance with the specified attributes.
     *
     * @param theMaxHealth    the maximum health of the Ogre
     * @param theMinAttack    the minimum attack of the Ogre
     * @param theMaxAttack    the maximum attack of the Ogre
     * @param theSpeed        the speed of the Ogre
     * @param theDefense      the defense value of the Ogre
     * @param theItemDropRate the chance for the Ogre to drop an item
     * @param theName         the name of the Ogre
     */
    public Ogre(final int theMaxHealth,
                final int theMinAttack, final int theMaxAttack, final int theSpeed,
                final int theDefense, final double theItemDropRate, final String theName) {
        super(theMaxHealth, theMinAttack, theMaxAttack, theSpeed, theDefense, theItemDropRate, theName);
        myName = theName;
    }

    /**
     * Generates a random item dropped by the Ogre.
     * @return the dropped item
     */
    @Override
    public Item getRandomItem() {
        final double randomNumber = Math.random() * 10;
        Item item;
            if (randomNumber < 2.01) {
                item = new VisionPotion();
            } else if(randomNumber < 4.01) {
                item = new SpeedPotion();
            } else {
                item = new HealthPotion();
            }
        return item;
    }

    /**
     * Provides a string representation of the Ogre.
     * @return a string of the Ogre's name
     */
    @Override
    public String toString() {
        return myName + " the " + DEFAULT_NAME;
    }
}
