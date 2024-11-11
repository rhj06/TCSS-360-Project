package dungeongame.src.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the player inventory.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
final public class PlayerInventory {
    Map<Item, Integer> myInventory;

    /**
     * Constructs an empty inventory that tracks the items the player has picked up and used.
     */
    public PlayerInventory() {
        myInventory = new HashMap<Item, Integer>();
    }

    /**
     * Returns a deep copy of the inventory map.
     */
    public HashMap<Item, Integer> getInventory() {
        HashMap<Item, Integer> inventoryCopy = new HashMap<Item, Integer>();
        for(Map.Entry<Item, Integer> entry : myInventory.entrySet()) {
            if(entry instanceof SpeedPotion) {
                Item itemCopy = new SpeedPotion();
                inventoryCopy.put(itemCopy, entry.getValue());
            } else if (entry instanceof HealthPotion) {
                Item itemCopy = new HealthPotion();
                inventoryCopy.put(itemCopy, entry.getValue());
            } else if (entry instanceof VisionPotion) {
                Item itemCopy = new VisionPotion();
                inventoryCopy.put(itemCopy, entry.getValue());
            }
        }
        return inventoryCopy;
    }

    /**
     * Adds an item to the inventory and updates the quantity.
     *
     * @param theItem the item to add to the inventory.
     */
    public void addItem(final Item theItem) {
        myInventory.put(theItem, myInventory.getOrDefault(theItem, 0) + 1);
    }

    /**
     * Removes an item to the inventory and updates the quantity.
     *
     * @param theItem the item to be deducted from the inventory.
     */
    public void useItem(final Item theItem) {
        if (myInventory.containsKey(theItem)) {
            if (myInventory.get(theItem) > 1) {
                myInventory.put(theItem, myInventory.get(theItem) - 1);
            } else if (myInventory.get(theItem) == 1) {
                myInventory.remove(theItem);
            }
        } else {
            throw new IllegalArgumentException("No item found");
        }
    }
}
