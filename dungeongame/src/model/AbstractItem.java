package dungeongame.src.model;

/**
 * The AbstractItem class provides a common foundation for all items in the
 * dungeon adventure game. It implements the Item interface and defines
 * shared behavior such as retrieving item information (name, description, value, count)
 * and checking whether the player possesses the item.
 *
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 */
public abstract class AbstractItem implements Item {

    /** The name of the item. */
    private final String myItemName;

    /** The description/type of the item. */
    private final String myItemDescription;

    /** The value of the item to be used for amount of health restored or amount of attack speed increased. */
    private final int myItemValue;

    /** The number of this item available. */
    private final int myItemQuantity;

    /**
     * Constructs an AbstractItem with the specified name, description, value,
     * and item quantity.
     *
     * @param theItemName The name of the item.
     * @param theItemDescription A description of the item.
     * @param theItemValue The value of the item.
     * @param theItemQuantity The initial number of this item.
     */
    public AbstractItem(String theItemName, String theItemDescription, int theItemValue, int theItemQuantity) {
        myItemName = theItemName;
        myItemDescription = theItemDescription;
        myItemValue = theItemValue;
        myItemQuantity = theItemQuantity;
    }


    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    @Override
    public String getMyItemName() {
        return myItemName;
    }

    /**
     * Retrieves the description of the item.
     *
     * @return A description of the item.
     */
    @Override
    public String getMyItemDescription() {
        return myItemDescription;
    }

    /**
     * Retrieves the value of the item.
     *
     * @return The value of the item.
     */
    @Override
    public int getMyItemValue() {
        return myItemValue;
    }

    /**
     * Retrieves the current number of this item.
     *
     * @return The number of this item available.
     */
    @Override
    public int getMyItemQuantity() {
        return getMyItemQuantity()
    }

    /**
     * Checks whether the player has this item.
     *
     * @return True if the player has the item, false otherwise.
     */
    @Override
    public boolean hasItem() {
        return myItemValue > 0;
    }

    /**
     * Defines the behavior when this item is used by a player. Subclasses must provide a specific implementation
     * of how the item affects the player.
     *
     * @param thePlayer The player who uses the item.
     */
    @Override
    public void useItem(Player thePlayer) {

    }

    public abstract void useItem();
}
