package dungeongame.src.controller;

import dungeongame.src.model.MonsterDatabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dungeongame.src.view.MainMenu;

public class Main extends Application {

    private MonsterDatabase myMonsterDatabase;

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

    public static void main(String[] args) {
        launch(args);
        System.out.println("Dungeon Adventure started");
    }
}