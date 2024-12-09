package dungeongame.src.view;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Item;
import dungeongame.src.model.Pillar;
import dungeongame.src.model.PlayerInventory;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the inventory screen where players can manage items and track collected Pillars.
 * This screen allows the player to:
 * - View and use potions (Health, Speed, Vision).
 * - Track collected Pillars.
 * - See real-time updates when the inventory changes.
 */
public class InventoryScreen extends AbstractScreen {

    /** The character whose inventory is displayed. */
    private final AbstractDungeonCharacter myCharacter;

    /** The player's inventory instance. */
    private final PlayerInventory myInventory;

    /** Factory for creating styled buttons. */
    private final ButtonFactory myButtonFactory;

    /** Map of potion names to their corresponding buttons. */
    private final Map<String, Button> myPotionButtons;

    /** Map of potion names to their corresponding count labels. */
    private final Map<String, Label> myPotionCountLabels;

    /** Label displaying the status of collected Pillars. */
    private final Label myPillarStatusLabel;

    /** Array of potion names to be displayed on the screen. */
    private static final String[] MY_POTION_NAMES = { "Health Potion", "Speed Potion", "Vision Potion" };

    /**
     * Constructs an InventoryScreen instance for managing the player's items and tracking Pillars.
     *
     * @param theCharacter the character whose inventory will be displayed
     */
    public InventoryScreen(AbstractDungeonCharacter theCharacter) {
        myCharacter = theCharacter;
        myInventory = PlayerInventory.getInstance();
        myButtonFactory = new ButtonFactory(150);

        myPotionButtons = new HashMap<>();
        myPotionCountLabels = new HashMap<>();
        for (String potionName : MY_POTION_NAMES) {
            myPotionButtons.put(potionName, createPotionButton(potionName));
            myPotionCountLabels.put(potionName, createPotionCountLabel());
        }

        myPillarStatusLabel = createPillarStatusLabel();

        // Add a listener to update the inventory UI in real-time
        myInventory.addPropertyChangeListener(evt -> {
            if ("Item Added".equals(evt.getPropertyName()) || "Item Used".equals(evt.getPropertyName())) {
                Platform.runLater(() -> {
                    updatePotionCounts();
                    updateButtonStates();
                    updatePillarStatus();
                });
            }
        });
    }

    /**
     * Displays the inventory screen in a new stage.
     * The screen is shown in a new window with details about the player's inventory and allows the player to interact
     * with items such as potions and Pillars.
     *
     */
    public void display() {
        Stage inventoryStage = new Stage();
        inventoryStage.setTitle("Inventory");
        inventoryStage.setScene(createScene(inventoryStage));
        inventoryStage.show();
        updatePotionCounts();
        updateButtonStates();
        updatePillarStatus();
    }

    /**
     * Creates the scene for the inventory screen.
     *
     * @param theStage the stage on which the scene will be displayed
     * @return the created Scene instance
     */
    @Override
    public Scene createScene(Stage theStage) {
        VBox layout = createLayout();
        for (String potionName : MY_POTION_NAMES) {
            layout.getChildren().add(createButtonWithCount(myPotionButtons.get(potionName),
                    myPotionCountLabels.get(potionName)));
        }
        layout.getChildren().add(myPillarStatusLabel);
        return new Scene(layout, 400, 400);
    }

    /**
     * Creates and styles the layout for the inventory screen.
     *
     * @return a VBox containing all UI elements
     */
    private VBox createLayout() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Image backgroundImage = new Image("file:.idea/resources/fonts/inventory.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        layout.setBackground(new Background(bgImage));
        return layout;
    }

    /**
     * Creates a button for a specified potion.
     *
     * @param thePotionName the name of the potion
     * @return a Button configured for the specified potion
     */
    private Button createPotionButton(String thePotionName) {
        return myButtonFactory.createButton(thePotionName, () -> usePotion(thePotionName));
    }

    /**
     * Creates a label for displaying potion counts.
     *
     * @return a styled Label with the initial count set to 0
     */
    private Label createPotionCountLabel() {
        Label label = new Label("x 0");
        label.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
        return label;
    }

    /**
     * Creates a label for displaying collected Pillar status.
     *
     * @return a styled Label for Pillar status
     */
    private Label createPillarStatusLabel() {
        Label label = new Label("Pillars Collected:\nNone");
        label.setStyle("-fx-text-fill: gray; -fx-font-size: 14px; -fx-font-weight: bold;");
        return label;
    }

    /**
     * Combines a button and its corresponding count label into a single UI element.
     *
     * @param theButton    the button for the potion
     * @param theCountLabel the label displaying the potion count
     * @return an HBox containing the button and label
     */
    private HBox createButtonWithCount(Button theButton, Label theCountLabel) {
        HBox hbox = new HBox(10);
        hbox.setStyle("-fx-alignment: center;");
        hbox.getChildren().addAll(theButton, theCountLabel);
        return hbox;
    }

    /**
     * Updates the states of potion buttons based on inventory quantities.
     * Disables buttons for potions that are not available in the inventory.
     */
    private void updateButtonStates() {
        for (String potionName : MY_POTION_NAMES) {
            Button button = myPotionButtons.get(potionName);
            if (button != null) {
                button.setDisable(getPotionCount(potionName) <= 0);
            }
        }
    }

    /**
     * Updates the potion count labels based on inventory quantities.
     */
    private void updatePotionCounts() {
        for (String potionName : MY_POTION_NAMES) {
            Label countLabel = myPotionCountLabels.get(potionName);
            if (countLabel != null) {
                countLabel.setText("x " + getPotionCount(potionName));
            }
        }
    }

    /**
     * Retrieves the count of a specific potion in the inventory.
     *
     * @param thePotionName the name of the potion
     * @return the count of the potion in the inventory
     */
    private int getPotionCount(String thePotionName) {
        HashMap<Item, Integer> inventory = myInventory.getInventory();
        return inventory.entrySet().stream()
                .filter(entry -> entry.getKey().getMyItemName().equalsIgnoreCase(thePotionName))
                .mapToInt(Map.Entry::getValue)
                .findFirst()
                .orElse(0);
    }

    /**
     * Uses a specified potion, applying its effect to the character.
     *
     * @param thePotionName the name of the potion to use
     */
    private void usePotion(String thePotionName) {
        myInventory.getInventory().entrySet().stream()
                .filter(entry -> entry.getKey().getMyItemName().equalsIgnoreCase(thePotionName))
                .findFirst()
                .ifPresentOrElse(entry -> {
                    Item potion = entry.getKey();
                    potion.useItem(myCharacter);
                    myInventory.useItem(potion);
                    updatePotionCounts();
                    updateButtonStates();
                }, () -> System.err.println("Potion '" + thePotionName + "' not available in inventory."));
    }

    /**
     * Updates the Pillar status label based on the current inventory.
     * Displays the names of all collected Pillars or "None" if no Pillars are collected.
     */
    private void updatePillarStatus() {
        HashMap<Item, Integer> inventory = myInventory.getInventory();
        String collectedPillars = inventory.keySet().stream()
                .filter(item -> item instanceof Pillar)
                .map(Item::getMyItemName)
                .collect(Collectors.joining(", "));
        myPillarStatusLabel.setText(collectedPillars.isEmpty()
                ? "Pillars Collected:\nNone"
                : "Pillars Collected:\n" + collectedPillars);
    }

    /**
     * Adds a potion to the inventory and updates the UI.
     *
     * @param thePotion the potion to add
     */
    public void addPotion(Item thePotion) {
        updatePotionCounts();
        updateButtonStates();
        updatePillarStatus();
    }
}