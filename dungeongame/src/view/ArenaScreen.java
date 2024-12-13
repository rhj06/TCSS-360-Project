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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Displays an arena and provides for player input.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 12/12/24
 */
public class ArenaScreen extends AbstractScreen{
    /** The path to the custom font used in the game. */
    private static final int IMAGE_SIZE = 150;

    /** The arena object representing the battle layout and player state. */
    private final Arena myArena;

    /** The primary stage of the application where the battle screen will be displayed. */
    private final Stage myStage;

    /** The player that will be fighting in the arena. */
    private final Player myPlayer;

    /** The monster that the player will be fighting. */
    private final AbstractMonster myMonster;

    /** The label that will be displayed at the top of the screen. */
    private final Label myMessageLabel;

    /** The queue of recent messages to be displayed. */
    private final Deque<String> myRecentMessages;

    /**
     * Constructs a new ArenaScreen with the specified arena.
     *
     * @param thePlayer The player character.
     * @param theMonster The monster the player is fighting.
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

        myStage.initModality(Modality.APPLICATION_MODAL);

        myMessageLabel = new Label("Fight begins!");
        myMessageLabel.setStyle("-fx-font-size: 14;-fx-background-color: rgba(28, 28, 28, 0.8); -fx-text-fill: white; -fx-padding: 5px;");

        Scene scene = createGameScene();
        myStage.setScene(scene);
        myStage.setTitle("Arena Battle");

        myArena.addPropertyChangeListener(event -> {
            if ("message".equals(event.getPropertyName())) {
                addMessage((String) event.getNewValue());
                updateMessageDisplay();
            }
        });

        myStage.show();
        myArena.startCombatLoop();

        myArena.addPropertyChangeListener(event -> {
            if ("monsterIsDead".equals(event.getPropertyName())) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(myStage::close);
            }
        });

        myArena.addPropertyChangeListener(event -> {
            if ("playerIsDead".equals(event.getPropertyName())) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(myStage::close);
            }
        });
    }

    /**
     * Adds a message to the recent messages queue. Deletes the oldest message if the size is larger than 5. Updates
     * the message display.
     *
     * @param theMessage The message to be added to the message queue.
     */
    public void addMessage(String theMessage) {
        if (myRecentMessages.size() == 5) {
            myRecentMessages.pollFirst();
        }

        myRecentMessages.addLast(theMessage);

        Platform.runLater(this::updateMessageDisplay);
    }

    /**
     * Updates the message label to display the most messages current in myRecentMessages queue.
     */
    private void updateMessageDisplay() {
        StringBuilder displayText = new StringBuilder();
        for (String message : myRecentMessages) {
            displayText.append(message).append("\n");
        }

        myMessageLabel.setText(displayText.toString());
    }

    /**
     * Creates and returns the arena scene.
     *
     * @return The game scene ready to be displayed on the primary stage.
     */
    public Scene createGameScene() {
        // Combat buttons
        CombatButtons combatButtons = new CombatButtons(myArena);

        HBox buttonLayout = combatButtons.createCombatButtons();

        // Create combat display with player and monster
        HBox combatDisplay = createCombatDisplay();

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(10));

        Label titleLabel = new Label("Arena Battle");
        titleLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");

        mainLayout.getChildren().addAll(titleLabel,combatDisplay, buttonLayout, myMessageLabel);
        String temp = "file:AnyMonsterFight.jpg";
        if (myMonster instanceof Boss) {
            if (("Dragon").equals(((Boss) myMonster).getType())) {
                temp = "file:FinalBossFight.jpg";
            }
        }
        BorderPane background = new BorderPane(mainLayout);
        background.setBackground(createBackground(temp));
        return new Scene(background, 750, 500);
    }

    /**
     * Creates a display for the player and monster with their images and health.
     *
     * @return An HBox containing the player and monster displays.
     */
    private HBox createCombatDisplay() {
        VBox playerDisplay = createCharacterDisplay(
                getPlayerImagePath(),
                ((AbstractDungeonCharacter) myPlayer).getCurHealthProperty(),
                ((AbstractDungeonCharacter) myPlayer).getMaxHealth(),
                myPlayer.toString()
        );

        VBox monsterDisplay = createCharacterDisplay(
                getMonsterImagePath(),
                myMonster.getCurHealthProperty(),
                myMonster.getMaxHealth(),
                myMonster.toString()
        );

        HBox combatDisplay = new HBox(20, playerDisplay, monsterDisplay);
        combatDisplay.setAlignment(Pos.CENTER);
        combatDisplay.setStyle("-fx-padding: 20;");
        return combatDisplay;
    }

    /**
     * Creates a VBox for a character with an image and health display.
     *
     * @param theImagePath the path to the character's image.
     * @param theCurrentHealthProperty the current health of the character.
     * @param theMaxHealth the maximum health of the character.
     * @param theName the name of the player character.
     * @return A VBox containing the image and health label.
     */
    private VBox createCharacterDisplay(String theImagePath, IntegerProperty theCurrentHealthProperty,
                                        int theMaxHealth, String theName) {
        Label nameLabel = new Label(theName);
        nameLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: white;");

        ImageView imageView = createImageView(theImagePath);

        Label healthLabel = new Label();
        healthLabel.textProperty().bind(theCurrentHealthProperty.asString("Health: %d / " + theMaxHealth));
        healthLabel.setStyle("-fx-font-size: 17; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox characterDisplay = new VBox(10, nameLabel, imageView, healthLabel);
        characterDisplay.setAlignment(Pos.CENTER);
        return characterDisplay;
    }

    /**
     * Determines the player's image path based on their type.
     *
     * @return Path to the player's image as a string.
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
     * @return Path to the monster's image as a string.
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
     * @param theImagePath Path to the image file.
     * @return me ImageView instance.
     */
    private ImageView createImageView(String theImagePath) {
        Image image = new Image(theImagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    @Override
    public Scene createScene(Stage theStage) {
        return null;
    }
}