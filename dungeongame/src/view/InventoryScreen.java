package dungeongame.src.view;

import dungeongame.src.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents the inventory screen in the dungeon game.
 * This screen allows players to view and use potions to modify their character's stats.
 */
public class InventoryScreen extends AbstractScreen {

    /** Factory for creating buttons with consistent styling. */
    private final ButtonFactory myButtonFactory;

    /** The character whose inventory is being displayed. */
    private final AbstractDungeonCharacter myCharacter;

    /** Tracks the count of each type of potion in the inventory. */
    private final Map<PotionType, Integer> myPotionCounts;

    /** Maps each potion type to its corresponding button in the inventory screen. */
    private final Map<PotionType, Button> myPotionButtons;

    /**
     * Represents the different types of potions available in the game.
     */
    private enum PotionType {
        HEALTH("Health Potion"),
        SPEED("Speed Potion"),
        VISION("Vision Potion");

        private final String myDisplayName;

        PotionType(String theDisplayName) {
            myDisplayName = theDisplayName;
        }

        public String getDisplayName() {
            return myDisplayName;
        }
    }

    /**
     * Constructs an InventoryScreen for the given character.
     *
     * @param theCharacter The character whose inventory will be displayed.
     */
    public InventoryScreen(AbstractDungeonCharacter theCharacter) {
        myButtonFactory = new ButtonFactory(150);
        myCharacter = theCharacter;

        myPotionCounts = new HashMap<>();
        myPotionCounts.put(PotionType.HEALTH, 0);
        myPotionCounts.put(PotionType.SPEED, 0);
        myPotionCounts.put(PotionType.VISION, 0);

        myPotionButtons = new HashMap<>();
    }

    /**
     * Creates and returns the scene for the inventory screen.
     *
     * @param theStage The stage where the scene will be displayed.
     * @return The created Scene.
     */
    @Override
    public Scene createScene(Stage theStage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        myPotionButtons.put(PotionType.HEALTH, createPotionButton(PotionType.HEALTH, this::useHealthPotion));
        myPotionButtons.put(PotionType.SPEED, createPotionButton(PotionType.SPEED, this::useSpeedPotion));
        myPotionButtons.put(PotionType.VISION, createPotionButton(PotionType.VISION, this::useVisionPotion));

        updatePotionButtons();

        layout.getChildren().addAll(
                LabelHelper.createCenteredLabel("Inventory", "Arial", 20, "-fx-font-weight: bold;"),
                myPotionButtons.get(PotionType.HEALTH),
                myPotionButtons.get(PotionType.SPEED),
                myPotionButtons.get(PotionType.VISION)
        );

        return new Scene(layout, 300, 400);
    }

    /**
     * Displays the inventory screen in a new window.
     */
    public void display() {
        Stage inventoryStage = new Stage();
        inventoryStage.setTitle("Player Inventory");
        inventoryStage.setScene(createScene(inventoryStage));
        inventoryStage.show();
    }

    /**
     * Creates a button for a specific potion type and action.
     *
     * @param thePotionType The potion type for which the button is created.
     * @param theAction     The action to execute when the button is clicked.
     * @return The created Button.
     */
    private Button createPotionButton(PotionType thePotionType, Runnable theAction) {
        Button button = myButtonFactory.createButton(thePotionType.getDisplayName(), theAction);
        button.setDisable(true); // Start disabled until updated
        return button;
    }

    /**
     * Generic method to use a potion of a specific type.
     *
     * @param thePotionType The potion type being used.
     * @param theEffect     The effect to apply to the character when the potion is used.
     */
    private void usePotion(PotionType thePotionType, Consumer<AbstractDungeonCharacter> theEffect) {
        Integer count = myPotionCounts.get(thePotionType);
        if (count != null && count > 0) {
            myPotionCounts.put(thePotionType, count - 1);
            theEffect.accept(myCharacter);
            updatePotionButtons();
            System.out.println("Used a " + thePotionType.getDisplayName() + ". Remaining: " + myPotionCounts.get(thePotionType));
        }
    }

    /**
     * Uses a health potion to restore the character's health.
     */
    private void useHealthPotion() {
        usePotion(PotionType.HEALTH, theCharacter -> theCharacter.setHealth(theCharacter.getHealth() + 20));
    }

    /**
     * Uses a speed potion to increase the character's speed.
     */
    private void useSpeedPotion() {
        usePotion(PotionType.SPEED, theCharacter -> theCharacter.setSpeed(theCharacter.getSpeed() + 5));
    }

    /**
     * Uses a vision potion to reveal rooms (logic can be implemented as needed).
     */
    private void useVisionPotion() {
        usePotion(PotionType.VISION, theCharacter -> {
            System.out.println("Used Vision Potion");
        });
    }

    /**
     * Updates the state and text of all potion buttons based on inventory counts.
     */
    private void updatePotionButtons() {
        for (PotionType type : PotionType.values()) {
            Button button = myPotionButtons.get(type);
            if (button != null) {
                Integer count = myPotionCounts.get(type);
                button.setDisable(count == null || count == 0);
                button.setText(type.getDisplayName() + " (" + (count != null ? count : 0) + ")");
            }
        }
    }

    /**
     * Adds a potion to the inventory and updates the UI.
     *
     * @param thePotion The potion to add.
     */
    public void addPotion(Item thePotion) {
        if (thePotion instanceof HealthPotion) {
            myPotionCounts.merge(PotionType.HEALTH, 1, Integer::sum);
        } else if (thePotion instanceof SpeedPotion) {
            myPotionCounts.merge(PotionType.SPEED, 1, Integer::sum);
        } else if (thePotion instanceof VisionPotion) {
            myPotionCounts.merge(PotionType.VISION, 1, Integer::sum);
        }
        updatePotionButtons();
    }
}