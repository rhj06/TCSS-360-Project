package dungeongame.src.model;

/**
 * Represents a Health Potion item in the dungeon adventure game.
 * When used, this potion restores a portion of the player's health.
 * It inherits common item properties from the AbstractItem class.
 *
 * @version 1.0
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public class HealthPotion extends AbstractItem {

    /**
     * Constructs a Health Potion with a predefined name, description, and value.
     * The Health Potion has a single use and the value represents the amount of health to be restored.
     */
    public HealthPotion() {

        super("Health Potion", "Restores health", 50, 1);
    }

    /**
     * Uses the Health Potion, restoring a portion of the player's health up to the max health.
     * This method is specific to the Health Potion's behavior in the game.
     *
     * @param theCharacter who will receive the health boost.
     */
    @Override
    public void useItem(final Character theCharacter) {
        if (theCharacter instanceof AbstractDungeonCharacter dungeonCharacter) {
            int healthBoost = getMyItemValue();
            dungeonCharacter.changeHealth(healthBoost);
        } else {
            throw new IllegalArgumentException("HealthPotion can only be used by a dungeon character.");
        }
    }

    /**
     * Returns a string representation of the Health Potion.
     *
     * @return The name of the potion.
     */
    @Override
    public String toString() {
        return getMyItemName();
    }
}
