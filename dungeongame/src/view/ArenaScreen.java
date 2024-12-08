package dungeongame.src.view;

import dungeongame.src.controller.Arena;
import dungeongame.src.model.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class ArenaScreen {
    /** The arena object representing the battle layout and player state. */
    private final Arena myArena;

    /** The primary stage of the application where the battle screen will be displayed. */
    private final Stage myStage;

    private final Player myPlayer;

    private final AbstractMonster myMonster;

    private final Label myMessageLabel;

    private final Deque<String> myRecentMessages;

    /**
     * Constructs a new ArenaScreen with the specified arena.
     *
     * @param theArena The arena object to use for this screen.
     */
    public ArenaScreen(Player thePlayer, AbstractMonster theMonster, Arena theArena) {
        if (thePlayer == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (theMonster == null) {
            throw new IllegalArgumentException("Monster cannot be null.");
        }
        if (theArena == null) {
            throw new IllegalArgumentException("Arena cannot be null.");
        }

        myStage = new Stage();
        myArena = theArena;
        myPlayer = thePlayer;
        myMonster = theMonster;
        myRecentMessages = new ArrayDeque<>(5);

        myMessageLabel = new Label("Fight begins!");
        myMessageLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black;");

        // Set up the scene
        Scene scene = createGameScene();
        myStage.setScene(scene);
        myStage.setTitle("Arena Battle");

        myArena.addPropertyChangeListener(event -> {
            if ("message".equals(event.getPropertyName())) {
                addMessage((String) event.getNewValue());
                updateMessageDisplay();
                //myMessageLabel.setText((String) event.getNewValue());
            }
        });

        // Show the stage
        myStage.show();
        myArena.startCombatLoop();
    }

    public void addMessage(String message) {
        if (myRecentMessages.size() == 5) {
            myRecentMessages.pollFirst();
        }

        myRecentMessages.addLast(message);

        Platform.runLater(this::updateMessageDisplay);
    }

    private void updateMessageDisplay() {
        StringBuilder displayText = new StringBuilder();
        for (String message : myRecentMessages) {
            displayText.append(message).append("\n");
        }

        myMessageLabel.setText(displayText.toString());
    }

    /**
     * Creates and returns the main game scene.
     *
     * @return The game scene ready to be displayed on the primary stage.
     */
    public Scene createGameScene() {
        // Combat buttons
        CombatButtons combatButtons = new CombatButtons(myArena);
        HBox buttonLayout = combatButtons.createDirectionalButtons();

        // Create combat display with player and monster
        HBox combatDisplay = createCombatDisplay();

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(10));

        Label titleLabel = new Label("Arena Battle");
        titleLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 24; -fx-font-weight: bold;");

        mainLayout.getChildren().addAll(titleLabel,combatDisplay, buttonLayout, myMessageLabel);

        return new Scene(mainLayout, 500, 500);
    }

    /**
     * Creates a display for the player and monster with their images and health.
     *
     * @return An HBox containing the player and monster displays.
     */
    private HBox createCombatDisplay() {
        // Player display
        VBox playerDisplay = createCharacterDisplay(
                getPlayerImagePath(),
                150,
                150,
                ((AbstractDungeonCharacter) myPlayer).getCurHealthProperty(),
                ((AbstractDungeonCharacter) myPlayer).getMaxHealth(),
                false,
                myPlayer.toString()
        );

        // Monster display
        VBox monsterDisplay = createCharacterDisplay(
                getMonsterImagePath(),
                150,
                150,
                myMonster.getCurHealthProperty(),
                myMonster.getMaxHealth(),
                true,
                myMonster.toString()
        );

        // Arrange both displays horizontally
        HBox combatDisplay = new HBox(20, playerDisplay, monsterDisplay);
        combatDisplay.setAlignment(Pos.CENTER);
        combatDisplay.setStyle("-fx-padding: 20;");
        return combatDisplay;
    }

    /**
     * Creates a VBox for a character with an image and health display.
     *
     * @param imagePath    Path to the character's image.
     * @param width        Width of the image.
     * @param height       Height of the image.
     * @param currentHealth Current health of the character.
     * @param maxHealth     Maximum health of the character.
     * @param mirror       Whether to mirror the image horizontally.
     * @return A VBox containing the image and health label.
     */
    private VBox createCharacterDisplay(String theImagePath, int theWidth, int theHeight, IntegerProperty theCurrentHealthProperty, int theMaxHealth, boolean theMirror, String theName) {
        // Create the name label
        Label nameLabel = new Label(theName);
        nameLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: black;");

        // Create the image view
        ImageView imageView = createImageView(theImagePath, theWidth, theHeight);
        if (theMirror) {
            imageView.setScaleX(-1);
        }

        // Create the health label
        Label healthLabel = new Label();
        healthLabel.textProperty().bind(theCurrentHealthProperty.asString("Health: %d / " + theMaxHealth));
        healthLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black;"); // Styling for visibility

        // Arrange the image and label vertically
        VBox characterDisplay = new VBox(10, nameLabel, imageView, healthLabel);
        characterDisplay.setAlignment(Pos.CENTER);
        return characterDisplay;
    }

    /**
     * Determines the player's image path based on their type.
     *
     * @return Path to the player's image.
     */
    private String getPlayerImagePath() {
        if (myPlayer instanceof Thief) {
            return "character_sprite/thief.png";
        } else if (myPlayer instanceof Wizard) {
            return "character_sprite/wizard.png";
        } else {
            return "character_sprite/warrior.png";
        }
    }

    /**
     * Determines the monster's image path based on their type.
     *
     * @return Path to the monster's image.
     */
    private String getMonsterImagePath() {
        if (myMonster instanceof Goblin) {
            return "character_sprite/goblin.png";
        } else if (myMonster instanceof Skeleton) {
            return "character_sprite/skeleton.png";
        } else if (myMonster instanceof Slime) {
            return "character_sprite/slime.png";
        } else if (myMonster instanceof Ogre) {
            return "character_sprite/ogre.png";
        } else {
            return myMonster.toString().contains("Lich")
                    ? "character_sprite/boss2.png"
                    : "character_sprite/boss1.png";
        }
    }

    /**
     * Creates an ImageView with the specified properties.
     *
     * @param imagePath Path to the image file.
     * @param width     Width of the image view.
     * @param height    Height of the image view.
     * @return Configured ImageView instance.
     */
    private ImageView createImageView(String theImagePath, int theWidth, int theHeight) {
        Image image = new Image(theImagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(theWidth);
        imageView.setFitHeight(theHeight);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}