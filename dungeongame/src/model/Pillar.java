package dungeongame.src.model;

/**
 * Represents a pillar item in the game.
 *
 * @version 1.0
 * @author Kaleb Anagnostou, David Bessex, Ryan Johnson
 *
 */
public final class Pillar extends AbstractItem{

    /**
     * Constructs a pillar with the specified name.
     *
     * @param theName the name of the pillar.
     */
    public Pillar(final String theName){
        super(theName, null, 0, 1);
    }
}
