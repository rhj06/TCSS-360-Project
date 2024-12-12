package dungeongame.src.controller;

import dungeongame.src.model.MonsterDatabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dungeongame.src.view.MainMenu;

/**
 *
 * Entry point for the Dungeon Adventure game.
 * This class initializes the JavaFX application, sets up the main menu, and initializes the monster database in a
 * background thread.
 *
 * @version 1.0
 * author Ryan Johnson, David Bessex, Kaleb Anagnostou
 */
public class Main extends Application {

    /** The database containing monster information for the game. */
    private MonsterDatabase myMonsterDatabase;

    /**
     * Starts the JavaFX application.
     * This method sets up the primary stage with the main menu scene and initializes the monster database asynchronously.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize the main menu screen
        MainMenu mainMenu = new MainMenu();
        Scene mainMenuScene = mainMenu.createScene(primaryStage);

        // Set up the stage
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Dungeon Adventure");
        primaryStage.show();

        // Initialize the monster database in a background thread
        new Thread(() -> {
            myMonsterDatabase = new MonsterDatabase();
            myMonsterDatabase.initializeDatabase();
        }).start();
    }

    /**
     * Main method for launching the application.
     * This method is the entry point of the Java application. It invokes the JavaFX {@code launch} method to start
     * the application.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("Dungeon Adventure started");
    }
}