package dungeongame.src.view;

import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Item;
import dungeongame.src.model.Pillar;
import dungeongame.src.model.PlayerInventory;
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
 * Represents the inventory screen where players can use items and track collected Pillars.
 */
public class InventoryScreen extends AbstractScreen {

    private final AbstractDungeonCharacter myCharacter;
    private final PlayerInventory myInventory;
    private final ButtonFactory myButtonFactory;

    private final Map<String, Button> potionButtons;
    private final Map<String, Label> potionCountLabels;
    private final Label myPillarStatusLabel;

    private static final String[] POTION_NAMES = { "Health Potion", "Speed Potion", "Vision Potion" };

    /**
     * Constructs an InventoryScreen instance for managing the player's items and tracking Pillars.
     *
     * @param theCharacter the character whose inventory will be displayed
     */
    public InventoryScreen(AbstractDungeonCharacter theCharacter) {
        myCharacter = theCharacter;
        myInventory = PlayerInventory.getInstance();
        myButtonFactory = new ButtonFactory(150); // Button width of 150

        potionButtons = new HashMap<>();
        potionCountLabels = new HashMap<>();
        for (String potionName : POTION_NAMES) {
            potionButtons.put(potionName, createPotionButton(potionName));
            potionCountLabels.put(potionName, createPotionCountLabel());
        }

        myPillarStatusLabel = createPillarStatusLabel();
    }

    /**
     * Displays the inventory screen in a new stage.
     */
    public void display() {
        Stage inventoryStage = new Stage();
        inventoryStage.setTitle("Inventory");
        inventoryStage.setScene(createScene(inventoryStage));
        inventoryStage.show();
        // Update button and label states
        updatePotionCounts();
        updateButtonStates();
        updatePillarStatus();
    }

    @Override
    public Scene createScene(Stage theStage) {
        VBox layout = getVBox();

        // Add buttons with counts and pillar status label directly
        for (String potionName : POTION_NAMES) {
            layout.getChildren().add(createButtonWithCount(potionButtons.get(potionName), potionCountLabels.get(potionName)));
        }
        layout.getChildren().add(myPillarStatusLabel);

        return new Scene(layout, 400, 400);
    }

    private VBox getVBox() {
        VBox layout = new VBox(20); // Add spacing between elements
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Load the background image
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

    private Button createPotionButton(String potionName) {
        return myButtonFactory.createButton(potionName, () -> usePotion(potionName));
    }

    private Label createPotionCountLabel() {
        Label label = new Label("x 0");
        label.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
        return label;
    }

    private Label createPillarStatusLabel() {
        Label label = new Label("Pillars Collected:\nNone");
        label.setStyle("-fx-text-fill: gray; -fx-font-size: 14px; -fx-font-weight: bold;");
        return label;
    }

    private HBox createButtonWithCount(Button button, Label countLabel) {
        HBox hbox = new HBox(10);
        hbox.setStyle("-fx-alignment: center;");
        hbox.getChildren().addAll(button, countLabel);
        return hbox;
    }

    private void updateButtonStates() {
        for (String potionName : POTION_NAMES) {
            Button button = potionButtons.get(potionName);
            if (button != null) {
                button.setDisable(getPotionCount(potionName) <= 0);
            }
        }
    }

    private void updatePotionCounts() {
        for (String potionName : POTION_NAMES) {
            Label countLabel = potionCountLabels.get(potionName);
            if (countLabel != null) {
                countLabel.setText("x " + getPotionCount(potionName));
            }
        }
    }

    private int getPotionCount(String potionName) {
        HashMap<Item, Integer> inventory = myInventory.getInventory();
        return inventory.entrySet().stream()
                .filter(entry -> entry.getKey().getMyItemName().equalsIgnoreCase(potionName))
                .mapToInt(Map.Entry::getValue)
                .findFirst()
                .orElse(0);
    }

    private void usePotion(String potionName) {
        // Retrieve the potion from the inventory
        myInventory.getInventory().entrySet().stream()
                .filter(entry -> entry.getKey().getMyItemName().equalsIgnoreCase(potionName))
                .findFirst()
                .ifPresentOrElse(entry -> {
                    Item potion = entry.getKey();
                    int previousHealth = myCharacter.getHealth();

                    // Use the potion and update the inventory
                    potion.useItem(myCharacter);
                    myInventory.useItem(potion);

                    // Log health changes and potion usage
                    System.out.println("Used " + potionName + ".");
                    System.out.println("Health before: " + previousHealth);
                    System.out.println("Health after: " + myCharacter.getHealth());

                    // Update the UI to reflect the changes
                    updatePotionCounts();
                    updateButtonStates();
                }, () -> {
                    // Log if the potion was not found
                    System.err.println("Potion '" + potionName + "' not available in inventory.");
                });
    }

    /**
     * Updates the pillar status label based on the current inventory.
     */
    private void updatePillarStatus() {
        HashMap<Item, Integer> inventory = myInventory.getInventory();

        String collectedPillars = inventory.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Pillar)
                .map(entry -> entry.getKey().getMyItemName())
                .collect(Collectors.joining(", "));

        if (collectedPillars.isEmpty()) {
            myPillarStatusLabel.setText("Pillars Collected:\nNone");
        } else {
            myPillarStatusLabel.setText("Pillars Collected:\n" + collectedPillars);
        }
    }

    public void addPotion(Item thePotion) {
        updatePotionCounts();
        updateButtonStates();
        updatePillarStatus();
    }
}