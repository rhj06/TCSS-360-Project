package dungeongame.src.view;

import dungeongame.src.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the inventory screen for the dungeon game.
 * This screen displays the player's potions and allows them to use potions directly from the UI.
 * The inventory dynamically updates when items are used or added.
 *
 */
public class InventoryScreen {

    /** Local copy of the player's inventory, used for UI updates. */
    private final Map<Class<? extends Item>, Integer> localInventory;

    /** Button for interacting with health potions in the inventory. */
    private Button healthPotionButton;

    /** Button for interacting with speed potions in the inventory. */
    private Button speedPotionButton;

    /** Button for interacting with vision potions in the inventory. */
    private Button visionPotionButton;

    /**
     * Constructs the inventory screen and initializes the local inventory.
     * The local inventory is derived from the global PlayerInventory.
     */
    public InventoryScreen() {
        localInventory = initializeLocalInventory();
    }

    /**
     * Displays the inventory screen in a new stage.
     * The screen contains buttons to view and use health, speed, and vision potions.
     */
    public void display() {
        Stage inventoryStage = new Stage();
        VBox inventoryLayout = new VBox(10);
        inventoryLayout.setAlignment(Pos.CENTER);

        Label inventoryLabel = new Label("Inventory");

        // Buttons to display and interact with items
        healthPotionButton = new Button();
        speedPotionButton = new Button();
        visionPotionButton = new Button();

        // Update button text initially
        updateButtonText(healthPotionButton, HealthPotion.class);
        updateButtonText(speedPotionButton, SpeedPotion.class);
        updateButtonText(visionPotionButton, VisionPotion.class);

        // Set up button actions
        healthPotionButton.setOnAction(e -> {
            if (useItem(HealthPotion.class)) {
                updateButtonText(healthPotionButton, HealthPotion.class);
            }
        });

        speedPotionButton.setOnAction(e -> {
            if (useItem(SpeedPotion.class)) {
                updateButtonText(speedPotionButton, SpeedPotion.class);
            }
        });

        visionPotionButton.setOnAction(e -> {
            if (useItem(VisionPotion.class)) {
                updateButtonText(visionPotionButton, VisionPotion.class);
            }
        });

        inventoryLayout.getChildren().addAll(inventoryLabel, healthPotionButton, speedPotionButton, visionPotionButton);
        Scene inventoryScene = new Scene(inventoryLayout, 300, 200);
        inventoryStage.setScene(inventoryScene);
        inventoryStage.show();
    }

    /**
     * Initializes the local inventory by copying the global PlayerInventory.
     *
     * @return A map of item types to their quantities in the inventory.
     */
    private Map<Class<? extends Item>, Integer> initializeLocalInventory() {
        Map<Class<? extends Item>, Integer> inventory = new HashMap<>();
        PlayerInventory globalInventory = PlayerInventory.getInstance();

        for (Map.Entry<Item, Integer> entry : globalInventory.getInventory().entrySet()) {
            inventory.put(entry.getKey().getClass(), entry.getValue());
        }
        return inventory;
    }

    /**
     * Updates the text and state of a button to reflect the quantity of a given item in the inventory.
     *
     * @param theButton The button to update.
     * @param theItemType The class of the item to display.
     */
    private void updateButtonText(Button theButton, Class<? extends Item> theItemType) {
        int itemCount = localInventory.getOrDefault(theItemType, 0);
        theButton.setText(theItemType.getSimpleName() + " x " + itemCount);
        theButton.setDisable(itemCount == 0);
    }

    /**
     * Decrements the quantity of a given item in the inventory and returns whether the operation was successful.
     *
     * @param theItemType The class of the item to use.
     * @return true if the item was successfully used; false otherwise.
     */
    private boolean useItem(Class<? extends Item> theItemType) {
        if (localInventory.containsKey(theItemType) && localInventory.get(theItemType) > 0) {
            localInventory.put(theItemType, localInventory.get(theItemType) - 1);
            System.out.println("Used one " + theItemType.getSimpleName());
            return true;
        }
        return false;
    }

    /**
     * Adds a given item to the inventory, incrementing its quantity.
     * If the inventory screen is open, the UI is updated to reflect the change.
     *
     * @param theItemType The class of the item to add.
     */
    public void addItem(Class<? extends Item> theItemType) {
        localInventory.put(theItemType, localInventory.getOrDefault(theItemType, 0) + 1);
        System.out.println("Added one " + theItemType.getSimpleName());
        refreshButtons(); // Update buttons dynamically if the screen is open
    }

    /**
     * Refreshes the buttons on the inventory screen to reflect the current inventory counts.
     */
    private void refreshButtons() {
        if (healthPotionButton != null) {
            updateButtonText(healthPotionButton, HealthPotion.class);
        }
        if (speedPotionButton != null) {
            updateButtonText(speedPotionButton, SpeedPotion.class);
        }
        if (visionPotionButton != null) {
            updateButtonText(visionPotionButton, VisionPotion.class);
        }
    }
}