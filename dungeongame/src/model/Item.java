
/**
 * Represents an item that can be used in the dungeon adventure game.
 * Defines common behaviors for all items, such as getting the item's
 * name, description, value, and usage.
 *
 * Implementing classes must define how the item is used by the player.
 *
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public interface Item {

    /**
     * Retrieves the name of the item.
     *
     * @return the name of the item.
     */
    String getItemName();

    /**
     * Provides a description of the item.
     *
     * @return a description of the item.
     */
    String getItemDescription();

    /**
     * Returns the value of the item, which may be used for amount of health to restore or amount speed increased.
     *
     * @return the value of the item.
     */
    int getItemValue();

    /**
     * Retrieves the current quantity of this item.
     *
     * @return the number of items available.
     */
    int getItemquantity();

    /**
     * Checks if the player has this item.
     *
     * @return True if the player has the item, false otherwise.
     */
    boolean hasItem();

    /**
     * Defines the behavior when the item is used by the player
     *
     * @param thePlayer the player who is using the item.
     */
    void useItem(Player thePlayer);
}
