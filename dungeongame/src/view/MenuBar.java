package dungeongame.src.view;

import dungeongame.src.controller.GameSaver;
import dungeongame.src.model.AbstractDungeonCharacter;
import dungeongame.src.model.Player;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a menu bar displayed in the game interface.
 * This menu bar contains options for saving the game and quitting the application.
 * It is designed to be embedded in the game screen and styled dynamically.
 *     MenuBar menuBar = new MenuBar(stage, character);
 *     VBox menuBarBox = menuBar.createMenuBar();
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
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
     * Save Game: Saves the current game state.
     * Quit: Closes the application.
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

        MenuItem quitGame = new MenuItem("Quit");
        quitGame.setOnAction(_ -> myStage.close());

        menuButton.getItems().addAll(saveGame, quitGame);

        VBox menuBox = new VBox(menuButton);
        menuBox.setStyle("-fx-alignment: top-left; -fx-padding: 0;");
        return menuBox;
    }

    /**
     * Saves the current game state.
     * This method delegates the save operation to the {@link GameSaver} singleton, which handles game persistence.
     * The player's data is set before saving.
     */
    public void save() {
        GameSaver.getInstance().setPlayer((Player) myCharacter);
        GameSaver.getInstance().saveGame();
    }
}