package dungeongame.src.model;

/**
 * Represents an item that can be used in the dungeon adventure game.
 * Defines common behaviors for all items, such as getting the item's
 * name, description, value, and usage.
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
    String getMyItemName();

    /**
     * Provides a description of the item.
     *
     * @return a description of the item.
     */
    String getMyItemDescription();

    /**
     * Returns the value of the item, which may be used for amount of health to restore or amount speed increased.
     *
     * @return the value of the item.
     */
    int getMyItemValue();

    /**
     * Retrieves the current quantity of this item.
     *
     * @return the number of items available.
     */
    int getMyItemQuantity();

    /**
     * Checks if the player has this item.
     *
     * @return True if the player has the item, false otherwise.
     */
    boolean hasItem();

    /**
     * Defines the behavior when the item is used by the player
     *
     * @param
     */
    void useItem(Player thePlayer);
}
