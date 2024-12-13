package dungeongame.src.view;

import dungeongame.src.controller.GameSaver;
import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a menu bar displayed in the game interface.
 * This menu bar contains options for saving the game, showing help,
 * and quitting the application.
 *
 * Usage:
 *     MenuBar menuBar = new MenuBar(stage, character);
 *     VBox menuBarBox = menuBar.createMenuBar();
 *
 * @version 1.0
 * Author: Ryan Johnson, David Bessex, Kaleb Anagnostou
 */
public class MenuBar {

    /**
     * The primary stage of the application.
     */
    private final Stage myStage;

    /**
     * The character controlled by the player.
     */
    private final AbstractDungeonCharacter myCharacter;

    /**
     * Constructs a new {@code MenuBar} instance.
     *
     * @param theStage     the primary stage of the application
     * @param theCharacter the player-controlled character
     */
    public MenuBar(Stage theStage, AbstractDungeonCharacter theCharacter) {
        myStage = theStage;
        myCharacter = theCharacter;
    }

    /**
     * Creates a menu bar for the game interface.
     * - Save Game: Saves the current game state.
     * - Help: Displays information about the game’s objective and mechanics.
     * - Quit: Closes the application.
     *
     * @return a {@code VBox} containing the menu bar
     */
    public VBox createMenuBar() {
        MenuButton menuButton = new MenuButton("Menu");
        menuButton.setStyle("-fx-font-size: 16px;");

        MenuItem saveGame = new MenuItem("Save Game");
        saveGame.setOnAction(_ -> {
            save();
            System.out.println("Game saved!");
        });

        MenuItem helpItem = new MenuItem("Help");
        helpItem.setOnAction(_ -> showHelpDialog());

        MenuItem quitGame = new MenuItem("Quit");
        quitGame.setOnAction(_ -> myStage.close());

        menuButton.getItems().addAll(saveGame, helpItem, quitGame);

        VBox menuBox = new VBox(menuButton);
        menuBox.setStyle("-fx-alignment: top-left; -fx-padding: 0;");
        return menuBox;
    }

    /**
     * Displays a dialog with information about the game's objective and mechanics.
     */
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help - The Four Pillars Quest");
        alert.setHeaderText(null);
        alert.setContentText(
                "The goal of the game is to collect the four pillars of OO (Abstraction, Encapsulation, " +
                        "Inheritance, and Polymorphism) by defeating the Lich mini-bosses that hold them.\n\n" +
                        "As you explore the maze, each room has a chance to drop useful items. " +
                        "You can save your health potions for the arena or use them as needed. " +
                        "Speed potions permanently increase your speed. If your speed is faster than the monster's, " +
                        "you'll get the first turn in combat, which can make all the difference.\n\n" +
                        "Once you’ve gathered all four OO pillars, seek out the final boss and find your path to victory!"
        );

        //Removes the 'i' icon in the right corner
        alert.setGraphic(null);
        alert.getDialogPane().setStyle("-fx-background-color: grey;");
        alert.showAndWait();
    }

    /**
     * Saves the current game state.
     * This method delegates the save operation to the {@link GameSaver} singleton,
     * which handles game persistence. The player's data is set before saving.
     */
    public void save() {
        GameSaver.getInstance().setPlayer((Player) myCharacter);
        GameSaver.getInstance().saveGame();
    }
}