package dungeongame.src.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the player inventory.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
final public class PlayerInventory implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 87531815144L;
    private static PlayerInventory uniqueInstance;

    private PropertyChangeSupport myPCS;
    private Map<Item, Integer> myInventory;

    /**
     * Constructs an empty inventory that tracks the items the player has picked up and used.
     */
    public PlayerInventory() {
        myPCS = new PropertyChangeSupport(this);
        myInventory = new HashMap<Item, Integer>();
    }

    public static PlayerInventory getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PlayerInventory();
        }
        return uniqueInstance;
    }

    public void updateFrom(PlayerInventory theOtherInventory) {
        myInventory = theOtherInventory.myInventory;
    }

    /**
     * Returns a deep copy of the inventory map.
     */
    public HashMap<Item, Integer> getInventory() {
        HashMap<Item, Integer> inventoryCopy = new HashMap<>();
        for (Map.Entry<Item, Integer> entry : myInventory.entrySet()) {
            Item itemCopy = null;
            if (entry.getKey() instanceof SpeedPotion) {
                itemCopy = new SpeedPotion();
            } else if (entry.getKey() instanceof HealthPotion) {
                itemCopy = new HealthPotion();
            } else if (entry.getKey() instanceof VisionPotion) {
                itemCopy = new VisionPotion();
            } else if (entry.getKey() instanceof Pillar) {
                itemCopy = new Pillar(entry.getKey().getMyItemName());
            }
            if (itemCopy != null) {
                inventoryCopy.put(itemCopy, entry.getValue());
            }
        }
        return inventoryCopy;
    }

    public boolean containsItem(Item item) {
        if (myInventory.containsKey(item)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds an item to the inventory and updates the quantity.
     *
     * @param theItem the item to add to the inventory.
     */
    public void addItem(final Item theItem) {
        myInventory.put(theItem, myInventory.getOrDefault(theItem, 0) + 1);
        myPCS.firePropertyChange("Item Added", null, myInventory);
        checkforPillars();
    }

    /**
     * Removes an item to the inventory and updates the quantity.
     *
     * @param theItem the item to be deducted from the inventory.
     */
    public void useItem(final Item theItem) {
        if (myInventory.containsKey(theItem) && !(theItem instanceof Pillar)) {
            if (myInventory.get(theItem) > 1) {
                myInventory.put(theItem, myInventory.get(theItem) - 1);
            } else if (myInventory.get(theItem) == 1) {
                myInventory.remove(theItem);
            }
            if (("Vision Potion").equals(theItem.getMyItemName())) {
                myPCS.firePropertyChange("use_vision", null, "use_vision");
            }
            myPCS.firePropertyChange("Item Used", null, myInventory);
        } else {
            throw new IllegalArgumentException("No item found / Can not use Item");
        }
    }

    public void checkforPillars() {
        List pillars = new ArrayList();
        for (Map.Entry<Item, Integer> entry : myInventory.entrySet()) {
            if (entry.getKey() instanceof Pillar) {
                pillars.add(entry.getKey().getMyItemName());
            }
        }
        if (pillars.contains("Encapsulation Pillar") &&
                pillars.contains("Inheritance Pillar") &&
                pillars.contains("Polymorphism Pillar") &&
                pillars.contains("Abstraction Pillar")) {
            Maze.getInstance().spawnExit();
        }
    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

}
