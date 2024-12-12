package dungeongame.src.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * The AbstractItem class provides a common foundation for all items in the
 * dungeon adventure game. It implements the Item interface and defines
 * shared behavior such as retrieving item information (name, description, value, count)
 * and checking whether the player possesses the item.
 *
 * @version 1.0
 * @author Ryan Johnsom, David Bessex, Kaleb Anagnostou
 *
 */
public abstract class AbstractItem implements Item, Serializable {
    @Serial
    private static final long serialVersionUID = 351354354L;

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
    public AbstractItem(final String theItemName, final String theItemDescription, final int theItemValue, final int theItemQuantity) {
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
    public int getMyItemQuantity() { return myItemQuantity; }

    /**
     * Defines the behavior when this item is used by a player. Subclasses must provide a specific implementation
     * of how the item affects the player.
     *
     * @param theCharacter allows the player to use this item.
     */
    @Override
    public void useItem(final Character theCharacter) {
    }

    /**
     * Returns the item's name as its string representation.
     *
     * @return The name of the item.
     */
    @Override
    public String toString() {
        return myItemName;
    }

    /**
     * Checks whether this item is equal to another object. Two items are considered equal
     * if they have the same name, description, value, and quantity.
     *
     * @param theObject The object to compare with.
     * @return {@code true} if the object is equal to this item, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object theObject) {
        if (this == theObject) return true;
        if (theObject == null || getClass() != theObject.getClass()) return false;
        AbstractItem that = (AbstractItem) theObject;
        return myItemValue == that.myItemValue &&
                myItemQuantity == that.myItemQuantity &&
                Objects.equals(myItemName, that.myItemName) &&
                Objects.equals(myItemDescription, that.myItemDescription);
    }

    /**
     * Computes the hash code for this item based on its name, description, value, and quantity.
     *
     * @return The hash code of this item.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myItemName, myItemDescription, myItemValue, myItemQuantity);
    }
}
