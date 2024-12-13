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
 * @version 1.0
 * @author Kaleb Anagnostou, Ryan Johnson, David Bessex
 */
final public class PlayerInventory implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 87531815144L;
    /** unique instance of Maze */
    private static PlayerInventory uniqueInstance;

    /** Property change support */
    private final PropertyChangeSupport myPCS;

    /** The map that will store items and quantities. */
    private Map<Item, Integer> myInventory;

    /** The player whose inventory this is. */
    private Player myPlayer;

    /**
     * Constructs an empty inventory that tracks the items the player has picked up and used.
     */
    private PlayerInventory() {
        myPCS = new PropertyChangeSupport(this);
        myInventory = new HashMap<>();
        myPlayer = null;
    }

    /**
     * Returns the singleton instance of the PlayerInventory class.
     */
    public static PlayerInventory getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PlayerInventory();
        }
        return uniqueInstance;
    }

    /**
     * Method that sets the fields equal to the fields of another instance.
     */
    public void updateFrom(PlayerInventory theOtherInventory) {
        myInventory = theOtherInventory.myInventory;
        myPlayer = theOtherInventory.myPlayer;
    }

    /**
     * Sets the player field.
     */
    public void setPlayer(Player thePlayer) {
        myPlayer = thePlayer;
    }

    /**
     * Clears the inventory and sets the player to null.
     */
    public void clear(){
        myInventory.clear();
        myPlayer = null;
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

    /**
     * Checks if the item is in the inventory.
     */
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
        theItem.useItem((Character) myPlayer);
        myInventory.put(theItem, myInventory.getOrDefault(theItem, 0) - 1);
    }

    /**
     * Checks to see if the inventory contains all 4 pillars and tells the maze to spawn the exit if it does.
     */
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
                pillars.contains("Abstraction Pillar") &&
                !Maze.getInstance().hasExit()) {
            Maze.getInstance().spawnExit();
        }
    }

    /**
     * Adds a property change listener to the arena.
     *
     * @param theListener the listener to be added.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    public void checkForPillars() {
    }
}
