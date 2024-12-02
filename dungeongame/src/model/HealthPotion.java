package dungeongame.src.model;

/**
 * Represents a Health Potion item in the dungeon adventure game.
 * When used, this potion restores a portion of the player's health.
 * It inherits common item properties from the AbstractItem class.
 *
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public class HealthPotion extends AbstractItem {

    /**
     * Constructs a Health Potion with a predefined name, description, and value.
     * The Health Potion has a single use and the value represents the amount of health to be restored.
     */
    public HealthPotion() {

        super("Health Potion", "Restores health", 20, 1);
    }

    /**
     * Uses the Health Potion, restoring a portion of the player's health up to the max health.
     * This method is specific to the Health Potion's behavior in the game.
     *
     * @param theCharacter who will receive the health boost.
     */
    @Override
    public void useItem(Character theCharacter) {
        int newHealth = theCharacter.getHealth() + getMyItemValue();
        theCharacter.setHealth(newHealth);
    }

    /**
     * Returns a string representation of the Health Potion.
     *
     * @return The name of the potion (e.g., "Health Potion").
     */
    @Override
    public String toString() {
        return getMyItemName();
    }
}
