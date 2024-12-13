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
    private static PlayerInventory uniqueInstance;

    private final PropertyChangeSupport myPCS;
    private Map<Item, Integer> myInventory;
    private Player myPlayer;

    /**
     * Constructs an empty inventory that tracks the items the player has picked up and used.
     */
    private PlayerInventory() {
        myPCS = new PropertyChangeSupport(this);
        myInventory = new HashMap<>();
        myPlayer = null;
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

    public void setPlayer(Player thePlayer) {
        myPlayer = thePlayer;
    }

    public void clear(){
        myInventory.clear();
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

            if (!(theItem instanceof VisionPotion)) {
                theItem.useItem(((AbstractDungeonCharacter)myPlayer));
            }



//            if (theItem instanceof HealthPotion) {
//                myPCS.firePropertyChange("HealthPotionUsed", null, theItem);
//            }
//
            if (theItem instanceof VisionPotion) {
                myPCS.firePropertyChange("VisionPotionUsed", null, theItem);
            }
//
//
           myPCS.firePropertyChange("Item Used", null, myInventory);
        } else {
            throw new IllegalArgumentException("No item found / Cannot use item");
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
                pillars.contains("Abstraction Pillar") &&
                !Maze.getInstance().hasExit()) {
            Maze.getInstance().spawnExit();
        }
    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
}
