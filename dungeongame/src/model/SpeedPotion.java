package dungeongame.src.model;

/**
 * Represents a Speed Potion item in the dungeon adventure game.
 * When used, this potion increases the player's attack speed.
 * It inherits common item properties from the AbstractItem class.
 *
 * @version 1.0
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 *
 */
public final class SpeedPotion extends AbstractItem  {

    /**
     * Constructs a Speed Potion with a predefined name, description, and value.
     * The Speed Potion has a single use and temporarily increases attack speed.
     */
    public SpeedPotion() {

        super("Speed Potion", "Increases attack speed", 1, 1);
    }

    /**
     * Uses the Speed Potion, granting a temporary speed boost to the player.
     * This method is specific to the Speed Potion's behavior in the game.
     *
     * @param theCharacter who will receive the speed boost.
     */
    @Override
    public void useItem(final Character theCharacter) {
        //int newSpeed = theCharacter.getSpeed() + getMyItemValue();
        theCharacter.setSpeed(getMyItemValue());
    }

    /**
     * Returns a string representation of the Speed Potion.
     *
     * @return The name of the potion.
     */
    @Override
    public String toString() {
        return getMyItemName();
        }
    }
