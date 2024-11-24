package dungeongame.src.view;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a menu bar for the dungeon game.
 * Provides options for saving the game and quitting the application.
 *
 * @version 11/24/2024
 */
public class MenuBar {

    /** The primary stage of the application, used to close the game. */
    private final Stage myStage;

    /**
     * Constructs a new MenuBar for the GameScreen.
     *
     * @param theStage The primary stage of the application.
     */
    public MenuBar(Stage theStage) {
        myStage = theStage;
    }

    /**
     * Creates and returns a VBox containing the menu bar.
     * The menu bar includes options for saving the game and quitting the application.
     *
     * @return A VBox containing the menu bar.
     */
    public VBox createMenuBar() {
        // Create a menu button
        MenuButton menuButton = new MenuButton("Menu");

        // Set button style and dimensions
        menuButton.setPrefSize(100, 1);
        menuButton.setStyle("-fx-font-size: 16px;");

        // Create menu items
        MenuItem saveGame = new MenuItem("Save Game");
        saveGame.setOnAction(e -> saveGame());

        MenuItem quitGame = new MenuItem("Quit");
        quitGame.setOnAction(e -> myStage.close());

        // Add menu items to the menu button
        menuButton.getItems().addAll(saveGame, quitGame);

        // Create a VBox to hold the menu button
        VBox menuBox = new VBox(menuButton);
        menuBox.setStyle("-fx-alignment: top-left; -fx-padding: 10;");

        return menuBox;
    }


    private void saveGame() {

        System.out.println("Game saved!");
    }
}