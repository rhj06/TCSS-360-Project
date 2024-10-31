package dungeongame.src.model;

/**
 *
 *
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public interface Player {
    /**
     * Checks if the player is alive based on health points.
     *
     * @return true if health is greater than 0, false otherwise.
     */
    boolean isAlive();

    /**
     * Gets the player's current health points.
     *
     * @return the current health points.
     */
    int getMyHealth();

    /**
     * Sets the player's health points to a specified value.
     *
     * @param theHealth the new health point value.
     */
    void setMyHealth(int theHealth);

    /**
     * Adds an item to the player's inventory.
     *
     * @param item the item to add.
     */
    void addItem(Item item);

    /**
     * Uses an item from the player's inventory by name.
     *
     * @param theItem the item to use.
     */
    void useItem(Item theItem);
}
